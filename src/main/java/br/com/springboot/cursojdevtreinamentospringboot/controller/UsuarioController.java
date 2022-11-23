package br.com.springboot.cursojdevtreinamentospringboot.controller;

import br.com.springboot.cursojdevtreinamentospringboot.dto.UsuarioDTO;
import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import br.com.springboot.cursojdevtreinamentospringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("listartodos")
    public Page<UsuarioDTO> listarUsuarios(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
    }

    @PostMapping("cadastrarusuario")
    @Transactional
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(new Usuario(usuario.getNome(), usuario.getIdade()));
    }
    @DeleteMapping("{id}")
    @Transactional
    public void deletarUsuario(@RequestParam Long idUser){
        usuarioRepository.deleteById(idUser);
    }
}
