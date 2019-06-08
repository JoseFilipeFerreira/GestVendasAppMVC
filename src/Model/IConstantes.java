package Model;

public interface IConstantes {
    /**
     * @return Número atual de filiais
     */
    int numeroFiliais();

    /**
     * @return Número atual de meses
     */
    int meses();

    /**
     * Verifica se a filial é valida
     * @param filial Filial a validar
     * @return Se a filial é valida ou não
     */
    boolean filialValida(int filial);

    /**
     * Verifica se um mês é valido
     * @param mes Mês a validar
     * @return Se o mês é valido ou não
     */
    boolean mesValido(int mes);

    /**
     * @return Caminho do ficheiro de clientes
     */
    String getClients();

    /**
     *
     * @return Caminho do ficheiro de produtos
     */
    String getProds();

    /**
     * @return Caminho do ficheiro de produtos
     */
    String getSales();
}
