package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
- Classe base abstrata que represnta um usuario do sistema.
- ONG e Doador herdam desta classe, compartilhando os campos comuns
- A estrategia JOINED cria uma tabela separada para cada subclasse, mas mantem os campos comuns na tabela
*/

@Data // Gera getters e setters automaticamente
@Entity // Avisa o Hibernate: "essa classe precisa existir como tabela no banco de dados."
@Table(name = "usuarios") // Define o nome da tabela no banco de dados
@Inheritance(strategy = InheritanceType.JOINED) // Herança: cada subclasse tem sua prorpia tabela no banco
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Evita erros de serialização do hibernate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public abstract class Usuario {
    @Id // Esse campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O ID é gerado automaticamente (1, 2, 3...)
    private Long id;

    @NotBlank(message = "Nome é obrigatório") // Não permite nome vazio ou nulo
    private String nome;

    @NotBlank(message = "Email é obrigatório") // Não permite email vazio ou nulo
    @Email(message = "Email inválido") // Valida o formato do email
    private String email;

    @JsonIgnore // Oculta a senha nas respostas da API
    private String senha;

    private String telefone;
}