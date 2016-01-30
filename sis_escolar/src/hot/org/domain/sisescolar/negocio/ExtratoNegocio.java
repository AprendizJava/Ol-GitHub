package org.domain.sisescolar.negocio;

import java.util.List;

import javax.persistence.EntityManager;

import org.domain.sisescolar.dto.PeriodoExtratoDTO;
import org.domain.sisescolar.util.ConversorDTO;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("extratoNegocio")
public class ExtratoNegocio {

	@In(required = true, create = true) UserNegocio userNegocio;
	
	@In private EntityManager entityManager;
	
	public List<PeriodoExtratoDTO> listarPeriodos(String login, String senha) {
		Usuario us = userNegocio.buscarUsuarioLogado(login, senha);
		List<PeriodoExtratoDTO> periodoDTO = null;
		if (us.getAluno() != null) {
			periodoDTO = ConversorDTO.converterPeriodoExtrato2DTO(us.getAluno());
		}
		
		return periodoDTO;
	}
	
}
