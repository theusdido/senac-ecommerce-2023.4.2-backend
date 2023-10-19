package ecommerce.ecommerce.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@Table(name = "categoria")
@Entity(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class CategoriaEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String descricao;

    public CategoriaEntity(String descricao){
        this.descricao = descricao;
    }
}