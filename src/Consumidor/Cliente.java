package Consumidor;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import Restaurante.Pedido;

public class Cliente implements Serializable {
    List<Pedido> pedidos;
    Fidelidade fidelidade;
    String nome;

    public String getNome() {
        return nome;
    }

    String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ExcecaoCPFInvalido {
        if(cpf.matches("^[0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[\\-][0-9]{2}$")) {
            this.cpf = cpf;
        }
        else {
            throw new ExcecaoCPFInvalido();
        }
    }

    public Cliente(String nome, String cpf) throws ExcecaoCPFInvalido{
        fidelidade = Fidelidade.BRANCO;
        pedidos = new ArrayList<Pedido>();
        this.nome = nome;
        setCpf(cpf);
    }

    public Fidelidade getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(Fidelidade fidelidade) {
        this.fidelidade = fidelidade;
    }


    public void realizarPedido(Pedido pedido) {
        verificarFidelidadeCliente();
        
        obterDescontoNoPedidoPorFidelidade(pedido);
        avaliarPedido(pedido);
        RelatorioPedido.getInstancia().solicitarExtratoPedidoEspecifico(this, pedido);

        pedidos.add(pedido);
    }

    public Pedido avaliarPedido(Pedido pedido) {
        Scanner sc = new Scanner(System.in);
        int avaliacao;

        do {
            System.out.print("Digite a nota da sua avaliação (Entre 1 e 5):");
            avaliacao = sc.nextInt();
        } while(avaliacao <= 0 && avaliacao > 5);

        pedido.setAvaliacao(avaliacao);

        return pedido;
    }

    public void verificarFidelidadeCliente() {
        Fidelidade fidelidadeCliente = FidelidadeCliente.getInstancia().obterFidelidade(pedidos);
        if (!fidelidade.equals(fidelidadeCliente)) {
            setFidelidade(fidelidadeCliente);
        }
    } 
    
    public Pedido obterDescontoNoPedidoPorFidelidade(Pedido pedido) {
        double novoValorPedido = pedido.getValorTotalPedido() - getFidelidade().getDesconto();
        pedido.setValorComDesconto(novoValorPedido);
        
        return pedido;
    }

    public double obterAvaliacaoMedia() {
        return pedidos.stream()
            .mapToDouble((pedido) -> pedido.getAvaliacao())
            .average()
            .orElse(0);
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }
}
