package es.uma.informatica.jpa.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Saludo {
	public String getSaludo() {
		// TODO Auto-generated constructor stub
		return "Hola mundo";
	}
}
