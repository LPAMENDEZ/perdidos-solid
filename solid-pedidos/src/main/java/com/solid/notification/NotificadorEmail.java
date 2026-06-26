package com.solid.notification;

import com.solid.model.Pedido;

public class NotificadorEmail implements NotificadorPedido {

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("[Email] Notificación enviada al cliente '"
                + pedido.getClienteId()
                + "' por el pedido '" + pedido.getId()
                + "' con total $" + pedido.getTotal());
    }
}
