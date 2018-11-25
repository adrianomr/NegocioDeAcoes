package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.exception.ResourceNotFoundException;
import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import NegocioDeAcoes.NegocioDeAcoes.repository.ContaRepository;
import NegocioDeAcoes.NegocioDeAcoes.repository.MonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MonitoramentoController {

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/contas/{contaId}/monitoramentos")
    public List<Monitoramento> getMonitoramentosByContaId(@PathVariable Long contaId) {
        return monitoramentoRepository.findByContaId(contaId);
    }

    @PostMapping("/contas/{contaId}/monitoramentos")
    public Monitoramento addMonitoramento(@PathVariable Long contaId,
                                          @Valid @RequestBody Monitoramento monitoramento) {
        return contaRepository.findById(contaId)
                .map(conta -> {
                    monitoramento.setConta(conta);
                    return monitoramentoRepository.save(monitoramento);
                }).orElseThrow(() -> new ResourceNotFoundException("Conta not found with id " + contaId));
    }

    @PutMapping("/contas/{contaId}/monitoramentos/{monitoramentoId}")
    public Monitoramento updateMonitoramento(@PathVariable Long contaId,
                                             @PathVariable Long monitoramentoId,
                                             @Valid @RequestBody Monitoramento monitoramentoRequest) {
        if (!contaRepository.existsById(contaId)) {
            throw new ResourceNotFoundException("conta not found with id " + contaId);
        }

        return monitoramentoRepository.findById(monitoramentoId)
                .map(monitoramento -> {
                    monitoramento.setConta(monitoramentoRequest.getConta());
                    monitoramento.setEmpresa(monitoramentoRequest.getEmpresa());
                    monitoramento.setPrecoCompra(monitoramentoRequest.getPrecoCompra());
                    monitoramento.setPrecoVenda(monitoramentoRequest.getPrecoVenda());
                    return monitoramentoRepository.save(monitoramento);
                }).orElseThrow(() -> new ResourceNotFoundException("monitoramento not found with id " + monitoramentoId));
    }

    @DeleteMapping("/contas/{contaId}/monitoramentos/{monitoramentoId}")
    public ResponseEntity<?> deletemonitoramento(@PathVariable Long contaId,
                                                 @PathVariable Long monitoramentoId) {

        return monitoramentoRepository.findById(monitoramentoId)
                .map(monitoramento -> {
                    monitoramentoRepository.delete(monitoramento);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("monitoramento not found with id " + monitoramentoId));

    }

}
