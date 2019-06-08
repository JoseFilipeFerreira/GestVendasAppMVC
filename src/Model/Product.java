package Model;

import java.io.Serializable;

public class Product implements IProduct, Serializable {
    private String id;

    /**
     * Construtor de um Produto dado o seu id
     * @param id Id do produto a construir
     */
    public Product(String id) {
        this.id = id;
    }

    private Product(Product a) {
        this.id = a.getId();
    }

    /**
     * Getter do Id do produto
     * @return ID do produto
     */
    public String getId() {
        return this.id;
    }

    /**
     * Verifica se um produto é valido ou não
     * @return Se o produto é valido ou não
     */
    public boolean verifyProduct() {
        return this.id.matches("[A-Z]{2}\\d{4}");
    }

    /**
     * Cria um clone do produto
     * @return Produto clonado
     */
    public IProduct clone() {
        return new Product(this);
    }
}
