package Model;

import java.util.List;

public interface ICatProds {
    boolean exists(String p);

    int howMany();

    List<IProduct> productList();
}
