package br.ifpe.web.missao01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoDAO extends JpaRepository<Evento, Integer> {

}
