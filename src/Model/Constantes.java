package Model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Constantes implements Serializable, IConstantes{
    private static final long serialVersionUID = -7078102694050931882L;
    private int numeroFiliais;
    private int meses;
    private String prods;
    private String sales;
    private String clients;

    /**
     * Construtor não parameterizado das Constantes
     */
    public Constantes(){
        this.numeroFiliais = 3;
        this.meses = 12;
        this.sales = "db/Vendas_1M.txt";
        this.prods = "db/Produtos.txt";
        this.clients = "db/Clientes.txt";
    }

    /**
     * Construtor da classe Constantes com informação de um ficheiro de configs
     * @param db Caminho do ficheiro de configs
     */
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

    /**
     * @return Número atual de filiais
     */
    public int numeroFiliais(){
        return this.numeroFiliais;
    }

    /**
     * @return Número atual de meses
     */
    public int meses() {
        return this.meses;
    }

    /**
     * Verifica se a filial é valida
     * @param filial Filial a validar
     * @return Se a filial é valida ou não
     */
    public boolean filialValida(int filial) {
        return filial > 0 && filial <= this.numeroFiliais;
    }

    /**
     * Verifica se um mês é valido
     * @param mes Mês a validar
     * @return Se o mês é valido ou não
     */
    public boolean mesValido(int mes)
    {
        return mes > 0 && mes <= this.meses;
    }

    /**
     * @return Caminho do ficheiro de clientes
     */
    public String getClients() {
        return clients;
    }

    /**
     *
     * @return Caminho do ficheiro de produtos
     */
    public String getProds() {
        return prods;
    }

    /**
     * @return Caminho do ficheiro de produtos
     */
    public String getSales() {
        return sales;
    }
}
