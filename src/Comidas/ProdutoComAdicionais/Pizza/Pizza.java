package Comidas.ProdutoComAdicionais.Pizza;

import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;
import Utils.StringUtils;

public class Pizza extends ProdutoComAdicionais {
    private static final double PRECO_BORDA_RECHEADA = 8;    
    private boolean bordaRecheada;

    Pizza(String descricao, double preco, boolean bordaRecheada) {
        super(descricao, preco);
        this.bordaRecheada = bordaRecheada;
    }
    
    public boolean isBordaRecheada() {
        return bordaRecheada;
    }

    private String formatarTextoCasoPossuaBordaRecheada() {
        return isBordaRecheada() ? super.getDescricao() + " com Borda Recheada" : super.getDescricao();
    }

    @Override
    public double getPreco() {
        return bordaRecheada ? super.getPreco() + PRECO_BORDA_RECHEADA : super.getPreco();
    }

    @Override
    public String toString() {
        String tratarFormatoPreco = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(getPreco());
        return tratarFormatoPreco + " - " +formatarTextoCasoPossuaBordaRecheada() + super.toString();
    }
}
