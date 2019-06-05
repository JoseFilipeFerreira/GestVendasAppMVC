package Model;

import Model.*;
import Utils.Crono;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;
public class read {
    public static void main(String[] args) {
        Crono crono = new Crono();
        crono.start();
        try {
            GestVendasModel a = new GestVendasModel("db/Clientes.txt", "db/Produtos.txt", "db/Vendas_5M.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(crono.stop());

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
        out.println((int) clientes.stream().map(Client::new).filter(Client::verifyClient).count());
        out.println((int) produtos.stream().map(Product::new).filter(Product::verifyProduct).count());
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
