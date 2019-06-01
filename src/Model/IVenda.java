package Model;

public interface IVenda {
    double totalSale();

    boolean validSale();

    int getMonth();

    int getFilial();

    int getQuant();

    String getCodProd();

    String getCodCli();
}
