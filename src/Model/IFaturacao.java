package Model;

import java.util.List;
import java.util.Map;

public interface IFaturacao {
    /**
     * Atualiza uma fatura com informação de uma venda
     * @param v Venda com a informação
     * @return Faturação atualizada
     */
    Faturacao update(IVenda v);

    /**
     * Calcula o total faturado
     * @return Faturação Total
     */
    double faturacaoTotal();

    /**
     * Calcula o total faturado por mês
     * @return Faturação mensal
     */
    Map<Integer, Double> totalFaturado();

    /**
     * Calcula o total faturado por mês numa filial
     * @param filial Filial que é desejada a faturação
     * @return Faturação mensal da filial
     */
    Map<Integer, Double> totalFaturadoFilial(int filial);

    /**
     * Calcula o número de produtos que foram comprados
     * @return Número de produtos que foram comprados
     */
    int produtosComprados();

    /**
     * Calcula a lista ordenada de produtos que não foram comprados
     * @return Lista com IDs dos produtos não comprados
     */
    List<String> listaProdutosNaoComprados();
}
