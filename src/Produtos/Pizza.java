package Produtos;
/*
 * Classe para representar o produto Pizza sem adicional
 */
public class Pizza extends Produto implements Adicional{
    private static double pBase = 25;
    private boolean bordaRecheada = false;
    /*Construtores */
    public Pizza(){
        super("Pizza tradicional: R$"+Pizza.pBase+"\n");
    }
    public Pizza(boolean bordaRecheada){
        super("Pizza tradicional: R$"+pBase+"\n");
        this.bordaRecheada = bordaRecheada;
    }
    /*get e set*/
    public boolean isBordaRecheada() {
        return bordaRecheada;
    }
    public void setBordaRecheada(boolean bordaRecheada) {
        this.bordaRecheada = bordaRecheada;
    }
    /* Métodos da interface 'Adicional' */
    @Override
    public String getDescricao() {
        return super.getDescricao();
    }
    @Override
    public double getPreco() {
        if(!bordaRecheada){
            return Pizza.pBase;
        } else {
            return Pizza.pBase+8;
        }
    }
    /*Método da classe pai 'Produto'*/
	@Override
	public void reajustarPrecoBase(double preco) {
		Pizza.pBase = preco;
	}   
}