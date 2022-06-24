package Consumidor;

public class ExcecaoClienteNaoCadastrado extends Exception {
    public ExcecaoClienteNaoCadastrado() {
        super("\nCliente não está cadastrado no sistema! Por favor, Tente novamente.\n");
    }
}
