package Produtos;
/*Interface mediadora entre os Produtos que podem possuir um adicional e os ingredientes adicionais
 * advinda do padrão de projeto Decorator
 */
public interface Adicional {
    public String getDescricao();
    public double getPreco();
}
