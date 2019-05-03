package View;

import Utils.StringBetter;

import java.util.ArrayList;
import java.util.Stack;

public class Menu implements IMenu{
    MenuInd menu;
    Stack<MenuInd> prev;
    ArrayList<MenuInd> options;
    String menuString;

    public enum MenuInd {
        Initial,
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
        this.menu = MenuInd.Initial;
        this.prev = new Stack<MenuInd>();
        this.options = new ArrayList<>();
        this.correctMenu();
    }

    public Menu(MenuInd menuInd) {
        this.menu = menuInd;
        this.prev = new Stack<MenuInd>();
        this.options = new ArrayList<>();
        this.correctMenu();
    }

    public Menu parser(String str){
        if (str.matches("^[+-]?\\d+$")) {
            this.selectOption(Integer.parseInt(str));
        }
        switch (str){
            case "b":
            case "..":
                this.back();
        }

        return this;
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
        return this;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBetter strB = new StringBetter("\t--");

        for (MenuInd val : this.prev)
            strB.append(val.name()).append("/");

        s.append(strB.append(this.menu.name()).append("--\n").red().toString())
                .append("\n")
                .append(this.menuString)
                .append("\n\n");
        for(int i = 0; i < this.options.size(); i++)
            s.append(i + 1).append("- ").append(this.menuOptionText(i)).append("\n");
        s.append("\n");
        return s.toString();
    }

    private String menuOptionText(int i) {
        String r = "";
        switch (this.options.get(i)){
            case Initial:
                r += "Menu Inicial";
                break;
            case Static:
                r += "Queries estáticas";
                break;
            case Dynamic:
                r += "Queries dinâmicas";
                break;
            case Q1:
                r += "Produtos não comprados";
                break;
            case Q2:
                r += "Total de vendas e clientes distintos";
                break;
            case Q3:
                r += "Stats sobre cliente (ano)";
                break;
            case Q4:
                r += "Stats sobre produto (ano)";
                break;
            case Q5:
                r += "Produtos mais comprados por cliente";
                break;
            case Q6:
                r += "N produtos mais vendidos";
                break;
            case Q7:
                r += "Três maiores compradores";
                break;
            case Q8:
                r += "N clientes que compraram mais produtos diferentes";
                break;
            case Q9:
                r += "Clientes que mais compraram um produto";
                break;
            case Q10:
                r += "Faturação total";
                break;
            case Q1_1:
                r += "Stats de ficheiros lidos";
                break;
            case Q1_2:
                r += "Stats globais";
                break;
        }
        return r;
    }

    private void correctMenu() {
        switch (this.menu) {
            case Initial:
                this.menuString = "Initial";
                this.options.clear();
                this.options.add(MenuInd.Static);
                this.options.add(MenuInd.Dynamic);
                break;
            case Static:
                this.menuString = "Static";
                this.options.clear();
                this.options.add(MenuInd.Q1_1);
                this.options.add(MenuInd.Q1_2);
                break;
            case Dynamic:
                this.menuString = "Dynamic";
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
            case Q1:
                this.menuString = "Q1";
                this.options.clear();
                break;
            case Q2:
                this.menuString = "Q2";
                this.options.clear();
                break;
            case Q3:
                this.menuString = "Q3";
                this.options.clear();
                break;
            case Q4:
                this.menuString = "Q4";
                this.options.clear();
                break;
            case Q5:
                this.menuString = "Q5";
                this.options.clear();
                break;
            case Q6:
                this.menuString = "Q6";
                this.options.clear();
                break;
            case Q7:
                this.menuString = "Q7";
                this.options.clear();
                break;
            case Q8:
                this.menuString = "Q8";
                this.options.clear();
                break;
            case Q9:
                this.menuString = "Q9";
                this.options.clear();
                break;
            case Q10:
                this.menuString = "Q10";
                this.options.clear();
                break;
            case Q1_1:
                this.menuString = "Q1.1";
                this.options.clear();
                break;
            case Q1_2:
                this.menuString = "Q1.2";
                this.options.clear();
                break;
        }
    }
}
