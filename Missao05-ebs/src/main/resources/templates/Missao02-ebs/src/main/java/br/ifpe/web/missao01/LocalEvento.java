package br.ifpe.web.missao01;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocalEvento {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer codigo;
	public String nome;
	public String endereco;
	public String link;
	public String capacidade;
	public Double ambiente;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(String capacidade) {
		this.capacidade = capacidade;
	}
	public Double getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(Double ambiente) {
		this.ambiente = ambiente;
	}
	
	
}
