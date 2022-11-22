package br.com.springboot.cursojdevtreinamentospringboot.repository;

import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
