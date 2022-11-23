package br.com.springboot.cursojdevtreinamentospringboot.controller;

import br.com.springboot.cursojdevtreinamentospringboot.dto.UsuarioDTO;
import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import br.com.springboot.cursojdevtreinamentospringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("buscarporid")
    public ResponseEntity<?> buscarUsuario(@RequestParam(name = "idUser") Long idUser){
        var usuario = usuarioRepository.findById(idUser).orElse(null);
        if (usuario != null) {
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }
    @PostMapping("cadastrarusuario")
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }
    @PutMapping("atualizar")
    @Transactional
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
        if (usuario.getId() != null) {
            usuarioRepository.saveAndFlush(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Id não informado pra atualização.", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity<String> deletarUsuario(@RequestParam Long idUser){
        var usuario = usuarioRepository.findById(idUser).orElse(null);
        if (usuario != null) {
            usuarioRepository.deleteById(idUser);
            return new ResponseEntity<String>("Usuaário deletado com sucesso.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }
}
