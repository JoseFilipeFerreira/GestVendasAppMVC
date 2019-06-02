package Controller;

import Exceptions.InvalidFilialException;
import Exceptions.MesInvalidoException;
import Model.Constantes;
import Model.GestVendasModel;
import Utils.Crono;
import View.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
                        int mesSales = this.menu.getInputInteiro(error, "Mês a pesquisar:");
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
                    try{
                        int filN = this.menu.getInputInteiro(
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
