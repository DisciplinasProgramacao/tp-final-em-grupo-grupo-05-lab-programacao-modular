package Restaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Arquivos.ArquivosBinarios;
import Bebidas.Bebida;
import Bebidas.IBebidaFactory;
import Bebidas.TipoBebida;
import Comidas.PratoFeito.IPratoFeitoFactory;
import Comidas.PratoFeito.PratoFeito;
import Comidas.ProdutoComAdicionais.Ingrediente;
import Comidas.ProdutoComAdicionais.ProdutoComAdicionais;
import Comidas.ProdutoComAdicionais.Pizza.IPizzaFactory;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Sanduiche.ISanduicheFactory;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Consumidor.Cliente;
import Consumidor.ExcecaoCPFInvalido;
import Consumidor.ExcecaoClienteJaExistente;
import Consumidor.ExcecaoClienteNaoCadastrado;
import Consumidor.RelatorioPedido;

public class GerenciaRestaurante {
    private IBebidaFactory bebidaFactory;
    private IPizzaFactory pizzaFactory;
    private ISanduicheFactory sanduicheFactory;
    private IPratoFeitoFactory pratoFeitoFactory;
    private List<Cliente> clientes;

    public GerenciaRestaurante(List<Cliente> clientes,IBebidaFactory bebidaFactory, IPizzaFactory pizzaFactory, ISanduicheFactory sanduicheFactory, IPratoFeitoFactory pratoFeitoFactory) {
        this.clientes = clientes;
        this.bebidaFactory = bebidaFactory;
        this.pizzaFactory = pizzaFactory;
        this.sanduicheFactory = sanduicheFactory;
        this.pratoFeitoFactory = pratoFeitoFactory;
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Mostar menu para a seleção de ações dentro do restaurante
    */
    public void mostrarMenuPrincipal() {
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
                    mostrarSecaoClientes();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);

        ArquivosBinarios.getInstancia().salvarDadosEmBinario(clientes);
    }

    /**
     * Método que mostra a seção de clientes do menu 
    */
    public void mostrarSecaoClientes() {
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
                    cadastrarCliente();
                    break;
                case 2: 
                    realizarPedido();
                    break;
                case 3:
                    gerarAvaliacaoMediaDePedidos();
                    break;
                case 4:
                    gerarRelatorioDeUmPedido();
                    break;
                case 5:
                    gerarRelatorioDeTodosOsPedidos();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo cliente no sistema
    */
    public void cadastrarCliente() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o nome do cliente:");
            String nome = sc.nextLine();
    
            System.out.println("Digite o CPF do cliente:");
            String CPF = sc.nextLine();
    
            Cliente clienteJaExistente = encontrarClientePorCPF(getClientes(), CPF);

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

        mostrarSecaoClientes();
    }

    /**
     * Busca o cliente através do CPF
     * @param clientes Lista contendo todos os clientes
     * @param cpf CPF que deseja ser buscado
     * @return Caso seja encontrado, é retornado o cliente, se não é retornado nulo
    */
    private Cliente encontrarClientePorCPF(List<Cliente> clientes, String cpf) {
        return clientes.stream()
            .filter((cliente) -> cliente.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);
    }

