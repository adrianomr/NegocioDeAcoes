package NegocioDeAcoes.NegocioDeAcoes.interfaces;

import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;

import java.util.HashMap;

public interface IMonitoramentoListaService {
    public HashMap<Long, Monitoramento> getMonitoramentos();
}
