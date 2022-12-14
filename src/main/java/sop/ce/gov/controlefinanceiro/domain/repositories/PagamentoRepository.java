package sop.ce.gov.controlefinanceiro.domain.repositories;

import sop.ce.gov.controlefinanceiro.domain.entidade.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
