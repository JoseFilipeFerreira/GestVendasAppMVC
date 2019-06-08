package Utils;

public class StringBetter implements IStringBetter{
    private String str;

    /**
     * Construtor da classe StringBetter
     */
    public StringBetter() { this.str = ""; }

    /**
     * Construtor da classe StringBetter
     * @param str conteudo da instância
     */
    public StringBetter(String str) { this.str = str; }

    /**
     * Métdo para repetir n Vezes a string contida
     * @param n número de vezes a repetir
     * @return this
     */
    public StringBetter repeat(int n){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < n; i++)
            s.append(this.str);
        return new StringBetter(s.toString());
    }

    /**
     * Método para fazer append entre duas strings
     * @param strA String a adicionar
     * @return this
     */
    public StringBetter append(String strA){
        this.str += strA;
        return this;
    }

    /**
     * Método para pintar a string de preto
     * @return this
     */
    public StringBetter black() { return new StringBetter("\033[30m" + this.str).RESET(); }

    /**
     * Método para pintar a string de vermelho
     * @return this
     */
    public StringBetter red() { return new StringBetter("\033[31m" + this.str).RESET(); }

    /**
     * Método para pintar a string de verde
     * @return this
     */
    public StringBetter green() { return new StringBetter("\033[32m" + this.str).RESET(); }

    /**
     * Método para pintar a string de laranja
     * @return this
     */
    public StringBetter orange() { return new StringBetter("\033[33m" + this.str).RESET(); }

    /**
     * Método para pintar a string de azul
     * @return this
     */
    public StringBetter blue() { return new StringBetter("\033[34m" + this.str).RESET(); }

    /**
     * Método para pintar a string de roxo
     * @return this
     */
    public StringBetter roxo() { return new StringBetter("\033[35m" + this.str).RESET(); }

    /**
     * Método para pintar a string de ciano
     * @return this
     */
    public StringBetter cyan() { return new StringBetter("\033[36m" + this.str).RESET(); }

    /**
     * Método para pintar a string de cinzento
     * @return this
     */
    public StringBetter grey() { return new StringBetter("\033[37m" + this.str).RESET(); }

    /**
     * Método para pintar a string de branco
     * @return this
     */
    public StringBetter white() { return new StringBetter( "\033[38m" + this.str).RESET(); }

    /**
     * Método para formatar uma string em bold
     * @return this
     */
    public StringBetter bold() { return new StringBetter("\033[1m" + this.str).RESET(); }

    /**
     * Método para formatar uma string em sublinhado
     * @return this
     */
    public StringBetter under(){ return new StringBetter("\033[4m" + this.str).RESET(); }

    /**
     * Método para formatar uma string a piscar
     * @return this
     */
    public StringBetter blink(){ return new StringBetter( "\033[5m" + this.str).RESET(); }

    /**
     * Método para esconder o cursor
     * @return this
     */
    public StringBetter hide_cursor(){ return new StringBetter(this.str + "\033[?25l"); }

    /**
     * Método para mostrar o cursor
     * @return this
     */
    public StringBetter show_cursor(){ return new StringBetter(this.str + "\033[?25h"); }

    private StringBetter RESET(){ return new StringBetter(this.str + "\033[0m"); }

    @Override
    public String toString() { return this.str; }
}
