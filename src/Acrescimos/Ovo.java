package Acrescimos;
import Produtos.*;
/*Classe que representa um dos ingredientes adicionáveis em Pizza ou sanduiche*/
public class Ovo extends Ingrediente{
    private static double valor = 2;
    public Ovo(Adicional adicional) {
        super(adicional);
    }
    /*Métodos da interface 'Adicional' implementado em Ingrediente*/
    @Override
    public String getDescricao() {
        if(getAdicional() instanceof Pizza){
            return super.getDescricao()+"Ovo R$"+Ovo.valor*2+"\n";
        } else{
            return super.getDescricao()+"Ovo R$"+Ovo.valor+"\n";
        } 
    }
    @Override
    public double getPreco() {
        if(getAdicional() instanceof Pizza){
            return super.getPreco()+(Ovo.valor*2);
        } else{
            return super.getPreco()+Ovo.valor;
        }
    }
}
