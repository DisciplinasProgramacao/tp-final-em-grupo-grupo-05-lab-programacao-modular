package Restaurante;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import Consumidor.*;

public class SistemaRestaurante {
    private static final SistemaRestaurante INSTANCE = new SistemaRestaurante();
    private static DAO<Cliente, String> arquivoCliente = new ClienteDAO();
    private static DAO<Pedido, Integer> arquivoPedido = new PedidoDAO();

    /*Métodos do padrão de projeto SINGLETON */
    private SistemaRestaurante(){}
    public static SistemaRestaurante getInstance(){
        return INSTANCE;
    }

    /*Métodos estáticos da classe SistemaRestaurante a seguir*/

    /*Método para cadastrar cliente
     * @param nome nome do cliente
     * @param cpf do cliente
     */
    public static Cliente cadastrarCliente(String nome, String cpf){
        if(arquivoCliente.get(cpf)==null){
            Cliente novo = new Cliente(nome, cpf);
            arquivoCliente.add(novo);
            return novo;
        } else {
            throw new RuntimeException("Este cliente já está cadastrado.");
        }        
    }

    /*métodos para instanciar pedido
     * @param produtos - lista de produtos a serem adicionados
     * @param cliente - cliente associado ao pedido
     * @param avaliacaoo - avaliação dada ao pedido feito
     * @return valor final do pedido já calculado
     * @throw RuntimeException - caso o pedido tenha mais de 10 itens, não pode ser realizado
    */
    public static double realizarPedido(List<Produto> produtos, Cliente cliente){
        if(produtos.size()<=10){
            Pedido novo = new Pedido(produtos, cliente);
            arquivoPedido.add(novo);
            return novo.calcularValor();
        } else {
            throw new RuntimeException("Um pedido pode conter no máximo 10 itens.");
        }
    }
    public static Pedido realizarPedido(List<Produto> produtos, Cliente cliente, int avaliacao){
        if(produtos.size()<=10){
            Pedido novo = new Pedido(produtos, cliente, avaliacao);
            arquivoPedido.add(novo);
            return novo;
        } else {
            throw new RuntimeException("Um pedido pode conter no máximo 10 itens.");
        }
    }
    /*Método que filtra os pedidos de um cliente específico
     * @param c cliente
     * @return pedidos especificos de um cliente 
    */
    private static List<Pedido> listaDePedidos(Cliente c){
        List<Pedido> pedidos = arquivoPedido.getAll();
        System.out.println(pedidos);
        pedidos.stream().filter(p->p.getCliente().equals(c));
        return pedidos;
    }
    /*Método que avalia qual nível de fidelidade o cliente se encaixa
     * @param c Cliente que será avaliado
     * @return Programa de fidelidade - enum que indica qual o desconto de direito do cliente e sua classificação de fidelidade
    */
    public static ProgramaDeFidelidade calcularFidelidade(Cliente c){
        List<Pedido> pedidos = listaDePedidos(c);
        List<Pedido> seisMeses = pedidos.stream().filter(p->p.getData().isAfter(LocalDate.now().minusMonths(6))).collect(Collectors.toList());
        List<Pedido> doisMeses = pedidos.stream().filter(p->p.getData().isAfter(LocalDate.now().minusMonths(2))).collect(Collectors.toList());
        List<Pedido> umMes = pedidos.stream().filter(p->p.getData().isAfter(LocalDate.now().minusMonths(1))).collect(Collectors.toList());
        double seissentos = seisMeses.stream().mapToDouble(p->p.calcularValor()).sum();
        double duzentosECinquenta = doisMeses.stream().mapToDouble(p->p.calcularValor()).sum();
        double cem = umMes.stream().mapToDouble(p->p.calcularValor()).sum();
        if(seisMeses.size()>=50|| seissentos>=600){
            return ProgramaDeFidelidade.FFEV;
        } else {
            if(doisMeses.size()>=10||duzentosECinquenta>=250){
                return ProgramaDeFidelidade.FPRETO;
            } else {
                if(umMes.size()>=4||cem>=100){
                    return ProgramaDeFidelidade.FPRATA;
                } else return ProgramaDeFidelidade.FBRANCO;
            }
        }
    }
    /*Método que gera o extrato resumido dos pedidos de um cliente
     * @param c cliente que solicitou o extrato
     * @return eResumido extrato Resumido
    */
    public static String extratoPedidos(Cliente c){
        List<Pedido> pedidos = listaDePedidos(c);
        String eResumido = " Extrato Resumido\n";
        for (Pedido pedido : pedidos) {
            eResumido+=pedido.extratoResumido();
        }
        return eResumido;
    }
    /*Método que gera o extrato específico de um pedido
     * @param c cliente que realizou o pedido
     * @param id identificador do pedido que será analisado
     * @return eEspecifico extrato específico
    */
    public static String extratoPedidoEspecifico(Cliente c, int id){
        List<Pedido> pedidos = listaDePedidos(c);
        pedidos.stream().filter(p->p.getId()==id).map(p->p.toString());
        String eEspecifico = pedidos.get(0).toString();
        return eEspecifico;
    }
    /*Método que calcula a avaliação média que um cliente dá aos pedidos
     * @param c cliente a ser analisado
     * @return media double que representa a média de avaliações
    */
    public static double avaliacaoMedia(Cliente c){
        List<Pedido> pedidos = listaDePedidos(c);
        double media = pedidos.stream().mapToDouble(p->p.getAvaliacao()).average().getAsDouble();
        return media;
    }

}
