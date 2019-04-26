import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.System.out;
public class read {
    public static void main(String[] args) {
        Crono.start();
        GestVendas a = new GestVendas("db/Clientes.txt", "db/Produtos.txt", "db/Vendas_1M.txt");
        out.println(Crono.print());

    }

    public static List<IVenda> readLinesWithBR(String fichtxt) {
        List<String> linhas = new ArrayList<>();
        BufferedReader inFile = null;
        String linha = null;
        try {
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
                linhas.add(linha);
        }
        catch(IOException e) {
            out.println(e);
        }
        return getVendas(linhas);

    }

    public static List<IVenda> readWithFiles(String fichtxt) {
        List<String> linhas = new ArrayList<>();
        List<String> clientes = new ArrayList<>();
        List<String> produtos = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(fichtxt), StandardCharsets.UTF_8);
            clientes = Files.readAllLines(Paths.get("db/Clientes.txt"), StandardCharsets.UTF_8);
            produtos = Files.readAllLines(Paths.get("db/Produtos.txt"), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            out.println(e);
        }
        out.println(clientes.stream().map(Client::new).filter(Client::verifyClient).collect(Collectors.toList()).size());
        out.println(produtos.stream().map(Product::new).filter(Product::verifyProduct).collect(Collectors.toList()).size());
        return getVendas(linhas);
    }

    private static List<IVenda> getVendas(List<String> linhas) {
        List<IVenda> linhasR = linhas
                .stream()
                .map(Venda::new)
                .filter(Venda::validSale)
                .collect(Collectors
                        .toList());
        out.println(linhasR.size());
        return linhasR;
    }
}
