package ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;

@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepositorio;
 
    @PostMapping (value = "/usuario")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity _user = usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
}