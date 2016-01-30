package org.domain.sisescolar.dto;

import java.util.List;

public class PeriodoDTO implements Comparable<PeriodoDTO> {
	
	private int id;

	private int ano;

	private int semestre;

	private List<DisciplinaDTO> disciplinaDTO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<DisciplinaDTO> getDisciplinaDTO() {
		return disciplinaDTO;
	}

	public void setDisciplinaDTO(List<DisciplinaDTO> disciplinaDTO) {
		this.disciplinaDTO = disciplinaDTO;
	}

	@Override
	public int compareTo(PeriodoDTO novo) {
		int i = new Integer(getAno()).compareTo(novo.getAno());
		if (i != 0) return i;
		
		i = new Integer(getSemestre()).compareTo(novo.getSemestre());
		if (i != 0) return i;
		
		return Integer.compare(getAno(), novo.getAno());
	}

}
