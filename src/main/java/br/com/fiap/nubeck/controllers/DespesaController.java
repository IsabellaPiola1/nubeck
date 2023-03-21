package br.com.fiap.nubeck.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.nubeck.models.Despesa;


@RestController
public class DespesaController {

    Logger log = LoggerFactory.getLogger(DespesaController.class);

    List<Despesa> despesas = new ArrayList<>();

    @GetMapping("/api/v1/despesas")
    public List<Despesa> index(){
        return despesas;
    }

    @PostMapping("/api/v1/despesas")
    public ResponseEntity<Despesa> create(@RequestBody Despesa despesa){
        log.info("cadstrar despesa: " + despesa);
        despesa.setId(despesa.size() + 1L);
        despesas.add(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("/api/v1/despesas/{id}")
    public ResponseEntity<Despesa> show(@PathVariable Long id){
        log.info("buscando despesa: " + id);
        //verificar se existe o Array
        var despesaEncontrada = despesas.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(despesaEncontrada.get());
    }

    @DeleteMapping("/api/v1/despesas/{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id){
        log.info("apagando despesa: " + id);
        var despesaEncontrada = despesas.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //no content 200 + 04
    }

    @PutMapping("/api/v1/despesas/{id}")
    public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa despesa){
        log.info("atualizando despesa: " + id);
        var despesaEncontrada = despesas.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(despesaEncontrada.isEmpty()) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        despesas.remove(despesaEncontrada.get());
        despesa.setId(id);
        despesa.add(despesa);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //no content 200 + 04
    }


    
}
