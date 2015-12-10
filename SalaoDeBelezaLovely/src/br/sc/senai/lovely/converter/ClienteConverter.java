package br.sc.senai.lovely.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.sc.senai.lovely.dominio.Cliente;

@FacesConverter(value="ClienteConverter")
public class ClienteConverter implements Converter {

	  
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        @SuppressWarnings("unchecked")
	        List<Cliente> listaCliente = (List<Cliente>) component.getAttributes().get("listaCliente");
	        for (Cliente cliente : listaCliente) {
	                if (cliente.getNome().equalsIgnoreCase(value)) {
	                    return cliente;
	                }
	            }
	        return null;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if (value.toString().equalsIgnoreCase("0")) {
	            return "Selecione";
	        } else {
	            Cliente cliente = (Cliente) value;
	            return cliente.getNome();
	        }
	    }
	
}
