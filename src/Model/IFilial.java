package Model;

import java.util.*;
import java.util.stream.Collectors;

public interface IFilial {
    /**
     * Atualiza a filial com informação de uma venda
     * @param v Venda com informação a atualizar
     * @return Filial atualizada
     */
    IFilial update(IVenda v);

    /**
     * Calcula o top de Clientes por total faturado
     * @return Lista ordenada de IDs de clientes por total faturado
     */
    List<String> getBestBuyers();

    /**
     * Calcula o numero de clientes e o total de vendas efetuadas num mês
     * @param mes Mês a calcular as estatisticas
     * @return Número de clientes e total de vendas efetuadas no mês em questão
     */
    Map.Entry<Integer, Integer> clientesVendasTotais(int mes);

    /**
     * Determina a lista de produtos comprados por um cliente e quantas unidades comprou
     * @param clientID ID do cliente em questão
     * @return Produtos comprados e respetiva faturação
     */
    Map<String, Integer> produtosCompradosPorCliente(String clientID);

    /**
     * Calcula a faturacao total e por quantos clientes foi comprado um produto
     * @return Faturação total e número total de clientes que compraram cada produto
     */
    Map<String, Map.Entry<Integer, Integer>> produtosMaisVendidos();

    /**
     * Determina os produtos comprados por cada cliente
     * @return Produtos comprados por cada cliente
     */
    Map<String, Set<String>> maisDiversidadeDeProdutos();

    /**
     * Determina a faturação de quem comprou um dado produtd
     * @param prodID Id do produto em questão
     * @return Clientes e respetiva faturação referente ao produto
     */
    Map<String, Double> clientesQueMaisCompraram(String prodID);

    /**
     * Determina a lista de produtos que um cliente comprou, quantas vezes
     * fizeram compras e quanto foi faturado no total num mes
     * @param clientID Cliente a procurar
     * @param mes Mes a pesquisar
     * @return Lista de produtos que foram comprados pelo cliente, quantas compras
     * fizeram e quanto foi faturado no mes
     */
    Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsCliente(String clientID, int mes);

    /**
     * Determina a lista de clientes que comprou um dado produto, quantas vezes
     * foi comprado e quanto foi faturado no total num mes
     * @param productID Produto a procurar
     * @param mes Mes a pesquisar
     * @return Lista de clientes que comprou o produto, quantas vezes foi comprado
     * e quanto foi faturado no mes
     */
    Map.Entry<Set<String>, Map.Entry<Integer, Double>> statsProduto(String productID, int mes);

    /**
     * Calcula o total faturado por produto por mes
     * @param mes Mes em questao
     * @return Faturacao total do mes por produto
     */
    Map<String, Double> faturacaoR(int mes);
}
