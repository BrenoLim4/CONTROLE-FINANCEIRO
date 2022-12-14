package sop.ce.gov.financeiro.domain.services;

import com.pedro5722.controlefinanceiro.api.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sop.ce.gov.financeiro.domain.entidade.Pagamento;
import sop.ce.gov.financeiro.domain.repositories.EmpenhoRepository;
import sop.ce.gov.financeiro.domain.repositories.PagamentoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PagamentoServices {
    private final PagamentoRepository pagamentoRepository;
    private final EmpenhoRepository empenhoRepository;

    public Pagamento save(PagamentoDTO pagamentoDto){
        Long idEmpenho = empenhoRepository.findByNumeroAndAno(pagamentoDto.getNumeroEmpenho(), pagamentoDto.getAnoEmpenho()).get().getId();
        Pagamento pagamento = Pagamento.builder()
                .ano(pagamentoDto.getAno())
                .numero(pagamentoDto.getNumero())
                .data(pagamentoDto.getData())
                .valor(pagamentoDto.getValor())
                .observacao(pagamentoDto.getObservacao())
                .idEmpenho(idEmpenho)
                .build();

        return pagamentoRepository.save(pagamento);
    }

    public void deleteById(Long id){
        pagamentoRepository.deleteById(id);
    }


    public Pagamento findById(Long id){
        return pagamentoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Pagamento> findAll(Pagamento pagamento){
        return pagamentoRepository.findAll();
    }
    public List<Pagamento> findAll(){
        return pagamentoRepository.findAll();
    }



}
