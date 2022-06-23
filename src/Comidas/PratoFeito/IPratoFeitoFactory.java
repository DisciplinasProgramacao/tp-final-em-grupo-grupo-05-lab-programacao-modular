package Comidas.PratoFeito;

public interface IPratoFeitoFactory {
    public PratoFeito criarPratoFeito(String descricao, double preco);
}
