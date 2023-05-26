package br.com.fiap.nubeck.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.nubeck.models.Credencial;
import br.com.fiap.nubeck.models.Token;
import br.com.fiap.nubeck.models.Usuario;
import br.com.fiap.nubeck.repository.UsuarioRepository;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                    .withSubject(credencial.email())
                    .withIssuer("Nubeck")
                    .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                    .sign(alg)
                    ;
        return new Token(token, "JWT", "Bearer");
    }

    public Usuario getUserByToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                    .withIssuer("Nubeck")
                    .build()
                    .verify(token)
                    .getSubject();
                ;
        return usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }
    
}