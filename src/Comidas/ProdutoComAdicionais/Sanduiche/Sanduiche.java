package Comidas.ProdutoComAdicionais.Sanduiche;

import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;

public class Sanduiche extends ProdutoComAdicionais {
    private static final double PRECO_PAO_ARTESANAL = 2;
    private boolean paoArtesanal;

    Sanduiche(String descricao, double preco, boolean paoArtesanal) {
        super(descricao, preco);
        this.paoArtesanal = paoArtesanal;
    }

    public boolean isPaoArtesanal() {
        return paoArtesanal;
    }

    private double obterPrecoSanduiche() {
        return isPaoArtesanal() ? super.getPreco() + PRECO_PAO_ARTESANAL : super.getPreco();
    }   

    @Override
    public double getPreco() {
        double precoAdicionais = super.obterValorTotalAdicionais();
        return precoAdicionais + obterPrecoSanduiche();
    }
}
