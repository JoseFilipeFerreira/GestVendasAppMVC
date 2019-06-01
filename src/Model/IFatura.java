package Model;

public interface IFatura {
    IFatura update(IVenda a);

    double getTotal();

    String getProdId();
}
