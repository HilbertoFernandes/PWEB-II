package br.edu.ifpb.bibliotecaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.bibliotecaweb.entity.Livro;

@FacesConverter("livroConverter")
public class LivroConverter extends SelectItemsBaseConverter {

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Livro)
			return ((Livro) value).getId().toString();
		else
			return null;
	}
}
