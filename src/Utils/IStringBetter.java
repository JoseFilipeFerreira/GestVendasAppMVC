package Utils;

public interface IStringBetter {

    /**
     * Métdo para repetir n Vezes a string contida
     * @param n número de vezes a repetir
     * @return this
     */
    StringBetter repeat(int n);

    /**
     * Método para fazer append entre duas strings
     * @param strA String a adicionar
     * @return this
     */
    StringBetter append(String strA);
    /**
     * Método para pintar a string de preto
     * @return this
     */
    StringBetter black();

    /**
     * Método para pintar a string de vermelho
     * @return this
     */
    StringBetter red();

    /**
     * Método para pintar a string de verde
     * @return this
     */
    StringBetter green();

    /**
     * Método para pintar a string de laranja
     * @return this
     */
    StringBetter orange();

    /**
     * Método para pintar a string de azul
     * @return this
     */
    StringBetter blue();

    /**
     * Método para pintar a string de roxo
     * @return this
     */
    StringBetter roxo();

    /**
     * Método para pintar a string de ciano
     * @return this
     */
    StringBetter cyan();

    /**
     * Método para pintar a string de cinzento
     * @return this
     */
    StringBetter grey();

    /**
     * Método para pintar a string de branco
     * @return this
     */
    StringBetter white();

    /**
     * Método para formatar uma string em bold
     * @return this
     */
    StringBetter bold();

    /**
     * Método para formatar uma string em sublinhado
     * @return this
     */
    StringBetter under();

    /**
     * Método para formatar uma string a piscar
     * @return this
     */
    StringBetter blink();

    /**
     * Método para esconder o cursor
     * @return this
     */
    StringBetter hide_cursor();

    /**
     * Método para mostrar o cursor
     * @return this
     */
    StringBetter show_cursor();

    String toString();
}
