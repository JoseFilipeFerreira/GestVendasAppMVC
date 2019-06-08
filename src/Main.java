import Controller.Controller;
import Controller.IController;
import Model.GestVendasModel;
import View.Menu;

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
        IController controller = new Controller(view, model);

        controller.start();
    }
}
