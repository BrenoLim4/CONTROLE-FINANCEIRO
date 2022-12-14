package sop.ce.gov.controlefinanceiro.domain.repositories;

import sop.ce.gov.controlefinanceiro.domain.entidade.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Optional<Despesa> findByNumeroProtocolo(String numeroProtocolo);
}
