package br.edu.devmedia.sis_escolar.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_DISCIPLINA")
public class Disciplina {

	@Id
	@Column(name = "id_disciplina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descricao;

	private Integer creditos;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@OneToMany(mappedBy = "disciplina")
	private List<Nota> notas;
	
	@ManyToMany
	@JoinTable(name = "tb_aluno_disciplina", 
		joinColumns = @JoinColumn(name = "id_disciplina"), 
		inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private List<Aluno> alunos;

	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "id_periodo")
	private Periodo periodo;

	@ManyToOne
	@JoinColumn(name = "id_frequencia")
	private Frequencia frequencia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
