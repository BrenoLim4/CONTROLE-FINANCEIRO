package sop.ce.gov.financeiro.api.controller;

import com.pedro5722.controlefinanceiro.api.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sop.ce.gov.financeiro.domain.entidade.Pagamento;
import sop.ce.gov.financeiro.domain.services.PagamentoServices;

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
