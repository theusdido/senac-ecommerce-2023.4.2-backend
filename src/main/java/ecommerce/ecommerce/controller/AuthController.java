package ecommerce.ecommerce.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.json.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.AutenticacaoModel;
import ecommerce.ecommerce.model.AuthenticationDTO;
import ecommerce.ecommerce.model.RegisterDTO;
import ecommerce.ecommerce.model.LoginResponseDTO;
import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;
import ecommerce.ecommerce.service.TokenService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    @Autowired
    public UsuarioRepository repositorio;

    @Autowired
    public AuthenticationManager auth_manager;

    @Autowired
    private TokenService token_service;

    /*
    @PostMapping
    public boolean post(@RequestBody AutenticacaoModel auth)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        UsuarioEntity user = repositorio.findByEmailAndSenha(
            auth.getEmail(),this.hashSenha(auth.getSenha())
        );
        return user == null ? false : true;
    }
    */
    
    /*
    public String hashSenha(String senha) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

       StringBuilder hexString = new StringBuilder();
       for (byte b : messageDigest) {
         hexString.append(String.format("%02X", 0xFF & b));
       }
       String senhahex = hexString.toString().toLowerCase();  
       return senhahex;    
    }    
    */
    /*
    @PostMapping(value="/auth")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.auth_manager.authenticate(usernamePassword);
        //var token = token_service.generateToken((UsuarioEntity) auth.getPrincipal());

        //return ResponseEntity.ok(new LoginResponseDTO(token));
        return ResponseEntity.ok().build();
    }
    */

    @PostMapping(value="/auth")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth    = this.auth_manager.authenticate(usernamePassword);
        var token   = token_service.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @GetMapping(value="/auth/verifytoken")
    public boolean verifyToken(@RequestParam String token){
        String _token = token_service.validateToken(token);
        return _token == "" ? false : true;
    }
}
