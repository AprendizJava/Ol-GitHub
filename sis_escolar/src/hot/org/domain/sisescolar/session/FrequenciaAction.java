package org.domain.sisescolar.session;

import java.util.List;

import org.domain.sisescolar.dto.PeriodoDTO;
import org.domain.sisescolar.negocio.FrequenciaNegocio;
import org.domain.sisescolar.negocio.UserNegocio;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;

import br.edu.devmedia.sis_escolar.entidade.Aluno;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("frequenciaAction")
@Scope(ScopeType.CONVERSATION)
public class FrequenciaAction {
	
	@In Credentials credentials;
	
	@In(required = true, create = true) UserNegocio userNegocio;
	
	@In(required = true, create = true) FrequenciaNegocio frequenciaNegocio;
	
	private Aluno aluno;
	
	public List<PeriodoDTO> getPeriodos() {
		return frequenciaNegocio.listarPeriodos(credentials.getUsername(), credentials.getPassword());
	}

	@Begin(join = true)
	public Aluno getAluno() {
		Usuario us = userNegocio.buscarUsuarioLogado(credentials.getUsername(), credentials.getPassword());
		setAluno(us.getAluno());
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
