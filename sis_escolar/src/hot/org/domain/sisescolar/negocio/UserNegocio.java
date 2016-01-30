package org.domain.sisescolar.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.domain.sisescolar.util.AutenticacaoUtil;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.edu.devmedia.sis_escolar.entidade.Roles;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("userNegocio")
public class UserNegocio {

	@In private EntityManager entityManager;
	
	public Usuario buscarUsuarioLogado(String usuario, String senha) {
		Query query = entityManager.createQuery("from Usuario where login = :login and senha = :senha");
        query.setParameter("login", usuario);
        query.setParameter("senha", AutenticacaoUtil.criptografarSenha(senha));
        
        @SuppressWarnings("rawtypes")
		List lista = query.getResultList();
        if (!lista.isEmpty()) {
        	Usuario us = (Usuario) lista.get(0);
        	return us;
        }
        return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Roles> listarRoles() {
		Query query = entityManager.createQuery("from Roles");
		return query.getResultList();
	}
	
	public Roles buscarRolesPorId(int id) {
		Query query = entityManager.createQuery("from Roles where id = :id");
		query.setParameter("id", id);
		return (Roles) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() {
		Query query = entityManager.createQuery("from Usuario");
		return query.getResultList();
	}

	public Usuario buscarUsuarioPorId(int id) {
		Query query = entityManager.createQuery("from Usuario where id = :id");
		query.setParameter("id", id);
		return (Usuario) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Roles> filtrarUsuarios(Roles roles) {
		Query query = entityManager.createQuery("from Roles where id = :id");
		query.setParameter("id", roles.getId());
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filtrarUsuariosPorLogin(String login) {
		Query query = entityManager.createQuery("from Usuario where login like :login");
		query.setParameter("login", "%" + login + "%");
		
		return query.getResultList();
	}
	
	public void removerUsuario(int idUsuario) {
		entityManager.remove(buscarUsuarioPorId(idUsuario));
	}
	
	public void alterarUsuario(Usuario us) {
		entityManager.merge(us);
	}

	public void salvarUsuario(Usuario us) {
		entityManager.persist(us);
	}
}
