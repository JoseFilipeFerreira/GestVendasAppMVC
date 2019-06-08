package View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public interface IMenu {

    enum MenuInd {
        Initial,
        Categories,
        Binary,
        Save,
        Load,
        Static,
        Dynamic,
        Q1,
        Q2,
        Q3,
        Q4,
        Q5,
        Q6,
        Q7,
        Q8,
        Q9,
        Q10,
        Q1_1,
        Q1_2
    }

    /**
     * Get if menu is running
     * @return if the menu is running
     */
    boolean getRun();

    /**
     * Método para obtert o menu em que se está no momento
     * @return O MenuInd em que se está
     */
    MenuInd getMenu();

    /**
     * Método para obter o input inteiro do utilizador
     * @param error Texto de erro a mostrar
     * @param text Texto a mostrar no pedido de input
     * @return Devolve o inteiro lido
     * @throws InputMismatchException Caso o valor introduzido não seja um inteiro.
     */
    int getInputInt(String error, String text) throws InputMismatchException;

    /**
     * Método para obter uma string do utilizador
     * @param error Texto de erro a mostrar
     * @param text Texto a mostrar no pedido de input
     * @return Devolve a string lida
     */
    String getInputString(String error, String text);

    /**
     * Lê o input do utilizador e altera o menu onde se está
     * @return this
     */
    Menu parser();

    /**
     * Recua no menu
     * @return this
     */
    Menu back();

    /**
     * Método para mostrar a Querie 1
     * @param notBought Lista de todos os produtos não comprados
     * @param time tempo que demorou a querie
     */
    void showQ1(List<String> notBought, String time);

    /**
     * Método para mostrar a Querie 2
     * @param sales Vendas totais e clientes totais na key e no value respetivamente
     * @param mesSales Mes de vendas a mostrar
     * @param filialSales filial a mostrar (0 se for todas)
     * @param time tempo que demorou a querie
     */
    void showQ2(Map.Entry<Integer, Integer> sales, int mesSales, int filialSales, String time);

    /**
     * Método para mostrar a Querie 3
     * @param client O cliente que foi pesquisado
     * @param nMes O número de meses calculados
     * @param cliStats A tabela de valores a apresentar
     * @param time tempo que demorou a querie
     */
    void showQ3(String client, int nMes, List<List<String>> cliStats, String time);

    /**
     * Método para mostrar a Querie 4
     * @param produto O produto que foi pesquisado
     * @param mes O mes que foi pesquisado
     * @param prodStats As estatisticas do produto a apresentar
     * @param time tempo que demorou a querie
     */
    void showQ4(String produto, int mes, Map.Entry<Integer, Map.Entry<Integer, Double>> prodStats, String time);

    /**
     * Método para mostrar a Querie 5
     * @param prodsCli Produtos mais comprados pelo cliente
     * @param client Cliente que foi pesquisado
     * @param time tempo que demorou a querie
     */
    void showQ5(List<String> prodsCli, String client, String time);

    /**
     * Método para mostrar a Querie 6
     * @param prodsM Dados a apresentar na tabela
     * @param time tempo que demorou a querie
     */
    void showQ6(List<List<String>> prodsM, String time);

    /**
     * Método para mostrar a Querie 7
     * @param clis Lista ordenada dos melhores clientes
     * @param time tempo que demorou a querie
     */
    void showQ7(List<String> clis, String time);

    /**
     * Método para mostrar a Querie 8
     * @param clis Lista de clientes a apresentar
     * @param time Tempo que demorou a querie
     */
    void showQ8(List<String> clis, String time);

    /**
     * Método para mostrar a Querie 9
     * @param clis Tabela de dados a apresentar
     * @param time tempo que demorou a querie
     */
    void showQ9(List<List<String>> clis, String time);

    /**
     * Método para mostrar a Querie 10
     * @param fatTotal Todos os produtos a mostrar
     * @param mes mes pesquisado
     * @param filial filial pesquisada
     * @param time tempo que demorou a querie
     */
    void showQ10(List<Map.Entry<String, Double>> fatTotal, int mes, int filial, String time);

    /**
     * Método para mostrar a Querie 1.1
     * @param val Valores a mostrar
     * @param time tempo que demorou a querie
     */
    void showQ11(List<List<String>> val, String time);

    /**
     * Método para mostrar a Querie 1.2
     * @param time tempo que demorou a querie
     * @param monthly estatisticas mensais
     * @param nMeses numero de meses
     * @param nFiliais numero de filiais
     */
    void showQ12(String time, List<List<String>> monthly, int nMeses, int nFiliais);

    /**
     * Método para mostrar o save do Object Stream
     * @param fName Nome do ficheiro
     * @param time tempo que demorou a querie
     */
    void showSave(String fName, String time);

    /**
     * Método para mostrar o load do Object Stream
     * @param fName Nome do ficheiro
     * @param time tempo que demorou a querie
     */
    void showLoad(String fName, String time);


    String toString();
}
