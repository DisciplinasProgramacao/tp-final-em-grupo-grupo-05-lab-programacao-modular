package Bebidas;

import Restaurante.Produto;

abstract class Bebida extends Produto {
    protected Bebida(String descricao, double preco) {
        super(descricao, preco);
    }
}
