package Comidas.PratoFeito;

import Restaurante.Produto;
import Utils.StringUtils;

public class PratoFeito extends Produto {
    PratoFeito(String descricao, double preco) {
        super(descricao, preco);
    }

    @Override
    public String toString() {
        String tratarFormatoPreco = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(getPreco());
        
        return "\n" + tratarFormatoPreco + " - "+ getDescricao();
    }
}
