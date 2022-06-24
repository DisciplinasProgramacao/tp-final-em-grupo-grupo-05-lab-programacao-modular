package Restaurante;
import java.util.ArrayList;
import java.util.List;

/*Imports java.io */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/*Classe do padrão DAO para armazenar e ler as instâncias da classe Pedido */
public class PedidoDAO implements DAO<Pedido, Integer> {
	private static final String filename = "pedido.bin";
	
	public PedidoDAO(){}
	/*Adiciona novo objeto ao arquivo
	 * @param p - Pedido - Objeto a ser armazenado no arquivo
	*/
	@Override
	public void add(Pedido p) {
		try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(filename, false))) {
            saida.writeObject(p);
			saida.flush();
            saida.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Pedido '" + p.getId() + "' no disco!");
			e.printStackTrace();
		}
	}
	/*
	Busca um objeto específico no arquivo
	 * @param id - Integer - atributo a ser utilizado na busca
	 */
	@Override
	public Pedido get(Integer id) {
		Pedido retorno = null;
		int isID = 0;

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(filename))) {
			if(entrada.available()>0){
				while ((isID = entrada.readInt()) != Pedido.contagemID) {
					if (id==isID) {
						retorno = (Pedido) entrada.readObject();
						break;
					} else {
						entrada.readObject();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Pedido '" + id + "' do disco rígido!");
			e.printStackTrace();
		}
		return retorno;
	}
	@Override
	public List<Pedido> getAll() {
		List<Pedido> produtos = new ArrayList<Pedido>();
		Pedido b = null;
		String isID = null;

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(filename))) {
			if(entrada.available()>0){
				b = (Pedido) entrada.readObject();
				while (b != null) {
					b = (Pedido) entrada.readObject();
					produtos.add(b);
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o Produto '" + isID + "' do disco rígido!");
			e.printStackTrace();
		}
		return produtos;
	}
	@Override
	public void update(Pedido p) {
		List<Pedido> produtos = getAll();
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.set(index, p);
		}
		saveToFile(produtos);
	}
	@Override
	public void delete(Pedido p) {
		List<Pedido> produtos = getAll();
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.remove(index);
		}
		saveToFile(produtos);
	}
	private void saveToFile(List<Pedido> produtos) {
		try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(filename, false))) {
			for (Pedido b : produtos) {
				saida.writeObject(b);
			}
			saida.flush();
            saida.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Pedido no disco!");
			e.printStackTrace();
		}
	}
}