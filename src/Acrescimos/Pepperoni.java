package Acrescimos;
import Produtos.*;
/*Classe que representa um dos ingredientes adicionáveis em Pizza ou sanduiche*/
public class Pepperoni extends Ingrediente{
    private static double valor = 4;
    public Pepperoni(Adicional adicional) {
        super(adicional);
    }
    /*Métodos da interface 'Adicional' implementado em Ingrediente*/
    @Override
    public String getDescricao() {
        if(getAdicional() instanceof Pizza){
            return super.getDescricao()+"Pepperoni R$"+Pepperoni.valor*2+"\n";
        } else{
            return super.getDescricao()+"Pepperoni R$"+Pepperoni.valor+"\n";
        } 
    }
    @Override
    public double getPreco() {
        if(getAdicional() instanceof Pizza){
            return super.getPreco() + (Pepperoni.valor*2);
        } else{
            return super.getPreco()+Pepperoni.valor;
        }
    }
}
