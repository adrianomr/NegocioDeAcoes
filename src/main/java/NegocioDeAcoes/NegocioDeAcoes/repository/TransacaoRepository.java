package NegocioDeAcoes.NegocioDeAcoes.repository;

import NegocioDeAcoes.NegocioDeAcoes.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long contaId);

}