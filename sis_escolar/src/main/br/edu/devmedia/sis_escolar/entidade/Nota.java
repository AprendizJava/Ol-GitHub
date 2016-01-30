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
@Table(name = "TB_NOTA")
public class Nota {

	@Id
	@Column(name = "id_nota")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double nota_1;
	private Double nota_2;
	private Double nota_3;
	private Double nota_4;

	private Double nota_rec;
	private Double nota_final;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getNota_1() {
		return nota_1;
	}

	public void setNota_1(Double nota_1) {
		this.nota_1 = nota_1;
	}

	public Double getNota_2() {
		return nota_2;
	}

	public void setNota_2(Double nota_2) {
		this.nota_2 = nota_2;
	}

	public Double getNota_3() {
		return nota_3;
	}

	public void setNota_3(Double nota_3) {
		this.nota_3 = nota_3;
	}

	public Double getNota_4() {
		return nota_4;
	}

	public void setNota_4(Double nota_4) {
		this.nota_4 = nota_4;
	}

	public Double getNota_rec() {
		return nota_rec;
	}

	public void setNota_rec(Double nota_rec) {
		this.nota_rec = nota_rec;
	}

	public Double getNota_final() {
		return nota_final;
	}

	public void setNota_final(Double nota_final) {
		this.nota_final = nota_final;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
