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

import static java.lang.System.*;

public class CatProds implements ICatProds{
    private Map<String, IProduct> catProds;

    public CatProds() {
        this.catProds = new HashMap<>();
    }

    public CatProds(String fp) {
        List<String> produtos = new ArrayList<>();
        try {
            produtos = Files.readAllLines(Paths.get(fp), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            out.println(e);
        }
        this.catProds = produtos
                .stream()
                .map(Product::new)
                .collect(Collectors
                        .toMap(IProduct::getId, Function.identity()));
    }

    public boolean exists(IProduct p) {
        return this
                .catProds
                .containsKey(p.getId());
    }

    public boolean exists(String p) {
        return this
                .catProds
                .containsKey(p);
    }

    public void add(IProduct p) {
        this
                .catProds
                .put(p.getId(), p);
    }

    public int howMany() {
        return this
                .catProds
                .size();
    }

    public List<IProduct> productList() {
        return this
                .catProds
                .values()
                .stream()
                .map(IProduct::clone)
                .collect(Collectors.toList());
    }
}
