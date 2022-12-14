package sop.ce.gov.financeiro.api.controller;

import com.pedro5722.controlefinanceiro.api.dto.EmpenhoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sop.ce.gov.financeiro.domain.entidade.Empenho;
import sop.ce.gov.financeiro.domain.exceptions.CantDeleteEmpenho;
import sop.ce.gov.financeiro.domain.services.EmpenhoServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/empenho")
public class EmpenhoController {
    @Autowired
    private EmpenhoServices empenhoServices;

    @GetMapping()
    public ResponseEntity<List<Empenho>> findAll(){
        List<Empenho> empenhos = empenhoServices.findAll();
        return ResponseEntity.ok().body(empenhos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Empenho> findById(@PathVariable Long id){
        Empenho empenho = empenhoServices.findById(id);
        return ResponseEntity.ok(empenho);
    }
    @PostMapping
    public ResponseEntity<Empenho> save(@RequestBody EmpenhoDTO empenhoDTO){
        Empenho empenho = empenhoServices.save(empenhoDTO);
        return ResponseEntity.ok(empenho);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            empenhoServices.deleteById(id);
            return new ResponseEntity<String>("Empenho excluido com sucesso", HttpStatus.OK);
        }catch (CantDeleteEmpenho e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
