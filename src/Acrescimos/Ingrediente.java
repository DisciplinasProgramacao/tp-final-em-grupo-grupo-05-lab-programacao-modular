package Acrescimos;
import Produtos.Adicional;
/**
 * Ingrediente
 * Classe abstrata que se relaciona com a interface 'adicional'
 * seguindo o padrão de projeto Decorator
 * @author Júlia Evelyn
 * @author Felipe Espíndola
 * @author Rafael Lopes
 * @version 1.0
 */
public abstract class Ingrediente implements Adicional{
    private final Adicional adicional;
    /*Construtor */
    public Ingrediente(Adicional adicional){
        if(adicional==null){
            throw new RuntimeException("Um ingrediente adicional deve estar associado a uma Pizza ou a um Sanduiche");
        } else {
            this.adicional = adicional;
        }
    }
    /*Métodos da interface adicional */
    @Override
    public String getDescricao() {
        return adicional.getDescricao();
    }
    @Override
    public double getPreco(){
        return adicional.getPreco();
    }
}