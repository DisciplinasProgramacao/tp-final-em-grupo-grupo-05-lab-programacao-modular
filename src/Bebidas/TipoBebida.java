package Bebidas;

public enum TipoBebida {
    CERVEJA(8),
    SUCO(5),
    AGUA(2),
    REFRIGERANTE(5);

    private final double valor;

    TipoBebida(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
