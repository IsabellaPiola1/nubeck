package br.com.fiap.nubeck.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.nubeck.models.Conta;
import br.com.fiap.nubeck.repository.ContaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {

        contaRepository.saveAll(List.of(
            new Conta(1L, "itau", new BigDecimal(100), "money"), 
            new Conta(2L, "bradesco", new BigDecimal(5), "money"), 
            new Conta(3L, "carteira", new BigDecimal(1), "coin")
        ));
    }
    
}