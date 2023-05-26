package br.com.fiap.nubeck.models;

public record Token(
    String token,
    String type,
    String prefix
) {}