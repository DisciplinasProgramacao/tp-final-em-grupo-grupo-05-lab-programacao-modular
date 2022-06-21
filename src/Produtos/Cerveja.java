package Produtos;
/*Classe que representa uma das bebidas disponíveis para compra*/
public class Cerveja extends Bebida{
    private static double pBase = 8;
    public Cerveja(){
        super("Cerveja R$"+Cerveja.pBase+"\n");
    }
    /*Método da classe Pai de Bebida*/
    @Override
    public void reajustarPrecoBase(double preco) {
        Cerveja.pBase = preco;
    }
}