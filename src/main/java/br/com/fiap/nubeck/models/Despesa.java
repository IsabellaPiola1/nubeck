package br.com.fiap.nubeck.models;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Despesa {

    private Long id; 
    private BigDecimal valor;
    private LocalDate data;
    private String descricao;

    
    public Despesa(Long id, BigDecimal valor, LocalDate data, String descricao) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return "Despesa [id=" + id + ", valor=" + valor + ", data=" + data + ", descricao=" + descricao + "]";
    }
    public long size() {
        return 0;
    }

    
    




}