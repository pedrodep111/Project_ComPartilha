package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "doadores")
@EqualsAndHashCode(callSuper = true)

public class Doador extends Usuario{

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doacao> doacoes;

}
