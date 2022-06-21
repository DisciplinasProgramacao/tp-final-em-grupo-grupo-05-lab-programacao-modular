package Produtos;
/*Classe que representa uma das bebidas disponíveis para compra*/
public class Agua extends Bebida{
    private static double pBase = 2;
    public Agua(){
        super("Agua R$"+Agua.pBase+"\n");
    }
    /*Método da classe Pai de Bebida*/
    @Override
    public void reajustarPrecoBase(double preco) {
        Agua.pBase = preco;
    }
}