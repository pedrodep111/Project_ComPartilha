package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser maior que zero")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "campanha_Id")
    private Campanha campanha;
}
