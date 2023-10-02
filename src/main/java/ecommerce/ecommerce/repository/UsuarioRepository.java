package ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecommerce.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
}