package Utils;

import static java.lang.System.nanoTime;
public class Crono implements ICrono{

  private long inicio;
  private long fim;

    /**
     * construtor da classe crono
     */
  public Crono() {
      this.inicio = 0;
      this.fim = 0;
  }

  /**
   * Método para começar a cronometrar o tempo
   */
  public void start() {
      this.fim = 0L; this.inicio = nanoTime();
  }

  /**
   * Método para terminar a contagem do tempo
   * @return tempo decorrido desde o último start em milisegundos
   */
  public double stop() {
      this.fim = nanoTime();
      long elapsedTime = this.fim - this.inicio;
      return elapsedTime / 1.0E06;
  }

  @Override
  public String toString() {
      long elapsedTime = this.fim - this.inicio;
      return String.format("%.2f",elapsedTime / 1.0E06) + "ms";
  }
}
