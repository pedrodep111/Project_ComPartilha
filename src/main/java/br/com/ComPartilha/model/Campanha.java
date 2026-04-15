package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/*
- Representa uma campanha criada por uma ONG
- Cada campanha pertence a uma ONG e possui uma meta de arrecadação
*/

@Data
@Entity
@Table(name = "campanhas")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente
    private Long id;

    @NotBlank(message = "Título é obrigatório") // Não permite titulo vazio ou nulo
    private String titulo;

    private String descricao; // Descrição opcional da campanha

    @NotNull(message = "Meta é obrigatória") // Não permite meta nula
    @Positive(message = "Meta deve ser maior que zero") // Meta deve ser um valor positivo
    private BigDecimal meta; // Valor alvo de arrecadação

    private BigDecimal valorCaptado; // Valor arrecadado até o momento

    @ManyToOne // Muitas campnhas pertencema a uma ONG
    @JoinColumn(name = "ong_id") // Chave estrangeira na tabela campanhas
    private ONG ong;
}