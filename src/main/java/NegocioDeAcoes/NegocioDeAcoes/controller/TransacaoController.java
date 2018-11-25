package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.exception.ResourceNotFoundException;
import NegocioDeAcoes.NegocioDeAcoes.model.Transacao;
import NegocioDeAcoes.NegocioDeAcoes.repository.ContaRepository;
import NegocioDeAcoes.NegocioDeAcoes.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/contas/{contaId}/transacoes")
    public List<Transacao> getMonitoramentosByContaId(@PathVariable Long contaId) {
        return transacaoRepository.findByContaId(contaId);
    }

    @PostMapping("/contas/{contaId}/transacoes")
    public Transacao addMonitoramento(@PathVariable Long contaId,
                                      @Valid @RequestBody Transacao monitoramento) {
        return contaRepository.findById(contaId)
                .map(conta -> {
                    monitoramento.setConta(conta);
                    return transacaoRepository.save(monitoramento);
                }).orElseThrow(() -> new ResourceNotFoundException("Conta not found with id " + contaId));
    }

    @PutMapping("/contas/{contaId}/transacoes/{monitoramentoId}")
    public Transacao updateMonitoramento(@PathVariable Long contaId,
                                         @PathVariable Long monitoramentoId,
                                         @Valid @RequestBody Transacao monitoramentoRequest) {
        if (!contaRepository.existsById(contaId)) {
            throw new ResourceNotFoundException("conta not found with id " + contaId);
        }

        return transacaoRepository.findById(monitoramentoId)
                .map(transacao -> {
                    transacao.setConta(monitoramentoRequest.getConta());
                    transacao.setEmpresa(monitoramentoRequest.getEmpresa());
                    transacao.setPrecoUnitario(monitoramentoRequest.getPrecoUnitario());
                    transacao.setQuantidade(monitoramentoRequest.getQuantidade());
                    transacao.setAcao(monitoramentoRequest.getAcao());
                    return transacaoRepository.save(transacao);
                }).orElseThrow(() -> new ResourceNotFoundException("transacao not found with id " + monitoramentoId));
    }

    @DeleteMapping("/contas/{contaId}/transacoes/{monitoramentoId}")
    public ResponseEntity<?> deletemonitoramento(@PathVariable Long contaId,
                                                 @PathVariable Long monitoramentoId) {

        return transacaoRepository.findById(monitoramentoId)
                .map(transacao -> {
                    transacaoRepository.delete(transacao);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("transacao not found with id " + monitoramentoId));

    }

}
