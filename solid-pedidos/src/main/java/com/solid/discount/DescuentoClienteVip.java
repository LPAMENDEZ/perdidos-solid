package com.solid.discount;

import com.solid.model.Pedido;


public class DescuentoClienteVip implements PoliticaDescuento {

    private static final double PORCENTAJE_DESCUENTO = 0.15; // 15 %

    @Override
    public void aplicarDescuento(Pedido pedido) {
        double descuento = pedido.getTotal() * PORCENTAJE_DESCUENTO;
        double nuevoTotal = pedido.getTotal() - descuento;
        pedido.setTotal(nuevoTotal);
        System.out.printf("[Descuento VIP] 15%% aplicado. Descuento: $%.2f → Total: $%.2f%n",
                descuento, nuevoTotal);
    }
}
