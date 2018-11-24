package NegocioDeAcoes.NegocioDeAcoes.model;

import NegocioDeAcoes.NegocioDeAcoes.interfaces.IMonitorEmissorPressos;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmissorPrecosTest {
    private IMonitorEmissorPressos iMonitorEmissorPressos;
    private double valor;
    private long tempoInicial;
    private long tempoFinal;
    private long total;

    @Test
    public void iniciaEmissao() throws InterruptedException {
        iMonitorEmissorPressos = new IMonitorEmissorPressos() {
            @Override
            public void setPreco(double preco) {
                tempoFinal = System.currentTimeMillis();
                total = tempoFinal - tempoInicial;
                System.out.println("Tempo Decorrido: " + total);
                assertTrue("Tempo total deve ser no máximo 5 segundos. O tempo foi de " + total, total <= 5000);
                assertTrue("Preco menor do que o valor limite", preco >= 9d);
                assertTrue("Preco maior do que o valor limite", preco <= 13.2);
                tempoInicial = tempoFinal;
                System.out.println("Preco: " + preco);
            }
        };
        EmissorPrecos emissorPrecos = new EmissorPrecos(12, 10, iMonitorEmissorPressos);
        tempoInicial = System.currentTimeMillis();
        emissorPrecos.iniciaEmissao();
        Thread.sleep(20000);
    }
}