package View;

public interface INavigator {

    /**
     * Método para avançar para a próxima página
     */
    void next();

    /**
     * Método para ir para a página anterior
     */
    void previous();

    String toString();
}
