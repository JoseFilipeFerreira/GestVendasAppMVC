package View;

import Utils.StringBetter;

import java.util.ArrayList;

public class Table<T> {
    private final ArrayList<String> linLabl;
    private final ArrayList<String> colLabl;
    private final ArrayList<ArrayList<T>> iT;
    private final StringBuilder builder;

    public Table(ArrayList<ArrayList<T>> iT, ArrayList<String> linLabel, ArrayList<String> colLabel) {
        this.linLabl = linLabel;
        this.colLabl = colLabel;
        this.iT = iT;
        this.builder = new StringBuilder();
    }

    private void printSeparatorLine(int[] sizeCols) {
        StringBetter sif = new StringBetter("-");
        for (int j = 0; j <= sizeCols.length - 1; j++)
            this.builder.append("+").append(sif.repeate(sizeCols[j]).toString());
        this.builder.append("+\n");
    }

    @Override
    public String toString() {
        builder.setLength(0);
        int col = this.colLabl.size();
        int lin = this.linLabl.size();
        StringBetter spac = new StringBetter(" ");

        /*find appropriate size for columns*/
        int[] sizeCols = new int[col + 1];
        int labelSize = 0;
        for (String s : this.linLabl) labelSize = (labelSize < s.length() ? s.length() : labelSize);
        sizeCols[0] = labelSize + 2;
        for (int j = 0; j < col; j++) {
            sizeCols[j + 1] = this.colLabl.get(j).length() + 2;
            for (int i = 0; i < lin; i++)
                sizeCols[j + 1] = (sizeCols[j + 1] < this.iT.get(i).get(j).toString().length() + 2) ?
                        this.iT.get(i).get(j).toString().length() + 2 : sizeCols[j + 1];
        }

        /*print label row*/
        this.printSeparatorLine(sizeCols);
        builder.append("|");
        builder.append(spac.repeate(sizeCols[0]));
        for (int j = 0; j < col; j++) {
            builder.append("| ").append(this.colLabl.get(j));
            builder.append(spac.repeate(sizeCols[j + 1] - this.colLabl.get(j).length() - 1));
        }
        builder.append("|\n");
        this.printSeparatorLine(sizeCols);

        /* print contents*/
        for (int i = 0; i < lin; i++) {
            builder.append("| ").append(this.linLabl.get(i));
            builder.append(spac.repeate(sizeCols[0] - this.linLabl.get(i).length() - 1));
            for (int j = 0; j < col; j++) {
                builder.append("| ").append(this.iT.get(i).get(j).toString());
                builder.append(spac.repeate(sizeCols[j + 1] - this.iT.get(i).get(j).toString().length() - 1));
            }
            builder.append("|\n");
            this.printSeparatorLine(sizeCols);
        }

        return builder.toString();
    }
}
