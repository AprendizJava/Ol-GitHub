package br.edu.devmedia.sis_escolar.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_FREQUENCIA")
public class Frequencia {

	@Id
	@Column(name = "id_frequencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer qtde_aulas;
	private Integer freq_1;
	private Integer freq_2;
	private Integer freq_3;
	private Integer freq_4;
	private Integer freq_5;

	@ManyToOne
	@JoinColumn(name = "id_periodo")
	private Periodo periodo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtde_aulas() {
		return qtde_aulas;
	}

	public void setQtde_aulas(Integer qtde_aulas) {
		this.qtde_aulas = qtde_aulas;
	}

	public Integer getFreq_1() {
		return freq_1;
	}

	public void setFreq_1(Integer freq_1) {
		this.freq_1 = freq_1;
	}

	public Integer getFreq_2() {
		return freq_2;
	}

	public void setFreq_2(Integer freq_2) {
		this.freq_2 = freq_2;
	}

	public Integer getFreq_3() {
		return freq_3;
	}

	public void setFreq_3(Integer freq_3) {
		this.freq_3 = freq_3;
	}

	public Integer getFreq_4() {
		return freq_4;
	}

	public void setFreq_4(Integer freq_4) {
		this.freq_4 = freq_4;
	}

	public Integer getFreq_5() {
		return freq_5;
	}

	public void setFreq_5(Integer freq_5) {
		this.freq_5 = freq_5;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}
