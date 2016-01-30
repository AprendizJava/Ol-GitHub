package org.domain.sisescolar.session;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.domain.sisescolar.negocio.UserNegocio;
import org.domain.sisescolar.util.AutenticacaoUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;

import br.edu.devmedia.sis_escolar.entidade.Roles;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("usuario")
@Scope(ScopeType.SESSION)
public class UserSession {
	
	private String login;
	
	private Roles roleSelected = new Roles();
	
	private List<Roles> rolesSelected = new ArrayList<Roles>();
	
	private List<Usuario> usuarios;
	
	private List<Roles> roles;
	
	private List<Roles> rolesPickToSelect = new ArrayList<Roles>();
	
	private List<Roles> rolesPickSelected = new ArrayList<Roles>();
	
	private int idUsuarioSelected;

	@In(required = true, create = true) private UserNegocio userNegocio;
	
	@In FacesMessages facesMessages;
	
	private Usuario usuarioAux = new Usuario();
	
	public List<Roles> getRoles() {
		return userNegocio.listarRoles();
	}
	
	public void salvarUsuario() {
		try {
			usuarioAux.setId(null);
			usuarioAux.setSenha(AutenticacaoUtil.criptografarSenha(usuarioAux.getSenha()));
			usuarioAux.setRoles(rolesPickSelected);
			userNegocio.salvarUsuario(usuarioAux);
			facesMessages.add("Usuário criado com sucesso!");
		} catch(Exception e) {
			facesMessages.add(Severity.ERROR, "Algo de errado aconteceu nessa operação. Favor tentar novamente.");
		}
	}
	
	public List<Usuario> buscarUsers() {
		return userNegocio.listarUsuarios();
	}
	
	public void recuperarUsuarioSelected(int idUsuario) {
		for (Usuario u : getUsuarios()) {
			if (u.getId() == idUsuario) {
				setRolesSelected(u.getRoles());
			}
		}
	}
	
	public void resetPick() {
		rolesPickToSelect = getRoles();
		rolesPickSelected = new ArrayList<Roles>();
	}
	
	public void limpar() {
		this.login = null;
		this.roleSelected = new Roles();
		this.usuarios = buscarUsers();
	}
	
	public void pesquisar() {
		if (roleSelected.getId() == null) {
			this.usuarios = userNegocio.filtrarUsuariosPorLogin(login);
		} else {
			this.roles = userNegocio.filtrarUsuarios(roleSelected);
			this.usuarios = new ArrayList<Usuario>();
			for (Roles r : roles) {
				if (login != null && !login.equals("")) {
					for (Usuario u : r.getUsuarios()) {
						if (u.getLogin().contains(login)) {
							usuarios.add(u);
						}
					}
				} else {
					usuarios.addAll(r.getUsuarios());
				}
			}
		}
	}

	public void removerUsuario() {
		try {
			userNegocio.removerUsuario(idUsuarioSelected);
			facesMessages.add("Usuário removido com sucesso!");
			this.usuarios = buscarUsers();
		} catch(Exception e) {
			facesMessages.add("Algo de errado aconteceu nessa operação. Favor tentar novamente.");
		}
	}
	
	public void popularUsuarioEdit(int idUsuario) {
		for (Usuario u : usuarios) {
			if (u.getId() == idUsuario) {
				usuarioAux = u;
			}
		}
		rolesPickToSelect = getRoles();
		rolesPickSelected = new ArrayList<Roles>();
		for (Roles r : rolesPickToSelect) {
			for (Roles rAux : usuarioAux.getRoles()) {
				if (r.getId() ==  rAux.getId()) {
					rolesPickSelected.add(r);
				}
			}
		}
	}
	
	public void atualizarUsuario() {
		try {
			usuarioAux.setRoles(rolesPickSelected);
			userNegocio.alterarUsuario(usuarioAux);
			facesMessages.add("Usuário alterado com sucesso!");
		} catch(Exception e) {
			facesMessages.add("Algo de errado aconteceu nessa operação. Favor tentar novamente.");
		}
	}
	
	public void configIdAux(ActionEvent actionEvent) {
		Integer id = (Integer) actionEvent.getComponent().getAttributes().get("idUsuario");
		setIdUsuarioSelected(id);
	}
	
	public Roles getRoleSelected() {
		return roleSelected;
	}

	public void setRoleSelected(Roles roleSelected) {
		this.roleSelected = roleSelected;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Roles> getRolesSelected() {
		return rolesSelected;
	}

	public void setRolesSelected(List<Roles> rolesSelected) {
		this.rolesSelected = rolesSelected;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = buscarUsers();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getIdUsuarioSelected() {
		return idUsuarioSelected;
	}

	public void setIdUsuarioSelected(int idUsuarioSelected) {
		this.idUsuarioSelected = idUsuarioSelected;
	}

	public Usuario getUsuarioAux() {
		return usuarioAux;
	}

	public void setUsuarioAux(Usuario usuarioAux) {
		this.usuarioAux = usuarioAux;
	}

	public List<Roles> getRolesPickSelected() {
		return rolesPickSelected;
	}

	public void setRolesPickSelected(List<Roles> rolesPickSelected) {
		this.rolesPickSelected = rolesPickSelected;
	}

	public List<Roles> getRolesPickToSelect() {
		return rolesPickToSelect;
	}

	public void setRolesPickToSelect(List<Roles> rolesPickToSelect) {
		this.rolesPickToSelect = rolesPickToSelect;
	}
}
