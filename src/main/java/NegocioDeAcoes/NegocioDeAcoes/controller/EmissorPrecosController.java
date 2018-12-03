package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.services.EmissorPrecos;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import NegocioDeAcoes.NegocioDeAcoes.services.MonitoramentoListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmissorPrecosController {

    @Autowired
    MonitoramentoRepository monitoramentoRepository;

    @Autowired
    EmissorPrecos emissorPrecos;

    @GetMapping("/emissor-precos/iniciar")
    public ResponseEntity<?> iniciaEmissaoPrecos() {
        RestTemplate restTemplate = new RestTemplate();


        MonitoramentoListaService monitoramentoListaService = new MonitoramentoListaService(monitoramentoRepository);
        for (Long codMonitoramento : monitoramentoListaService.getMonitoramentos().keySet()) {
            emissorPrecos.iniciaEmissao(codMonitoramento);
        }
        return ResponseEntity.ok().build();
    }
}
