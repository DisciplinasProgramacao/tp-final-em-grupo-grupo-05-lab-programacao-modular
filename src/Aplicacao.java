import java.util.List;

import Arquivos.ArquivosBinarios;
import Bebidas.BebidaFactoryConcreto;
import Bebidas.IBebidaFactory;
import Comidas.PratoFeito.IPratoFeitoFactory;
import Comidas.PratoFeito.PratoFeitoFactoryConcreto;
import Comidas.ProdutoComAdicionais.Pizza.IPizzaFactory;
import Comidas.ProdutoComAdicionais.Pizza.PizzaFactoryConcreto;
import Comidas.ProdutoComAdicionais.Sanduiche.ISanduicheFactory;
import Comidas.ProdutoComAdicionais.Sanduiche.SanduicheFactoryConcreto;
import Consumidor.Cliente;
import Restaurante.GerenciaRestaurante;

public class Aplicacao {

    public static void main(String[] args) {
        IBebidaFactory bebidaFactory = new BebidaFactoryConcreto();
        IPizzaFactory pizzaFactory = new PizzaFactoryConcreto();
        ISanduicheFactory sanduicheFactory = new SanduicheFactoryConcreto();
        IPratoFeitoFactory pratoFeitoFactory = new PratoFeitoFactoryConcreto();
        
        // Inicializando classes com arquivo de texto
        // ArquivosTexto arquivosTexto = ArquivosTexto.getInstancia();

        // List<Bebida> bebidas = arquivosTexto.obterBebidas(bebidaFactory);
        // List<Pizza> pizzas = arquivosTexto.obterPizzas(pizzaFactory);
        // List<Sanduiche> sanduiches = arquivosTexto.obterSanduiches(sanduicheFactory);
        // List<Cliente> clientes = arquivosTexto.obterClientes();
        
        // Inicializando classe com arquivo binário
        List<Cliente> clientes = ArquivosBinarios.getInstancia().lerDadosArquivoBinario();

        GerenciaRestaurante gerenciaRestaurante = new GerenciaRestaurante(clientes, bebidaFactory, pizzaFactory, sanduicheFactory, pratoFeitoFactory);
        gerenciaRestaurante.mostrarMenuPrincipal();
    }
}