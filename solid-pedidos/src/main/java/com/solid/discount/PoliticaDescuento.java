package com.solid.discount;

import com.solid.model.Pedido;


public interface PoliticaDescuento {


    void aplicarDescuento(Pedido pedido);
}
