package hilosbanco;

/**
 *
 * @author JAIME EDUARDO DIAZ TOBON
 */
public class Banco {
    
    private final double cuentas[];
    
    public Banco() {
        cuentas = new double[100];
        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i] = 2000;
        }
    }
    
    public void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) {
        if(cuentas[cuentaOrigen] < cantidad) { // evalua que el saldo no es inferior a la transferencia
            return ;
        }
        System.out.println(Thread.currentThread());
        cuentas[cuentaOrigen]-= cantidad; //dinero que sale de la cuenta
        System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
        cuentas[cuentaDestino]+= cantidad;
        System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
    }
    
    public double getSaldoTotal() {
        double sumaCuentas = 0;
        for (double cuenta : cuentas) {
            sumaCuentas+= cuenta;
        }
        return sumaCuentas;
    }
}
