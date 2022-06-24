package Restaurante;

public class ExcecaoForaDoLimiteDeProdutos extends Exception {
    public ExcecaoForaDoLimiteDeProdutos() {
        super("A quantidade de produtos do pedido deve ficar entre 1 e 10");
    }
}
