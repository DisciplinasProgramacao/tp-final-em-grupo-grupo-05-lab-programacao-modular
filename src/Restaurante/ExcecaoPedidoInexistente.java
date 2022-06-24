package Restaurante;

public class ExcecaoPedidoInexistente extends Exception {
    public ExcecaoPedidoInexistente() {
        super("\nPedido com este id não existe no sistema\n");
    }
}
