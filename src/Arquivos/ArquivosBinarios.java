package Arquivos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import Consumidor.Cliente;

public class ArquivosBinarios  {
    private static final ArquivosBinarios INSTANCIA = new ArquivosBinarios();
    private static final String CAMINHO_CLIENTE_BINARIO = "src/ArquivosBinarios/clientes.bin";
    
    private List<Cliente> clientes = new ArrayList<Cliente>();

    private ArquivosBinarios() {}

    /**
     * Obtem a instância da classe Arquivos Binários, padrão de projeto SINGLETON
     * @return Instância da classe Arquivos Binários (Singleton)
    */
    public static ArquivosBinarios getInstancia() {
        return INSTANCIA;
    }

    /**
     * Obtem os dados salvos de um arquivo binário
     * @return Lista contendo todos os clientes com seus pedidos
    */
    @SuppressWarnings("unchecked")
    public List<Cliente> lerDadosArquivoBinario() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_CLIENTE_BINARIO));
            clientes = (List<Cliente>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar binário do objeto");
        } catch (ClassNotFoundException e) {
            System.err.println("Classe não foi encontrada");
        }

        return clientes;
    }

    /**
     * Salva os dados do cliente em um arquivo binário
     * @param clientes Clientes que serão salvos
    */
    public void salvarDadosEmBinario(List<Cliente> clientes) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_CLIENTE_BINARIO));
            oos.writeObject(clientes);
            oos.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar binário do objeto");
        }
    }

}
