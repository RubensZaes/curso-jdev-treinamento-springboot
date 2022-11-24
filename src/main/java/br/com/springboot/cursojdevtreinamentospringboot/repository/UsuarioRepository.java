package br.com.springboot.cursojdevtreinamentospringboot.repository;

import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAll(Pageable pageable);
    @Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
    Page<Usuario> findUsuarioByNomeIgnoreCase(String name, Pageable pageable);
}
