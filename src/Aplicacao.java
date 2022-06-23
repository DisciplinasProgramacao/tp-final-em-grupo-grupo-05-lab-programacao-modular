import Bebidas.Bebida;
import Bebidas.BebidaFactoryConcreto;
import Bebidas.TipoBebida;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Comidas.ProdutoComAdicionais.Sanduiche.SanduicheFactoryConcreto;

public class Aplicacao {
    public static void main(String[] args) {
        BebidaFactoryConcreto bebidaFactory = new BebidaFactoryConcreto();
        SanduicheFactoryConcreto sanduicheFactory = new SanduicheFactoryConcreto();

        Bebida agua = bebidaFactory.criarBebida(TipoBebida.AGUA);
        Bebida cerveja = bebidaFactory.criarBebida(TipoBebida.CERVEJA);

        Sanduiche sanduiche = sanduicheFactory.criarSanduiche(true);
        Sanduiche sanduiche2 = sanduicheFactory.criarSanduiche(false);
        
        System.out.println(sanduiche.getPreco());
        System.out.println(sanduiche2.getPreco());
    }
}