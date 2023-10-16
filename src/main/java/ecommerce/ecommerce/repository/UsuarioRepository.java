package ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import ecommerce.ecommerce.model.UsuarioEntity;

public interface UsuarioRepository 
extends JpaRepository<UsuarioEntity, Long> {
    //public UsuarioEntity findByEmailAndSenha(String email,String senha);
    UserDetails findByEmail(String email);
}