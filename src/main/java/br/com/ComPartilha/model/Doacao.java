package br.com.ComPartilha.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

/*
- Representa uma doação realizada por um doador para uma campanha
- Cada doação está vinculada a um doador e a uma campanha específica
*/

@Data
@Entity
@Table(name = "doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id gerado automaticamente
    private long id;

    @NotNull(message = "Valor é obrigatório") // Não permite valor nulo
    @Positive(message = "Valor deve ser maior que zero") // Valor deve ser positivo
    private BigDecimal valor;

    @ManyToOne // Muitas doações pertencem a um Doador
    @JoinColumn(name = "doador_id") // Chave estrangeira na tabela doacoes
    private Doador doador;

    @ManyToOne // Muitas doacoes pertencem a uma campanha
    @JoinColumn(name = "campanha_Id") // Chave estrageira na tabela doacoes
    private Campanha campanha;
}
