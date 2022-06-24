import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Bebidas.Bebida;
import Bebidas.BebidaFactoryConcreto;
import Bebidas.TipoBebida;
import Comidas.PratoFeito.PratoFeito;
import Comidas.PratoFeito.PratoFeitoFactoryConcreto;
import Comidas.ProdutoComAdicionais.Ingrediente;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Pizza.PizzaFactoryConcreto;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Comidas.ProdutoComAdicionais.Sanduiche.SanduicheFactoryConcreto;
import Consumidor.Cliente;
import Consumidor.ExcecaoCPFInvalido;
import Consumidor.ExcecaoClienteJaExistente;
import Consumidor.ExcecaoClienteNaoCadastrado;
import Consumidor.RelatorioPedido;
import Restaurante.ExcecaoPedidoInexistente;
import Restaurante.Pedido;
import Restaurante.Produto;

public class Aplicacao {
    private static final String caminhoArquivoBebidas = "src/ArquivosTexto/bebidas.txt";
    private static final String caminhoArquivoProdutosAdicionais = "src/ArquivosTexto/produtosAdicionais.txt";
    private static final String caminhoArquivoClientes = "src/ArquivosTexto/clientes.txt";


    public static void main(String[] args) {
        BebidaFactoryConcreto bebidaFactory = new BebidaFactoryConcreto();
        PizzaFactoryConcreto pizzaFactory = new PizzaFactoryConcreto();
        SanduicheFactoryConcreto sanduicheFactory = new SanduicheFactoryConcreto();
        PratoFeitoFactoryConcreto pratoFeitoFactory = new PratoFeitoFactoryConcreto();
        
        // Inicializando classes com arquivo de texto
        List<Bebida> bebidas = alimentarClasseBebidaAtravesDeArquivoTexto(bebidaFactory);
        List<Pizza> pizzas = alimentarClassePizzaAtravesDeArquivoTexto(pizzaFactory);
        List<Sanduiche> sanduiches = alimentarClasseSanduicheAtravesDeArquivoTexto(sanduicheFactory);
        List<Cliente> clientes = alimentarClasseClienteAtravesDeArquivoDeTexto();
        
        List<Cliente> clientes = lerDadosArquivoBinario();
        

        mostrarMenu(clientes, bebidaFactory, pizzaFactory, sanduicheFactory, pratoFeitoFactory);
    }

    /**
     * Mostar menu para a seleção de ações dentro do restaurante
     * @param clientes Lista contendo todos os clientes do sistema
     * @param bebidaFactory Factory de bebidas 
     * @param pizzaFactory Factory de pizzas
     * @param sanduicheFactory Factory de sanduíches
     * @param pratoFeitoFactory Factory de prato feitos
     */
    public static void mostrarMenu(List<Cliente> clientes, BebidaFactoryConcreto bebidaFactory, PizzaFactoryConcreto pizzaFactory, SanduicheFactoryConcreto sanduicheFactory, PratoFeitoFactoryConcreto pratoFeitoFactory) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("\nSISTEMA RESTAURANTE:\n");
            System.out.println("Qual seção gostaria de acessar?\n");
            System.out.println("[1] Clientes");
            System.out.println("[0] Sair do sistema");
            System.out.print("\nDigite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    mostrarMenuClientes(clientes, bebidaFactory, pratoFeitoFactory, sanduicheFactory, pizzaFactory);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);

        salvarDadosEmBinario(clientes);
    }

    public static void cadastrarCliente(List<Cliente> clientes, BebidaFactoryConcreto bebidaFactory, PizzaFactoryConcreto pizzaFactory, SanduicheFactoryConcreto sanduicheFactory, PratoFeitoFactoryConcreto pratoFeitoFactory) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o nome do cliente:");
            String nome = sc.nextLine();
    
            System.out.println("Digite o CPF do cliente:");
            String CPF = sc.nextLine();
    
            Cliente clienteJaExistente = encontrarClientePorCPFOuNome(clientes, CPF);

