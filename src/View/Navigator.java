package View;

import Utils.IStringBetter;
import Utils.StringBetter;
import Utils.Terminal;

import java.util.List;


public class Navigator<T> implements INavigator{
    private final List<T> strings;
    private final StringBuilder builder;
    private int pageSize;
    private int nCols;
    private int nPages;
    private int page;
    private final int separator;
    private int maxPrint;
    private final Terminal term;

    /**
     * Construtor para a classe
     * @param strings Lista de valores a respresentar
     */
    public Navigator(List<T> strings) {
        this.builder = new StringBuilder();
        this.strings = strings;
        this.term = new Terminal();
        this.maxPrint = 0;
        strings.forEach(string -> this.maxPrint = (string.toString().length() < this.maxPrint) ?
                this.maxPrint : string.toString().length());
        this.separator = this.maxPrint + 3;
        this.pageSize = this.term.getLines() - 10;
        this.update();
        this.page = 0;
    }

    /**
     * Método para avançar para a próxima página
     */
    public void next() {
        this.page = (page + 1 <= nPages)?page + 1 : page;
    }

    /**
     * Método para ir para a página anterior
     */
    public void previous() {
        this.page = (page - 1 >= 0)?page -1 : page;
    }

    private void update(){
        term.update();
        this.pageSize = this.term.getLines() - 10;
        if (this.pageSize < 1)
            this.pageSize = 1;
        if (this.pageSize > 20)
            this.pageSize = 20;
        this.nCols = 1;
        while (true) {
            if (((this.nCols + 1) * (String.valueOf(strings.size() + 1).length() + 2 + this.maxPrint + this.separator) < this.term.getColumns()))
                this.nCols++;
            else
                break;
        }
        if (this.strings.size() == 0)
            this.nPages = 0;
        else
            this.nPages = (strings.size() / (pageSize * nCols))
                - ((strings.size() % (pageSize * nCols) != 0) ? 0 : 1);
        if(this.page > this.nPages)
            this.page = this.nPages;
    }

    @Override
    public String toString(){
        this.update();
        IStringBetter spac = new StringBetter(" ");
        IStringBetter senter = new StringBetter("\n");
        int pos, r = 0;
        builder.setLength(0);
        builder.append("Total: " + this.strings.size()).append("\n");
        for(int i = this.pageSize * this.page; i < this.pageSize * (this.page + 1); i++){
            for(int j = 0; j < this.nCols && j + i * this.nCols < this.strings.size(); j++){
                pos = j + i * this.nCols;
                builder.append(pos + 1).append(".");
                builder.append(spac.repeat(
                        String.valueOf(this.strings.size()).length() - String.valueOf(pos + 1).length() + 1
                        ).toString());
                builder.append(this.strings.get(pos)).append(spac.repeat(this.separator));
                builder.append(spac.repeat(this.maxPrint - this.strings.get(pos).toString().length()));

            }
            r++;
            builder.append("\n");
        }
        builder.append(senter.repeat(this.pageSize - r  + 2));
        builder.append("\t\t").append(this.page + 1).append("/").append(this.nPages+1);
        return builder.toString();
    }
}
