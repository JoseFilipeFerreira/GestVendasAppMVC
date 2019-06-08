package Model;

public interface IVenda {

    /**
     * Calcula o valor total da Venda
     * @return Total da venda
     */
    double totalSale();

    /**
     * Calcula o valor total da Venda
     * @param c Constantes em uso
     * @return Total da venda
     */
    boolean validSale(IConstantes c);

    /**
     * Getter do mês da venda
     * @return Mês onde foi efetuada a venda
     */
    int getMonth();

    /**
     * Getter da filial onde foi feita a venda
     * @return Filial onde foi feita a venda
     */
    int getFilial();

    /**
     * Getter da quantidade de produtos vendidos
     * @return Quantidade vendida do produto
     */
    int getQuant();

    /**
     * Getter do codigo do produto
     * @return Codigo do produto
     */
    String getCodProd();

    /**
     * Getter do codigo do cliente
     * @return Codigo do cliente
     */
    String getCodCli();
}
