package org.domain.sisescolar.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.domain.sisescolar.dto.DisciplinaDTO;
import org.domain.sisescolar.dto.NotaDTO;
import org.domain.sisescolar.dto.PeriodoDTO;
import org.domain.sisescolar.dto.PeriodoExtratoDTO;

import br.edu.devmedia.sis_escolar.entidade.Aluno;
import br.edu.devmedia.sis_escolar.entidade.Disciplina;
import br.edu.devmedia.sis_escolar.entidade.Extrato;
import br.edu.devmedia.sis_escolar.entidade.Nota;

public class ConversorDTO {
	
	public static List<PeriodoExtratoDTO> converterPeriodoExtrato2DTO(Aluno aluno) {
		List<PeriodoExtratoDTO> retorno = new ArrayList<PeriodoExtratoDTO>();
		List<Integer> idsPeriodos = new ArrayList<Integer>();
		
		for (Extrato extrato : aluno.getExtratos()) {
			if (!idsPeriodos.contains(extrato.getPeriodo().getId())) {
				PeriodoExtratoDTO periodoDTO = new PeriodoExtratoDTO();
				periodoDTO.setAno(extrato.getPeriodo().getAno());
				periodoDTO.setId(extrato.getPeriodo().getId());
				periodoDTO.setSemestre(extrato.getPeriodo().getSemestre());
				periodoDTO.setExtratos(new ArrayList<Extrato>());
				
				retorno.add(periodoDTO);
				idsPeriodos.add(extrato.getPeriodo().getId());
			}
		}
		
		for (Extrato extrato : aluno.getExtratos()) {
			for (PeriodoExtratoDTO p : retorno){
				if (extrato.getPeriodo().getId() == p.getId()) {
					p.getExtratos().add(extrato);
				}
			}
		}
		
		Collections.sort(retorno);
		
		return retorno;
	}
	
	public static List<PeriodoDTO> converterPeriodo2DTO(Aluno aluno) {
		List<PeriodoDTO> retorno = new ArrayList<PeriodoDTO>();
		List<Integer> idsPeriodos = new ArrayList<Integer>();
		
		for (Disciplina disc : aluno.getDisciplinas()) {
			if (!idsPeriodos.contains(disc.getPeriodo().getId())) {
				PeriodoDTO periodoDTO = new PeriodoDTO();
				periodoDTO.setAno(disc.getPeriodo().getAno());
				periodoDTO.setId(disc.getPeriodo().getId());
				periodoDTO.setSemestre(disc.getPeriodo().getSemestre());
				periodoDTO.setDisciplinaDTO(new ArrayList<DisciplinaDTO>());
				
				retorno.add(periodoDTO);
				idsPeriodos.add(disc.getPeriodo().getId());
			}
		}
		
		for (Disciplina disc : aluno.getDisciplinas()) {
			DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
			disciplinaDTO.setCodDisciplina(String.valueOf("DIS." + disc.getId()));
			disciplinaDTO.setDisciplina(disc.getDescricao());
			disciplinaDTO.setTurma(disc.getTurma().getDescricao());
			disciplinaDTO.setPresencas_1(disc.getFrequencia().getFreq_1());
			disciplinaDTO.setPresencas_2(disc.getFrequencia().getFreq_2());
			disciplinaDTO.setPresencas_3(disc.getFrequencia().getFreq_3());
			disciplinaDTO.setPresencas_4(disc.getFrequencia().getFreq_4());
			disciplinaDTO.setPresencas_5(disc.getFrequencia().getFreq_5());
			disciplinaDTO.setQtde_aulas(disc.getFrequencia().getQtde_aulas());
			
			for (PeriodoDTO pDTO : retorno) {
				if (pDTO.getId() == disc.getPeriodo().getId()) {
					pDTO.getDisciplinaDTO().add(disciplinaDTO);
				}
			}
		}
		
		Collections.sort(retorno);
		
		return retorno;
	}
	
	public static List<NotaDTO> converterNotas2DTO(Aluno aluno) {
		List<NotaDTO> retorno = new ArrayList<NotaDTO>();
		for (Nota n : aluno.getNotas()) {
			NotaDTO dto = new NotaDTO();
			dto.setNota1(n.getNota_1());
			dto.setNota2(n.getNota_2());
			dto.setNota3(n.getNota_3());
			dto.setNota4(n.getNota_4());
			
			dto.setCodDisciplina(String.valueOf("DIS." + n.getDisciplina().getId()));
			dto.setIdDisciplina(n.getDisciplina().getId());
			dto.setIdTurma(n.getDisciplina().getTurma().getId());
			dto.setDisciplina(n.getDisciplina().getDescricao());
			dto.setTurma(n.getDisciplina().getTurma().getDescricao());
			dto.setSemestre(n.getDisciplina().getPeriodo().getSemestre());
			
			retorno.add(dto);
		}
		return retorno;
	}

}
