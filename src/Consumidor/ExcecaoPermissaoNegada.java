package Consumidor;

public class ExcecaoPermissaoNegada extends Exception {
    public ExcecaoPermissaoNegada() {
        super("\nSomente o cliente associado ao pedido possui acesso ao relatório\n");

    }
}
