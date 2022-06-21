package Acrescimos;
import Produtos.*;
/*Classe que representa um dos ingredientes adicionáveis em Pizza ou sanduiche*/
public class Palmito extends Ingrediente{
    private static double valor = 3;
    public Palmito(Adicional adicional) {
        super(adicional);
    }
    /*Métodos da interface 'Adicional' implementado em Ingrediente*/
    @Override
    public String getDescricao() {
        if(getAdicional() instanceof Pizza){
            return super.getDescricao()+"Palmito R$"+Palmito.valor*2+"\n";
        } else{
            return super.getDescricao()+"Palmito R$"+Palmito.valor+"\n";
        } 
    }
    @Override
    public double getPreco() {
        if(getAdicional() instanceof Pizza){
            return super.getPreco()+(Palmito.valor*2);
        } else{
            return super.getPreco();
        }
    }
}
