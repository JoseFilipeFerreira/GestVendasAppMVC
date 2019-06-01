package View;
import Utils.StringBetter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.out;

public class Menu implements IMenu{
    private MenuInd menu;
    private Stack<MenuInd> prev;
    private ArrayList<MenuInd> options;
    private boolean run;

    public <T> void menuNavigator(Navigator<T> nav){
        Scanner scanner = new Scanner(System.in);
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
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

    public int getInputMes(){
        Scanner scanner = new Scanner(System.in);
        String str;
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Mês Inválido").under().toString());
            else
                out.println();
            out.println("Inserir Mês: ");
            str = scanner.nextLine();
            if (str.matches("^[1-9]|1[0-2]$"))
                return Integer.parseInt(str);
            else
                error = true;
        }
    }

    public enum MenuInd {
        Categories,
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

    public Menu() {
        this.menu = MenuInd.Categories;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public Menu(MenuInd menuInd) {
        this.menu = menuInd;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public MenuInd getMenu() {
        return this.menu;
    }

    public Menu parser(String str){
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

    public boolean getRun() {
        return this.run;
    }

    public Menu selectOption(int i){
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

    private String menuOptionText(int i) {
        switch (this.options.get(i)){
            case Categories:
                return "Menu Inicial";
            case Static:
                return "Queries estáticas";
            case Dynamic:
                return "Queries dinâmicas";
            case Q1:
                return "Produtos não comprados";
            case Q2:
                return "Total de vendas e clientes distintos";
            case Q3:
                return "Stats sobre cliente (ano)";
            case Q4:
                return "Stats sobre produto (ano)";
            case Q5:
                return "Produtos mais comprados por cliente";
            case Q6:
                return "N produtos mais vendidos";
            case Q7:
                return  "Três maiores compradores";
            case Q8:
                return "N clientes que compraram mais produtos diferentes";
            case Q9:
                return "Clientes que mais compraram um produto";
            case Q10:
                return "Faturação total";
            case Q1_1:
                return "Stats de ficheiros lidos";
            case Q1_2:
                return  "Stats globais";
                default:
                    return "";
        }
    }

    private void correctMenu() {
        this.options.clear();
        switch (this.menu) {
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
}
