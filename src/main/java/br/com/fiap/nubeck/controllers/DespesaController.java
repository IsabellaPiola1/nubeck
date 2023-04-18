package br.com.fiap.nubeck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.nubeck.exception.RestNotFoundException;
import br.com.fiap.nubeck.models.Despesa;
import br.com.fiap.nubeck.repository.ContaRepository;
import br.com.fiap.nubeck.repository.DespesaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/despesas")
@Slf4j
public class DespesaController {

    @Autowired
    DespesaRepository despesaRespository; // IoD

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<Despesa> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        if (busca == null)
            return despesaRespository.findAll(pageable);
        return despesaRespository.findByDescricaoContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<Despesa> create(
            @RequestBody @Valid Despesa despesa,
            BindingResult result) {
        log.info("cadastrando despesa: " + despesa);
        despesaRespository.save(despesa);
        despesa.setConta(contaRepository.findById(despesa.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("{id}")
    public ResponseEntity<Despesa> show(@PathVariable Long id) {
        log.info("buscando despesa: " + id);
        return ResponseEntity.ok(getDespesa(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id) {
        log.info("apagando despesa: " + id);
        despesaRespository.delete(getDespesa(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Despesa> update(
            @PathVariable Long id,
            @RequestBody @Valid Despesa despesa) {
        log.info("atualizando despesa: " + id);
        getDespesa(id);
        despesa.setId(id);
        despesaRespository.save(despesa);
        return ResponseEntity.ok(despesa);
    }

    private Despesa getDespesa(Long id) {
        return despesaRespository.findById(id).orElseThrow(
                () -> new RestNotFoundException("despesa não encontrada"));
    }

}