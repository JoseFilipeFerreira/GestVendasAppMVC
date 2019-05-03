package Model;

public interface IProduct {
    public boolean verifyProduct();

    public String getId();

    public IProduct clone();

    public IProduct fromString(String s);
}
