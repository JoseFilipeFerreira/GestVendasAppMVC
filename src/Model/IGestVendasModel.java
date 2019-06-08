package Model;

import Exceptions.InvalidClientException;
import Exceptions.InvalidFilialException;
import Exceptions.InvalidProductExecption;
import Exceptions.MesInvalidoException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IGestVendasModel {
    /**
     * @return Tempo que demorou a ser inicializado o model
     */
    String time();

    /**
     * @return Número de Filiais atual
     */
    int numeroFiliais();

    /**
     * @return Número de Meses
     */
    int meses();

    /**
     * Calcula o numero de vendas a 0.0
     * @return Numero de vendas a 0
     */
    int getVendasDadas();

    /**
     * Calcula o numero de vendas do ficheiro que sao invalidas
     * @return Numero de vendas invalidas
     */
    int vendasInvalidas();

    /**
     * Getter do caminho do ficheiro de vendas
     * @return Caminho do ficheiro de vendas
     */
    String getVendasFile();

    /**
     * Getter do caminho do ficheiro de Produtos
     * @return Caminho do ficheiro de Produtos
     */
    String getProductFile();

    /**
     * Getter do caminho do ficheiro de Clientes
     * @return Caminho do ficheiro de Clientes
     */
    String getClientFile();

    /**
     * Calcula o numero total de produtos lidos
     * @return Total de produtos lidos
     */
    int getTotalProdutos();

    /**
     * Calcula o numero de produtos comprados e nao comprados
     * @return Numero de produtos comprados e nao comprados
     */
    Map.Entry<Integer, Integer> getProdutosComprados();

    /**
     * Calcula o numero de clientes que fizeram e nao fizeram compras
     * @return Numero de clientes que fizeram e nao fizeram compras
     */
    Map.Entry<Integer, Integer> getClientesCompradores();

    /**
     * Calcula o total faturado em todas as vendas efetuadas
     * @return Total faturado
     */
    double totalFaturado();

    //1.2

    /**
     * Calcula o numero de vendas efetuadas por mes
     * @return Total de vendas feitas por mes
     */
    Map<Integer, Integer> vendasMensais();

    /**
     * Calcula o total faturado por mes
     * @return Faturacao total por mes
     */
    Map<Integer, Double> faturacaoTotal();

    /**
     * Calcula o total faturado por mes numa filial
     * @param filial Filial da qual pretendemos a faturacao
     * @return Faturacao por mes da filial
     * @throws InvalidFilialException Exceção se a filial for inválida
     */
    Map<Integer, Double> faturacaoPorFilial(int filial) throws InvalidFilialException;

    /**
     * Calcula o numero de clientes que fizeram compras numa filial por mes
     * @param filial Filial da qual se deseja a informacao
     * @return Numero de clientes que compraram na filial mes a mes
     * @throws InvalidFilialException Exceção se a filial for inválida
     */
    Map<Integer, Integer> clientesPorFilial(int filial) throws InvalidFilialException;

    //query 1
    /**
     * Calcula a lista ordenada dos Produtos que ninguem comprou
     * @return Lista de Produtos nao comprados
     */
    List<String> listaDeProdutosNaoComprados();

    //query 2 vendas, clientes

    /**
     * Calcula o total de vendas efetuadas e o numero de clientes que fizeram compras num determinado
     * mes
     * @param mes Mes referente as vendas
     * @return Total de vendas efetuadas e numero de clientes distintos que fizeram compras
     * @throws MesInvalidoException Exceção se o mês for inválido
     */
    Map.Entry<Integer, Integer> clientesVendasTotais(int mes) throws MesInvalidoException;

    /**
     * Calcula o total de vendas efetuadas e o numero de clientes que fizeram compras num
     * determindado mes e numa certa filial
     * @param filial Filial onde foram feitas as compras
     * @param mes Mes referente as vendas
     * @return Total de vendas efetuadas e numero de clientes distintos que fizeram compras
     * @throws InvalidFilialException Exceção se a filial for inválida
     * @throws MesInvalidoException Exceção se o mês for inválido
     */
    Map.Entry<Integer, Integer> clientesVendasTotais(int filial, int mes) throws InvalidFilialException, MesInvalidoException;

    //query 3 (produtos comprados, n compras, quanto gastou)
    /**
     * Calcula o numero de produto diferentes comprados, quantas compras fez e quando gastou no total
     * de um dado cliente
     * @param clientID ID do cliente a procurar
     * @param mes Mes onde foram efetuadas as compras
     * @return Numero total de produtos distintos, quantas compras e quanto foi gasto pelo cliente num mes
     * @throws MesInvalidoException Exceção se o mês for inválido
     * @throws InvalidClientException Exceção se o cliente for inválido
     */
    Map.Entry<Integer, Map.Entry<Integer,Double>> statsClientes(String clientID, int mes) throws MesInvalidoException, InvalidClientException;

     /**
     * Calcula o numero de clientes diferentes que compraram um determinado produto, quantas vezes foi comprado e quanto
      * foi faturado num mes
     * de um dado cliente
     * @param productID ID do produto a procurar
     * @param mes Mes onde foram efetuadas as compras
     * @return Numero total de clientes distintos que compraram o produto, quantas vezes foi comprado e quanto foi gasto
     * @throws MesInvalidoException Exceção se o mês for invalido
     * @throws InvalidProductExecption Exceção se o produto for inválido
     */
    Map.Entry<Integer, Map.Entry<Integer,Double>> statsProdutos(String productID, int mes) throws MesInvalidoException, InvalidProductExecption;

    //Query 5
    /**
     * Determina a lista ordenada de produtos e quantas vezes um dado cliente
     * comprou
     * @param clientID Cliente do qual pretendemos a lista
     * @return Lista de produtos comprados por um cliente
     * @throws InvalidClientException Exceção se o Cliente não existir
     */
    List<Map.Entry<String, Integer>> produtosPorCliente(String clientID) throws InvalidClientException;

    //query 6

    /**
     * Determina a lista ordenada dos produtos mais vendidos durante o ano,
     * bem como quem os comprou
     * @return Lista ordenada dos produtos mais vendidos
     */
    List<Map.Entry<String, Integer>> produtosMaisVendidos();

    //query 7

    /**
     * Determina o top de clientes da filial
     * @param filial Filial em questao
     * @return Lista com IDs do top de clientes
     * @throws InvalidFilialException Exceção se a filial for inválida
     */
    List<String> melhoresClientesPorFilial(int filial) throws InvalidFilialException;

    //query 8

    /**
     * Determina a lista de clientes que compraram mais produtos diferentes
     * @return Lista de clientes ordenada pelo numero de produtos distintos
     * comprados
     */
    List<String> clientesComMaisDiversidade();

    //query 9

    /**
     * Determina a lista de clientes que mais compraram um dado produto e quanto
     * gastaram no total
     * @param prodID Codigo do Produto em questao
     * @return Lista ordenada dos clientes que mais compraram o produto
     * @throws InvalidProductExecption Exceção se o Produto não existir
     */
    List<Map.Entry<String,Double>> clientesQueMaisCompraram(String prodID) throws InvalidProductExecption;

    //query 10

    /**
     * Determina a faturacao por produto total num mes numa filial
     * @param mes Mes da faturacao
     * @param filial Filial referente
     * @return Lista de produtos comprados na filial e respetiva faturacao
     * @throws InvalidFilialException Exceção se a filial não existir
     * @throws MesInvalidoException Exceção se o mês for inválido
     */
    Map<String, Double> faturacaoProd(int mes, int filial) throws InvalidFilialException, MesInvalidoException;

    /**
     * Guarda o estado atual do Model para um dado ficheiro
     * @param fName Caminho do ficheiro a guardar
     * @throws IOException Exceção de erro a escrever para o ficheiro
     */
    void save(String fName) throws IOException;
}
