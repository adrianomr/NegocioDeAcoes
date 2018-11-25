package NegocioDeAcoes.NegocioDeAcoes.repository;

import NegocioDeAcoes.NegocioDeAcoes.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
