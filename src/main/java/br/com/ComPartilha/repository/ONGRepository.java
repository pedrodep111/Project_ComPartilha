package br.com.ComPartilha.repository;

import br.com.ComPartilha.model.ONG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
- Repositório responsável pelas operações de banco de dados da ONG
- Herda os métodos prontos do JpaRepository:
        save(), findById(), findALL(), deleteById(), etc.
- O primeiro parâmetro (ONG) é a classe gerenciada
- O segundo parâmetro (Long) é o tipo do ID
*/

@Repository
public interface ONGRepository extends JpaRepository<ONG, Long> { // ONG = classe que o repositorio vai gerenciar, Long = tipo
}