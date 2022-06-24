package Consumidor;

public class ExcecaoClienteJaExistente extends Exception {
    public ExcecaoClienteJaExistente() {
        super("\nCliente já está cadastro no sistema.\n");
    }
}
