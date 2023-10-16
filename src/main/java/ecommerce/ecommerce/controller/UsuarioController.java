package ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecommerce.model.RegisterDTO;
import ecommerce.ecommerce.model.UsuarioEntity;
import ecommerce.ecommerce.repository.UsuarioRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuario_repositorio;
    
    
    /*
    @PostMapping (value = "/usuario/salvar")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario) {

        AuthController auth = new AuthController();
        
        try{
            String hash = auth.hashSenha(usuario.getSenha());
            usuario.setSenha(hash);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        UsuarioEntity _user = usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
    */

    /*
    @PostMapping (value = "/usuario/salvar")
    public ResponseEntity salvar(@RequestBody RegisterDTO data){
        if (this.usuario_repositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity new_user = new UsuarioEntity(data.nome(), data.email(),encryptedPassword,data.role());

        this.usuario_repositorio.save(new_user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
    }    
    */

    @PostMapping (value = "/usuario/salvar")
    public String salvar(){
        return "TESTE";
    }
    
    /*
    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> getMethodName() {
        return usuarioRepositorio.findAll();
    }
    */
    
    /*
    @DeleteMapping(path ={"/usuario/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    } 
    */   
}