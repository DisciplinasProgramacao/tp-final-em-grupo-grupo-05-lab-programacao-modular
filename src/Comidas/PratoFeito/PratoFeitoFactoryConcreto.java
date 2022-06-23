package Comidas.PratoFeito;

public class PratoFeitoFactoryConcreto implements IPratoFeitoFactory {
    private static final double PRECO_PRATO_FEITO = 15;

    @Override
    public PratoFeito criarPratoFeito() {
        return new PratoFeito("Prato Feito", PRECO_PRATO_FEITO);
    }
}
