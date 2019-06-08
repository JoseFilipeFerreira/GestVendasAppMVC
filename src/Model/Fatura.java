package Model;

import java.io.Serializable;
import java.util.*;

public class Fatura implements IFatura, Serializable {
    private static final long serialVersionUID = 390685106496680839L;
    private String prodId;
    private boolean foiComprado;
    private int[][] nVendas; //mes filial
    private double[][] total; //mes filial
    private int[] quant;

    /**
     * Construtor de uma fatura
     * @param id ID do produto referente
     */
    Fatura(String id) {
        this.prodId = id;
        this.foiComprado = false;
        this.nVendas = new int[3][12];
        this.total = new double[3][12];
        this.quant = new int[12];
    }

    /**
     * Construtor de uma Fatura
     * @param a Fatura a clonar
     */
    private Fatura(Fatura a) {
        this.prodId = a.prodId;
        this.foiComprado = a.foiComprado;
        this.nVendas = a.nVendas.clone();
        this.total = a.total.clone();
        this.quant = a.quant.clone();
    }

    /**
     * Atualiza uma fatura com a informcação de uma venda
     * @param v Venda a com informação a adicionar à fatura
     * @return Fatura atualizada
     */
    public Fatura update(IVenda v) {
        this.foiComprado = true;
        this.nVendas[v.getFilial()-1][v.getMonth()-1]++;
        this.total[v.getFilial()-1][v.getMonth()-1] += v.totalSale();
        this.quant[v.getMonth()-1] += v.getQuant();
        return this;
    }

    /**
     * Calcula o total de uma fatura
     * @return Total da fatura
     */
    public double getTotal() {
        double a = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                a += this.total[i][j];
            }
        }
        return a;
    }

    /**
     * Calcula o total de uma fatura numa filial
     * @param filial Filial a procurar
     * @return Faturação por mês numa filial da respetiva fatura
     */
    public Map<Integer, Double> getTotalFilial(int filial) {
        Map<Integer, Double> a = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            a.put(i, this.total[filial-1][i]);
        }
        return a;
    }

    /**
     * Calcula a faturação total mensal de uma fatura
     * @return Total faturado por mês na fatura em todas as filiais
     */
    public Map<Integer, Double> getTotalMensal() {
        Map<Integer, Double> a = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            a.put(i, Arrays.stream(this.total)
                    .mapToDouble(e -> e[finalI])
                    .sum());
        }
        return a;
    }

    /**
     * Getter do ID do produto ao que a fatura se refere
     * @return ID do produto
     */
    @Override
    public String getProdId() {
        return this.prodId;
    }

    /**
     * Determina de um produto foi comprado ou não
     * @return Se o produto foi comprado ou não
     */
    public boolean isFoiComprado() {
        return this.foiComprado;
    }

    /**
     * Clone de uma fatura
     * @return Fatura clonada
     */
    public Fatura clone() {
        return new Fatura(this);
    }
}
