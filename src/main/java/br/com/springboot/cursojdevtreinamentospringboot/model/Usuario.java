package br.com.springboot.cursojdevtreinamentospringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "usuarios")
@Table(name = "usuarios")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;

    public Usuario() {}

    public Usuario(String nome, int idade) {
        this.idade = idade;
        this.nome = nome;
    }
}
