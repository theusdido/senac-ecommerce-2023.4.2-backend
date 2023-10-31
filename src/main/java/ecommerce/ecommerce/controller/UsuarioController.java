package ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.RegisterDTO;
import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;
import io.micrometer.core.ipc.http.HttpSender.Response;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuario_repositorio;
    
    @GetMapping(value="/usuario/load/{id}")
    public Optional<UsuarioEntity> get(@PathVariable Long id){
        Optional<UsuarioEntity> _user = usuario_repositorio.findById(id);
        return _user;
    }
    
    @PostMapping (value = "/usuario/salvar")
    public ResponseEntity salvar(@RequestBody RegisterDTO data){
        if (this.usuario_repositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity new_user = new UsuarioEntity(data.nome(), data.email(),encryptedPassword,data.role());

        this.usuario_repositorio.save(new_user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
    }    

    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> listar() {
        return usuario_repositorio.findAll();
    }
    
    @DeleteMapping(path ={"/usuario/{id}"})
    public ResponseEntity <?> deletar(@PathVariable long id) {
        usuario_repositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping (value = "/usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(
        @RequestBody UsuarioEntity usuario,
        @PathVariable Long id
    ) {
        UsuarioEntity _user = usuario_repositorio.findById(id).get();
        _user = usuario;
        usuario_repositorio.save(_user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/usuario/pesquisar/{termo}")
    public ResponseEntity pesquisar(
        @PathVariable String termo
    ){
        List<UsuarioEntity> list = this.usuario_repositorio
        .findByNomeContaining(termo);

        return ResponseEntity.ok(list);
    } 
}