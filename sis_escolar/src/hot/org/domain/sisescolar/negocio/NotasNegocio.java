package org.domain.sisescolar.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.domain.sisescolar.dto.NotaDTO;
import org.domain.sisescolar.util.ConversorDTO;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.edu.devmedia.sis_escolar.entidade.Aluno;
import br.edu.devmedia.sis_escolar.entidade.Nota;
import br.edu.devmedia.sis_escolar.entidade.Turma;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("notaNegocio")
public class NotasNegocio {

	@In(required = true, create = true) UserNegocio userNegocio;
	
	@In private EntityManager entityManager;
	
	public List<NotaDTO> listarNotas(String login, String senha) {
		Usuario us = userNegocio.buscarUsuarioLogado(login, senha);
		List<NotaDTO> notasDTO = null;
		if (us.getAluno() != null) {
			notasDTO = ConversorDTO.converterNotas2DTO(us.getAluno());
			
			Double mf = 0d;
			int cont = 0;
			for (Nota nt : us.getAluno().getNotas()) {
				mf = (nt.getNota_1() + nt.getNota_2() + nt.getNota_3() + nt.getNota_4())/4;
				nt.setNota_final(mf);
				entityManager.merge(nt);
				notasDTO.get(cont++).setMediaFinal(mf);
			}
			
			this.calcularNotaTurma(notasDTO);
		}
		
		return notasDTO;
	}
	
	public Turma buscarTurmaPorId(int idTurma) {
		Query query = entityManager.createQuery("from Turma where id = :id");
        query.setParameter("id", idTurma);
        
        return (Turma) query.getSingleResult();
	}
	
	private void calcularNotaTurma(List<NotaDTO> notasDTO) {
		for (NotaDTO dto : notasDTO) {
			Double mt = 0d;
			Turma turma = buscarTurmaPorId(dto.getIdTurma());
			for (Aluno al : turma.getAlunos()) {
				for (Nota nt : al.getNotas()) {
					if (nt.getDisciplina().getId() == dto.getIdDisciplina()) {
						mt = (nt.getNota_1() + nt.getNota_2() + nt.getNota_3() + nt.getNota_4())/4;
					}
				}
			}
			dto.setMediaTurma(mt);
		}
	}
}
