package NegocioDeAcoes.NegocioDeAcoes.repository;

import NegocioDeAcoes.NegocioDeAcoes.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long contaId);
    @Query(value = "SELECT " +
            "   COALESCE(" +
            "       SUM(" +
            "           CASE t.acao " +
            "           WHEN 'Compra' then t.quantidade " +
            "           WHEN 'Venda' THEN -t.quantidade " +
            "           ELSE 0 " +
            "       END)" +
            "   , 0) " +
            "FROM transacoes AS t " +
            "WHERE " +
            "   t.empresa = :empresa" +
            "   AND t.conta.id = :conta_id"
    )
    double getQuantidadeAcoesByConta(@Param("empresa") String empresa, @Param("conta_id") long contaId);
}