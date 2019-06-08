package Controller;

import Exceptions.InvalidClientException;
import Exceptions.InvalidFilialException;
import Exceptions.InvalidProductExecption;
import Exceptions.MesInvalidoException;
import Model.GestVendasModel;
import Model.IGestVendasModel;
import Utils.Crono;
import Utils.ICrono;
import View.IMenu;
import View.Menu;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Controller implements IController{
    private final IMenu menu;
    private IGestVendasModel model;
    private final ICrono crono;

    /**
     * Construtor para a classe Controller
     * @param view instancia da View
     * @param model instancia do Model
     */
    public Controller(Menu view, GestVendasModel model) {
        this.menu = view;
        this.model = model;
        this.crono = new Crono();
    }

    /**
     * Método para começar o Controller
     */
    public void start(){
        String error = "";
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Q1:
                    this.crono.start();
                    List <String> prodsNComprados = this.model.listaDeProdutosNaoComprados();
                    this.crono.stop();
                    this.menu.showQ1(prodsNComprados, this.crono.toString());
                    
                    error = "";
                    break;

                case Q2:
                    try {
                        int mesSales = this.menu.getInputInt(
                                error,
                                "Mês a pesquisar [1-" + this.model.meses() + "]:");
                        int filialSales = this.menu.getInputInt(
                                error,
                                "Filial a pesquisar [1-" + this.model.numeroFiliais() + "] [0 para o total]:");
                        Map.Entry<Integer, Integer> tSales;
                        this.crono.start();
                        if(filialSales == 0)
                            tSales = this.model.clientesVendasTotais(mesSales);
                        else
                            tSales = this.model.clientesVendasTotais(filialSales, mesSales);
                        this.crono.stop();
                        this.menu.showQ2(tSales, mesSales, filialSales, this.crono.toString());
                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e) { error = "Input is not a number"; }
                    catch (MesInvalidoException e) {error = "Mes Inválido"; }
                    catch (InvalidFilialException e) {error = "Filial Inválida"; }
                    break;

                case Q3:
                    try {
                        String cliSStats = this.menu.getInputString(error, "Cliente a pesquisar:");
                        List<Map.Entry<Integer, Map.Entry<Integer, Double>>> cliStats = new ArrayList<>();
                        this.crono.start();
                        for(int mes = 1; mes <= this.model.meses(); mes++)
                             cliStats.add(this.model.statsClientes(cliSStats, mes));
                        this.crono.stop();

                        List<String> l1 = new ArrayList<>();
                        List<String> l2 = new ArrayList<>();
                        List<String> l3 = new ArrayList<>();
                        for(int mes = 0; mes < this.model.meses(); mes++){
                            l1.add(cliStats.get(mes).getKey().toString());
                            l2.add(cliStats.get(mes).getValue().getKey().toString());
                            l3.add(String.format("%.2f", cliStats.get(mes).getValue().getValue()));
                        }

                        List<List<String>> tab = new ArrayList<>();
                        tab.add(l1);
                        tab.add(l2);
                        tab.add(l3);

                        this.menu.showQ3(cliSStats, this.model.meses(), tab, this.crono.toString());

                        this.menu.back();
                        error = "";

                    }
                    catch (InvalidClientException e) { error = "Cliente Inválido"; }
                    catch (MesInvalidoException | InputMismatchException e) { error = "Mês Inválido"; }
                    break;

                case Q4:
                    try  {
                        String prodSStats = this.menu.getInputString(error, "Produto a pesquisar:");
                        int mesSStats = this.menu.getInputInt(
                                error,
                                "Mês a pesquisar [1-" + this.model.meses() + "]:");
                        this.crono.start();
                        Map.Entry<Integer, Map.Entry<Integer, Double>> prodStats = this.model.statsProdutos(prodSStats, mesSStats);
                        this.crono.stop();

                        this.menu.showQ4(prodSStats, mesSStats, prodStats, this.crono.toString());

                        this.menu.back();
                        error = "";

                    }
                    catch (InvalidProductExecption e) { error = "Produto Inválido"; }
                    catch (MesInvalidoException | InputMismatchException e) { error = "Mês Inválido"; }
                    break;

                case Q5:
                    try {
                        String clientProd = this.menu.getInputString(error, "Cliente a pesquisar:");
                        this.crono.start();
                        List<Map.Entry<String, Integer>> listProdsCli = this.model.produtosPorCliente(clientProd);
                        this.crono.stop();

                        this.menu.showQ5(
                                listProdsCli
                                        .stream()
                                        .map(x -> x.getKey() + " [" + x.getValue() + "]")
                                        .collect(Collectors.toList()),
                                clientProd,
                                this.crono.toString());

                        error = "";
                    }
                    catch (InvalidClientException e) { error = "Invalid Client"; }
                    break;

                case Q6:
                    try {
                        int nProdVend = this.menu.getInputInt(error, "Número de Produtos a Pesquisar:");
                        this.crono.start();
                        List<Map.Entry<String, Integer>> prods = this.model.produtosMaisVendidos();
                        this.crono.stop();
                        this.menu.showQ6(
                                prods
                                        .stream()
                                        .map(e -> {
                                            List<String> a = new ArrayList<>();
                                            a.add(e.getKey());
                                            a.add(e.getValue().toString());
                                            return a;})
                                        .limit(nProdVend)
                                        .collect(Collectors.toList()),
                                this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e){ error = "Invalid Input"; }
                    break;

                case Q7:
                    try{
                        int filN = this.menu.getInputInt(
                                error,
                                "Inserir Filial [1-" + this.model.numeroFiliais() + "]:");
                        this.crono.start();
                        List <String> clis = this.model.melhoresClientesPorFilial(filN);
                        this.crono.stop();
                        this.menu.showQ7(clis, this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e) { error = "Input is not a number"; }
                    catch (InvalidFilialException e) { error = "Número de Filial Inválido"; }
                    break;

                case Q8:
                    try {
                        int nCliSearch = this.menu.getInputInt(error, "Número de Clientes a pesquisar:");
                        this.crono.start();
                        List<String> clisDiverse = this.model.clientesComMaisDiversidade();
                        this.crono.stop();
                        this.menu.showQ8(
                                clisDiverse
                                        .stream()
                                        .limit(nCliSearch)
                                        .collect(Collectors.toList()),
                                this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e){ error = "Invalid Input"; }
                    break;

                case Q9:
                    try{
                        String prodBougth = this.menu.getInputString(error, "Produto a pesquisar:");
                        int nProdBougth = this.menu.getInputInt(error, "Número de clientes a pesquisar:");
                        this.crono.start();
                        List<Map.Entry<String,Double>> highestBuyer = this.model.clientesQueMaisCompraram(prodBougth);
                        this.crono.stop();
                        this.menu.showQ9(
                                highestBuyer
                                        .stream()
                                        .map(e -> {
                                            List<String> a = new ArrayList<>();
                                            a.add(e.getKey());
                                            a.add(String.format("%.2f", e.getValue()));
                                            return a;})
                                        .limit(nProdBougth)
                                        .collect(Collectors.toList()),
                                this.crono.toString());
                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e) {error = "Invalid input";}
                    catch (InvalidProductExecption e) {error = "Produto Inválido";}
                    break;

                case Q10:
                    try{
                        int mes = this.menu.getInputInt(error, "Mês a pesquisar:");
                        int filial = this.menu.getInputInt(
                                error,
                                "Filial a pesquisar [1-" + this.model.numeroFiliais() + "]:");

                        this.crono.start();
                        Map<String, Double> fatTotal = this.model.faturacaoProd(mes, filial);
                        this.crono.stop();

                        this.menu.showQ10(fatTotal, mes, filial, this.crono.toString());

                        error = "";

                    }
                    catch (InvalidFilialException e) { error = "Filial Inválida"; }
                    catch (MesInvalidoException e)     { error = "Mês inválido"; }
                    break;

                case Q1_1:
                    this.crono.start();
                    String fileVendas = this.model.getVendasFile();
                    String fileProdutos = this.model.getProductFile();
                    String fileCli = this.model.getClientFile();
                    long vendas = this.model.getVendasDadas();
                    int vendasInvalidas = this.model.vendasInvalidas();
                    int totalProd = this.model.getTotalProdutos();
                    double totalFat = this.model.totalFaturado();
                    Map.Entry<Integer, Integer> prodsComprados = this.model.getProdutosComprados();
                    Map.Entry<Integer, Integer> cliCompraram = this.model.getClientesCompradores();
                    this.crono.stop();

                    List<String> q11 = new ArrayList<>();
                    q11.add(this.model.time());
                    q11.add(fileVendas);
                    q11.add(fileProdutos);
                    q11.add(fileCli);
                    q11.add(String.valueOf(vendas));
                    q11.add(String.valueOf(vendasInvalidas));
                    q11.add(String.valueOf(totalProd));
                    q11.add(String.format("%.2f", totalFat));
                    q11.add(prodsComprados.getKey().toString());
                    q11.add(prodsComprados.getValue().toString());
                    q11.add(cliCompraram.getKey().toString());
                    q11.add(cliCompraram.getValue().toString());

                    this.menu.showQ11(
                            q11.stream()
                                    .map(Arrays::asList)
                                    .collect(Collectors.toList()),
                            this.crono.toString());

                    this.menu.back();

                    break;

                case Q1_2:
                    this.crono.start();
                    Map<Integer, Integer> monthSales = this.model.vendasMensais();
                    List<Map<Integer, Double>> fatPerFilial = new ArrayList<>();
                    List<Map<Integer, Integer>> cliFilMes = new ArrayList<>();
                    for(int filial = 1; filial <= this.model.numeroFiliais(); filial++)
                        try {
                            fatPerFilial.add(this.model.faturacaoPorFilial(filial));
                            cliFilMes.add(this.model.clientesPorFilial(filial));
                        } catch (InvalidFilialException ignored) {}
                    this.crono.stop();

                    List<List<String>> monthly = new ArrayList<>();

                    List<String> monthSalesList = new ArrayList<>();
                    for(int mes = 1; mes <= this.model.meses(); mes++)
                        monthSalesList.add(monthSales.get(mes).toString());
                    monthly.add(monthSalesList);

                    for(int filial = 0; filial < this.model.numeroFiliais(); filial++) {
                        List<String> tmpFat = new ArrayList<>();
                        for (int mes = 0; mes < this.model.meses(); mes++)
                            tmpFat.add(String.format("%.2f", fatPerFilial.get(filial).get(mes)));
                        monthly.add(tmpFat);
                    }

                    for(int filial = 0; filial < this.model.numeroFiliais(); filial++) {
                        List<String> tmpCli = new ArrayList<>();
                        for (int mes = 1; mes <= this.model.meses(); mes++)
                            tmpCli.add(cliFilMes.get(filial).get(mes).toString());
                        monthly.add(tmpCli);
                    }

                    this.menu.showQ12(this.crono.toString(), monthly, this.model.meses(), this.model.numeroFiliais());

                    this.menu.back();
                    break;

                case Save:
                    try {
                        String fName = this.menu.getInputString(error, "Caminho para o ficheiro para guardar:");
                        this.crono.start();
                        this.model.save(fName);
                        this.crono.stop();

                        this.menu.showSave(fName, this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (IOException e) {error = "Ficheiro Inválido";}
                    break;
                case Load:
                    try {
                        String fName = this.menu.getInputString(error, "Caminho para o ficheiro para carregar:");
                        this.crono.start();
                        this.model = GestVendasModel.read(fName);
                        this.crono.stop();

                        this.menu.showLoad(fName, this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (IOException | ClassNotFoundException e) {error = "Ficheiro Inválido";}
                    break;

                    default:
                        out.println(menu);
                        menu.parser();
            }
        }
    }
}
