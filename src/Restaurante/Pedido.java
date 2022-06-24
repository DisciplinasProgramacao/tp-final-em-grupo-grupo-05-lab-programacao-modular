package Restaurante;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import Consumidor.Cliente;
/*Classe que representa o pedido do cliente */
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
    public static int contagemID;
    private List<Produto> itens;
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private double desconto;
    private int avaliacao;
    /*Construtores*/
    public Pedido(List<Produto> itens, Cliente c){
        this.setItens(itens);
        this.id=Pedido.contagemID++;
        this.setCliente(c);
        this.data = LocalDate.now();
    }
    public Pedido(List<Produto> itens, Cliente c, int avaliacao){
        this.setItens(itens);
        this.id=Pedido.contagemID++;
        this.setCliente(c);
        this.data = LocalDate.now();
        this.setAvaliacao(avaliacao);
    }
    protected Pedido(List<Produto> itens, int id, Cliente c, CharSequence data, double desconto, int avaliacao){
        this.setItens(itens);
        this.id=id;
        this.setCliente(c);
        this.data = LocalDate.parse(data);
        this.desconto = desconto;
        this.setAvaliacao(avaliacao);
    }
    /*getters e setters*/
    public List<Produto> getItens() {
        return itens;
    }
    public int getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public LocalDate getData() {
        return data;
    }
    public double getDesconto() {
        return desconto;
    }
    public int getAvaliacao() {
        return avaliacao;
    }
    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setDesconto(double desconto) {
        this.desconto = calculaDesconto();
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    /*Método público que calcula o valor final da compra
     * @return double - retorna o valor final com o desconto do cliente incluso
    */
    public double calcularValor(){
        double valor =  itens.stream()
        .mapToDouble(p -> p.getPreco())
        .sum();
        valor-=(valor*desconto);
        return valor;
    }
    /*Método privado que calcula o desconto de direito do cliente
     * @return desconto - porcentagem de desconto
    */
    private double calculaDesconto(){
        ProgramaDeFidelidade p = SistemaRestaurante.calcularFidelidade(cliente);
        double desconto = p.getDesconto();
        return desconto;
    }
    /*Override de toString para relatório*/
    @Override
    public String toString() {
        String descricao = "id: "+this.getId()+"\ncliente: "+cliente.getNome()+"\nItens:\n";
        for (Produto produto : itens) {
            descricao += produto.toString()+"\n";
        }
        descricao += "\n valor total: R$"+this.calcularValor()+"\n";
        return descricao;
    }
    /*Método para relatório resumido*/
    public String extratoResumido(){
        return "cliente: "+cliente.getNome()+"; id: "+this.getId()+"; data: "+this.getData();
    }
}