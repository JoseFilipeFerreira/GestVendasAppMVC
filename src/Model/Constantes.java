package Model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Constantes implements Serializable {
    private int numeroFiliais;
    private int meses;
    private String prods;
    private String sales;
    private String clients;

    public Constantes(){
        this.numeroFiliais = 3;
        this.meses = 12;
    }

    public Constantes(String db) {
        try {
            this.numeroFiliais = 3;
            this.meses = 12;
            this.sales = "db/Vendas_1M.txt";
            this.prods = "db/Produtos.txt";
            this.clients = "db/Clientes.txt";
            Files
                    .readAllLines(Paths.get(db), StandardCharsets.UTF_8)
                    .stream()
                    .map(String::trim)
                    .filter(s -> s.contains(":"))
                    .forEach(this::parseLine);
        } catch (IOException | NumberFormatException ignored) {
            this.numeroFiliais = 3;
            this.meses = 12;
            this.sales = "db/Vendas_1M.txt";
            this.prods = "db/Produtos.txt";
            this.clients = "db/Clientes.txt";
        }
    }

    private void parseLine(String l){
        String[] pLine = l.split(":");
        String categoria = pLine[0];
        switch (categoria) {
            case "Filiais":
                this.numeroFiliais = Integer.parseInt(pLine[1]);
                break;
            case "Meses":
                this.meses = Integer.parseInt(pLine[1]);
                break;
            case "Produtos":
                this.prods = pLine[1];
                break;
            case "Clientes":
                this.clients = pLine[1];
                break;
            case "Vendas":
                this.sales = pLine[1];
                break;
        }
    }


    public int numeroFiliais(){
        return this.numeroFiliais;
    }

    public int meses() {
        return this.meses;
    }

    public boolean filialValida(int filial) {
        return filial > 0 && filial <= this.numeroFiliais;
    }

    public boolean mesValido(int mes)
    {
        return mes > 0 && mes <= this.meses;
    }

    public String getClients() {
        return clients;
    }

    public String getProds() {
        return prods;
    }

    public String getSales() {
        return sales;
    }
}
