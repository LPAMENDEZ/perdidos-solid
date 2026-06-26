package com.solid.repository;

import com.solid.model.Pedido;
import java.util.HashMap;
import java.util.Map;

public class PedidosRepositoryEnMemoria implements PedidosRepository {

    private final Map<String, Pedido> almacenamiento = new HashMap<>();

    @Override
    public void guardar(Pedido pedido) {
        almacenamiento.put(pedido.getId(), pedido);
        System.out.println("[Repositorio] Pedido guardado: " + pedido);
    }

    @Override
    public Pedido buscarPorId(String id) {
        return almacenamiento.get(id);
    }
}
