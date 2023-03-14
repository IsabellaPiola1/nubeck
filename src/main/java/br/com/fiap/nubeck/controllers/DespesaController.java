package br.com.fiap.nubeck.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

//import java.math.BigDecimal;
//import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.nubeck.models.Despesa;


@RestController
public class DespesaController {

    @GetMapping("/api/v1/despesas")
    public Despesa index(){
        return new Despesa(1l, new BigDecimal(100), LocalDate.now(), "cinema");
    }
    
}
