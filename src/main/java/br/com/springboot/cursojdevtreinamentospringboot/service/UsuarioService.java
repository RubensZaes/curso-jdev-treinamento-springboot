package br.com.springboot.cursojdevtreinamentospringboot.service;

import br.com.springboot.cursojdevtreinamentospringboot.dto.UsuarioDTO;
import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;
import br.com.springboot.cursojdevtreinamentospringboot.repository.UsuarioRepository;
import br.com.springboot.cursojdevtreinamentospringboot.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseModel<UsuarioDTO> listarUsuarios(Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
        return getUsuarioDTOResponseModel(usuarios);
    }
    public ResponseModel<UsuarioDTO> buscarPorNome(String name, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioRepository.findUsuarioByNomeIgnoreCase(name, pageable).map(UsuarioDTO::new);
        return getUsuarioDTOResponseModel(usuarios);
    }

    private static ResponseModel<UsuarioDTO> getUsuarioDTOResponseModel(Page<UsuarioDTO> usuarios) {
        ResponseModel<UsuarioDTO> responseModel = new ResponseModel<>(HttpStatus.OK.name());
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
    @Transactional
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.saveAndFlush(usuario);
    }
    @Transactional
    public void deletarUsuario(Long idUser) {
        usuarioRepository.deleteById(idUser);
    }

}
