package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

/*
- Representa uma ONG cadastrada no sitema
- herda os campos comuns de Usuario (nome, email, senha, telefone)
- Uma ONG pode ter varias campanhas associadas a ela
*/

@Data
@Entity
@Table(name = "ongs")
@EqualsAndHashCode(callSuper = true) // Incluiu os tipos da classe pai (Usuario) no equals e hashCode

public class ONG extends Usuario {

    @NotBlank(message = "CNPJ é obrigatório") // Não permite CNPJ vazio ou nulo
    private String cnpj;

    @JsonIgnore // Oculta a lista de campanhas para evitar referência circular na resposta JSON
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL) // Uma ONG tem muitas campanhas
    private List<Campanha> campanhas;
}
