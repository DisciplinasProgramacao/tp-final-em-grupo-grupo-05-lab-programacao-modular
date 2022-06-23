package Comidas.ProdutoComAdicionais;

import java.util.ArrayList;
import java.util.List;
import Restaurante.Produto;

public abstract class ProdutoComAdicionais extends Produto {
    private List<Adicional> adicionais;

    protected ProdutoComAdicionais(String descricao, double preco) {
        super(descricao, preco);
        adicionais = new ArrayList<Adicional>();
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        Adicional adicionalJaExistente = obterAdicionalJaExistente(ingrediente);
        if (adicionalJaExistente != null) {
            adicionalJaExistente.aumentarQuantidadeIngrediente();
        }
        else {
            Adicional novoAdicional = new Adicional(ingrediente);
            adicionais.add(novoAdicional);
        }
    }

    /**
     * Verifica se o ingrediente já existe e retorna o seu adicional
     * @param ingrediente Ingrediente que deseja ser adicionado
     * @return Caso já exista um adicional com esse ingrediente, é retornado o adicional, se não é retornado null 
    */
    private Adicional obterAdicionalJaExistente(Ingrediente ingrediente) {
        return adicionais.stream()
            .filter((adicional) -> adicional.getIngrediente().equals(ingrediente))
            .findFirst()
            .orElse(null);
    }

    public double obterValorTotalAdicionais() {
        return adicionais.stream()
            .mapToDouble((adicional) -> adicional.obterValorAdicional())
            .sum();
    }

    public List<Adicional> getAdicionais() {
        return this.adicionais;
    }

    public boolean existeAdicionais() {
        return !getAdicionais().isEmpty();
    }
}
