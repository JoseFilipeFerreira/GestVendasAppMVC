package Model;

import java.util.*;
import java.util.stream.Collectors;

class Filial {
    private Map<String, List<IVenda>> infoClients;
    private Map<String, List<IVenda>> infoProds;

    Filial() {
        this.infoClients = new HashMap<>();
        this.infoProds = new HashMap<>();
    }

    Filial update(IVenda v) {
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

    int getNClientes() {
        return this.infoClients.size();
    }

    List<String> getBestBuyers() {
        return this.infoClients
                .entrySet()
                .stream()
                .map(e -> new AbstractMap
                        .SimpleEntry<String, Double>(e.getKey(),
                        e.getValue()
                                .stream()
                                .mapToDouble(IVenda::totalSale)
                                .sum()))
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    Map.Entry<Integer, Integer> clientesVendasTotais(int mes) {
        List<Map.Entry<String, Integer>> b = this.infoClients.entrySet()
                .stream()
                .map(e -> {e.getValue().removeIf(a -> a.getMonth() != mes); return new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size());})
                .collect(Collectors.toList());
        return new AbstractMap.SimpleEntry<>(b.size(), b.stream().mapToInt(Map.Entry::getValue).sum());
    }

    Map<String, Integer> produtosCompradosPorCliente(String clientID) {
        List<IVenda> a = this.infoClients.get(clientID);
        if(a != null) {
            return a.stream().collect(Collectors.toMap(IVenda::getCodProd, IVenda::getQuant, Integer::sum));
        }
        return null;
    }

    Map<String, Map.Entry<Integer, Integer>> produtosMaisVendidos() {
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

    Map<String, Set<String>> maisDiversidadeDeProdutos() {
        return this.infoClients.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(IVenda::getCodProd)
                                .collect(Collectors.toSet())));
    }

    Map<String, Double> clientesQueMaisCompraram(String prodID) {
        List<IVenda> a = this.infoProds.get(prodID);
        if (a != null)
            return a.stream()
                    .collect(Collectors.toMap(IVenda::getCodCli, IVenda::totalSale, Double::sum));
        return null;
    }

    Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsCliente(String clientID, int mes) {
        List<IVenda> a = this.infoClients.get(clientID).stream().filter(e -> e.getMonth() == mes).collect(Collectors.toList());
        return getSetEntryEntry(a);
    }

    private Map.Entry<Set<String>, Map.Entry<Integer, Double>> getSetEntryEntry(List<IVenda> a) {
        List<Double> z = a.stream()
                .map(IVenda::totalSale)
                .collect(Collectors.toList());
        Map.Entry<Integer, Double> o = new AbstractMap.SimpleEntry<>(z.size(), z.stream().reduce(0.0, Double::sum));
        return new AbstractMap.SimpleEntry<>(
                a.stream()
                        .map(IVenda::getCodProd)
                        .collect(Collectors.toSet()),
                o);
    }

    Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsProduto(String productID, int mes) {
        List<IVenda> a = this.infoProds.get(productID).stream().filter(e -> e.getMonth() == mes).collect(Collectors.toList());
        return getSetEntryEntry(a);
    }

    Map<String, Double> faturacaoR(int mes) {
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
