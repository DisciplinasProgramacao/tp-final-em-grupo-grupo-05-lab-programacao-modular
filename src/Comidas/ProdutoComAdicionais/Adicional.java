package Comidas.ProdutoComAdicionais;

public class Adicional {
    private static final int QUANTIDADE_INICIAL = 1;

    private Ingrediente ingrediente;
    private int quantidade;

    public Adicional(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
        this.quantidade = QUANTIDADE_INICIAL;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void aumentarQuantidadeIngrediente() {
        this.quantidade++;
    }

    public double obterValorAdicional() {
        double precoIngrediente = getIngrediente().getPreco();
        return precoIngrediente * getQuantidade();
    }
}
