package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.model.EmissorPrecos;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import NegocioDeAcoes.NegocioDeAcoes.services.MonitoramentoListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EmissorPrecosController {

    @Autowired
    MonitoramentoRepository monitoramentoRepository;

    @GetMapping("/emissor-precos/iniciar")
    public ResponseEntity<?> iniciaEmissaoPrecos() {
        EmissorPrecos emissorPrecos = new EmissorPrecos();
        RestTemplate restTemplate = new RestTemplate();


        MonitoramentoListaService monitoramentoListaService = new MonitoramentoListaService(monitoramentoRepository);
        for (Long codMonitoramento : monitoramentoListaService.getMonitoramentos().keySet()) {
            emissorPrecos.iniciaEmissao(0, codMonitoramento);
        }
        return ResponseEntity.ok().build();
    }
}
