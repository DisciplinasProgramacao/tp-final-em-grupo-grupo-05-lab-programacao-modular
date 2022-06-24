package Restaurante;

import java.io.Serializable;

public abstract class Produto implements Serializable {
    private String descricao;
    private double preco;

    protected Produto(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPreco() {
        return preco;
    }

    /**
     * Reajusta o preço do produto
     * @param preco Novo preço que o produto irá receber
    */
    public void reajustarPreco(double preco) {
        this.preco = preco;
    }
}
