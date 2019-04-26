public class Client implements IClient{
    private String id;

    public Client(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean verifyClient() {
        return this.id.matches("[A-Z]([0-4]\\d{3}|50{3})");
    }

    public Client clone() {
        return new Client(this.getId());
    }
}
