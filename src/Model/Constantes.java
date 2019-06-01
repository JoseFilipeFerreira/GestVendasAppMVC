package Model;

public class Constantes {
    private int numeroFiliais;
    private int meses;

    public Constantes(){
        this.numeroFiliais = 3;
        this.meses = 12;
    }

    public int numeroFiliais(){
        return this.numeroFiliais;
    }

    public int meses() {
        return this.meses;
    }

    public boolean filialValida(int filial) {
        return filial > 0 && filial <= this.numeroFiliais;
    }

    public boolean mesValido(int mes) {
        return mes > 0 && mes <= this.meses;
    }
}
