package Utils;

public interface ICrono {

        /**
         * Método para começar a cronometrar o tempo
         */
        void start();

        /**
         * Método para terminar a contagem do tempo
         * @return tempo decorrido desde o último start em milisegundos
         */
        double stop();

        String toString();
}