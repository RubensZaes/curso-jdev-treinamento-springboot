package br.com.springboot.cursojdevtreinamentospringboot.service;

import br.com.springboot.cursojdevtreinamentospringboot.dto.UsuarioDTO;
import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import br.com.springboot.cursojdevtreinamentospringboot.repository.UsuarioRepository;
import br.com.springboot.cursojdevtreinamentospringboot.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseModel<UsuarioDTO> listarUsuarios(Pageable pageable) {
        ResponseModel<UsuarioDTO> responseModel = new ResponseModel<>(HttpStatus.OK.name());
        Page<UsuarioDTO> usuarios = usuarioRepository.findAll(pageable).map(UsuarioDTO::new);

        responseModel.setList(usuarios.getContent());
        responseModel.setTotalElements(usuarios.getTotalElements());
        responseModel.setTotalPages(usuarios.getTotalPages());

        return responseModel;
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.saveAndFlush(usuario);
    }
    @Transactional
    public void deletarUsuario(Long idUser) {
        usuarioRepository.deleteById(idUser);
    }

}
