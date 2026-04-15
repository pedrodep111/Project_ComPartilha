package br.com.ComPartilha.repository;

import br.com.ComPartilha.model.ONG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ONGRepository extends JpaRepository<ONG, Long> {
}