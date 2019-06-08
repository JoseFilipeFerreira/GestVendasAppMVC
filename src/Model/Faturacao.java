package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Representa toda a faturação do sistema
 */
public class Faturacao implements IFaturacao, Serializable {
    private static final long serialVersionUID = 3685986535800590995L;
    private Map<String, IFatura> faturacao;

    /**
     * Construtor da Faturação
     * @param p Catálogo de Produtos ao qual a informação da faturação se vai referir
     */
    Faturacao(ICatProds p) {
        this.faturacao = p
                .productList()
                .stream()
                .map(IProduct::getId)
                .collect(Collectors
                        .toMap(Function.identity(), Fatura::new));
    }

    /**
     * Atualiza uma fatura com informação de uma venda
     * @param v Venda com a informação
     * @return Faturação atualizada
     */
    public Faturacao update(IVenda v) {
        this.faturacao.get(v.getCodProd()).update(v);
        return this;
    }

    /**
     * Calcula o total faturado
     * @return Faturação Total
     */
    public double faturacaoTotal() {
        return this.faturacao.values().stream()
                .mapToDouble(IFatura::getTotal)
                .sum();
    }

    /**
     * Calcula o total faturado por mês
     * @return Faturação mensal
     */
    public Map<Integer, Double> totalFaturado() {
        return this.faturacao.values()
                .stream()
                .flatMap(e -> e.getTotalMensal().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));
    }

    /**
     * Calcula o total faturado por mês numa filial
     * @param filial Filial que é desejada a faturação
     * @return Faturação mensal da filial
     */
    public Map<Integer, Double> totalFaturadoFilial(int filial) {
        return this.faturacao.values()
                .stream()
                .flatMap(e -> e.getTotalFilial(filial).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));
    }

    /**
     * Calcula o número de produtos que foram comprados
     * @return Número de produtos que foram comprados
     */
    public int produtosComprados() {
        return (int) this.faturacao
                .values()
                .stream()
                .filter(e -> !(e.getProdId().equals("")))
                .count();
    }

    /**
     * Calcula a lista ordenada de produtos que não foram comprados
     * @return Lista com IDs dos produtos não comprados
     */
    public List<String> listaProdutosNaoComprados() {
        return this.faturacao
                .values()
                .stream()
                .filter(e -> e.getProdId().equals(""))
                .map(IFatura::getProdId)
                .sorted()
                .collect(Collectors.toList());
    }
}
