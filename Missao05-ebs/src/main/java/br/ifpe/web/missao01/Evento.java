package br.ifpe.web.missao01;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@NotBlank(message="O nome deve ser preenchido com texto válido!")
	private String nome;
	private String descricao;
	@DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull(message="informe uma data.") @FutureOrPresent(message="infome uma data atual ou posterior!")
	private LocalDate data_realizacao;
	@Min(value=10, message="quantidade de convidados invalido, deve ter no minimo 10 convidados.")
	private int quant_max_convidados;
	private String responsavel;
	private String telefone;
	@Min(value=1, message="Duração de hora, deve ser maior que zero.")
	private int duracao_horas;
	@ManyToOne @NotNull(message="O evento precisa de um local!")
	private LocalEvento localEvento;
	
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
	public int getDuracao_horas() {
		return duracao_horas;
	}
	public void setDuracao_horas(int duracao_horas) {
		this.duracao_horas = duracao_horas;
	}
	public LocalEvento getLocalEvento() {
		return localEvento;
	}
	public void setLocalEvento(LocalEvento localEvento) {
		this.localEvento = localEvento;
	}
	
	
	
	
}
