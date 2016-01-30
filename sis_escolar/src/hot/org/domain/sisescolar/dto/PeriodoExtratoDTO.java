package org.domain.sisescolar.dto;

import java.util.List;

import br.edu.devmedia.sis_escolar.entidade.Extrato;

public class PeriodoExtratoDTO implements Comparable<PeriodoExtratoDTO> {

	private int id;

	private int ano;

	private int semestre;

	private List<Extrato> extratos;

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

	public List<Extrato> getExtratos() {
		return extratos;
	}

	public void setExtratos(List<Extrato> extratos) {
		this.extratos = extratos;
	}

	@Override
	public int compareTo(PeriodoExtratoDTO novo) {
		int i = new Integer(getAno()).compareTo(novo.getAno());
		if (i != 0) return i;
		
		i = new Integer(getSemestre()).compareTo(novo.getSemestre());
		if (i != 0) return i;
		
		return Integer.compare(getAno(), novo.getAno());
	}

}
