package Consumidor;

import Restaurante.Pedido;
import Utils.StringUtils;

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
    
    public void solicitarExtratoResumidoTodosOsPedidos(Cliente cliente) {
        for(Pedido pedido : cliente.getPedidos()) {
            System.out.println("Extrato pedido resumido:");
            System.out.println("Pedido N°" + Pedido.getId());
            System.out.println("Cliente:" + pedido.getCliente().getNome());
            System.out.println("Data:" + StringUtils.formatarDataParaDdMmAAAA(pedido.getDataRealizacao()));
            System.out.println("\n------------------------\n");
        }
    }
}
