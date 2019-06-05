import Controller.Controller;
import Model.GestVendasModel;
import View.Menu;
import Utils.Crono;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Crono crono = new Crono();
        Menu view = new Menu();
        crono.start();
        GestVendasModel model = null;
        try {
            model = new GestVendasModel("db/Clientes.txt", "db/Produtos.txt", "db/Vendas_1M.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        crono.stop();
        Controller controller = new Controller(view, model, crono);

        controller.start();
    }
}
