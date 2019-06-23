package br.ifpe.web.missao01;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoDAO extends JpaRepository<Evento, Integer> {
	
	public List<Evento> findByNomeContainingIgnoreCase(String nomePesquisa);
}
