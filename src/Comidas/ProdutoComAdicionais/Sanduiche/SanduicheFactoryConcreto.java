package Comidas.ProdutoComAdicionais.Sanduiche;

public class SanduicheFactoryConcreto implements ISanduicheFactory {
    private final double PRECO_SANDUICHE = 12;

    @Override
    public Sanduiche criarSanduiche(boolean paoArtesanal) {
        return new Sanduiche("Sanduíche", PRECO_SANDUICHE, paoArtesanal);
    }
}
