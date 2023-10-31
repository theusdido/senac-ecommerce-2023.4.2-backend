package ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.AuthenticationDTO;
import ecommerce.ecommerce.model.LoginResponseDTO;
import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.service.TokenService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    @Autowired
    public AuthenticationManager auth_manager;

    @Autowired
    private TokenService token_service;

    @PostMapping(value="/auth")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth    = this.auth_manager.authenticate(usernamePassword);
            var token   = token_service.generateToken((UsuarioEntity) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch(Exception e){
            return ResponseEntity.ok().build();
        }   

        

    }
    
    @GetMapping(value="/auth/verifytoken/{token}")
    public ResponseEntity verifyToken(@PathVariable String token){
        String _token = token_service.validateToken(token);
        return ResponseEntity.ok().body((_token == "" ? false : true));
    }
    
}