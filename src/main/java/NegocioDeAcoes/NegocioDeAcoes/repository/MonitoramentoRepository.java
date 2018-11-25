package NegocioDeAcoes.NegocioDeAcoes.repository;

import NegocioDeAcoes.NegocioDeAcoes.model.Monitoramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
    List<Monitoramento> findByContaId(Long contaId);
}