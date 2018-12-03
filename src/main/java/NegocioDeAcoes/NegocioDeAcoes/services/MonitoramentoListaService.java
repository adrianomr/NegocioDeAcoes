package NegocioDeAcoes.NegocioDeAcoes.services;

import NegocioDeAcoes.NegocioDeAcoes.interfaces.IMonitoramentoListaService;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.model.MonitoramentoLista;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class MonitoramentoListaService implements IMonitoramentoListaService {
    private MonitoramentoRepository monitoramentoRepository;
    public MonitoramentoListaService(MonitoramentoRepository monitoramentoRepository) {
        this.monitoramentoRepository = monitoramentoRepository;
    }

    public HashMap<Long, Monitoramento> getMonitoramentos(){
        List<Monitoramento> monitoramentoList = monitoramentoRepository.findAll();
        for (Monitoramento monitoramento:monitoramentoList) {
            MonitoramentoLista.getMonitoramentos().put(monitoramento.getId(), monitoramento);
        }
        return MonitoramentoLista.getMonitoramentos();
    }

}
