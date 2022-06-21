package Produtos;
/*
 * Classe genérica para representar qualquer produto do restaurante que se tornará um item de pedido
 */
public abstract class Produto {
    private String descricao;
    public Produto(String descricao){
        setDescricao(descricao);        
    }
    /*Get e set*/
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /*
     * Método utilizado para reajustar o preço base do produto
     * @param preco - double - preço que substituirá o precoBase anteriormente estabelecido
     */
    public abstract void reajustarPrecoBase(double preco);
}