            if (clienteJaExistente == null) {
                Cliente cliente = new Cliente(nome, CPF);
                clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
            }
            else {
                throw new ExcecaoClienteJaExistente();
            }
        } catch (ExcecaoClienteJaExistente | ExcecaoCPFInvalido e) {
            System.err.println(e.getMessage());
        }

        mostrarMenuClientes(clientes, bebidaFactory, pratoFeitoFactory, sanduicheFactory, pizzaFactory );
    }

    public static void salvarDadosEmBinario(List<Cliente> clientes) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/ArquivosBinarios/clientes.bin"));
            oos.writeObject(clientes);
            oos.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar binário do objeto");
        }
    }


    public static void fazerPedido(BebidaFactoryConcreto bebidaFactory, PratoFeitoFactoryConcreto pratoFeitoFactory, PizzaFactoryConcreto pizzaFactory, SanduicheFactoryConcreto sanduicheFactory, List<Cliente> clientes) {
        try {
            Cliente cliente = executarLoginDoUsuario(clientes);
            List<Produto> produtos = new ArrayList<Produto>();
            mostrarProdutos(bebidaFactory, pratoFeitoFactory, pizzaFactory, sanduicheFactory, produtos, clientes);
            
            cliente.realizarPedido(new Pedido(produtos, cliente));
        } catch (ExcecaoClienteNaoCadastrado e) {
            System.err.println(e.getMessage());
        }
    }

    public static Cliente encontrarClientePorCPFOuNome(List<Cliente> clientes, String cpf) {
        return clientes.stream()
            .filter((cliente) -> cliente.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);
    }

    public static void mostrarProdutos(BebidaFactoryConcreto bebidaFactory, PratoFeitoFactoryConcreto pratoFeitoFactory, PizzaFactoryConcreto pizzaFactory, SanduicheFactoryConcreto sanduicheFactory,List<Produto> produtos, List<Cliente> clientes) {
        System.out.println("Qual tipo de produto você deseja adicionar no pedido?\n");

        int opcao;
        Scanner sc = new Scanner(System.in);
            
        do {
            System.out.println("[1] Bebidas");
            System.out.println("[2] Prato Feito");
            System.out.println("[3] Pizza");
            System.out.println("[4] Sanduíche");
            System.out.println("[0] Finalizar pedido");

            System.out.print("\nDigite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    cadastrarBebidas(bebidaFactory,produtos, clientes);
                    break;
                case 2:
                    cadastrarPratoFeito(pratoFeitoFactory,produtos, clientes);
                    break;
                case 3:
                    cadastrarPizza(pizzaFactory,produtos, clientes);
                    break;
                case 4:
                    cadastrarSanduiche(sanduicheFactory,produtos, clientes);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarSanduiche(SanduicheFactoryConcreto sanduicheFactory, List<Produto> produtos, List<Cliente> clientes) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Como deseja seu sanduíche?");
            System.out.println("[1] Sanduíche com pão artesanal");
            System.out.println("[2] Sanduíche sem pão artesanal");
            System.out.println("[0] Voltar para a seção de produtos");

            System.out.print("Digite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    Sanduiche sanduicheComPaoArtesanal = sanduicheFactory.criarSanduiche(true);
                    produtos.add(sanduicheComPaoArtesanal);
                    System.out.println("\n Sanduíche com pão artesanal adicionado com sucesso\n");
                    break;
                case 2: 
                    Sanduiche sanduicheSemPaoArtesanal = sanduicheFactory.criarSanduiche(false);
                    produtos.add(sanduicheSemPaoArtesanal);
                    System.out.println("\n Sanduíche sem pão artesanal adicionado com sucesso\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastrarPizza(PizzaFactoryConcreto pizzaFactory, List<Produto> produtos, List<Cliente> clientes) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Como deseja sua pizza?");
            System.out.println("[1] Pizza com borda Recheada");
            System.out.println("[2] Pizza sem borda recheada");
            System.out.println("[0] Voltar para a seção de produtos");

            System.out.print("Digite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    Pizza pizzaComBordaRecheada = pizzaFactory.criarPizza(true);
                    adicionarAcrescimos(pizzaComBordaRecheada);
                    produtos.add(pizzaComBordaRecheada);
                    System.out.println("\n Pizza com borda recheada adicionada com sucesso\n");
                    break;
                case 2: 
                    Pizza pizzaSemBordaRecheada = pizzaFactory.criarPizza(false);
                    adicionarAcrescimos(pizzaSemBordaRecheada);
                    produtos.add(pizzaSemBordaRecheada);
                    System.out.println("\n Pizza sem borda recheada adicionada com sucesso\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public static void adicionarAcrescimos(Pizza pizza) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("Deseja adicionar algum adicional?");
            for (int i = 1; i <= Ingrediente.values().length; i++) {
                System.out.println("[" + i +"] " + Ingrediente.values()[i - 1]);
            }

            System.out.println("[0] Não, obrigado.");
            System.out.print("Digite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    adicionarIngrediente(pizza, Ingrediente.PEPPERONI);
                    break;
                case 2: 
                    adicionarIngrediente(pizza, Ingrediente.PICLES);
                    break;
                case 3: 
                    adicionarIngrediente(pizza, Ingrediente.QUEIJO);
                    break;
                case 4: 
                    adicionarIngrediente(pizza, Ingrediente.BACON);
                    break;
                case 5: 
                    adicionarIngrediente(pizza, Ingrediente.PALMITO);
                    break;
                case 6: 
                    adicionarIngrediente(pizza, Ingrediente.OVO);
                    break;
                case 7:
                    adicionarIngrediente(pizza, Ingrediente.BATATA_PALHA);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    private static void adicionarIngrediente(Pizza pizza, Ingrediente ingrediente) {
        pizza.adicionarIngrediente(ingrediente);
        System.out.println("\n " + ingrediente + " adicionado com sucesso\n");
    }

    public static void cadastrarPratoFeito(PratoFeitoFactoryConcreto pratoFeitoFactory, List<Produto> produtos, List<Cliente> clientes) {
        PratoFeito pratoFeito = pratoFeitoFactory.criarPratoFeito();
        produtos.add(pratoFeito);
        System.out.println("\nPrato feito adicionado com sucesso\n");
    }

    public static void cadastrarBebidas(BebidaFactoryConcreto bebidaFactory, List<Produto> produtos, List<Cliente> clientes) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("[1] Cerveja");
            System.out.println("[2] Suco");
            System.out.println("[3] Água");
            System.out.println("[4] Refrigerante");
            System.out.println("[0] Voltar para seções de produtos");

            System.out.print("Digite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    Bebida cerveja = bebidaFactory.criarBebida(TipoBebida.CERVEJA);
                    produtos.add(cerveja);
                    System.out.println("\nCerveja adicionado com sucesso\n");
                    break;
                case 2: 
                    Bebida suco = bebidaFactory.criarBebida(TipoBebida.SUCO);
                    produtos.add(suco);
                    System.out.println("\nSuco adicionado com sucesso\n");
                    break;
                case 3: 
                    Bebida agua = bebidaFactory.criarBebida(TipoBebida.AGUA);
                    produtos.add(agua);
                    System.out.println("\nAgua adicionado com sucesso\n");
                    break;
                case 4: 
                    Bebida refrigerante = bebidaFactory.criarBebida(TipoBebida.REFRIGERANTE);
                    produtos.add(refrigerante);
                    System.out.println("\nRefrigerante adicionado com sucesso\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public static void mostrarMenuClientes(List<Cliente> clientes, BebidaFactoryConcreto bebidaFactory, PratoFeitoFactoryConcreto pratoFeitoFactory, SanduicheFactoryConcreto sanduicheFactory, PizzaFactoryConcreto pizzaFactory) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println("\nQual ação gostaria de realizar? \n");
            System.out.println("[1] Cadastrar cliente");
            System.out.println("[2] Fazer pedido");
            System.out.println("[3] Gerar avaliação média de pedidos");
            System.out.println("[4] Gerar relatório de um pedido");
            System.out.println("[5] Gerar relatório de todos os pedidos");

            System.out.println("[0] Voltar para seções");

            System.out.print("\nDigite sua opção: ");

            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    cadastrarCliente(clientes, bebidaFactory, pizzaFactory, sanduicheFactory, pratoFeitoFactory);
                    break;
                case 2: 
                    fazerPedido(bebidaFactory, pratoFeitoFactory, pizzaFactory, sanduicheFactory, clientes);
                    break;
                case 3:
                    gerarAvaliacaoMediaDePedidos(clientes);
                    break;
                case 4:
                    gerarRelatorioDeUmPedido(clientes);
                    break;
                case 5:
                    gerarRelatorioDeTodosOsPedidos(clientes);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }
    
    private static void gerarRelatorioDeTodosOsPedidos(List<Cliente> clientes) {
        try {
            Cliente cliente = executarLoginDoUsuario(clientes);

            RelatorioPedido.getInstancia().solicitarExtratoPedidoTodosOsPedidos(cliente);
        }
        catch (ExcecaoClienteNaoCadastrado e) {
            System.err.println(e.getMessage());
        }
    }

    private static void gerarRelatorioDeUmPedido(List<Cliente> clientes) {
        try {
            Cliente cliente = executarLoginDoUsuario(clientes);
            Pedido pedido = encontrarPedidoAtravesDoId(cliente.getPedidos());

            RelatorioPedido.getInstancia().solicitarExtratoPedidoEspecifico(cliente, pedido);
        }
        catch (ExcecaoClienteNaoCadastrado | ExcecaoPedidoInexistente e) {
            System.err.println(e.getMessage());
        }
    }

    private static Pedido encontrarPedidoAtravesDoId(List<Pedido> pedidos) throws ExcecaoPedidoInexistente {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número do id para encontrar o pedido:");
        int id = sc.nextInt();

        Pedido pedidoEncontrado = pedidos.stream().filter((pedido) -> Pedido.getId() == id).findFirst().orElse(null);

        if (pedidoEncontrado != null) {
            return pedidoEncontrado;
        }
        else {
            throw new ExcecaoPedidoInexistente();
        }
    }

    private static void gerarAvaliacaoMediaDePedidos(List<Cliente> clientes) {
        try {
            Cliente cliente = executarLoginDoUsuario(clientes);
            double avaliacaoMedia = cliente.obterAvaliacaoMedia();
            System.out.println(avaliacaoMedia);
        }
        catch (ExcecaoClienteNaoCadastrado e) {
            System.err.println(e.getMessage());
        }
    }

    public static Cliente executarLoginDoUsuario(List<Cliente> clientes) throws ExcecaoClienteNaoCadastrado{
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite o CPF do cliente para começar a fazer o pedido:");
        String cpf = sc.nextLine();
        Cliente cliente = encontrarClientePorCPFOuNome(clientes, cpf);

        if (cliente != null) {
            System.out.println("\nSeja bem vindo, " + cliente.getNome() + "!\n");
            return cliente;
        }
        else {
            throw new ExcecaoClienteNaoCadastrado();
        }
    }

    public static List<Bebida> alimentarClasseBebidaAtravesDeArquivoTexto(BebidaFactoryConcreto bebidaFactory) {
        List<Bebida> bebidas = new ArrayList<Bebida>();

        try {
            Scanner sc = new Scanner(new File(caminhoArquivoBebidas));
            
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

    public static List<Pizza> alimentarClassePizzaAtravesDeArquivoTexto(PizzaFactoryConcreto pizzaFactory) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        
        try {
            Scanner sc = new Scanner(new File(caminhoArquivoProdutosAdicionais));
            
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

    public static List<Sanduiche> alimentarClasseSanduicheAtravesDeArquivoTexto(SanduicheFactoryConcreto sanduicheFactory) {
        List<Sanduiche> sanduiches = new ArrayList<Sanduiche>();
        
        try {
            Scanner sc = new Scanner(new File(caminhoArquivoProdutosAdicionais));
            
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

    public static List<Cliente> alimentarClasseClienteAtravesDeArquivoDeTexto() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try {
            Scanner sc = new Scanner(new File(caminhoArquivoClientes));
            
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