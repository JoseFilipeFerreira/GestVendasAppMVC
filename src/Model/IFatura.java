package Model;

import java.util.Map;

public interface IFatura {
    /**
     * Atualiza uma fatura com a informcação de uma venda
     * @param a Venda a com informação a adicionar à fatura
     * @return Fatura atualizada
     */
    IFatura update(IVenda a);

    /**
     * Calcula o total de uma fatura
     * @return Total da fatura
     */
    double getTotal();

    /**
     * Calcula o total de uma fatura numa filial
     * @param filial Filial a procurar
     * @return Faturação por mês numa filial da respetiva fatura
     */
    Map<Integer, Double> getTotalFilial(int filial);

    /**
     * Calcula a faturação total mensal de uma fatura
     * @return Total faturado por mês na fatura em todas as filiais
     */
    Map<Integer, Double> getTotalMensal();

    /**
     * Determina de um produto foi comprado ou não
     * @return Se o produto foi comprado ou não
     */
    boolean isFoiComprado();

    /**
     * Getter do ID do produto ao que a fatura se refere
     * @return ID do produto
     */
    String getProdId();
}
