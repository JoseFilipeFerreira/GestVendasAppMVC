package Model;

import java.util.List;

public interface ICatProds {
    public boolean exists(IProduct p);

    public boolean exists(String p);

    public void add(IProduct p);

    public int howMany();

    public List<IProduct> productList();
}
