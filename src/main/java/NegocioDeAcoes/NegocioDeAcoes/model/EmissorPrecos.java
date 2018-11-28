package NegocioDeAcoes.NegocioDeAcoes.model;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

@Service
public class EmissorPrecos {

    @Async
    public void iniciaEmissao(long tempoMaximo, double precoCompra, double precoVenda, long monitoramento_id, long conta_id){
        long tempoInicial = currentTimeMillis();
        long tempoAtual = currentTimeMillis();
        System.out.println(precoCompra+" - "+precoVenda);;
        if(tempoMaximo > 0){
            while ((tempoAtual - tempoInicial) < tempoMaximo){
                geraPreco(precoCompra, precoVenda, monitoramento_id, conta_id);
                tempoAtual = currentTimeMillis();
            }
        }else{
            while(true){
                geraPreco(precoCompra, precoVenda, monitoramento_id, conta_id);
            }
        }
    }

    public void geraPreco(double precoCompra, double precoVenda, long monitoramento_id, long conta_id){
        double precoMaximo = precoVenda + (precoVenda/10);
        double precoMinimo = precoCompra - (precoCompra/10);
        System.out.println(this.toString());
        double preco = ThreadLocalRandom.current().nextDouble(precoMinimo, precoMaximo);
//        IMonitorEmissorPressos.setPreco(preco);
        System.out.println(preco);
        RestTemplate restTemplate = new RestTemplate();

        String monitoramentoLista = restTemplate.getForObject(
                "http://localhost:8080/contas/"+conta_id+"/monitoramentos", String.class);
        System.out.println(monitoramentoLista);

        restTemplate.postForObject(
                "http://localhost:8080/contas/"+conta_id+"/monitoramentos/"+monitoramento_id+"/preco",
                preco,
                ResponseEntity.class);
        try {
            sleep(4995L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
