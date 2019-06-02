package Controller;

import Exceptions.InvalidFilialException;
import Exceptions.InvalidProductExecption;
import Exceptions.IvalidClientException;
import Exceptions.MesInvalidoException;
import Model.Constantes;
import Model.GestVendasModel;
import Utils.Crono;
import View.Menu;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Controller {
    private Menu menu;
    private GestVendasModel model;
    private Crono cronoLoad;
    private Crono crono;
    private Constantes constantes;

    public Controller(Menu view, GestVendasModel model, Crono crono) {
        this.menu = view;
        this.model = model;
        this.cronoLoad = crono;
        this.crono = new Crono();
        this.constantes = new Constantes();
    }

    public void start(){
        String error = "";
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Q1:
                    this.crono.start();
                    List <String> prodsNComprados = this.model.listaDeProdutosNaoComprados();
                    this.crono.stop();
                    this.menu.showQ1(prodsNComprados, this.crono.toString());
                    this.menu.back();
                    error = "";
                    break;

                case Q2:
                    try {
                        int mesSales = this.menu.getInputInt(error, "Mês a pesquisar:");
                        this.crono.start();
                        Map.Entry<Integer, Integer> tSales = this.model.clientesVendasTotais(mesSales);
                        this.crono.stop();
                        this.menu.showQ2(tSales, mesSales, this.crono.toString());
                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e) { error = "Input is not a number"; }
                    catch (MesInvalidoException e) {error = "Mes Invalido"; }
                    break;

                case Q3:
                    try {
                        String cliSStats = this.menu.getInputString(error, "Cliente a pesquisar:");
                        int mesSStats = this.menu.getInputInt(error, "Mês a pesquisar:");
                        this.crono.start();
                        Map.Entry<Integer, Map.Entry<Integer, Double>> cliStats = this.model.statsClientes(cliSStats, mesSStats);
                        this.crono.stop();

                        this.menu.showQ3(cliSStats, mesSStats, cliStats, this.crono.toString());

                        this.menu.back();
                        error = "";

                    }
                    catch (IvalidClientException e) { error = "Cliente Inválido"; }
                    catch (MesInvalidoException | InputMismatchException e) { error = "Mês Inválido"; }
                    break;

                case Q4:
                    try {
                        String prodSStats = this.menu.getInputString(error, "Produto a pesquisar:");
                        int mesSStats = this.menu.getInputInt(error, "Mês a pesquisar:");
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
                        this.menu.back();
                        error = "";
                    }
                    catch (IvalidClientException e) { error = "Invalid Client"; }
                    break;

                case Q6:
                    try {
                        int nProdVend = this.menu.getInputInt(error, "Número de Produtos a Pesquisar:");
                        this.crono.start();
                        List<Map.Entry<String, Integer>> prods = this.model.produtosMaisVendidos(nProdVend);
                        this.crono.stop();
                        this.menu.showQ6(
                                prods
                                        .stream()
                                        .map(e -> {List<String> a = new ArrayList<>();a.add(e.getKey()); a.add(e.getValue().toString());return a;})
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
                                "Inserir Filial [1-" + this.constantes.numeroFiliais() + "]:");
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
                        List<String> clisDiverse = this.model.clientesComMaisDiversidade(nCliSearch);
                        this.crono.stop();
                        this.menu.showQ8(clisDiverse, this.crono.toString());

                        this.menu.back();
                        error = "";
                    }
                    catch (InputMismatchException e){ error = "Invalid Input"; }
                    break;

                case Q9:
                    try{
                        String prodBougth = this.menu.getInputString(error, "Produto a pesquisar:");
                        int nProdBrougth = this.menu.getInputInt(error, "Número de clientes a pesquisar:");
                        this.crono.start();
                        List<Map.Entry<String,Double>> highestBuyer = this.model.clientesQueMaisCompraram(prodBougth, nProdBrougth);
                        this.crono.stop();
                        this.menu.showQ9(
                                highestBuyer
                                        .stream()
                                        .map(e -> {
                                            List<String> a = new ArrayList<>();
                                            a.add(e.getKey());
                                            a.add(String.format("%.2f", e.getValue()));
                                            return a;})
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
                                "Filial a pesquisar [1-" + this.constantes.numeroFiliais() + "]:");

                        this.crono.start();
                        Map<String, Double> fatTotal = this.model.faturacaoProd(mes, filial);
                        this.crono.stop();

                        this.menu.showQ10(fatTotal, mes, filial, this.crono.toString());

                        this.menu.back();
                        error = "";

                    }
                    catch (InvalidFilialException e) { error = "Filial Inválida"; }
                    catch (MesInvalidoException e)     { error = "Mês inválido"; }
                    break;

                case Q1_1:
                    out.println(this.cronoLoad);
                    break;

                case Q1_2:
                    this.crono.start();
                    this.crono.stop();
                    break;

                    default:
                        out.println(menu);
                        menu.parser();
            }

        }

    }
}
