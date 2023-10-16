package ecommerce.ecommerce.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import ecommerce.ecommerce.model.UsuarioEntity;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioEntity usuario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("ecommerce-senac")
                .withSubject(usuario.getEmail())
                .withExpiresAt(genExpirationDate())
                .sign(algoritmo);
            return token;
        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro na geração do token", exception);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer("ecommerce-senac")
                .build()
                .verify(token)
                .getSubject();
        }catch(JWTVerificationException exception){
            return "";
        }
    }
}