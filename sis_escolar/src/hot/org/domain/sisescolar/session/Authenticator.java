package org.domain.sisescolar.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.domain.sisescolar.util.AutenticacaoUtil;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.edu.devmedia.sis_escolar.entidade.Roles;
import br.edu.devmedia.sis_escolar.entidade.Usuario;

@Name("authenticator")
public class Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    
    @In EntityManager entityManager;

    public boolean authenticate()
    {
        log.info("authenticating {0}", credentials.getUsername());
        
        Query query = entityManager.createQuery("from Usuario where login = :login and senha = :senha");
        query.setParameter("login", credentials.getUsername());
        query.setParameter("senha", AutenticacaoUtil.criptografarSenha(credentials.getPassword()));
        
        @SuppressWarnings("rawtypes")
		List lista = query.getResultList();
        if (!lista.isEmpty()) {
        	Usuario us = (Usuario) lista.get(0);
        	for (Roles r : us.getRoles()) {
        		identity.addRole(r.getDescricao());
        	}
            return true;
        }
        return false;
    }

}
