package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "ongs")
@EqualsAndHashCode(callSuper = true)

public class ONG extends Usuario {

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL)
    private List<Campanha> campanhas;
}
