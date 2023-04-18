package br.com.fiap.nubeck.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.nubeck.models.Conta;
import br.com.fiap.nubeck.models.Despesa;
import br.com.fiap.nubeck.repository.ContaRepository;
import br.com.fiap.nubeck.repository.DespesaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public void run(String... args) throws Exception {

        Conta c1 = new Conta(1L, "itau", new BigDecimal(100), "money");
        Conta c2 = new Conta(2L, "bradesco", new BigDecimal(5), "money");
        Conta c3 = new Conta(3L, "carteira", new BigDecimal(1), "coin");
        contaRepository.saveAll(List.of(c1, c2, c3));

        despesaRepository.saveAll(List.of(
            Despesa.builder().valor(new BigDecimal(100)).descricao("aluguel").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(50)).descricao("cinema").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(54)).descricao("estacionamento").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(45)).descricao("restaurante").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(87)).descricao("ifood").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(79)).descricao("tarifa").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(85)).descricao("imposto").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(69)).descricao("seguro").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(96)).descricao("cinema").data(LocalDate.now()).conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(10)).descricao("netflix").data(LocalDate.now()).conta(c1).build()
        ));


    }
    
}