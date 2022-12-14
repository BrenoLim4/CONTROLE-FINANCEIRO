package sop.ce.gov.financeiro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sop.ce.gov.financeiro.domain.entidade.StatusDespesa;

public interface StatusDespesaRepository extends JpaRepository<StatusDespesa, Integer> {
}
