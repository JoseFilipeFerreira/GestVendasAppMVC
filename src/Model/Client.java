package Model;

public class Client implements IClient{
    private String id;

    /**
     * Construtor de um cliente
     * @param id ID do cliente a construir
     */
    Client(String id) {
        this.id = id;
    }

    /**
     * Getter do ID do cliente
     * @return ID do cliente
     */
    public String getId() {
        return this.id;
    }

    /**
     * Verifica se é um cliente valido ou não
     * @return Se o cliente existe ou não
     */
    public boolean verifyClient() {
        return this.id.matches("[A-Z]([0-4]\\d{3}|50{3})");
    }

    /**
     * Cria um clone de um cliente
     * @return Cliente clonado
     */
    public Client clone() {
        return new Client(this.getId());
    }
}
