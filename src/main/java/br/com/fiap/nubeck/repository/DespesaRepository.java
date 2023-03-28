package br.com.fiap.nubeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.nubeck.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
    
}
