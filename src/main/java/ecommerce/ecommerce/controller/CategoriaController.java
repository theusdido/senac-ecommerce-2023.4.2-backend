package ecommerce.ecommerce.controller;

import java.util.List;

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

import ecommerce.ecommerce.model.CategoriaDTO;
import ecommerce.ecommerce.model.CategoriaEntity;
import ecommerce.ecommerce.repository.CategoriaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoriaController {

    @Autowired
    public CategoriaRepository categoria_repositorio;

    @PostMapping (value = "/categoria/salvar")
    public ResponseEntity salvar(@RequestBody CategoriaDTO data){

        try{
            CategoriaEntity _categoria = new CategoriaEntity(data.descricao());
            this.categoria_repositorio.save(_categoria);
            return new ResponseEntity<>(_categoria, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value="/categoria/listar")
    public List<CategoriaEntity> listar() {
        return categoria_repositorio.findAll();
    }

    @GetMapping(value="/categoria/load/{id}")
    public ResponseEntity load(@PathVariable Long id) {
        return ResponseEntity.ok(categoria_repositorio.findById(id));
    }    

    @PutMapping(value="/categoria/editar/{id}")
    public ResponseEntity editar(@RequestBody CategoriaEntity dados){
        this.categoria_repositorio.save(dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path ={"/categoria/excluir/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        this.categoria_repositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }
}