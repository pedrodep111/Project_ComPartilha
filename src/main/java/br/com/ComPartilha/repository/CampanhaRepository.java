package br.com.ComPartilha.repository;

import br.com.ComPartilha.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
- Repositório responsável pelas operações de banco de dados da Campanha.
- Herda os métodos prontos do JpaRepository:
- save(), findById(), findAll(), deleteById(), etc.
- Possui também um método customizado para buscar campanhas por ONG.
 */

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    /*
     - Busca todas as campanhas de uma ONG específica.
     - O Spring gera a query automaticamente pelo nome do método:
     - "findBy" + "Ong" + "Id" → WHERE ong_id = ?
     - @param ongId - ID da ONG
     - @return - lista de campanhas da ONG
     */

    List<Campanha> findByOngId(Long ongId);
}