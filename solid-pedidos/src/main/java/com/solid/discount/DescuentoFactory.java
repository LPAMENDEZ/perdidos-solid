package com.solid.discount;

import com.solid.model.TipoCliente;

public class DescuentoFactory {

    public static PoliticaDescuento obtener(TipoCliente tipo) {
        switch (tipo) {
            case VIP:
                return new DescuentoClienteVip();
            case REGULAR:
            default:
                return new DescuentoClienteRegular();
        }
    }
}
