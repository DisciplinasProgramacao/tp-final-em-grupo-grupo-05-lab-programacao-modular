package Consumidor;

public class ExcecaoCPFInvalido extends Exception {
    public ExcecaoCPFInvalido() {
        super("\nCPF do usuário é inválido. Deve respeitar o padrão 123.456.789-10\nPor favor, tente novamente!");
    }
}
