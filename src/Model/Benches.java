package Model;
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

import static java.lang.System.*;

public class Benches {
    public static void main(String[] args) {
        Crono a = new Crono();
        CatCli cli = null;
        try {
            cli = new CatCli("db/Clientes.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CatProds prod = null;
        try {
            prod = new CatProds("db/Produtos.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CatProds finalProd = prod;
        CatCli finalCli = cli;

        out.println("BF 1M");
        a.start();
        List<String> b1 = readLinesWithBR("db/Vendas_1M.txt");
        List<IVenda> e1 = b1.parallelStream().map(Venda::new)
                .filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());


        out.println("BF 3M");
        a.start();
        List<String> b2 = readLinesWithBR("db/Vendas_3M.txt");
        List<IVenda> e2 = b2.parallelStream().map(Venda::new)
                .filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());

        out.println("BF 5M");
        a.start();
        List<String> b3 = readLinesWithBR("db/Vendas_5M.txt");
        List<IVenda> e3 = b2.parallelStream().map(Venda::new)
                .filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());

        out.println("F 1M");
        a.start();
        List<String> b4 = readWithFiles("db/Vendas_1M.txt");
        List<IVenda> e4 = b4.parallelStream().map(Venda::new).filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());

        out.println("F 3M");
        a.start();
        List<String> b5 = readWithFiles("db/Vendas_3M.txt");
        List<IVenda> e5 = b5.parallelStream().map(Venda::new).filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());

        out.println("F 5M");
        a.start();
        List<String> b6 = readWithFiles("db/Vendas_5M.txt");
        List<IVenda> e6 = b6.parallelStream().map(Venda::new).filter(o ->
                        finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());
   }

    public static List<String> readLinesWithBR(String fichtxt) {
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
        return linhas;

    }

    public static List<String> readWithFiles(String fichtxt) {
        List<String> linhas = new ArrayList<>();
        try {
            linhas = Files.readAllLines(Paths.get(fichtxt), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            out.println(e);
        }
        return linhas;
    }
}
