package Model;

public class Fatura implements IFatura {
    private String prodId;
    private int[][] nVendas; //mes filial
    private double[][] total; //mes filial
    private int[] quant;

    public Fatura() {
        this.nVendas = new int[3][12];
        this.total = new double[3][12];
        this.quant = new int[12];
    }

    public Fatura(Fatura a) {
        this.prodId = a.prodId;
        this.nVendas = a.nVendas.clone();
        this.total = a.total.clone();
        this.quant = a.quant.clone();
    }

    public IFatura update(IVenda v) {
        this.nVendas[v.getFilial()][v.getMonth()]++;
        this.total[v.getFilial()][v.getMonth()] += v.totalSale();
        this.quant[v.getMonth()] += v.getQuant();
        return this;
    }

    public Fatura clone() {
        return new Fatura(this);
    }
}
