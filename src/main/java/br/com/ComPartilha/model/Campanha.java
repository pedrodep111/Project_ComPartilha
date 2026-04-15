package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Entity
@Table(name = "campanhas")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Meta é obrigatória")
    @Positive(message = "Meta deve ser maior que zero")
    private BigDecimal meta;

    private BigDecimal valorCaptado;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    private ONG ong;
}