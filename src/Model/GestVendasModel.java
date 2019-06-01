package Model;

import Exceptions.InvalidFilialException;
import Exceptions.MesInvalidoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.List;
import java.nio.file.*;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class GestVendasModel {
    private ICatCli catCli;
    private ICatProds catProds;
    private List<IVenda> vendas;
    private Faturacao faturacao;
    private Filial[] filiais;
    private Constantes constantes;

    public GestVendasModel(String clients, String products, String sales) {
        this.catCli = new CatCli(clients);
        this.catProds = new CatProds(products);
        this.constantes = new Constantes();
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

        faturacao = new Faturacao(catProds);
        this.filiais = new Filial[constantes.numeroFiliais()];
        for (int i = 0; i < constantes.numeroFiliais(); i++) {
            this.filiais[i] = new Filial();
        }
        this.vendas.forEach(e -> {this.faturacao.update(e); this.filiais[e.getFilial()-1].update(e);});
        out.println(faturacao.totalFaturado());
    }

    public long getVendasDadas() {
        return this
                .vendas
                .stream()
                .filter(e -> e.totalSale() == 0)
                .count();
    }

    public int clientesPorFilial(int filial) throws InvalidFilialException {
        if(this.constantes.filialValida(filial))
            throw new InvalidFilialException();
        return this.filiais[filial-1].getNClientes();
    }

    //query 7
    public List<String> melhoresClientesPorFilial(int filial) throws InvalidFilialException {
        if(this.constantes.filialValida(filial))
            throw new InvalidFilialException();
        return this.filiais[filial-1].getBestBuyers();
    }

    //query 1
    public List<String> listaDeProdutosNaoComprados() {
        return this.faturacao.listaProdutosNaoComprados();
    }

    //query 2 vendas, clientes
    public Map.Entry<Integer, Integer> clientesVendasTotais(int mes) throws MesInvalidoException {
        if(!constantes.mesValido(mes))
            throw new MesInvalidoException();
        List<IVenda> a = this.vendas.stream().filter(e -> e.getMonth() != mes).collect(Collectors.toList());
        return new AbstractMap.SimpleEntry<>(a.size(), (int) a
                .stream()
                .map(IVenda::getCodCli)
                .distinct()
                .count());
    }

    public Map.Entry<Integer, Integer> clientesVendasTotais(int filial, int mes) throws InvalidFilialException, MesInvalidoException {
        if(!constantes.filialValida(filial))
            throw new InvalidFilialException();
        if(!constantes.mesValido(mes))
            throw new MesInvalidoException();
        return this.filiais[filial-1].clientesVendasTotais(mes);
    }
}
