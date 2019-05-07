package Model;

public class Product implements IProduct{
    private String id;

    public Product(String id) {
        this.id = id;
    }

    public Product(Product a) {
        this.id = a.getId();
    }

    public String getId() {
        return this.id;
    }

    public boolean verifyProduct() {
        return this.id.matches("[A-Z]{2}\\d{4}");
    }

    public IProduct clone() {
        return new Product(this);
    }
}
