package sop.ce.gov.financeiro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sop.ce.gov.financeiro.domain.entidade.Empenho;

import java.util.List;
import java.util.Optional;

public interface EmpenhoRepository extends JpaRepository<Empenho, Long> {

    //List<Empenho> findDataBetween(String dataInicial, String dataFinal);

    Optional<Empenho> findByNumeroAndAno(Integer numero, Integer ano);

}
