package hilosbanco;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author JAIME EDUARDO DIAZ TOBON
 */
public class Banco {

    private final double cuentas[];
    private Lock cierreBanco = new ReentrantLock();
    private Condition saldoSuficiente;

    public Banco(int salarioInicial) {
        cuentas = new double[100];
        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i] = salarioInicial;
        }
        saldoSuficiente = cierreBanco.newCondition();
    }

    public void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {
        cierreBanco.lock();
        try {
            while(cuentas[cuentaOrigen] < cantidad) { // evalua que el saldo no es inferior a la transferencia
                saldoSuficiente.await();
            }
            System.out.println(Thread.currentThread());
            cuentas[cuentaOrigen] -= cantidad; //dinero que sale de la cuenta
            System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
            cuentas[cuentaDestino] += cantidad;
            System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
            saldoSuficiente.signalAll();
        } finally {
            cierreBanco.unlock();
        }
    }

    public double getSaldoTotal() {
        double sumaCuentas = 0;
        for (double cuenta : cuentas) {
            sumaCuentas += cuenta;
        }
        return sumaCuentas;
    }
}
