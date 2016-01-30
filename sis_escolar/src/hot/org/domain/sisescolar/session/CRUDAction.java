package org.domain.sisescolar.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;

import br.edu.devmedia.sis_escolar.entidade.Pessoa;

@Name("crud")
@Scope(ScopeType.SESSION)
public class CRUDAction {
	
	@In FacesMessages facesMessages;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	private Pessoa pessoa = new Pessoa();
	
	private Pessoa pessoaAux = new Pessoa();
	
	private int cont = 0;
	
	private int idPessoaSelecionada;
	
	public void salvarPessoa() {
		pessoa.setId(++cont);
		pessoas.add(pessoa);
		pessoa = new Pessoa();
		facesMessages.add("Pessoa cadastrada com sucesso!");
	}
	
	public void listarPessoas() {
		System.out.println("Qtde de pessoas: " + pessoas.size());
		System.out.println("Id selecionado: " + idPessoaSelecionada);
	}
	
	public void configIdAux(ActionEvent actionEvent) {
		Integer id = (Integer) actionEvent.getComponent().getAttributes().get("idPessoa");
		idPessoaSelecionada = id;
	}
	
	public void popularPessoaEdit(int idPessoa) {
		for (Pessoa p : pessoas) {
			if (p.getId() == idPessoa) {
				pessoaAux = p;
			}
		}
		System.out.println(pessoaAux);
	}
	
	public void remover() {
		if (idPessoaSelecionada != 0) {
			Iterator<Pessoa> i = pessoas.iterator();
			while (i.hasNext()) {
				Pessoa p = i.next();
				if (p.getId() == idPessoaSelecionada) {
					i.remove();
				}
			}
			facesMessages.add("Pessoa removida com sucesso!");
		} else {
			facesMessages.add(Severity.ERROR, "Erro ao deletar. Favor tente novamente...");
		}
	}
	
	public void atualizar() {
		int pos = -1;
		for (Pessoa p : pessoas) {
			if (p.getId() == pessoaAux.getId()) {
				pos = pessoas.indexOf(p);
			}
		}
		if (pos != -1) {
			pessoas.set(pos, pessoaAux);
			facesMessages.add("Pessoa atualizada com sucesso!");
		} else {
			facesMessages.add(Severity.ERROR, "Erro ao atualizar. Favor tente novamente...");
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getIdPessoaSelecionada() {
		return idPessoaSelecionada;
	}

	public void setIdPessoaSelecionada(int idPessoaSelecionada) {
		this.idPessoaSelecionada = idPessoaSelecionada;
	}

	public Pessoa getPessoaAux() {
		return pessoaAux;
	}

	public void setPessoaAux(Pessoa pessoaAux) {
		this.pessoaAux = pessoaAux;
	}
	
}
