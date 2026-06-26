package com.solid;

import com.solid.discount.PoliticaDescuento;
import com.solid.model.Pedido;
import com.solid.model.TipoCliente;
import com.solid.notification.NotificadorEmail;
import com.solid.notification.NotificadorPedido;
import com.solid.repository.PedidosRepository;
import com.solid.repository.PedidosRepositoryEnMemoria;
import com.solid.service.ProcesarPedidoService;
import com.solid.usecase.ProcesarPedidoUseCase;

public class Main {

    public static void main(String[] args) {

        // ── Composition Root: construir dependencias de afuera hacia adentro ──
        PedidosRepository repositorio   = new PedidosRepositoryEnMemoria();
        NotificadorPedido notificador   = new NotificadorEmail();

        // DIP: el servicio recibe abstracciones, no implementaciones concretas
        ProcesarPedidoUseCase servicio  = new ProcesarPedidoService(repositorio, notificador);

        // ── Caso 1: Cliente Regular (descuento 5 %) ───────────────────────────
        Pedido pedidoRegular = new Pedido("P-001", "cliente-juan", TipoCliente.REGULAR, 200.00);
        servicio.procesar(pedidoRegular);

        // ── Caso 2: Cliente VIP (descuento 15 %) ──────────────────────────────
        Pedido pedidoVip = new Pedido("P-002", "cliente-maria", TipoCliente.VIP, 500.00);
        servicio.procesar(pedidoVip);

        // ── Verificación en repositorio ───────────────────────────────────────
        System.out.println("=== Estado final en repositorio ===");
        System.out.println(repositorio.buscarPorId("P-001"));
        System.out.println(repositorio.buscarPorId("P-002"));
    }
}
