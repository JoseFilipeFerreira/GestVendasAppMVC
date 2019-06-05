package Model;

import java.util.Map;

public interface IFatura {
    IFatura update(IVenda a);

    double getTotal();

    Map<Integer, Double> getTotalFilial(int filial);

    Map<Integer, Double> getTotalMensal();

    String getProdId();
}
