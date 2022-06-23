package Comidas.ProdutoComAdicionais.Sanduiche;

import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;

class Sanduiche extends ProdutoComAdicionais {
    private boolean paoArtesanal;

    protected Sanduiche(String descricao, double preco, boolean paoArtesanal) {
        super(descricao, preco);
        this.paoArtesanal = paoArtesanal;
    }

    public boolean isPaoArtesanal() {
        return paoArtesanal;
    }
}
