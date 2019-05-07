package Model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class GestVendasModel {
    ICatCli catCli;
    ICatProds catProds;
    List<IVenda> vendas;


    public GestVendasModel(String clients, String products, String sales) {
        this.catCli = new CatCli(clients);
        this.catProds = new CatProds(products);
        try {
            this.vendas = Files
                    .readAllLines(Paths.get(sales), StandardCharsets.UTF_8)
                    .stream()
                    .map(Venda::new)
                    .filter(e -> e.validSale()
                            && catProds.exists(e.getCodProd())
                            && catCli.exists(e.getCodCli()))
                    .collect(Collectors
                            .toList());
            out.println(vendas.size());
        }
        catch(IOException e) {
            out.println(e);
        }
    }

    public GestVendasModel() {
        this.catCli = null;
        this.catProds = null;
        this.vendas = null;
    }

    public long getVendasDadas() {
        return this
                .vendas
                .stream()
                .filter(e -> e.totalSale() == 0)
                .count();
    }
}
