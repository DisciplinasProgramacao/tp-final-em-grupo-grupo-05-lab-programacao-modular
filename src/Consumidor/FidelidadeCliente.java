package Consumidor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import Restaurante.Pedido;

public class FidelidadeCliente {
    private static final FidelidadeCliente INSTANCIA = new FidelidadeCliente();

    private static int NUMERO_PEDIDOS_MINIMO_FIDELIDADE_PRATA = 4;
    private static int VALOR_MINIMO_FIDELIDADE_PRATA = 100;
    private static int MESES_ANTERIORES_FIDELIDADE_PRATA = 1;

    private static int NUMERO_PEDIDOS_MINIMO_FIDELIDADE_PRETO = 10;
    private static int VALOR_MINIMO_FIDELIDADE_PRETO = 250;
    private static int MESES_ANTERIORES_FIDELIDADE_PRETO = 2;

    private static int NUMERO_PEDIDOS_MINIMO_FIDELIDADE_F_V = 50;
    private static int VALOR_MINIMO_FIDELIDADE_F_V = 600;
    private static int MESES_ANTERIORES_FIDELIDADE_F_V = 6;

    private FidelidadeCliente() {}
    
    public static FidelidadeCliente getInstancia() {
        return INSTANCIA;
    } 

    private List<Pedido> obterPedidosNosUltimosMeses(List<Pedido> pedidos, int numeroMesesAnteriores) {
        return pedidos.stream().filter((pedido) -> pedidoEstaEmDataLimite(pedido, numeroMesesAnteriores)).collect(Collectors.toList());
    }

    private double obterValorPedidosNosUltimosMeses(List<Pedido> pedidosNosUltimosMeses) {
        return pedidosNosUltimosMeses.stream().mapToDouble((pedido) -> pedido.getValorTotalPedido()).sum();
    }
    
    private boolean verificarSePedidoSeAplicaARegraDeFidelidade(List<Pedido> pedidos, int numeroMesesAnteriores, int numeroPedidosMinimo, int numeroPedidosMaximo, int valorMinimoFidelidade, int valorMaximoFidelidade) {
        List<Pedido> pedidosFiltrados = obterPedidosNosUltimosMeses(pedidos, numeroMesesAnteriores);
        double valorTotalPedidos = obterValorPedidosNosUltimosMeses(pedidosFiltrados);

        return (pedidosFiltrados.size() > numeroPedidosMinimo && pedidosFiltrados.size() < numeroPedidosMaximo) || (valorTotalPedidos > valorMinimoFidelidade && valorTotalPedidos < valorMaximoFidelidade);
    }

    private boolean verificarSePedidoSeAplicaARegraDeFidelidade(List<Pedido> pedidos, int numeroMesesAnteriores, int numeroPedidosMinimo, int valorMinimoFidelidade) {
        List<Pedido> pedidosFiltrados = obterPedidosNosUltimosMeses(pedidos, numeroMesesAnteriores);
        double valorTotalPedidos = obterValorPedidosNosUltimosMeses(pedidosFiltrados);

        return (pedidosFiltrados.size() > numeroPedidosMinimo) || (valorTotalPedidos > valorMinimoFidelidade);
    }

    private boolean pedidoEstaEmDataLimite(Pedido pedido, int numeroMesesAnteriores) {
        LocalDateTime dataAtual = LocalDateTime.now();                                      
        LocalDateTime dataLimiteFidelidade = dataAtual.minusMonths(numeroMesesAnteriores); 
        int estaNoPrazo = pedido.getDataRealizacao().compareTo(dataLimiteFidelidade);
        
        return estaNoPrazo > 0;
    }
    
    public Fidelidade obterFidelidade(List<Pedido> pedidos) {
        boolean fidelidadePrata = verificarSePedidoSeAplicaARegraDeFidelidade(pedidos, MESES_ANTERIORES_FIDELIDADE_PRATA, NUMERO_PEDIDOS_MINIMO_FIDELIDADE_PRATA, NUMERO_PEDIDOS_MINIMO_FIDELIDADE_PRETO, VALOR_MINIMO_FIDELIDADE_PRATA, VALOR_MINIMO_FIDELIDADE_PRETO);
        boolean fidelidadePreto = verificarSePedidoSeAplicaARegraDeFidelidade(pedidos, MESES_ANTERIORES_FIDELIDADE_PRETO, NUMERO_PEDIDOS_MINIMO_FIDELIDADE_PRETO, NUMERO_PEDIDOS_MINIMO_FIDELIDADE_F_V, VALOR_MINIMO_FIDELIDADE_PRETO, VALOR_MINIMO_FIDELIDADE_F_V);
        boolean fidelidadeF_V = verificarSePedidoSeAplicaARegraDeFidelidade(pedidos, MESES_ANTERIORES_FIDELIDADE_F_V, NUMERO_PEDIDOS_MINIMO_FIDELIDADE_F_V, VALOR_MINIMO_FIDELIDADE_F_V);
        
        if (fidelidadeF_V) {
            return Fidelidade.F_V;
        }
        else if(fidelidadePreto) {
            return Fidelidade.PRETO;
        }
        else if(fidelidadePrata) {
            return Fidelidade.PRATA;
        }
        else {
            return Fidelidade.BRANCO;
        }
    }
}
