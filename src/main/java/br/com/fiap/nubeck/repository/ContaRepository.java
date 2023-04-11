package br.com.fiap.nubeck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.nubeck.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    
}