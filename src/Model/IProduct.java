package Model;

public interface IProduct {
    /**
     * Verifica se um produto é valido ou não
     * @return Se o produto é valido ou não
     */
    boolean verifyProduct();

    /**
     * Getter do Id do produto
     * @return ID do produto
     */
    String getId();

    /**
     * Cria um clone do produto
     * @return Produto clonado
     */
    IProduct clone();
}
