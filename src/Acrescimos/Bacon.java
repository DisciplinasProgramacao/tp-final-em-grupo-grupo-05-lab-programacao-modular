package Acrescimos;
import Produtos.*;
/*Classe que representa um dos ingredientes adicionáveis em Pizza ou sanduiche*/
public class Bacon extends Ingrediente{
    private static double valor = 3;
    public Bacon(Adicional adicional) {
        super(adicional);
    }
    /*Métodos da interface 'Adicional' implementado em Ingrediente*/
    @Override
    public String getDescricao() {
        if(getAdicional() instanceof Pizza){
            return super.getDescricao()+"Bacon R$"+Bacon.valor*2+"\n";
        } else{
            return super.getDescricao()+"Bacon R$"+Bacon.valor+"\n";
        } 
    }
    @Override
    public double getPreco() {
        if(getAdicional() instanceof Pizza){
            return super.getPreco()+(Bacon.valor*2);
        } else{
            return super.getPreco()+Bacon.valor;
        }
    }
}
