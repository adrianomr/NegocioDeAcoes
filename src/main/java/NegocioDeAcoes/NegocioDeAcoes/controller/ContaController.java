package NegocioDeAcoes.NegocioDeAcoes.controller;

import NegocioDeAcoes.NegocioDeAcoes.exception.ResourceNotFoundException;
import NegocioDeAcoes.NegocioDeAcoes.model.Conta;
import NegocioDeAcoes.NegocioDeAcoes.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/contas")
    public Page<Conta> getContas(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }


    @PostMapping("/contas")
    public Conta createConta(@Valid @RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    @PutMapping("/contas/{contaId}")
    public Conta updateconta(@PathVariable Long contaId,
                             @Valid @RequestBody Conta contaRequest) {
        return contaRepository.findById(contaId)
                .map(conta -> {
                    conta.setDescricao(contaRequest.getDescricao());
                    return contaRepository.save(conta);
                }).orElseThrow(() -> new ResourceNotFoundException("conta not found with id " + contaId));
    }


    @DeleteMapping("/contas/{contaId}")
    public ResponseEntity<?> deleteconta(@PathVariable Long contaId) {
        return contaRepository.findById(contaId)
                .map(conta -> {
                    contaRepository.delete(conta);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("conta not found with id " + contaId));
    }

}
