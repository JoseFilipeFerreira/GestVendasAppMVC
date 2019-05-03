package Model;

import java.util.List;

public interface ICatCli {
    boolean exists(IClient p);

    boolean exists(String p);

    void add(IClient p);

    int howMany();

    List<IClient> clientList();
}
