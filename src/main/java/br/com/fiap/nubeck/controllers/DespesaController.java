package br.com.fiap.nubeck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        var despesas = (busca == null) ? 
            despesaRespository.findAll(pageable): 
            despesaRespository.findByDescricaoContaining(busca, pageable);

        return assembler.toModel(despesas.map(Despesa::toEntityModel)); //HAL
    }

    @PostMapping
    public ResponseEntity<EntityModel<Despesa>> create(
            @RequestBody @Valid Despesa despesa,
            BindingResult result) {
        log.info("cadastrando despesa: " + despesa);
        despesaRespository.save(despesa);
        despesa.setConta(contaRepository.findById(despesa.getConta().getId()).get());
        return ResponseEntity
            .created(despesa.toEntityModel().getRequiredLink("self").toUri())
            .body(despesa.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Despesa> show(@PathVariable Long id) {
        log.info("buscando despesa: " + id);
        return getDespesa(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Despesa> destroy(@PathVariable Long id) {
        log.info("apagando despesa: " + id);
        despesaRespository.delete(getDespesa(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Despesa>> update(
            @PathVariable Long id,
            @RequestBody @Valid Despesa despesa) {
        log.info("atualizando despesa: " + id);
        getDespesa(id);
        despesa.setId(id);
        despesaRespository.save(despesa);
        return ResponseEntity.ok(despesa.toEntityModel());
    }

    private Despesa getDespesa(Long id) {
        return despesaRespository.findById(id).orElseThrow(
                () -> new RestNotFoundException("despesa não encontrada"));
    }

}