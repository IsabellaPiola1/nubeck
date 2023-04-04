package br.com.fiap.nubeck.controllers;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.nubeck.exception.RestNotFoundException;
import br.com.fiap.nubeck.models.Despesa;
import br.com.fiap.nubeck.repository.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

    Logger log = LoggerFactory.getLogger(DespesaController.class);

    @Autowired
    DespesaRepository repository; //IoD

    @GetMapping
    public List<Despesa> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Despesa> create(
            @RequestBody @Valid Despesa despesa, 
            BindingResult result
        ){
        log.info("cadastrando despesa: " + despesa);
        repository.save(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("{id}")
    public ResponseEntity<Despesa> show(@PathVariable Long id){
        log.info("buscando despesa: " + id);
        return ResponseEntity.ok(getDespesa(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id){
        log.info("apagando despesa: " + id);
        repository.delete(getDespesa(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Despesa> update(
        @PathVariable Long id, 
        @RequestBody @Valid Despesa despesa
    ){
        log.info("atualizando despesa: " + id);
        getDespesa(id);
        despesa.setId(id);
        repository.save(despesa);
        return ResponseEntity.ok(despesa);
    }

    private Despesa getDespesa(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new RestNotFoundException("despesa não encontrada"));
    }
    
}