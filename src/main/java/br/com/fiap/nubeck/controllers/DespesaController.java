package br.com.fiap.nubeck.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void create(@RequestBody Despesa despesa){
        log.info("cadstrar despesa: " + despesa);
        despesas.add(despesa);
    }
    
}
