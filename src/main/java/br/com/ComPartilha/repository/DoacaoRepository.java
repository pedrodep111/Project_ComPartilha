package br.com.ComPartilha.repository;

import br.com.ComPartilha.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
- Repositório responsável pelas operações de banco de dados da Doacao.
- Herda os métodos prontos do JpaRepository:
- save(), findById(), findAll(), deleteById(), etc.
- Possui também um método customizado para buscar doações por doador
*/

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    /*
    - Busca todas doacoes de um doador específico
    - O Spring gera a query automaticamente pelo nome de metodo:
    - "findBy" + "Doador" + "Id" → WHERE ong_id = ?
    - @param doadorId - ID da ONG
    - @return - Lista de campanhas da ONG
     */

    List<Doacao> findByDoadorId(Long doadorId);
}