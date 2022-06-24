package Consumidor;

import Restaurante.Pedido;

public class RelatorioPedido {
    private static final RelatorioPedido INSTANCIA = new RelatorioPedido();

    private RelatorioPedido() {}

    public static RelatorioPedido getInstancia() {
        return INSTANCIA;
    } 

    public void solicitarExtratoPedidoEspecifico(Pedido pedido) {
        System.out.println("Relatório pedido:\n");
        System.out.println(pedido.toString());
    }

    
    public void solicitarExtratoPedidoTodosOsPedidos(Cliente cliente) {
        for(Pedido pedido : cliente.getPedidos()) {
            solicitarExtratoPedidoEspecifico(pedido);
        }
    }
}
