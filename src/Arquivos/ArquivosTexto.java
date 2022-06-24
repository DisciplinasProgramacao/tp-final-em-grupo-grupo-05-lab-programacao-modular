package Arquivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import Bebidas.Bebida;
import Bebidas.IBebidaFactory;
import Bebidas.TipoBebida;
import Comidas.PratoFeito.IPratoFeitoFactory;
import Comidas.PratoFeito.PratoFeito;
import Comidas.ProdutoComAdicionais.Pizza.IPizzaFactory;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Sanduiche.ISanduicheFactory;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Consumidor.Cliente;
import Consumidor.ExcecaoCPFInvalido;

public class ArquivosTexto implements ITratarArquivos {
    private static final String CAMINHO_BEBIDAS = "src/ArquivosTexto/bebidas.txt";
    private static final String CAMINHO_PRODUTOS_ADICIONAIS = "src/ArquivosTexto/produtosAdicionais.txt";
    private static final String CAMINHO_CLIENTES = "src/ArquivosTexto/clientes.txt";
    private static final ArquivosTexto INSTANCIA = new ArquivosTexto();

    private ArquivosTexto() {}

    public static ArquivosTexto getInstancia() {
        return INSTANCIA;
    }

    @Override
    public List<Bebida> obterBebidas(IBebidaFactory bebidaFactory) {
        List<Bebida> bebidas = new ArrayList<Bebida>();

        try {
            Scanner sc = new Scanner(new File(CAMINHO_BEBIDAS));
            
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                Bebida bebida = bebidaFactory.criarBebida(TipoBebida.valueOf(linha));
                bebidas.add(bebida);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    
        return bebidas;
    }

    @Override
    public List<Pizza> obterPizzas(IPizzaFactory pizzaFactory) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        
        try {
            Scanner sc = new Scanner(new File(CAMINHO_PRODUTOS_ADICIONAIS));
            
            while (sc.hasNextLine()) {
                boolean linha = sc.nextBoolean();
                Pizza pizza = pizzaFactory.criarPizza(linha);
                pizzas.add(pizza);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    
        return pizzas;
    }

    @Override
    public List<Sanduiche> obterSanduiches(ISanduicheFactory sanduicheFactory) {
        List<Sanduiche> sanduiches = new ArrayList<Sanduiche>();
        
        try {
            Scanner sc = new Scanner(new File(CAMINHO_PRODUTOS_ADICIONAIS));
            
            while (sc.hasNextLine()) {
                boolean linha = sc.nextBoolean();
                Sanduiche sanduiche = sanduicheFactory.criarSanduiche(linha);
                sanduiches.add(sanduiche);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    
        return sanduiches;
    }

    @Override
    public List<PratoFeito> obterPratoFeitos(IPratoFeitoFactory pratoFeitoFactory) {
        List<PratoFeito> pratosFeitos = new ArrayList<PratoFeito>();
        
        try {
            Scanner sc = new Scanner(new File(CAMINHO_PRODUTOS_ADICIONAIS));
            
            while (sc.hasNextLine()) {
                sc.nextLine();
                PratoFeito pratoFeito = pratoFeitoFactory.criarPratoFeito();
                pratosFeitos.add(pratoFeito);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    
        return pratosFeitos;
    }

    @Override
    public List<Cliente> obterClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try {
            Scanner sc = new Scanner(new File(CAMINHO_CLIENTES));
            
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] detalhes = linha.split(";");
                String nome = detalhes[0];
                String cpf = detalhes[1];
                Cliente cliente = new Cliente(nome, cpf);
                clientes.add(cliente);
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        catch (ExcecaoCPFInvalido e) {
            System.err.println(e.getMessage());
        }
    
        return clientes;
    }
}
