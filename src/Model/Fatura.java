package Model;

public class Fatura implements IFatura {
    private String prodId;
    private int[][] nVendas; //mes filial
    private double[][] total; //mes filial
    private int[] quant;

    public Fatura() {
        this.prodId = "";
        this.nVendas = new int[3][12];
        this.total = new double[3][12];
        this.quant = new int[12];
    }

    public Fatura(String id) {
        this.prodId = id;
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

    public Fatura update(IVenda v) {
        this.nVendas[v.getFilial()-1][v.getMonth()-1]++;
        this.total[v.getFilial()-1][v.getMonth()-1] += v.totalSale();
        this.quant[v.getMonth()-1] += v.getQuant();
        return this;
    }

    public double getTotal() {
        double a = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                a += this.total[i][j];
            }
        }
        return a;
    }

    @Override
    public String getProdId() {
        return this.prodId;
    }

    public Fatura clone() {
        return new Fatura(this);
    }
}
