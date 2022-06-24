package Restaurante;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import Consumidor.Cliente;
import Utils.StringUtils;

public class Pedido implements Serializable {
    private List<Produto> produtos;
    private int avaliacao;

    private Cliente cliente;
    public Cliente getCliente() {
        return cliente;
    }

    private static int id = 0;
    private LocalDateTime dataRealizacao;
    private double valorComDesconto;

    public double getValorComDesconto() {
        return valorComDesconto;
    }

    public Pedido(List<Produto> produtos, Cliente cliente) {
        Pedido.id++;
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataRealizacao = LocalDateTime.now();
        this.avaliacao = 0;
    }

    public double getValorTotalPedido() {
        return produtos.stream().mapToDouble((produto) -> produto.getPreco()).sum();
    }

    public void setValorComDesconto(double valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        if (avaliacao > 0 && avaliacao < 5) {
            this.avaliacao = avaliacao;
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public static int getId() {
        return id;
    }

    @Override
    public String toString() {
        String valorTotalPedidoFormatado = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(getValorTotalPedido());
        String valorFinalPedidoComDescontoFormatado = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(getValorComDesconto());

        String texto = "*****************\n\nPedido N°" + getId() + " feito por " + getCliente().getNome() + "\n\nPRODUTOS DO PEDIDO:\n";

        for (Produto produto : produtos) {
            texto += "\n-------------------------------\n" + produto.toString() + "\n";
        }

        texto+= "\nVALOR TOTAL DO PEDIDO: " + valorTotalPedidoFormatado + "\n\n";
        texto+= "\nVALOR FINAL COM DESCONTO: " + valorFinalPedidoComDescontoFormatado + "\n\n";

        texto += "\n***************";

        return texto;
    }
}
