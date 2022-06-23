package Restaurante;
/*
 * Classe genérica para representar qualquer produto do restaurante que se tornará um item de pedido
 */
public abstract class Produto {
    private String descricao;
    private double preco;
    public Produto(String descricao, double preco){
        setDescricao(descricao);
        setPreco(preco);    
    }
    /*Getters e setters*/
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    /*
     * Método utilizado para reajustar o preço base do produto
     * @param preco - double - preço que substituirá o precoBase anteriormente estabelecido
     */
    public void reajustarPrecoBase(double preco){
        this.setPreco(preco);
    }
}