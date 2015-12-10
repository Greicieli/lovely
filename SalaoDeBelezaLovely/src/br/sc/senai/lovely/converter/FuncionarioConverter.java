package br.sc.senai.lovely.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.sc.senai.lovely.dominio.Funcionario;

@FacesConverter(value="FuncionarioConverter")
public class FuncionarioConverter implements Converter {

	  
	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	    	@SuppressWarnings("unchecked")
	        List<Funcionario> listaFuncionario = (List<Funcionario>) component.getAttributes().get("listaFuncionario");
	        for (Funcionario funcionario : listaFuncionario) {
	                if (funcionario.getNome().equalsIgnoreCase(value)) {
	                    return funcionario;
	                }
	            }
	        return null;
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if (value.toString().equalsIgnoreCase("0")) {
	            return "Selecione";
	        } else {
	            Funcionario funcionario = (Funcionario) value;
	            return funcionario.getNome();
	        }
	    }
	
}
