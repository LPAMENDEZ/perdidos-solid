package com.solid.service;

import com.solid.discount.DescuentoFactory;
import com.solid.discount.PoliticaDescuento;
import com.solid.model.Pedido;
import com.solid.notification.NotificadorPedido;
import com.solid.repository.PedidosRepository;
import com.solid.usecase.ProcesarPedidoUseCase;

/**
 * Servicio que implementa el caso de uso ProcesarPedidoUseCase.
 *
 * ─────────────────────────────────────────────────────────────
 *  PRINCIPIOS SOLID APLICADOS
 * ─────────────────────────────────────────────────────────────
 *
 *  SRP – Single Responsibility Principle
 *      Esta clase solo orquesta el flujo de procesamiento del pedido
 *      (descuento → guardar → notificar). Cada responsabilidad concreta
 *      está delegada a su colaborador especializado.
 *
 *  OCP – Open/Closed Principle
 *      Para cambiar la notificación o el repositorio basta con inyectar
 *      otra implementación; este servicio no se modifica.
 *
 *  LSP – Liskov Substitution Principle
 *      Cualquier implementación de PedidosRepository o NotificadorPedido
 *      puede sustituir a otra sin que el servicio se rompa.
 *
 *  ISP – Interface Segregation Principle
 *      El servicio depende de interfaces pequeñas y cohesivas
 *      (PedidosRepository, NotificadorPedido, ProcesarPedidoUseCase).
 *
 *  DIP – Dependency Inversion Principle
 *      Todas las dependencias se reciben por constructor como abstracciones
 *      (interfaces), nunca como clases concretas.
 * ─────────────────────────────────────────────────────────────
 */
public class ProcesarPedidoService implements ProcesarPedidoUseCase {

    // ── Dependencias inyectadas por constructor (DIP) ───────────────────────
    private final PedidosRepository pedidosRepository;
    private final NotificadorPedido notificadorPedido;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param pedidosRepository repositorio donde se persiste el pedido
     * @param notificadorPedido canal por el que se notifica al cliente
     */
    public ProcesarPedidoService(PedidosRepository pedidosRepository,
                                  NotificadorPedido notificadorPedido) {
        this.pedidosRepository = pedidosRepository;
        this.notificadorPedido = notificadorPedido;
    }

    /**
     * Flujo principal:
     *  1. Selecciona la política de descuento según el tipo de cliente.
     *  2. Aplica el descuento al pedido.
     *  3. Persiste el pedido actualizado.
     *  4. Notifica al cliente.
     */
    @Override
    public void procesar(Pedido pedido) {
        System.out.println("\n========================================");
        System.out.println(" Procesando pedido: " + pedido.getId());
        System.out.println("========================================");

        // 1. OCP + DIP: la política se resuelve por abstracción, no por if/else aquí
        PoliticaDescuento politica = DescuentoFactory.obtener(pedido.getTipoCliente());
        politica.aplicarDescuento(pedido);

        // 2. DIP: dependemos de PedidosRepository, no de la BD concreta
        pedidosRepository.guardar(pedido);

        // 3. DIP: dependemos de NotificadorPedido, no de Email/SMS concreto
        notificadorPedido.notificar(pedido);

        System.out.println(" Pedido procesado con éxito.\n");
    }
}
