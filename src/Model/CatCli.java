package Model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class CatCli implements ICatCli {
    private Map<String, IClient> catCli;

    public CatCli() {
        this.catCli = new HashMap<>();
    }

    public CatCli(String fp) {
        List<String> clients = new ArrayList<>();
        try {
            clients = Files.readAllLines(Paths.get(fp), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            out.println(e);
        }
        this.catCli = clients
                .stream()
                .map(Client::new)
                .collect(Collectors
                        .toMap(IClient::getId, Function.identity()));
    }

    public boolean exists(IClient p) {
        return this
                .catCli
                .containsKey(p.getId());
    }

    public boolean exists(String p) {
        return this
                .catCli
                .containsKey(p);
    }

    public void add(IClient p) {
        this
                .catCli
                .put(p.getId(), p);
    }

    public int howMany() {
        return this
                .catCli
                .size();
    }

    public List<IClient> clientList() {
        return this
                .catCli
                .values()
                .stream()
                .map(IClient::clone)
                .collect(Collectors.toList());
    }
}
