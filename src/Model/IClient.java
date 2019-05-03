package Model;

public interface IClient {
    boolean verifyClient();

    String getId();

    IClient clone();
}
