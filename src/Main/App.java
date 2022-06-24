package Main;
import java.io.*;
import java.util.*;
import Consumidor.*;
import Restaurante.*;

public class App {
    public static List<Produto> listaP(){
        List<Produto> lista = new ArrayList<Produto>();
        Produto p1 = new Produto("prod1", 20);
        Produto p2 = new Produto("prod2", 10);
        Produto p3 = new Produto("prod3", 30);
        Produto p4 = new Produto("prod4", 50);
        lista.add(p1); lista.add(p2); lista.add(p3); lista.add(p4);
        return lista;
    }
    public static List<Produto> listaQ(){
        List<Produto> lista = new ArrayList<Produto>();
        Produto p5 = new Produto("prod5", 25);
        Produto p6 = new Produto("prod6", 15);
        
        lista.add(p5); lista.add(p6);
        return lista;
    }


    public static void main(String[] args) throws EOFException {
        SistemaRestaurante.getInstance();
        Cliente j = SistemaRestaurante.cadastrarCliente("Julia", "1234");
        Cliente k = SistemaRestaurante.cadastrarCliente("Felipe", "4321");
        System.out.println("Preço do pedido 1: "+SistemaRestaurante.realizarPedido(listaP(), j));
        SistemaRestaurante.realizarPedido(listaQ(), j, 3);
        SistemaRestaurante.realizarPedido(listaP(), k, 5);
        System.out.println(SistemaRestaurante.extratoPedidos(j));
        //System.out.println(SistemaRestaurante.extratoPedidoEspecifico(j, 0));
        //System.out.println(SistemaRestaurante.avaliacaoMedia(j));
        //System.out.println(SistemaRestaurante.calcularFidelidade(j));
    }
}