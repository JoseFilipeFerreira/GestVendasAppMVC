import Controller.Controller;
import Model.GestVendasModel;
import View.Menu;
import Utils.Crono;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Menu view = new Menu();
        GestVendasModel model = null;
        try {
            model = new GestVendasModel("db/configs.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = new Controller(view, model);

        controller.start();
    }
}
