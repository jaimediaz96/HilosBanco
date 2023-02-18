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

    public Banco(int salarioInicial) {
        cuentas = new double[100];
        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i] = salarioInicial;
        }
    }

    public synchronized void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException {
        while (cuentas[cuentaOrigen] < cantidad) { // evalua que el saldo no es inferior a la transferencia
            wait(); // pone el hilo a la espera hasta que se cumpla la condicion y libera el codigo para que otro hilo pueda entrar
        } // de la forma implicita solo se puede generar una condicion, de la forma explicita se pueden poner tantas condiciones sean necesarias
        System.out.println(Thread.currentThread());
        cuentas[cuentaOrigen] -= cantidad; //dinero que sale de la cuenta
        System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
        cuentas[cuentaDestino] += cantidad;
        System.out.printf(" Saldo total: %10.2f%n", getSaldoTotal());
        notifyAll(); // Notifica o despierta a los hilos en espera para que intenten de nuevo su condicion
    }

    public double getSaldoTotal() {
        double sumaCuentas = 0;
        for (double cuenta : cuentas) {
            sumaCuentas += cuenta;
        }
        return sumaCuentas;
    }
}
