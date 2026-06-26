package com.solid.repository;

import com.solid.model.Pedido;

public interface PedidosRepository {


    void guardar(Pedido pedido);

    Pedido buscarPorId(String id);
}
