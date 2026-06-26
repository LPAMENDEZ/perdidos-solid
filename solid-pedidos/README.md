# Proyecto SOLID - Sistema de Procesamiento de Pedidos

## Estructura del proyecto
```
src/main/java/com/solid/
├── Main.java                              ← Punto de entrada / Composition Root
├── model/
│   ├── Pedido.java                        ← Entidad de dominio
│   └── TipoCliente.java                   ← Enum REGULAR / VIP
├── usecase/
│   └── ProcesarPedidoUseCase.java         ← Interfaz del caso de uso (DIP)
├── repository/
│   ├── PedidosRepository.java             ← Interfaz de persistencia (DIP/ISP)
│   └── PedidosRepositoryEnMemoria.java    ← Implementación en memoria (OCP)
├── notification/
│   ├── NotificadorPedido.java             ← Interfaz de notificación (DIP/ISP)
│   └── NotificadorEmail.java              ← Implementación Email (OCP)
├── discount/
│   ├── PoliticaDescuento.java             ← Interfaz de descuento (DIP/OCP)
│   ├── DescuentoClienteRegular.java       ← 5% para clientes regulares (LSP)
│   ├── DescuentoClienteVip.java           ← 15% para clientes VIP (LSP)
│   └── DescuentoFactory.java              ← Fábrica de políticas (SRP)
└── service/
    └── ProcesarPedidoService.java         ← Servicio principal (todos los SOLID)
```

## Compilar y ejecutar
```bash
# Compilar
javac -d out $(find src -name "*.java")

# Ejecutar
java -cp out com.solid.Main
```

## Salida esperada
```
========================================
 Procesando pedido: P-001
========================================
[Descuento Regular] 5% aplicado. Descuento: $10.00 → Total: $190.00
[Repositorio] Pedido guardado: Pedido{id='P-001', clienteId='cliente-juan', tipo=REGULAR, total=190.0}
[Email] Notificación enviada al cliente 'cliente-juan' por el pedido 'P-001' con total $190.0
 Pedido procesado con éxito.

========================================
 Procesando pedido: P-002
========================================
[Descuento VIP] 15% aplicado. Descuento: $75.00 → Total: $425.00
[Repositorio] Pedido guardado: Pedido{id='P-002', clienteId='cliente-maria', tipo=VIP, total=425.0}
[Email] Notificación enviada al cliente 'cliente-maria' por el pedido 'P-002' con total $425.0
 Pedido procesado con éxito.

=== Estado final en repositorio ===
Pedido{id='P-001', clienteId='cliente-juan', tipo=REGULAR, total=190.0}
Pedido{id='P-002', clienteId='cliente-maria', tipo=VIP, total=425.0}
```

## Mapeo de principios SOLID → clases

| Principio | Clase(s) clave | Descripción |
|-----------|----------------|-------------|
| **SRP** | `Pedido`, `ProcesarPedidoService`, `DescuentoFactory` | Cada clase tiene una única razón para cambiar |
| **OCP** | `PoliticaDescuento`, `NotificadorPedido`, `PedidosRepository` | Abierto a extensión, cerrado a modificación |
| **LSP** | `DescuentoClienteRegular`, `DescuentoClienteVip` | Sustituyen a `PoliticaDescuento` sin romper el sistema |
| **ISP** | Todas las interfaces | Interfaces pequeñas y cohesivas de un solo método |
| **DIP** | `ProcesarPedidoService` (constructor injection) | Depende de abstracciones, no de concreciones |
