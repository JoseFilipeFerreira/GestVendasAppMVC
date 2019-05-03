package Model;

public class Venda implements IVenda{
    private String codCli;
    private String codProd;
    private double unitPrice;
    private int quant;
    private int filial;
    private int month;
    private char type;

    public Venda(String sale) {
        String[] saleF = sale.split(" ");
        if(saleF.length != 7) this.unitPrice = -1;
        else {
            this.codProd = saleF[0];
            this.unitPrice = Double.parseDouble(saleF[1]);
            this.quant = Integer.parseInt(saleF[2]);
            this.type = saleF[3].toCharArray()[0];
            this.codCli = saleF[4];
            this.month = Integer.parseInt(saleF[5]);
            this.filial = Integer.parseInt(saleF[6]);
        }
    }

    public boolean validSale() {
        return this.unitPrice >= 0
                && this.unitPrice <= 999.99
                && this.quant >= 0
                && this.quant <= 250
                && this.month >= 1
                && this.month <= 12
                && this.filial >= 1
                && this.filial <= 12;
    }

    public double totalSale() {
        return this.quant * this.unitPrice;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
