package sop.ce.gov.controlefinanceiro.api.controller;

import sop.ce.gov.controlefinanceiro.api.dto.PagamentoDTO;
import sop.ce.gov.controlefinanceiro.domain.entidade.Pagamento;
import sop.ce.gov.controlefinanceiro.domain.services.PagamentoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoServices pagamentoServices;

    @GetMapping()
    public ResponseEntity<List<Pagamento>> findAll(){
        List<Pagamento> pagamentos = pagamentoServices.findAll();
        return ResponseEntity.ok().body(pagamentos);
    }

    @GetMapping("pagamentoId")
    public ResponseEntity<Pagamento> findById(Long id){
        Pagamento pagamento = pagamentoServices.findById(id);
        return ResponseEntity.ok().body(pagamento);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        pagamentoServices.deleteById(id);
        return new ResponseEntity<String>("Pagamento deletado com sucesso", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Pagamento> salvar(@RequestBody PagamentoDTO pagamentoDTO){
        Pagamento pagamento = pagamentoServices.save(pagamentoDTO);
        return ResponseEntity.ok(pagamento);
    }
}
