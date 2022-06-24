package Restaurante;

public class NotaDeCompra {
    private Pedido pedido;
    public NotaDeCompra(Pedido pedido){
        this.pedido = pedido;
    }
    @Override
    public String toString() {
        String dados = "Nota de compra\n";
        dados += pedido.toString();
        return dados;
    }
}
