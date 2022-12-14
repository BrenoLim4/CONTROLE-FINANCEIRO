package sop.ce.gov.controlefinanceiro.domain.repositories;

import sop.ce.gov.controlefinanceiro.domain.entidade.Empenho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpenhoRepository extends JpaRepository<Empenho, Long> {

    //List<Empenho> findDataBetween(String dataInicial, String dataFinal);

    Optional<Empenho> findByNumeroAndAno(Integer numero, Integer ano);

}
