package ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecommerce.model.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Long>  {

}