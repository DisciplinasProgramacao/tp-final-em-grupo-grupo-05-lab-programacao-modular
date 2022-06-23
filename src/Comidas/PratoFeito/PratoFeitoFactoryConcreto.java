package Comidas.PratoFeito;

public class PratoFeitoFactoryConcreto implements IPratoFeitoFactory {

    @Override
    public PratoFeito criarPratoFeito(String descricao, double preco) {
        return new PratoFeito(descricao, preco);
    }
}
