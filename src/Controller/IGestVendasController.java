package Controller;

import Model.IGestVendasModel;
import View.IGestVendasView;

public interface IGestVendasController {
    /**
     * Método para começar o GestVendasController
     */
    void start();

    /**
     * Setter para a view
     * @param a View a utilizar
     */
    void setView(IGestVendasView a);

    /**
     * Setter para o model
     * @param a Model a utilizar
     */
    void setModel(IGestVendasModel a);
}
