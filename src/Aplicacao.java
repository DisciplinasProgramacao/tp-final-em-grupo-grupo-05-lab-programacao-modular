import Bebidas.Bebida;
import Bebidas.BebidaFactoryConcreto;
import Bebidas.TipoBebida;

public class Aplicacao {
    public static void main(String[] args) {
        BebidaFactoryConcreto bebidaFactory = new BebidaFactoryConcreto();
        Bebida agua = bebidaFactory.criarBebida(TipoBebida.AGUA);
        Bebida cerveja = bebidaFactory.criarBebida(TipoBebida.CERVEJA);
    }
}