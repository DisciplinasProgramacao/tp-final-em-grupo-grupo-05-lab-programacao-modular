package Arquivos;

import java.util.List;

import Bebidas.Bebida;
import Bebidas.IBebidaFactory;
import Comidas.PratoFeito.IPratoFeitoFactory;
import Comidas.PratoFeito.PratoFeito;
import Comidas.ProdutoComAdicionais.Pizza.IPizzaFactory;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Sanduiche.ISanduicheFactory;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Consumidor.Cliente;

public interface ITratarArquivos {
    public List<Bebida> obterBebidas(IBebidaFactory bebidaFactory);
    public List<Pizza> obterPizzas(IPizzaFactory pizzaFactory);
    public List<Sanduiche> obterSanduiches(ISanduicheFactory sanduicheFactory);
    public List<PratoFeito> obterPratoFeitos(IPratoFeitoFactory pratoFeitoFactory);
    public List<Cliente> obterClientes();
}
