package Model;

import java.util.List;

public interface ICatProds {
    /**
     * Verifica se um produto existe na base de dados de Produtos
     * @param p Id do produto a procurar
     * @return Se o Produto existe ou não
     */
    boolean exists(String p);

    /**
     * Calcula o número de produtos existentes
     * @return Número de produtos existentes
     */
    int howMany();

    /**
     * Calcula toda a lista de produtos
     * @return Lista de produto
     */
    List<IProduct> productList();
}
