package br.com.ComPartilha.model;

import jakarta.persistence.*; // Permite transformar as classes em tabelas do banco de dados // @Entity, @Id, @Column
import lombok.Data; // Biblioteca de produtividade // @Data = gera automaticamente metodos getters, setters, toString e o construtor
import lombok.EqualsAndHashCode; // Gera os metodos equals() e hashCode() // comparar dois objetos e saber se são iguais
import java.util.List; // Interface do java para lidar com listas de objetos
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

/*
- Representa um doador cadastrado no sistema.
- Herda os campos comuns de Usuario (nome, email, senha, telefone)
- Um doador pode realizar varias doacoes
*/

@Data
@Entity
@Table(name = "doadores")
@EqualsAndHashCode(callSuper = true) // Inclui os campos da classe pai (Usuario) no equals e hashCode

public class Doador extends Usuario{

    @NotBlank(message = "CPF é obrigatório") // Não permite CPF vazio ou nulo
    private String cpf;

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL) // Um doador pode ter muitas doações
    @JsonIgnore // Oculta a lista de doações para evitar referência circular na resposta JSON
    private List<Doacao> doacoes;

}
