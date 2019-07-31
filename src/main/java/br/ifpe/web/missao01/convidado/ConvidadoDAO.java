package br.ifpe.web.missao01.convidado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvidadoDAO extends JpaRepository<Convidado, Integer> {
	
	@Query("select c from Convidado c where c.nome = :nome")
	public String findByConvidado(String nome);

}
