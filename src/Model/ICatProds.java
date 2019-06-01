package Model;

import java.util.List;

public interface ICatProds {
    boolean exists(IProduct p);

    boolean exists(String p);

    ICatProds add(IProduct p);

    int howMany();

    List<IProduct> productList();
}
