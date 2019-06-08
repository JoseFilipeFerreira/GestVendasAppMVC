package Model;

public interface ICatCli {
    /**
     * Verifica se um Cliente existe na base de dados
     * @param p ID do cliente a pesquisar
     * @return Se o cliente existe ou não
     */
    boolean exists(String p);

    /**
     * Calcula o número de clientes exixtentes
     * @return Numero de clientes
     */
    int howMany();
}
