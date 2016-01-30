package org.domain.sisescolar.dto;

public class DisciplinaDTO {

	private String codDisciplina;
	private String disciplina;
	private String turma;

	private Integer qtde_aulas;
	private Integer presencas_1;
	private Integer presencas_2;
	private Integer presencas_3;
	private Integer presencas_4;
	private Integer presencas_5;
	
	private Double pctFaltas;
	
	private String classPct;

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Integer getQtde_aulas() {
		return qtde_aulas;
	}

	public void setQtde_aulas(Integer qtde_aulas) {
		this.qtde_aulas = qtde_aulas;
	}

	public Integer getPresencas_1() {
		return presencas_1;
	}

	public void setPresencas_1(Integer presencas_1) {
		this.presencas_1 = presencas_1;
	}

	public Integer getPresencas_2() {
		return presencas_2;
	}

	public void setPresencas_2(Integer presencas_2) {
		this.presencas_2 = presencas_2;
	}

	public Integer getPresencas_3() {
		return presencas_3;
	}

	public void setPresencas_3(Integer presencas_3) {
		this.presencas_3 = presencas_3;
	}

	public Integer getPresencas_4() {
		return presencas_4;
	}

	public void setPresencas_4(Integer presencas_4) {
		this.presencas_4 = presencas_4;
	}

	public Integer getPresencas_5() {
		return presencas_5;
	}

	public void setPresencas_5(Integer presencas_5) {
		this.presencas_5 = presencas_5;
	}

	public Double getPctFaltas() {
		// x = (qtde - presencas) x 100 / (qtde_aulas * 5)
		
		int pres = this.presencas_1 + this.presencas_2 + this.presencas_3 + this.presencas_4 + this.presencas_5;
		
		pctFaltas = (double) (((this.qtde_aulas * 5 - pres) * 100)/(qtde_aulas * 5));
		
		return pctFaltas;
	}

	public void setPctFaltas(Double pctFaltas) {
		this.pctFaltas = pctFaltas;
	}

	public String getClassPct() {
		if (getPctFaltas() > 20) {
			return "pctFaltas-red";
		} else if (getPctFaltas() > 15 && getPctFaltas() <= 20) {
			return "pctFaltas-orange";
		} else {
			return "pctFaltas-green";
		}
	}

	public void setClassPct(String classPct) {
		this.classPct = classPct;
	}

}
