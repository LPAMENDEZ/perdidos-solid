package com.solid.discount;

import com.solid.model.Pedido;


public class DescuentoClienteRegular implements PoliticaDescuento {

    private static final double PORCENTAJE_DESCUENTO = 0.05; // 5 %

    @Override
    public void aplicarDescuento(Pedido pedido) {
        double descuento = pedido.getTotal() * PORCENTAJE_DESCUENTO;
        double nuevoTotal = pedido.getTotal() - descuento;
        pedido.setTotal(nuevoTotal);
        System.out.printf("[Descuento Regular] 5%% aplicado. Descuento: $%.2f → Total: $%.2f%n",
                descuento, nuevoTotal);
    }
}
