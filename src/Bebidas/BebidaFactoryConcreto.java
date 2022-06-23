package Bebidas;

public class BebidaFactoryConcreto implements IBebidaFactory {
    @Override
    public Bebida criarBebida(TipoBebida bebida) {
        switch (bebida) {
            case AGUA:
                return new Agua("Água", TipoBebida.AGUA.getValor());
            case CERVEJA:
                return new Cerveja("Cerveja", TipoBebida.CERVEJA.getValor());
            case REFRIGERANTE:
                return new Refrigerante("Refrigerante", TipoBebida.REFRIGERANTE.getValor());
            case SUCO:
                return new Suco("Suco", TipoBebida.SUCO.getValor());
            default:
                return null;
        }
    }
}
