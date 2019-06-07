package View;
import Utils.StringBetter;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Menu implements IMenu{
    private MenuInd menu;
    private final Stack<MenuInd> prev;
    private List<MenuInd> options;
    private boolean run;

    public Menu() {
        this.menu = MenuInd.Initial;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public boolean getRun() { return this.run; }

    public MenuInd getMenu() { return this.menu; }

    public int getInputInt(String error, String text){
        Scanner scanner = new Scanner(System.in);
        this.displayMenuHeader(error);
        out.println(text);
        return scanner.nextInt();
    }

    public String getInputString(String error, String text){
        Scanner scanner = new Scanner(System.in);
        this.displayMenuHeader(error);
        out.println(text);
        return scanner.nextLine();
    }

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

    private Menu selectOption(int i){
        if (this.options.size() > i - 1) {
            this.prev.push(this.menu);
            this.menu = this.options.get(i - 1);
            this.correctMenu();
        }
        return this;
    }

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

    public void showQ1(List<String> notBought, String time){
        Navigator<String> nav = new Navigator<>(notBought);
        this.menuNavigator(nav, time,"Produtos não comprados");
    }

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

    public void showQ3(String client, int mes, Map.Entry<Integer, Map.Entry<Integer, Double>> cliStats, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("produtos comprados");
        colLabl.add("n compras");
        colLabl.add("Total gasto");

        List<String> linLabl = new ArrayList<>();
        linLabl.add(client);

        List<String> valLine = new ArrayList<>();
        valLine.add(cliStats.getKey().toString());
        valLine.add(cliStats.getValue().getKey().toString());
        valLine.add(String.format("%.2f", cliStats.getValue().getValue()));

        List<List<String>> val = new ArrayList<>();
        val.add(valLine);

        Table<String> tab = new Table<>(val, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println("Mês [" + mes + "]:");
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

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

        Table<String> tab = new Table<>(val, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println("Mês [" + mes + "]:");
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    public void showQ5(List<String> prodsCli, String client, String time){
        this.displayMenuHeader(time);
        Navigator<String> nav = new Navigator<>(prodsCli);
        this.menuNavigator(nav, time,"Produtos mais comprados por " + client);
    }

    public void showQ6(List<List<String>> prodsM, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Produto");
        colLabl.add("Vendas");

        this.displayMenuHeader(time);
        out.println(this.defaultTable(colLabl, prodsM));

        new Scanner(System.in).nextLine();
    }

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

    public void showQ9(List<List<String>> clis, String time){
        List<String> colLabl = new ArrayList<>();
        colLabl.add("Cliente");
        colLabl.add("Total Gasto");

        this.displayMenuHeader(time);
        out.println(this.defaultTable(colLabl, clis));

        new Scanner(System.in).nextLine();
    }

    public void showQ10(Map<String, Double> fatTotal, int mes, int filial, String time){
        List<String> lines = new ArrayList<>();
        for (String key : fatTotal.keySet())
            lines.add(key + " [" + String.format("%.2f", fatTotal.get(key)) + "]");

        Navigator<String> nav = new Navigator<>(lines);
        this.menuNavigator(nav, time, "Faturação total no mês [" + mes + "] na filial [" + filial + "]:");
    }

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

        Table<String> tab = new Table<>(val, linLabl, colLabl);
        out.println(tab);

        new Scanner(System.in).nextLine();

    }

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

        Table<String> tab = new Table<>(monthly, linLabl, colLabl);

        this.displayMenuHeader(time);
        out.println(tab);

        new Scanner(System.in).nextLine();
    }

    private <T> Table defaultTable(List <String> colLabl, List<List <T>> vals){
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
        StringBetter strHeader = new StringBetter("\t--");
        for (MenuInd val : this.prev)
            strHeader.append(val.name()).append("/");

        return strHeader.append(this.menu.name()).append("--\n").red().toString();
    }

    private <T> void menuNavigator(Navigator<T> nav, String time, String title){
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
                    this.back();
                    return;
            }
        }
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
                this.options.clear();
                this.options.add(MenuInd.Q1_1);
                this.options.add(MenuInd.Q1_2);
                break;
            case Dynamic:
                this.options.clear();
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
