package Produtos;
/*
 * Classe para representar o produto Sanduiche sem adicional
 */
public class Sanduiche extends Produto implements Adicional{
    private static double pBase = 12;
    private boolean paoArtesanal = false;
    public Sanduiche(double precoBase){
        super("Sanduiche Tradicional: R$"+Sanduiche.pBase+"\n");
    }
    public Sanduiche(boolean paoArtesanal){
        super("Pizza tradicional: R$"+Sanduiche.pBase+"\n");
        this.paoArtesanal = paoArtesanal;
    }
    /*get e set*/
    public boolean isPaoArtesanal() {
        return paoArtesanal;
    }
    public void setPaoArtesanal(boolean paoArtesanal) {
        this.paoArtesanal = paoArtesanal;
    }
    /* Métodos da interface 'Adicional' */
    @Override
    public String getDescricao() {
        return super.getDescricao();
    }
    @Override
    public double getPreco() {
        if(!paoArtesanal){
            return Sanduiche.pBase;
        } else {
            return Sanduiche.pBase+2;
        }
    }
    /*Método da classe pai 'Produto' */
    @Override
    public void reajustarPrecoBase(double preco) {
        Sanduiche.pBase = preco;
    }
}
