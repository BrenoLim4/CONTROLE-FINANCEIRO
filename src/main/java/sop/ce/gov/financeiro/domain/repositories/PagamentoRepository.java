package sop.ce.gov.financeiro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sop.ce.gov.financeiro.domain.entidade.Pagamento;


public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
