import Controller.GestVendasController;
import Controller.IGestVendasController;
import Model.GestVendasModel;
import Model.IGestVendasModel;
import View.GestVendasView;
import View.IGestVendasView;

import java.io.IOException;

public class GestVendasAppMVC {
    public static void main(String[] args) {

        IGestVendasView view = new GestVendasView();
        IGestVendasModel model = null;
        try {
            model = new GestVendasModel("db/configs.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        IGestVendasController controller = new GestVendasController();
        controller.setView(view);
        controller.setModel(model);

        controller.start();
    }
}
