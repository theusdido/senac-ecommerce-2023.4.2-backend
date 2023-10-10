package ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepositorio;
    
    @GetMapping(value="/usuario/load/{id}")
    public Optional<UsuarioEntity> get(@PathVariable Long id){
        Optional<UsuarioEntity> _user = usuarioRepositorio.findById(id);
        return _user;
    }
    
    @PostMapping (value = "/usuario")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity _user = usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
    

    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> listar() {
        return usuarioRepositorio.findAll();
    }
    
    @DeleteMapping(path ={"/usuario/{id}"})
    public ResponseEntity <?> deletar(@PathVariable long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping (value = "/usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(
        @RequestBody UsuarioEntity usuario,
        @PathVariable Long id
    ) {
        UsuarioEntity _user = usuarioRepositorio.findById(id).get();
        _user = usuario;
        usuarioRepositorio.save(_user);
        return ResponseEntity.ok().build();
    }
}