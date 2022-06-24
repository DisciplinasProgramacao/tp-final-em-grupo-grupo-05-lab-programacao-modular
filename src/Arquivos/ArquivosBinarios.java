package Arquivos;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;


import Bebidas.Bebida;
import Comidas.PratoFeito.PratoFeito;
import Comidas.ProdutoComAdicionais.Pizza.Pizza;
import Comidas.ProdutoComAdicionais.Sanduiche.Sanduiche;
import Consumidor.Cliente;
import Restaurante.Pedido;

public class ArquivosBinarios implements ITratarArquivos {
    private List<Cliente> clientes = new ArrayList<Cliente>();
    
    public List<Cliente> lerDadosArquivoBinario() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/ArquivosBinarios/clientes.bin"));
            clientes = (List<Cliente>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar binário do objeto");
        } catch (ClassNotFoundException e) {
            System.err.println("Classe não foi encontrada");
        }

        return clientes;
    }


    @Override
    public List<Bebida> obterBebidas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Pizza> obterPizzas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Sanduiche> obterSanduiches() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PratoFeito> obterPratoFeitos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Cliente> obterClientes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Pedido> obterPedidos() {
        // TODO Auto-generated method stub
        return null;
    }
}
