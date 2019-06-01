import Controller.Controller;
import Model.GestVendas;
import View.Menu;
import Utils.Crono;

public class Main {
    public static void main(String[] args) {
        Crono crono = new Crono();
        Menu view = new Menu();
        crono.start();
        GestVendas model = new GestVendas("db/Clientes.txt", "db/Produtos.txt", "db/Vendas_1M.txt");
        crono.stop();
        Controller controller = new Controller(view, model, crono);

        controller.start();
    }
}
