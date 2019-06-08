package Utils;

public interface ITerminal {
    /**
     * Atualiza o tamanho do terminal
     */
    void update();

    /**
     * obter numero de colunas do terminal
     * @return número de colunas do terminal
     */
    int getColumns();

    /**
     * obter numero de linhas do terminal
     * @return número de linhas do terminal
     */
    int getLines();

    String toString();
}
