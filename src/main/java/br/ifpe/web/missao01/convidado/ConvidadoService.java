package br.ifpe.web.missao01.convidado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoDAO convidadoRep;
	
public void salvar(Convidado convidado) throws Exception {
		
		if(this.findByConvidado(convidado.getNome()) != null) {
			throw new ConvidadoExistsException("Já existe convidado com esse nome: ");
		}
		convidadoRep.save(convidado);
	}
	
	//VERIFICANDO SE JÁ EXISTE O NOME DO CONVIDADO
	public String findByConvidado(String nome) {
		return convidadoRep.findByConvidado(nome);
	}
	
	//LISTANDO TODO CONVIDADOS
	public List<Convidado> ListarTodos(){
		return convidadoRep.findAll();
	}
}
