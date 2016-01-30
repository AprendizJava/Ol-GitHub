package org.domain.sisescolar.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.domain.sisescolar.negocio.UserNegocio;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.edu.devmedia.sis_escolar.entidade.Roles;

@Name("rolesConverter")
public class RolesConverter implements Converter {

	@In(required = true, create = true) private UserNegocio userNegocio;
	
	public List<Roles> getRoles() {
		return userNegocio.listarRoles();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		for (Roles r : getRoles()) {
			if (r.getDescricao().equals(valor)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			return ((Roles) obj).getDescricao();
		}
		return null;
	}

}
