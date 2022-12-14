package sop.ce.gov.controlefinanceiro.api.controller;

import sop.ce.gov.controlefinanceiro.api.dto.DespesaDTO;
import sop.ce.gov.controlefinanceiro.domain.entidade.Despesa;
import sop.ce.gov.controlefinanceiro.domain.entidade.StatusDespesa;
import sop.ce.gov.controlefinanceiro.domain.entidade.TipoDespesa;
import sop.ce.gov.controlefinanceiro.domain.exceptions.CantDeleteDespesa;
import sop.ce.gov.controlefinanceiro.domain.services.DespesaServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/despesa")
public class DespesaController {
    private final DespesaServices despesaServices;
    @GetMapping()
    public ResponseEntity<List<Despesa>> findAll(){
        List<Despesa> despesas = despesaServices.findAll();
        return ResponseEntity.ok().body(despesas);
    };
    @GetMapping("/{id}")
    public ResponseEntity<Despesa> findById(@PathVariable Long id){
        Despesa despesa = despesaServices.findById(id);
        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            despesaServices.deleteById(id);
            return new ResponseEntity<String>("Despesa Excluida com sucesso", HttpStatus.OK);
        } catch (CantDeleteDespesa e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping()
    public ResponseEntity<Despesa> salvar(@RequestBody DespesaDTO despesaDTO){
        Despesa despesa = Despesa.builder()
                .status(new StatusDespesa(despesaDTO.getStatus()))
                .tipo(new TipoDespesa(despesaDTO.getTipo()))
                .id(despesaDTO.getId())
                .valor(despesaDTO.getValor())
                .credor(despesaDTO.getCredor())
                .dataProtocolo(despesaDTO.getDataProtocolo())
                .descricao(despesaDTO.getDescricao())
                .dataVencimento(despesaDTO.getDataVencimento())
                .numeroProtocolo(despesaDTO.getNumeroProtocolo()).build();

        despesa = despesaServices.save(despesa);
        return ResponseEntity.ok(despesa);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Despesa> update(@RequestBody DespesaDTO despesaDTO){
        Despesa despesa = Despesa.builder()
                .status(new StatusDespesa(despesaDTO.getStatus()))
                .tipo(new TipoDespesa(despesaDTO.getTipo()))
                .id(despesaDTO.getId())
                .valor(despesaDTO.getValor())
                .credor(despesaDTO.getCredor())
                .dataProtocolo(despesaDTO.getDataProtocolo())
                .descricao(despesaDTO.getDescricao())
                .dataVencimento(despesaDTO.getDataVencimento())
                .numeroProtocolo(despesaDTO.getNumeroProtocolo()).build();

        despesa = despesaServices.save(despesa);
        return ResponseEntity.ok(despesa);
    }


    @GetMapping("/lista-filtrada")
    public List<Despesa> getFilteredList(DespesaDTO despesaDTO){
        return despesaServices.findAll(despesaDTO);
    }
}
