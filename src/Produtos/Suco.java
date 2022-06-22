package Produtos;
/*Classe que representa uma das bebidas disponíveis para compra*/
public class Suco extends Bebida{
    private static double pBase = 5;
    public Suco(){
        super("Suco R$"+Suco.pBase+"\n");
    }
    /*Método da classe Pai de Bebida*/
    @Override
    public void reajustarPrecoBase(double preco) {
        Suco.pBase = preco;
    }
}