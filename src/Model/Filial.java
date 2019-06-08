package Model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Filial implements IFilial, Serializable {
    private Map<String, List<IVenda>> infoClients;
    private Map<String, List<IVenda>> infoProds;

    /**
     * Construtor de uma Filial
     */
    Filial() {
        this.infoClients = new HashMap<>();
        this.infoProds = new HashMap<>();
    }

    /**
     * Atualiza a filial com informação de uma venda
     * @param v Venda com informação a atualizar
     * @return Filial atualizada
     */
    public Filial update(IVenda v) {
        List<IVenda> c = this.infoClients.get(v.getCodCli());
        List<IVenda> p = this.infoProds.get(v.getCodProd());

        try {
            c.add(v);
        }
        catch (NullPointerException ignored) {
            List<IVenda> a = new ArrayList<>();
            a.add(v);
            this.infoClients.put(v.getCodCli(), a);
        }

        try {
            p.add(v);
        }
        catch (NullPointerException ignored) {
            List<IVenda> a = new ArrayList<>();
            a.add(v);
            this.infoProds.put(v.getCodProd(), a);
        }
        return this;
    }

    /**
     * Calcula o top de Clientes por total faturado
     * @return Lista ordenada de IDs de clientes por total faturado
     */
    public List<String> getBestBuyers() {
        return this.infoClients
                .entrySet()
                .stream()
                .map(e -> new AbstractMap
                        .SimpleEntry<String, Double>(e.getKey(),
                        e.getValue()
                                .stream()
                                .mapToDouble(IVenda::totalSale)
                                .sum()))
                .sorted(Collections.reverseOrder(Comparator.comparingDouble(Map.Entry::getValue)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Calcula o numero de clientes e o total de vendas efetuadas num mês
     * @param mes Mês a calcular as estatisticas
     * @return Número de clientes e total de vendas efetuadas no mês em questão
     */
    public Map.Entry<Integer, Integer> clientesVendasTotais(int mes) {
        List<Map.Entry<String, Integer>> b = this.infoClients.entrySet()
                .stream()
                .map(e -> {e.getValue().removeIf(a -> a.getMonth() != mes); return new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size());})
                .collect(Collectors.toList());
        return new AbstractMap.SimpleEntry<>(b.size(), b.stream().mapToInt(Map.Entry::getValue).sum());
    }

    /**
     * Determina a lista de produtos comprados por um cliente e quantas unidades comprou
     * @param clientID ID do cliente em questão
     * @return Produtos comprados e respetiva faturação
     */
    public Map<String, Integer> produtosCompradosPorCliente(String clientID) {
        List<IVenda> a = this.infoClients.get(clientID);
        if(a != null) {
            return a.stream().collect(Collectors.toMap(IVenda::getCodProd, IVenda::getQuant, Integer::sum));
        }
        return null;
    }

    /**
     * Calcula a faturacao total e por quantos clientes foi comprado um produto
     * @return Faturação total e número total de clientes que compraram cada produto
     */
    public Map<String, Map.Entry<Integer, Integer>> produtosMaisVendidos() {
        return this.infoProds.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(),
                        new AbstractMap.SimpleEntry<>(e.getValue().stream()
                                .mapToInt(IVenda::getQuant)
                                .sum(),
                                (int) e.getValue().stream()
                                        .map(IVenda::getCodCli)
                                        .distinct()
                                        .count())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Determina os produtos comprados por cada cliente
     * @return Produtos comprados por cada cliente
     */
    public Map<String, Set<String>> maisDiversidadeDeProdutos() {
        return this.infoClients.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(IVenda::getCodProd)
                                .collect(Collectors.toSet())));
    }

    /**
     * Determina a faturação de quem comprou um dado produtd
     * @param prodID Id do produto em questão
     * @return Clientes e respetiva faturação referente ao produto
     */
    public Map<String, Double> clientesQueMaisCompraram(String prodID) {
        List<IVenda> a = this.infoProds.get(prodID);
        if (a != null)
            return a.stream()
                    .collect(Collectors.toMap(IVenda::getCodCli, IVenda::totalSale, Double::sum));
        return null;
    }

    /**
     * Determina a lista de produtos que um cliente comprou, quantas vezes
     * fizeram compras e quanto foi faturado no total num mes
     * @param clientID Cliente a procurar
     * @param mes Mes a pesquisar
     * @return Lista de produtos que foram comprados pelo cliente, quantas compras
     * fizeram e quanto foi faturado no mes
     */
    public Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsCliente(String clientID, int mes) {
        List<IVenda> a = this.infoClients.get(clientID).stream().filter(e -> e.getMonth() == mes).collect(Collectors.toList());
        return getSetEntryEntry(a);
    }

    private Map.Entry<Set<String>, Map.Entry<Integer, Double>> getSetEntryEntry(List<IVenda> a) {
        List<Double> z = a.stream()
                .map(IVenda::totalSale)
                .collect(Collectors.toList());
        Map.Entry<Integer, Double> o = new AbstractMap.SimpleEntry<>(z.size(), z.stream()
                .reduce(0.0, Double::sum));
        return new AbstractMap.SimpleEntry<>(
                a.stream()
                        .map(IVenda::getCodProd)
                        .collect(Collectors.toSet()),
                o);
    }

    /**
     * Determina a lista de clientes que comprou um dado produto, quantas vezes
     * foi comprado e quanto foi faturado no total num mes
     * @param productID Produto a procurar
     * @param mes Mes a pesquisar
     * @return Lista de clientes que comprou o produto, quantas vezes foi comprado
     * e quanto foi faturado no mes
     */
    public Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsProduto(String productID, int mes) {
        List<IVenda> a = this.infoProds.get(productID).stream().filter(e -> e.getMonth() == mes).collect(Collectors.toList());
        return getSetEntryEntry(a);
    }

    /**
     * Calcula o total faturado por produto por mes
     * @param mes Mes em questao
     * @return Faturacao total do mes por produto
     */
    public Map<String, Double> faturacaoR(int mes) {
        return this.infoProds.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue()
                        .stream()
                        .filter(a -> a.getMonth() == mes)
                        .mapToDouble(IVenda::totalSale)
                        .sum()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
