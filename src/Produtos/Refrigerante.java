package Produtos;
/*Classe que representa uma das bebidas disponíveis para compra*/
public class Refrigerante extends Bebida{
    private static double pBase = 5;
    public Refrigerante(){
        super("Refrigerante R$"+Refrigerante.pBase+"\n");
    }
    /*Método da classe Pai de Bebida*/
    @Override
    public void reajustarPrecoBase(double preco) {
        Refrigerante.pBase = preco;
    }
}