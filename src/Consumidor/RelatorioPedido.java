package Consumidor;

import Restaurante.Pedido;

public class RelatorioPedido {
    private static final RelatorioPedido INSTANCIA = new RelatorioPedido();

    private RelatorioPedido() {}

    public static RelatorioPedido getInstancia() {
        return INSTANCIA;
    } 

    public void solicitarExtratoPedidoEspecifico(Cliente cliente, Pedido pedido) {
        try {
            if (cliente.equals(pedido.getCliente())) {
                System.out.println("Relatório pedido:\n");
                System.out.println(pedido.toString());
            }
            else {
                throw new ExcecaoPermissaoNegada();
            }
        } catch (ExcecaoPermissaoNegada e) {
            System.err.println(e.getMessage());
        }
    }

    
    public void solicitarExtratoPedidoTodosOsPedidos(Cliente cliente) {
        for(Pedido pedido : cliente.getPedidos()) {
            solicitarExtratoPedidoEspecifico(cliente, pedido);
        }
    }
}
