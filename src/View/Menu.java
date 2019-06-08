package View;
import Utils.IStringBetter;
import Utils.StringBetter;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Menu implements IMenu{
    private MenuInd menu;
    private final Stack<MenuInd> prev;
    private List<MenuInd> options;
    private boolean run;

    /**
     * Construtor da classe menu
     */
    public Menu() {
        this.menu = MenuInd.Initial;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }


    /**
     * Get if menu is running
     * @return if the menu is running
     */
    public boolean getRun() { return this.run; }

    /**
     * Método para obtert o menu em que se está no momento
     * @return O MenuInd em que se está
     */
    public MenuInd getMenu() { return this.menu; }

    /**
     * Método para obter o input inteiro do utilizador
     * @param error Texto de erro a mostrar
     * @param text Texto a mostrar no pedido de input
     * @return Devolve o inteiro lido
     * @throws InputMismatchException Caso o valor introduzido não seja um inteiro.
     */
    public int getInputInt(String error, String text) throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        this.displayMenuHeader(error);
        out.println(text);
        return scanner.nextInt();
    }

    /**
     * Método para obter uma string do utilizador
     * @param error Texto de erro a mostrar
     * @param text Texto a mostrar no pedido de input
     * @return Devolve a string lida
     */
    public String getInputString(String error, String text){
        Scanner scanner = new Scanner(System.in);
        this.displayMenuHeader(error);
        out.println(text);
        return scanner.nextLine();
    }

    /**
     * Lê o input do utilizador e altera o menu onde se está
     * @return this
     */
    public Menu parser(){
        String str = new Scanner(System.in).nextLine();
        if (str.matches("^[+-]?\\d{1,8}$")) {
            this.selectOption(Integer.parseInt(str));
        }
        switch (str){
            case "b":
            case "..":
                this.back();
                break;
            case "e":
                this.run = false;
                break;
        }

        return this;
    }

    /**
     * Recua no menu
     * @return this
     */
    public Menu back(){
        if (this.prev.size() > 0) {
            this.menu = this.prev.pop();
            this.correctMenu();
        }
        else {
            this.run = false;
        }
        return this;
    }

    /**
     * Método para mostrar a Querie 1
     * @param notBought Lista de todos os produtos não comprados
     * @param time tempo que demorou a querie
     */
    public void showQ1(List<String> notBought, String time){
        INavigator nav = new Navigator<>(notBought);
        this.menuNavigator(nav, time,"Produtos não comprados");
    }

    /**
     * Método para mostrar a Querie 2
     * @param sales Vendas totais e clientes totais na key e no value respetivamente
     * @param mesSales Mes de vendas a mostrar
     * @param filialSales filial a mostrar (0 se for todas)
     * @param time tempo que demorou a querie
     */
    public void showQ2(Map.Entry<Integer, Integer> sales, int mesSales, int filialSales, String time){
        this.displayMenuHeader(time);
        if(filialSales == 0){
            out.println("Vendas totais no mês [" + mesSales + "]: " + sales.getKey());
            out.println("Clientes totais no mês [" + mesSales + "]: " + sales.getValue());
        }
        else {
            out.println("Vendas no mês [" + mesSales + "] na filial [" + filialSales + "]: " + sales.getKey());
            out.println("Clientes no mês [" + mesSales + "] na filial [" + filialSales + "]: " + sales.getValue());
        }
        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 3
     * @param client O cliente que foi pesquisado
     * @param nMes O número de meses calculados
     * @param cliStats A tabela de valores a apresentar
     * @param time tempo que demorou a querie
     */
    public void showQ3(String client, int nMes, List<List<String>> cliStats, String time){
        List<String> linLabl = new ArrayList<>();
        linLabl.add("produtos comprados");
        linLabl.add("n compras");
        linLabl.add("Total gasto");

        List<String> colLabl = new ArrayList<>();
        for(int mes = 1; mes <= nMes; mes++)
            colLabl.add("Mes [" + mes + "]");

        ITable tab = new Table<>(cliStats, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 4
     * @param produto O produto que foi pesquisado
     * @param mes O mes que foi pesquisado
     * @param prodStats As estatisticas do produto a apresentar
     * @param time tempo que demorou a querie
     */
    public void showQ4(String produto, int mes, Map.Entry<Integer, Map.Entry<Integer, Double>> prodStats, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Clientes que compraram");
        colLabl.add("n compras");
        colLabl.add("Total gasto");

        List<String> linLabl = new ArrayList<>();
        linLabl.add(produto);

        List<String> valLine = new ArrayList<>();
        valLine.add(prodStats.getKey().toString());
        valLine.add(prodStats.getValue().getKey().toString());
        valLine.add(String.format("%.2f", prodStats.getValue().getValue()));

        List<List<String>> val = new ArrayList<>();
        val.add(valLine);

        ITable tab = new Table<>(val, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println("Mês [" + mes + "]:");
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 5
     * @param prodsCli Produtos mais comprados pelo cliente
     * @param client Cliente que foi pesquisado
     * @param time tempo que demorou a querie
     */
    public void showQ5(List<String> prodsCli, String client, String time){
        this.displayMenuHeader(time);
        INavigator nav = new Navigator<>(prodsCli);
        this.menuNavigator(nav, time,"Produtos mais comprados por " + client);
    }

    /**
     * Método para mostrar a Querie 6
     * @param prodsM Dados a apresentar na tabela
     * @param time tempo que demorou a querie
     */
    public void showQ6(List<List<String>> prodsM, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Produto");
        colLabl.add("Vendas");

        this.displayMenuHeader(time);
        out.println(this.defaultTable(colLabl, prodsM));

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 7
     * @param clis Lista ordenada dos melhores clientes
     * @param time tempo que demorou a querie
     */
    public void showQ7(List<String> clis, String time){
        this.displayMenuHeader(time);
        out.println();
        out.println("3 melhores clientes:");
        out.println();
        for(int i = 0; i < clis.size() && i < 3; i++){
            out.println((i + 1) + "º " + clis.get(i));
        }

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 8
     * @param clis Lista de clientes a apresentar
     * @param time Tempo que demorou a querie
     */
    public void showQ8(List<String> clis, String time){
        this.displayMenuHeader(time);
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Clientes mais diversificados");
        out.println(defaultTable(
                colLabl,
                clis
                        .stream()
                        .map(e -> {List<String> a = new ArrayList<>();a.add(e);return a;})
                        .collect(Collectors.toList())));
        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 9
     * @param clis Tabela de dados a apresentar
     * @param time tempo que demorou a querie
     */
    public void showQ9(List<List<String>> clis, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Cliente");
        colLabl.add("Total Gasto");

        this.displayMenuHeader(time);
        out.println(this.defaultTable(colLabl, clis));

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar a Querie 10
     * @param fatTotal Todos os produtos a mostrar
     * @param mes mes pesquisado
     * @param filial filial pesquisada
     * @param time tempo que demorou a querie
     */
    public void showQ10(List<Map.Entry<String, Double>> fatTotal, int mes, int filial, String time){
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, Double> line : fatTotal)
            lines.add(line.getKey() + " [" + String.format("%.2f", line.getValue()) + "]");

        INavigator nav = new Navigator<>(lines);
        this.menuNavigator(nav, time, "Faturação total no mês [" + mes + "] na filial [" + filial + "]:");
    }

    /**
     * Método para mostrar a Querie 1.1
     * @param val Valores a mostrar
     * @param time tempo que demorou a querie
     */
    public void showQ11(List<List<String>> val, String time){
        this.displayMenuHeader(time);
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Stats de Ficheiros Lidos");

        List<String> linLabl = new ArrayList<>();
        linLabl.add("Tempo de Carregamento");
        linLabl.add("Ficheiro de Vendas");
        linLabl.add("Ficheiro de Produtos");
        linLabl.add("Ficheiro de Clientes");
        linLabl.add("Vendas a zero");
        linLabl.add("Vendas Inválidas");
        linLabl.add("Produtos Lidos");
        linLabl.add("Total Faturado");
        linLabl.add("Produtos Comprados");
        linLabl.add("Produtos não Comprados");
        linLabl.add("Clientes que Compraram");
        linLabl.add("Clientes que não Compraram");

        ITable tab = new Table<>(val, linLabl, colLabl);
        out.println(tab);

        new Scanner(System.in).nextLine();

    }

    /**
     * Método para mostrar a Querie 1.2
     * @param time tempo que demorou a querie
     * @param monthly estatisticas mensais
     * @param nMeses numero de meses
     * @param nFiliais numero de filiais
     */
    public void showQ12(String time, List<List<String>> monthly, int nMeses, int nFiliais){
        List<String> colLabl = new ArrayList<>();
        for(int i = 0; i < nMeses; i++)
            colLabl.add("Mes ["+ (i + 1) +"]");

        List<String>linLabl = new ArrayList<>();
        linLabl.add("Total de Vendas");
        for(int i = 0; i < nFiliais; i++)
            linLabl.add("Faturação filial [" + (i+1) + "]");
        for(int i = 0; i < nFiliais; i++)
            linLabl.add("Clientes filial [" + (i+1) + "]");

        ITable tab = new Table<>(monthly, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar o save do Object Stream
     * @param fName Nome do ficheiro
     * @param time tempo que demorou a querie
     */
    public void showSave(String fName, String time){
        this.displayMenuHeader(time);
        out.println();
        out.println("Estado guardado com sucesso!");
        out.println(fName);

        new Scanner(System.in).nextLine();
    }

    /**
     * Método para mostrar o load do Object Stream
     * @param fName Nome do ficheiro
     * @param time tempo que demorou a querie
     */
    public void showLoad(String fName, String time){
        this.displayMenuHeader(time);
        out.println();
        out.println("Estado Carregado com sucesso!");
        out.println(fName);

        new Scanner(System.in).nextLine();
    }

    private <T> ITable defaultTable(List <String> colLabl, List<List <T>> vals){
        List<String> linLabl = new ArrayList<>();
        for(int i = 0; i < vals.size(); i++)
            linLabl.add((i +1) + "º");
        return new Table<>(vals, linLabl, colLabl);
    }

    private void displayMenuHeader(String error) {
        out.print("\033\143");
        out.println(this.createHeader());
        out.println(new StringBetter(error).under().toString());
    }

    private String createHeader(){
        IStringBetter strHeader = new StringBetter("\t--");
        for (MenuInd val : this.prev)
            strHeader.append(val.name()).append("/");

        return strHeader.append(this.menu.name()).append("--\n").red().toString();
    }

    private <T> void menuNavigator(INavigator nav, String time, String title){
        Scanner scanner = new Scanner(System.in);
        while(true){
            this.displayMenuHeader(time);
            out.println(title);
            out.println(nav);
            switch (scanner.next().trim().charAt(0)){
                case 'n':
                    nav.next();
                    break;
                case 'p':
                    nav.previous();
                    break;
                case 'b':
                    return;
            }
        }
    }

    private Menu selectOption(int i){
        if (this.options.size() > i - 1) {
            this.prev.push(this.menu);
            this.menu = this.options.get(i - 1);
            this.correctMenu();
        }
        return this;
    }

    private String menuOptionText(int i) {
        switch (this.options.get(i)){
            case Binary:
                return "Estado de programa";
            case Load:
                return "Carregar Dados";
            case Save:
                return "Guardar Dados";
            case Categories:
                return "Queries";
            case Static:
                return "Queries estáticas";
            case Dynamic:
                return "Queries dinâmicas";
            case Q1:
                return "Produtos não comprados";
            case Q2:
                return "Vendas e clientes distintos";
            case Q3:
                return "Stats sobre cliente (ano)";
            case Q4:
                return "Stats sobre produto (ano)";
            case Q5:
                return "Produtos mais comprados por cliente";
            case Q6:
                return "N produtos mais vendidos";
            case Q7:
                return "Três maiores compradores";
            case Q8:
                return "N clientes que compraram mais produtos diferentes";
            case Q9:
                return "Clientes que mais compraram um produto";
            case Q10:
                return "Faturação total";
            case Q1_1:
                return "Stats de ficheiros lidos";
            case Q1_2:
                return "Stats globais";
                default:
                    return "";
        }
    }

    private void correctMenu() {
        this.options = new ArrayList<>();
        switch (this.menu) {
            case Initial:
                this.options.add(MenuInd.Categories);
                this.options.add(MenuInd.Binary);
                break;
            case Binary:
                this.options.add(MenuInd.Load);
                this.options.add(MenuInd.Save);
                break;
            case Categories:
                this.options.add(MenuInd.Static);
                this.options.add(MenuInd.Dynamic);
                break;
            case Static:
                this.options.add(MenuInd.Q1_1);
                this.options.add(MenuInd.Q1_2);
                break;
            case Dynamic:
                this.options.add(MenuInd.Q1);
                this.options.add(MenuInd.Q2);
                this.options.add(MenuInd.Q3);
                this.options.add(MenuInd.Q4);
                this.options.add(MenuInd.Q5);
                this.options.add(MenuInd.Q6);
                this.options.add(MenuInd.Q7);
                this.options.add(MenuInd.Q8);
                this.options.add(MenuInd.Q9);
                this.options.add(MenuInd.Q10);
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\033\143");
        s.append(this.createHeader()).append("\n\n");

        for(int i = 0; i < this.options.size(); i++)
            s.append(i + 1).append("- ").append(this.menuOptionText(i)).append("\n");
        s.append("\n");
        return s.toString();
    }
}
