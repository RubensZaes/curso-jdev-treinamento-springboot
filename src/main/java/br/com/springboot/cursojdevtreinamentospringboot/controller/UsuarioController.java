package br.com.springboot.cursojdevtreinamentospringboot.controller;

import br.com.springboot.cursojdevtreinamentospringboot.dto.UsuarioDTO;
import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import br.com.springboot.cursojdevtreinamentospringboot.repository.UsuarioRepository;
import br.com.springboot.cursojdevtreinamentospringboot.service.UsuarioService;
import br.com.springboot.cursojdevtreinamentospringboot.utils.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("listartodos")
    public ResponseModel<UsuarioDTO> listarUsuarios (Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("buscarporid")
    public ResponseEntity<?> buscarUsuario(@RequestParam(name = "idUser") Long idUser){
        var usuario = usuarioService.buscarUsuario(idUser).orElse(null);
        return Objects.nonNull(usuario) ? new ResponseEntity<>(usuario, HttpStatus.OK) : new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("cadastrarusuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("atualizar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
        if ((usuario.getId() != null) && (usuarioService.buscarUsuario(usuario.getId()).isPresent())) {
            usuarioService.atualizarUsuario(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>("Id não informado pra atualização.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deletarUsuario(@RequestParam Long idUser){
        var usuario = usuarioService.buscarUsuario(idUser).orElse(null);
        if (usuario != null) {
            usuarioService.deletarUsuario(idUser);
            return new ResponseEntity<String>("Usuaário deletado com sucesso.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }
}
