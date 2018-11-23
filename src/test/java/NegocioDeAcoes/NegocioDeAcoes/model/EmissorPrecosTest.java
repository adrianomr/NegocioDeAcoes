package NegocioDeAcoes.NegocioDeAcoes.model;

import NegocioDeAcoes.NegocioDeAcoes.interfaces.IMonitorEmissorPressos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmissorPrecosTest {
    IMonitorEmissorPressos iMonitorEmissorPressos;
    @Before
    public void setUp() throws Exception {
        iMonitorEmissorPressos = new IMonitorEmissorPressos() {
            @Override
            public void setPreco(double preco) {
                System.out.println(preco);
            }
        };
    }

    @Test(timeout=1000000)
    public void iniciaEmissao() throws InterruptedException {
        EmissorPrecos emissorPrecos = new EmissorPrecos(12, 10, iMonitorEmissorPressos);
        emissorPrecos.iniciaEmissao();
        Thread.sleep(1000000);
        fail();
    }
}