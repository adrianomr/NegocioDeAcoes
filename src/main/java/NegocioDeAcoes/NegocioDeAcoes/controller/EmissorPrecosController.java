package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.model.EmissorPrecos;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EmissorPrecosController {
    @GetMapping("/emissor-precos/iniciar")
    public ResponseEntity<?> iniciaEmissaoPrecos() {
        EmissorPrecos emissorPrecos = new EmissorPrecos();
        RestTemplate restTemplate = new RestTemplate();

        Monitoramento[] monitoramentoLista = restTemplate.getForObject(
                "http://localhost:8080/contas/1101/monitoramentos", Monitoramento[].class);
        Monitoramento[] monitoramentos = monitoramentoLista;
        for (Monitoramento monitoramento : monitoramentos) {
            System.out.println(monitoramento.toString());
            emissorPrecos.iniciaEmissao(0, monitoramento.getPrecoCompra(), monitoramento.getPrecoVenda(), monitoramento.getId(), 1101);
        }
        return ResponseEntity.ok().build();
    }
}
