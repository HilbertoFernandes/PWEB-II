package br.edu.ifpb.bibliotecaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.bibliotecaweb.entity.Usuario;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter extends SelectItemsBaseConverter {

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Usuario)
			return ((Usuario) value).getId().toString();
		else
			return null;
	}
}
