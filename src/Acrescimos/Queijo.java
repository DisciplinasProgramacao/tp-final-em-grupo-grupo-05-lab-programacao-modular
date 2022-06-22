package Acrescimos;
import Produtos.*;
/*Classe que representa um dos ingredientes adicionáveis em Pizza ou sanduiche*/
public class Queijo extends Ingrediente{
    private static double valor = 2;
    public Queijo(Adicional adicional) {
        super(adicional);
    }
    /*Métodos da interface 'Adicional' implementado em Ingrediente*/
    @Override
    public String getDescricao() {
        if(getAdicional() instanceof Pizza){
            return super.getDescricao()+"Queijo R$"+Queijo.valor*2+"\n";
        } else{
            return super.getDescricao()+"Queijo R$"+Queijo.valor+"\n";
        } 
    }
    @Override
    public double getPreco() {
        if(getAdicional() instanceof Pizza){
            return super.getPreco()+(Queijo.valor*2);
        } else{
            return super.getPreco()+Queijo.valor;
        }
    }
}
