package br.ifpe.web.missao01;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {
	
	@Id @GeneratedValue
	private Integer codigo;
	private String nome;
	private String descricao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_realizacao;
	private int quant_max_convidados;
	private String responsavel;
	private String telefone;
	private String duracao_horas;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData_realizacao() {
		return data_realizacao;
	}
	public void setData_realizacao(LocalDate data_realizacao) {
		this.data_realizacao = data_realizacao;
	}
	public int getQuant_max_convidados() {
		return quant_max_convidados;
	}
	public void setQuant_max_convidados(int quant_max_convidados) {
		this.quant_max_convidados = quant_max_convidados;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDuracao_horas() {
		return duracao_horas;
	}
	public void setDuracao_horas(String duracao_horas) {
		this.duracao_horas = duracao_horas;
	}
	
	
	
}
