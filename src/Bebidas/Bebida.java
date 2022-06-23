package Bebidas;

import Restaurante.Produto;

public abstract class Bebida extends Produto {
    protected Bebida(String descricao, double preco) {
        super(descricao, preco);
    }
}
