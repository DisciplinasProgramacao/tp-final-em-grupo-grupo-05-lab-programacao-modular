package Arquivos;

import java.util.List;

import Bebidas.Bebida;
import Comidas.PratoFeito.PratoFeito;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Consumidor.Cliente;
import Restaurante.Pedido;

public interface ITratarArquivos {
    public List<Bebida> obterBebidas();
    public List<Pizza> obterPizzas();
    public List<Sanduiche> obterSanduiches();
    public List<PratoFeito> obterPratoFeitos();
    public List<Cliente> obterClientes();
    public List<Pedido> obterPedidos();
}
