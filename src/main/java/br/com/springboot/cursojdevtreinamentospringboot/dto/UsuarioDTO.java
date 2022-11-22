package br.com.springboot.cursojdevtreinamentospringboot.dto;

import br.com.springboot.cursojdevtreinamentospringboot.model.Usuario;

public record UsuarioDTO(Long id, int idade, String nome) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getIdade(), usuario.getNome());
    }

}
