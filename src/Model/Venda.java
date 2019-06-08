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

    /**
     * Construtor de uma venda, a partir de uma String
     * @param sale String com informação da venda
     */
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

    /**
     * Verifica se uma venda é valida
     * @return Se a venda é valida ou não
     */
    public boolean validSale(IConstantes c) {
        return this.unitPrice >= 0
                && this.unitPrice <= 999.99
                && this.quant >= 0
                && this.quant <= 250
                && c.mesValido(this.month)
                && c.filialValida(this.filial);
    }

    /**
     * Calcula o valor total da Venda
     * @return Total da venda
     */
    public double totalSale() {
        return this.quant * this.unitPrice;
    }

    /**
     * Getter do codigo do cliente
     * @return Codigo do cliente
     */
    public String getCodCli() {
        return codCli;
    }

    /**
     * Getter do codigo do produto
     * @return Codigo do produto
     */
    public String getCodProd() {
        return codProd;
    }

    /**
     * Getter do valor unitario do produto comprado
     * @return Preço unirário do produto comprado
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Getter da quantidade de produtos vendidos
     * @return Quantidade vendida do produto
     */
    public int getQuant() {
        return quant;
    }

    /**
     * Getter da filial onde foi feita a venda
     * @return Filial onde foi feita a venda
     */
    public int getFilial() {
        return filial;
    }

    /**
     * Getter do mês da venda
     * @return Mês onde foi efetuada a venda
     */
    public int getMonth() {
        return month;
    }
}
