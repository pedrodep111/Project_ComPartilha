package br.com.ComPartilha.repository;

import br.com.ComPartilha.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
- Repositório responsável pelas operações de banco de dados do Doador.
- Herda os métodos prontos do JpaRepository:
        save(), findById(), findAll(), deleteById(), etc.
*/

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
}