    /**
     * Cadastra um novo pedido associado ao cliente
    */
    private void realizarPedido() {
        try {
            Cliente cliente = executarLoginDoCliente();
            List<Produto> produtos = new ArrayList<Produto>();
            mostrarProdutos(produtos);
            
            verificarSeEstaNoLimiteDeProdutosParaRealizarPedido(produtos);
            
            cliente.realizarPedido(new Pedido(produtos, cliente));
        } catch (ExcecaoClienteNaoCadastrado | ExcecaoForaDoLimiteDeProdutos e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Mostra o menu contendo os produtos do sistema, para o usuário escolher qual deseja adicionar no seu pedido
     * @param produtos Lista contendo todos os produtos
    */
    public void mostrarProdutos(List<Produto> produtos) {
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
                    cadastrarBebidas(produtos);
                    break;
                case 2:
                    cadastrarPratoFeito(produtos);
                    break;
                case 3:
                    cadastrarPizza(produtos);
                    break;
                case 4:
                    cadastrarSanduiche(produtos);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona um sanduíche lista de produtos
     * @param produtos Lista de produtos
    */
    public void cadastrarSanduiche(List<Produto> produtos) {
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
                    adicionarAcrescimos(sanduicheComPaoArtesanal);
                    produtos.add(sanduicheComPaoArtesanal);
                    System.out.println("\n Sanduíche com pão artesanal adicionado com sucesso\n");
                    break;
                case 2: 
                    Sanduiche sanduicheSemPaoArtesanal = sanduicheFactory.criarSanduiche(false);
                    adicionarAcrescimos(sanduicheSemPaoArtesanal);
                    produtos.add(sanduicheSemPaoArtesanal);
                    System.out.println("\n Sanduíche sem pão artesanal adicionado com sucesso\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona uma pizza lista de produtos
     * @param produtos Lista de produtos
    */
    public void cadastrarPizza(List<Produto> produtos) {
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

    /**
     * Menu para selecionar os acréscimos que você deseja
     * @param produtoComAdicionais Produto que pode receber adicionais
    */
    public void adicionarAcrescimos(ProdutoComAdicionais produtoComAdicionais) {
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
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.PEPPERONI);
                    break;
                case 2: 
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.PICLES);
                    break;
                case 3: 
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.QUEIJO);
                    break;
                case 4: 
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.BACON);
                    break;
                case 5: 
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.PALMITO);
                    break;
                case 6: 
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.OVO);
                    break;
                case 7:
                    adicionarIngrediente(produtoComAdicionais, Ingrediente.BATATA_PALHA);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona um ingrediente em um produto que possa ter adicionais
     * @param produtoComAdicionais Produto que pode ter adicionais
     * @param ingrediente Ingrediente que deseja adicionar
    */
    public void adicionarIngrediente(ProdutoComAdicionais produtoComAdicionais, Ingrediente ingrediente) {
        produtoComAdicionais.adicionarIngrediente(ingrediente);
        System.out.println("\n " + ingrediente + " adicionado com sucesso\n");
    }

    /**
     * Adiciona um prato feito na lista de produtos
     * @param produtos Lista de produtos
    */
    public void cadastrarPratoFeito(List<Produto> produtos) {
        PratoFeito pratoFeito = pratoFeitoFactory.criarPratoFeito();
        produtos.add(pratoFeito);
        System.out.println("\nPrato feito adicionado com sucesso\n");
    }

    /**
     * Adiciona uma bebida na lista de produtos
     * @param produtos Lista de produtos
    */
    public void cadastrarBebidas(List<Produto> produtos) {
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

    /**
     * Verifica se os produtos presentes dentro do pedido estão dentro do limite estabelecido
     * @param produtos Lista com todos os produtos do pedido
     * @throws ExcecaoForaDoLimiteDeProdutos Caso esteja fora do limite
    */
    private void verificarSeEstaNoLimiteDeProdutosParaRealizarPedido(List<Produto> produtos) throws ExcecaoForaDoLimiteDeProdutos {
        if (!(produtos.size() > 0 && produtos.size() <= 10)) {
            throw new ExcecaoForaDoLimiteDeProdutos();
        }
    }

    /**
     * Executa o login do cliente para ele poder realizar ações dentro do sistema
     * @return Cliente encontrado
     * @throws ExcecaoClienteNaoCadastrado Caso o cliente não seja encontrado
    */
    private Cliente executarLoginDoCliente() throws ExcecaoClienteNaoCadastrado {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite o CPF do cliente para começar a fazer o pedido:");
        String cpf = sc.nextLine();
        Cliente cliente = encontrarClientePorCPF(clientes, cpf);

        if (cliente != null) {
            System.out.println("\nSeja bem vindo, " + cliente.getNome() + "!\n");
            return cliente;
        }
        else {
            throw new ExcecaoClienteNaoCadastrado();
        }
    }

    /**
     * Gera um relatório resumido de todos os pedidos do usuário
    */
    public void gerarRelatorioDeTodosOsPedidos() {
        try {
            Cliente cliente = executarLoginDoCliente();

            RelatorioPedido.getInstancia().solicitarExtratoResumidoTodosOsPedidos(cliente);
        }
        catch (ExcecaoClienteNaoCadastrado e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Gera um relatório detalhado sobre o pedido
    */
    public void gerarRelatorioDeUmPedido() {
        try {
            Cliente cliente = executarLoginDoCliente();
            Pedido pedido = encontrarPedidoAtravesDoId(cliente.getPedidos());

            RelatorioPedido.getInstancia().solicitarExtratoPedidoEspecifico(cliente, pedido);
        }
        catch (ExcecaoClienteNaoCadastrado | ExcecaoPedidoInexistente e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Busca um pedido através do seu id
     * @param pedidos Lista contendo todos os pedidos
     * @return Pedido encontrado
     * @throws ExcecaoPedidoInexistente Caso o pedido não exista
    */
    public Pedido encontrarPedidoAtravesDoId(List<Pedido> pedidos) throws ExcecaoPedidoInexistente {
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

    /**
     * Gera a avalição média de pedidos de um cliente
    */
    public void gerarAvaliacaoMediaDePedidos() {
        try {
            Cliente cliente = executarLoginDoCliente();
            double avaliacaoMedia = cliente.obterAvaliacaoMedia();
            System.out.println("Nota de avaliação média do cliente " + cliente.getNome() + ": " + avaliacaoMedia);
        }
        catch (ExcecaoClienteNaoCadastrado e) {
            System.err.println(e.getMessage());
        }
    }

}
