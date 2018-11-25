package NegocioDeAcoes.NegocioDeAcoes.model;

import NegocioDeAcoes.NegocioDeAcoes.interfaces.IMonitorEmissorPressos;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EmissorPrecos {

    private double preco;
    private ArrayList<Double> precos;
    private double precoMaximo;
    private double precoMinimo;
    private IMonitorEmissorPressos IMonitorEmissorPressos;

    public EmissorPrecos(double precoMaximo, double precoMinimo, IMonitorEmissorPressos IMonitorEmissorPressos) {
        this.precoMaximo = precoMaximo;
        this.precoMinimo = precoMinimo;
        this.IMonitorEmissorPressos = IMonitorEmissorPressos;
        this.preco = 0;
        this.precos = new ArrayList<>();
    }

    public void iniciaEmissao(){
        new Thread() {

            @Override
            public void run() {
                while(true){
                    preco = ThreadLocalRandom.current().nextDouble(precoMinimo, precoMaximo);
                    precos.add(preco);
                    IMonitorEmissorPressos.setPreco(preco);
                    try {
                        sleep(4995);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

}
