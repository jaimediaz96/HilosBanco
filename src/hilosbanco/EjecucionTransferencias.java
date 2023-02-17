package hilosbanco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAIME EDUARDO DIAZ TOBON
 */
public class EjecucionTransferencias implements Runnable {

    private Banco banco;
    private int cuentaOrigen;
    private double cantidadMax;

    public EjecucionTransferencias(Banco banco, int cuentaOrigen, double cantidadMax) {
        this.banco = banco;
        this.cuentaOrigen = cuentaOrigen;
        this.cantidadMax = cantidadMax;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cuentaDestino = (int) (100 * Math.random());
                double cantidad = cantidadMax * Math.random();
                banco.transferencia(cuentaOrigen, cuentaDestino, cantidad);
                Thread.sleep((int) (10 * Math.random()));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(EjecucionTransferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * @return the cuentaOrigen
     */
    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * @param cuentaOrigen the cuentaOrigen to set
     */
    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    /**
     * @return the cantidadMax
     */
    public double getCantidadMax() {
        return cantidadMax;
    }

    /**
     * @param cantidadMax the cantidadMax to set
     */
    public void setCantidadMax(double cantidadMax) {
        this.cantidadMax = cantidadMax;
    }
}
