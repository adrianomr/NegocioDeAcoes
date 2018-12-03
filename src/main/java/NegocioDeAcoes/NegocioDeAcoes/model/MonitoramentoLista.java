package NegocioDeAcoes.NegocioDeAcoes.model;

import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public class MonitoramentoLista {
    private static HashMap<Long, Monitoramento> monitoramentos;
    private MonitoramentoLista(){}

    public static HashMap<Long, Monitoramento> getMonitoramentos() {
        if(monitoramentos == null) {
            monitoramentos = new HashMap<>();
        }
        return monitoramentos;
    }

    public void setMonitoramentos(HashMap<Long, Monitoramento> monitoramentos) {
        this.monitoramentos = monitoramentos;
    }
}
