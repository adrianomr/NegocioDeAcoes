package NegocioDeAcoes.NegocioDeAcoes.services;

import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.model.MonitoramentoLista;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

@Service
public class EmissorPrecos {

    private final RestTemplate restTemplate;

    public EmissorPrecos(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public void iniciaEmissao(long monitoramento_id){
        Thread t = new Thread(() -> {
            while(true){
                geraPreco(monitoramento_id);
            }
        });
        t.start();
    }

    public Double geraPreco(long monitoramento_id){
        Monitoramento monitoramento = MonitoramentoLista.getMonitoramentos().get(monitoramento_id);
        double precoMaximo = monitoramento.getPrecoVenda() + (monitoramento.getPrecoVenda()/10);
        double precoMinimo = monitoramento.getPrecoCompra() - (monitoramento.getPrecoCompra()/10);
        double preco = ThreadLocalRandom.current().nextDouble(precoMinimo, precoMaximo);
        System.out.println("Monitoramento: "+monitoramento_id+", Preco:"+preco);
        restTemplate.postForObject(
                "http://localhost:8080/contas/"+monitoramento.getConta().getId()+
                        "/monitoramentos/"+monitoramento_id+
                        "/preco", preco,
                ResponseEntity.class);
        try {
            sleep(4995L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return preco;
    }

}
