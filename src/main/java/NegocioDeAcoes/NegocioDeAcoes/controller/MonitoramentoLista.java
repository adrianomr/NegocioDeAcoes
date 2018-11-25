package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.model.EmissorPrecos;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MonitoramentoLista {
    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @PostConstruct
    public void inciaMonitoramento() {
        List<Monitoramento> monitoramentoLista = monitoramentoRepository.findAll();
        for(Monitoramento monitoramento : monitoramentoLista){
            EmissorPrecos emissorPrecos = new EmissorPrecos(12, 10, monitoramento);
            emissorPrecos.iniciaEmissao();
        }
    }
}
