package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Faturacao {
    private Map<String, IFatura> faturacao;

    public Faturacao() {
        this.faturacao = new HashMap<>();
    }

    Faturacao(ICatProds p) {
        this.faturacao = p
                .productList()
                .stream()
                .map(IProduct::getId)
                .collect(Collectors
                        .toMap(Function.identity(), Fatura::new));
    }

    Faturacao syncWithSales(List<IVenda> l) {
        this.faturacao = l
                .stream()
                .map(e -> this.faturacao
                        .get(e.getCodProd())
                        .update(e))
                .collect(Collectors.toMap(IFatura::getProdId, Function.identity(), (e1, e2) -> e1));
        return this;
    }

    Faturacao update(IVenda v) {
        this.faturacao.get(v.getCodProd()).update(v);
        return this;
    }

    double totalFaturado() {
        return this
                .faturacao
                .values()
                .stream()
                .mapToDouble(IFatura::getTotal)
                .sum();
    }

    public int produtosComprados() {
        return (int) this.faturacao
                .values()
                .stream()
                .filter(e -> !(e.getProdId().equals("")))
                .count();
    }

    public int produtosNaoComprados() {
        return (int) this.faturacao
                .values()
                .stream()
                .filter(e -> e.getProdId().equals(""))
                .count();
    }

    List<String> listaProdutosNaoComprados() {
        return this.faturacao
                .values()
                .stream()
                .filter(e -> e.getProdId().equals(""))
                .map(IFatura::getProdId)
                .sorted()
                .collect(Collectors.toList());
    }
}
