package Comidas.ProdutoComAdicionais.Pizza;

import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;

class Pizza extends ProdutoComAdicionais {
    private static final double PRECO_BORDA_RECHEADA = 8;    
    private boolean bordaRecheada;

    protected Pizza(String descricao, double preco, boolean bordaRecheada) {
        super(descricao, preco);
        this.bordaRecheada = bordaRecheada;
    }

    @Override
    public double getPreco() {
        return bordaRecheada ? super.getPreco() + PRECO_BORDA_RECHEADA : super.getPreco();
    }
}
