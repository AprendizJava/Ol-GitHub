package org.domain.sisescolar.session;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.domain.sisescolar.dto.PeriodoExtratoDTO;
import org.domain.sisescolar.negocio.ExtratoNegocio;
import org.domain.sisescolar.negocio.UserNegocio;
import org.domain.sisescolar.util.BoletoUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;
import org.jrimum.bopepo.view.BoletoViewer;

import br.edu.devmedia.sis_escolar.entidade.Aluno;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("extratoAction")
@Scope(ScopeType.CONVERSATION)
public class ExtratoAction {

	@In Credentials credentials;

	@In(required = true, create = true) UserNegocio userNegocio;

	@In(required = true, create = true) ExtratoNegocio extratoNegocio;

	private Aluno aluno;
	
	private Date vencimento;
	private Double valor;

	public List<PeriodoExtratoDTO> getPeriodos() {
		return extratoNegocio.listarPeriodos(credentials.getUsername(), credentials.getPassword());
	}

	public void configBoletoAux(ActionEvent actionEvent) {
		vencimento = (Date) actionEvent.getComponent().getAttributes().get("vencimento");
		valor = (Double) actionEvent.getComponent().getAttributes().get("valor");
	}

	public String download(){
		BoletoViewer boletoViewer = BoletoUtil.gerarBoleto(vencimento, valor);

		byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=boleto.pdf");

			OutputStream output = response.getOutputStream();
			output.write(pdfAsBytes);
			response.flushBuffer();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
