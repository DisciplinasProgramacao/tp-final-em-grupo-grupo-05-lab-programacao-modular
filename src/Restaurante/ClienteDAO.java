package Restaurante;
import Consumidor.Cliente;
import java.util.ArrayList;
import java.util.List;
/*Imports java.io */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/*Classe do padrão DAO para armazenar e ler as instâncias da classe Cliente */
public class ClienteDAO implements DAO<Cliente, String> {
	private static final String filename = "cliente.bin";
	
	public ClienteDAO(){}
	/*Adiciona novo objeto ao arquivo
	 * @param p - Cliente - Objeto a ser armazenado no arquivo
	*/
	@Override
	public void add(Cliente p) {
		try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(filename, false))) {
            saida.writeObject(p);
			saida.flush();
            saida.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Cliente de cpf:'" + p.getCpf() + "' no disco!");
			e.printStackTrace();
		}
	}
	/*
	 Busca um objeto específico no arquivo
	 * @param cpf - String - atributo a ser utilizado na busca
	 * @return Cliente - retorna o objeto Cliente que estava sendo procurado
	 */
	@Override
	public Cliente get(String cpf) {
		Cliente retorno = null;
		String cCpf = null;

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(filename))) {
			if(entrada.available()>0){
				while ((cCpf = entrada.readUTF()) != null) {
					if (cpf.equals(cCpf)) {
						retorno = (Cliente) entrada.readObject();
						break;
					} else {
						entrada.readObject();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Cliente de cpf: '" + cpf + "' do disco rígido!");
			e.printStackTrace();
		}
		return retorno;
	}
	@Override
	public List<Cliente> getAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente b = null;
		String cpf = null;

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(filename))) {
			while ((cpf = entrada.readUTF()) != null) {
				b = (Cliente) entrada.readObject();
				clientes.add(b);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Cliente de cpf: '" + cpf + "' do disco rígido!");
			e.printStackTrace();
		}
		return clientes;
	}
	@Override
	public void update(Cliente p) {
		List<Cliente> clientes = getAll();
		int index = clientes.indexOf(p);
		if (index != -1) {
			clientes.set(index, p);
		}
		saveToFile(clientes);
	}
	@Override
	public void delete(Cliente p) {
		List<Cliente> clientes = getAll();
		int index = clientes.indexOf(p);
		if (index != -1) {
			clientes.remove(index);
		}
		saveToFile(clientes);
	}
	private void saveToFile(List<Cliente> clientes) {
		try (ObjectOutputStream saida = 
			new ObjectOutputStream(new FileOutputStream(filename, false))) {
			for (Cliente b : clientes) {
				saida.writeObject(b);
			}
			saida.flush();
            saida.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Cliente no disco!");
			e.printStackTrace();
		}
	}
}