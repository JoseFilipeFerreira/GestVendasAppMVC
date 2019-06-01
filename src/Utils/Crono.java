package Utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
/**
 * Utils.Crono = mede um tempo entre start() e stop()
 * O tempo e medido em nanosegundos e convertido para 
 *  um double que representa os segs na sua parte inteira.
 * 
 * @author FMM 
 * @version (a version number or a date)
 */
import static java.lang.System.nanoTime;
public class Crono {

  private long inicio;
  private long fim;

    public Crono() {
        this.inicio = 0;
        this.fim = 0;
    }

    public void start() {
      this.fim = 0L; this.inicio = nanoTime();
  }
  
  public double stop() {
      this.fim = nanoTime();
      long elapsedTime = this.fim - this.inicio;
      return elapsedTime / 1.0E09;
  }

  @Override
  public String toString() {
      long elapsedTime = this.fim - this.inicio;
      return "" + (elapsedTime / 1.0E09);
  }
}
