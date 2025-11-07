package eredua.bean;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.event.SelectEvent;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {
	private String izena;
	private String pasahitza;
	private Date data;

	public LoginBean() {
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String egiaztatu() {
		if (izena.length()!=pasahitza.length()){
		 FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Errorea: izenaren eta pasahitzaren luzera desberdinak dira."));
		 return null;
		}
		if (izena.equals("pirata"))
		return "error";
		else
		return "ok";
		}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Data aukeratua: "+event.getObject()));
		}
}