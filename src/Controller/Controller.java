package Controller;

import Model.GestVendasModel;
import Utils.Crono;
import View.Menu;
import View.Navigator;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private Menu menu;
    private GestVendasModel gestvendas;
    private Crono cronoLoad;
    private Crono crono;

    public Controller(Menu view, GestVendasModel model, Crono crono) {
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
                    this.crono.stop();
                    break;
                case Q2:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q3:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q4:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q5:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q6:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q7:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q8:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q9:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q10:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;
                case Q1_1:
                    out.println(this.cronoLoad);
                    scanner.nextLine();
                    
                    break;
                case Q1_2:
                    this.crono.start();
                    this.crono.stop();
                    
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
            }

        }

    }
}
