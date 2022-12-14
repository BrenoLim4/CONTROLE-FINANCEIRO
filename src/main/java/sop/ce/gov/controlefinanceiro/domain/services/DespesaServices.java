package sop.ce.gov.controlefinanceiro.domain.services;

import sop.ce.gov.controlefinanceiro.api.dto.DespesaDTO;
import sop.ce.gov.controlefinanceiro.domain.entidade.Despesa;
import sop.ce.gov.controlefinanceiro.domain.entidade.StatusDespesa;
import sop.ce.gov.controlefinanceiro.domain.entidade.TipoDespesa;
import sop.ce.gov.controlefinanceiro.domain.exceptions.CantDeleteDespesa;
import sop.ce.gov.controlefinanceiro.domain.repositories.DespesaRepository;
import sop.ce.gov.controlefinanceiro.domain.repositories.TipoDespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class DespesaServices {
    private final DespesaRepository despesaRepository;
    private final TipoDespesaRepository tipoDespesaRepository;

    public Despesa save(Despesa despesa){
        if(despesa.getId() == null){
            despesa.setStatus(new StatusDespesa(1));
        }
        return despesaRepository.save(despesa);
    }

    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }
    public List<Despesa> findAll(DespesaDTO despesaDTO){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Despesa despesa = Despesa.builder()
                .dataProtocolo(despesaDTO.getDataProtocolo())
                .tipo(new TipoDespesa(despesaDTO.getTipo()))
                .credor(despesaDTO.getCredor())
                .build();
        Example<Despesa> example = Example.of(despesa, matcher);
        return despesaRepository.findAll(example);
    }
    public Despesa findById(Long id){
        return despesaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id)throws CantDeleteDespesa {
        Despesa despesa = findById(id);

        // Não deve ser permitido deletar uma Despesa que tenha ao menos um Empenho assoaciado.
        if (despesa.getEmpenhos().isEmpty()){
            despesaRepository.deleteById(id);
        }else{
            throw new CantDeleteDespesa("Impossivel deletar: existem empenhos associados à despesa.");
        }
    }

    public Despesa findByNumeroProtocolo(String numeroProtocolo){
        return despesaRepository.findByNumeroProtocolo(numeroProtocolo).orElseThrow(RuntimeException::new);
    }

    public List<TipoDespesa> findAllTipoDespesa(){
        return tipoDespesaRepository.findAll();
    }

    public List<TipoDespesa> findAllStatusDespesa(){
        return tipoDespesaRepository.findAll();
    }
}
