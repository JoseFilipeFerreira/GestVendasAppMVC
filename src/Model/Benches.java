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
        List<String> b = readLinesWithBR("db/Vendas_3M.txt");
        List<IVenda> e = b.parallelStream().map(Venda::new)
                .filter(o -> o.validSale()
                        && finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());
/*
        out.println("BF 3M");
        a.start();
        List<String> c = readLinesWithBR("db/Vendas_3M.txt");
        List<IVenda> e = c.stream().map(Venda::new).collect(Collectors.toList());
        a.stop();
        out.println(a.toString());
         */
/*
        out.println("BF 5M");
        a.start();
        List<String> d = readLinesWithBR("db/Vendas_5M.txt");
        List<IVenda> e = d.stream().map(Venda::new).collect(Collectors.toList());
        a.stop();
        out.println(a.toString());
 */
/*
        out.println("F 1M");
        a.start();
        List<String> b = readWithFiles("db/Vendas_3M.txt");
        List<IVenda> e = b.parallelStream().map(Venda::new).filter(o -> o.validSale()
                        && finalProd.exists(o.getCodProd())
                        && finalCli.exists(o.getCodCli()))
                .collect(Collectors
                        .toList());
        a.stop();
        out.println(a.toString());
*/
/*
        out.println("F 3M");
        a.start();
        List<String> c = readWithFiles("db/Vendas_3M.txt");
        List<IVenda> e = c.stream().map(Venda::new).collect(Collectors.toList());
        a.stop();
        out.println(a.toString());

 */
/*
        out.println("F 5M");
        a.start();
        List<String> d = readWithFiles("db/Vendas_5M.txt");
        List<IVenda> e = d.stream().map(Venda::new).collect(Collectors.toList());
        a.stop();
        out.println(a.toString());
 */
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
