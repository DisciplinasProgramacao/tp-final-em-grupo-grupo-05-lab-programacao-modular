package Bebidas;

class BebidaFactoryConcreto implements IBebidaFactory {
    @Override
    public Bebida criarBebida(TipoBebida bebida) {
        switch (bebida) {
            case AGUA:
                return new Agua(null, TipoBebida.AGUA.getValor());
            case CERVEJA:
                return new Cerveja(null, TipoBebida.CERVEJA.getValor());
            case REFRIGERANTE:
                return new Refrigerante(null, TipoBebida.REFRIGERANTE.getValor());
            case SUCO:
                return new Suco(null, TipoBebida.SUCO.getValor());
            default:
                return null;
        }
    }
}
