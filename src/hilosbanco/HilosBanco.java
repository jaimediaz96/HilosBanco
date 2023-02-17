package hilosbanco;

/**
 *
 * @author JAIME EDUARDO DIAZ TOBON
 */
public class HilosBanco {

    static final int cantidadMax = 2000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco = new Banco(cantidadMax);
        for (int i = 0; i < 100; i++) {
            EjecucionTransferencias run = new EjecucionTransferencias(banco, i, cantidadMax);
            Thread hilo = new Thread(run);
            hilo.start();
        }
    }
}