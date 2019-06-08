package Model;

public interface IClient {

    /**
     * Verifica se é um cliente valido ou não
     * @return Se o cliente existe ou não
     */
    boolean verifyClient();

    /**
     * Getter do ID do cliente
     * @return ID do cliente
     */
    String getId();

    /**
     * Cria um clone de um cliente
     * @return Cliente clonado
     */
    IClient clone();
}
