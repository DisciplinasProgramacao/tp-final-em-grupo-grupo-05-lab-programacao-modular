package Restaurante;

public class ExcecaoLimiteAvaliacaoInvalida extends Exception {
    public ExcecaoLimiteAvaliacaoInvalida() {
        super("A avaliação deve ser entre 1 e 5");
    }
}
