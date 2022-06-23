package Comidas.ProdutoComAdicionais.Sanduiche;

import Comidas.ProdutoComAdicionais.Adicional;
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

    public String obterTextoCasoExistaAdicionais() {
        boolean existeAdicionais = super.existeAdicionais();
        String textoExisteAdicionais = "\nADICIONAIS:";
        String textoSemAdicionais = "Produto não contém nenhum ingrediente adicional";

        for (Adicional adicional : getAdicionais()) {
            textoExisteAdicionais += adicional.toString();
        }

        return existeAdicionais ? textoExisteAdicionais : textoSemAdicionais;
    }

    @Override
    public String toString() {
        return getDescricao() + ":\n" + obterTextoCasoExistaAdicionais();
    }
}
