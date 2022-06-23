package Comidas.ProdutoComAdicionais;

import java.util.List;
import Restaurante.Produto;

public abstract class ProdutoComAdicionais extends Produto {
    private List<Adicional> adicionais;

    protected ProdutoComAdicionais(String descricao, double preco) {
        super(descricao, preco);
    }

    public void adicionarIngrediente(Adicional ingrediente) {
        adicionais.add(ingrediente);
    }
}
