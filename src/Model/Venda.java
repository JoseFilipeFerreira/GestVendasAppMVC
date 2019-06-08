package Model;

import java.io.Serializable;

public class Venda implements IVenda, Serializable {
    private String codCli;
    private String codProd;
    private double unitPrice;
    private int quant;
    private int filial;
    private int month;
    private char type;

    Venda(String sale) {
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
        Constantes c = new Constantes();
        return this.unitPrice >= 0
                && this.unitPrice <= 999.99
                && this.quant >= 0
                && this.quant <= 250
                && c.mesValido(this.month)
                && c.filialValida(this.filial);
    }

    public double totalSale() {
        return this.quant * this.unitPrice;
    }

    public String getCodCli() {
        return codCli;
    }

    public String getCodProd() {
        return codProd;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuant() {
        return quant;
    }

    public int getFilial() {
        return filial;
    }

    public int getMonth() {
        return month;
    }
}
