package br.edu.devmedia.sis_escolar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PERIODO")
public class Periodo {

	@Id
	@Column(name = "id_periodo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer semestre;
	private Integer ano;
	
	@OneToMany(mappedBy = "periodo")
	private List<Frequencia> frequencias;
	
	@OneToMany(mappedBy = "periodo")
	private List<Extrato> extratos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}

	public List<Extrato> getExtratos() {
		return extratos;
	}

	public void setExtratos(List<Extrato> extratos) {
		this.extratos = extratos;
	}

}
