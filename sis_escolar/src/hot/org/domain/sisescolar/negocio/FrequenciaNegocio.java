package org.domain.sisescolar.negocio;

import java.util.List;

import javax.persistence.EntityManager;

import org.domain.sisescolar.dto.PeriodoDTO;
import org.domain.sisescolar.util.ConversorDTO;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("frequenciaNegocio")
public class FrequenciaNegocio {

	@In(required = true, create = true) UserNegocio userNegocio;
	
	@In private EntityManager entityManager;
	
	public List<PeriodoDTO> listarPeriodos(String login, String senha) {
		Usuario us = userNegocio.buscarUsuarioLogado(login, senha);
		List<PeriodoDTO> periodoDTO = null;
		if (us.getAluno() != null) {
			periodoDTO = ConversorDTO.converterPeriodo2DTO(us.getAluno());
		}
		
		return periodoDTO;
	}
	
}
