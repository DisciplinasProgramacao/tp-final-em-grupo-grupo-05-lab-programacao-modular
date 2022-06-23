package Comidas.ProdutoComAdicionais.Pizza;

public class PizzaFactoryConcreto implements IPizzaFactory {
    private static final double PRECO_PIZZA = 25;

    @Override
    public Pizza criarPizza(boolean bordaRecheada) {
        return new Pizza("Pizza", PRECO_PIZZA, bordaRecheada);
    }
}
