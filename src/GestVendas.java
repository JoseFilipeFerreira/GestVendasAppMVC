import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class GestVendas {
    ICatCli catCli;
    ICatProds catProds;
    List<IVenda> vendas;


    public GestVendas(String clients, String products, String sales) {
        this.catCli = new CatCli(clients);
        this.catProds = new CatProds(products);
        try {
            this.vendas = Files
                    .readAllLines(Paths.get(sales), StandardCharsets.UTF_8)
                    .stream()
                    .map(Venda::new)
                    .filter(Venda::validSale)
                    .filter(e -> catProds.exists(e.getCodProd())
                            && catCli.exists(e.getCodCli()))
                    .collect(Collectors
                            .toList());
            out.println(vendas.size());
        }
        catch(IOException e) {
            out.println(e);
        }
    }
}
