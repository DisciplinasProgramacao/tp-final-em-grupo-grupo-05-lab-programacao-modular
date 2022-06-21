package Produtos;
/*
 * Classe para representar o produto Prato Feito
 */
public class PratoFeito extends Produto {
    private static double pBase = 15;
    /*Construtor */
    public PratoFeito() {
        super("Prato feito: R$"+PratoFeito.pBase+"\n");
    }
    /*get */
    public static double getpBase() {
        return pBase;
    }
    /*Método da classe pai 'Produto' */
    @Override
    public void reajustarPrecoBase(double preco) {
        PratoFeito.pBase = preco; 
    }
    
}
