package Consumidor;

public enum Fidelidade {
    BRANCO(0),
    PRATA(0.05),
    PRETO(0.1),
    F_V(0.2);

    private final double desconto;

    Fidelidade(double desconto) {
        this.desconto = desconto;
    }

    public double getDesconto() {
        return this.desconto;
    }
}
