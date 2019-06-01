package Controller;

import Model.GestVendas;
import Utils.Crono;
import View.Menu;
import View.Navigator;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    Menu menu;
    GestVendas gestvendas;
    Crono cronoLoad;
    Crono crono;

    public Controller(Menu view, GestVendas model, Crono crono) {
        this.menu = view;
        this.gestvendas = model;
        this.cronoLoad = crono;
        this.crono = new Crono();
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Q1:
                    this.crono.start();
                    ArrayList<String> navstr = new ArrayList<>();
                    for(int i = 0; i< 2000; i++)
                        navstr.add("1234");
                    this.crono.stop();
                    menu.menuNavigator(new Navigator<String>(navstr));
                    break;
                case Q2:
                    menu.getInputMes();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q3:
                    menu.getInputClient();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q4:
                    menu.getInputProduto();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q5:
                    menu.getInputClient();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q6:
                    menu.getInputInteiro();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q7:
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q8:
                    menu.getInputInteiro();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q9:
                    menu.getInputProduto();
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q10:
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;
                case Q1_1:
                    out.println(this.cronoLoad);
                    scanner.nextLine();
                    menu.back();
                    break;
                case Q1_2:
                    this.crono.start();
                    this.crono.stop();
                    menu.back();
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
            }

        }

    }
}
