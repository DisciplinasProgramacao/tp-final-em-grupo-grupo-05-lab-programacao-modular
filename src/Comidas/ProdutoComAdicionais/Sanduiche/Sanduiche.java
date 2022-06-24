package Comidas.ProdutoComAdicionais.Sanduiche;

import java.text.NumberFormat;

import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;
import Utils.StringUtils;

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

    /**
     * Obtem o preço do sanduíche
     * @return Preço do sanduíche, e caso seja produzido com pão artesanal ele adiciona no preço final
    */
    private double obterPrecoSanduiche() {
        return isPaoArtesanal() ? super.getPreco() + PRECO_PAO_ARTESANAL : super.getPreco();
    }   

    private String formatarTextoCasoSejaPaoArtesanal() {
        return isPaoArtesanal() ? super.getDescricao() + " com Pão Artesanal" : super.getDescricao();
    }

    @Override
    public double getPreco() {
        double precoAdicionais = super.obterValorTotalAdicionais();
        return precoAdicionais + obterPrecoSanduiche();
    }

    @Override
    public String toString() {
        String tratarFormatoPreco = StringUtils.formatarNumeroParaStringEmFormatoDeMoedaBrasileira(obterPrecoSanduiche());
        return tratarFormatoPreco + " - " + formatarTextoCasoSejaPaoArtesanal() + super.toString();
    }
}
