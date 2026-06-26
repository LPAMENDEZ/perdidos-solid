package com.solid.model;


public class Pedido {

    private final String id;
    private final String clienteId;
    private final TipoCliente tipoCliente;
    private double total;

    public Pedido(String id, String clienteId, TipoCliente tipoCliente, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.tipoCliente = tipoCliente;
        this.total = total;
    }

    public String getId() { return id; }
    public String getClienteId() { return clienteId; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Pedido{id='" + id + "', clienteId='" + clienteId +
               "', tipo=" + tipoCliente + ", total=" + total + "}";
    }
}
