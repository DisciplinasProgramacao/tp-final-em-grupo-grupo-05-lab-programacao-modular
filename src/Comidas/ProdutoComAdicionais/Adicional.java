package Comidas.ProdutoComAdicionais;

public enum Adicional {
    PEPPERONI(4),
    PICLES(2),
    QUEIJO(2),
    BACON(3),
    PALMITO(3),
    OVO(2),
    BATATA_PALHA(2);

    private final double preco;

    Adicional(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return this.preco;
    }
}
