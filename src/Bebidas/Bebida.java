package Bebidas;

import Restaurante.Produto;
import Utils.StringUtils;

public abstract class Bebida extends Produto {
    protected Bebida(String descricao, double preco) {
        super(descricao, preco);
    }

    @Override
    public String toString() {
        String valorBebida = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(getPreco());
        return "\n" + valorBebida + " - " + getDescricao() + "\n";
    }
}
