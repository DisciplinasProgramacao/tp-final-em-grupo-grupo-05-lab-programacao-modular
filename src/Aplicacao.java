import Bebidas.Bebida;
import Bebidas.BebidaFactoryConcreto;
import Bebidas.TipoBebida;
import Comidas.ProdutoComAdicionais.Ingrediente;
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
        
        sanduiche.adicionarIngrediente(Ingrediente.BACON);
        sanduiche.adicionarIngrediente(Ingrediente.BACON);
        sanduiche.adicionarIngrediente(Ingrediente.BATATA_PALHA);
        sanduiche.adicionarIngrediente(Ingrediente.OVO);

        
        System.out.println(sanduiche.toString());
    }
}