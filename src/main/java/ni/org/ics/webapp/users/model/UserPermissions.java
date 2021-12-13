package ni.org.ics.webapp.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Simple objeto de dominio que representa los permisos de los usuario en los procesos
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "usuarios_permisos", catalog = "a2cares")
public class UserPermissions {
	private String username;
	private Boolean muestra=false;
	private Boolean encuestaCasa=false;
	private Boolean encuestaParticipante=false;
	private Boolean encuestaSatisfaccion=false;
	private Boolean obsequio=false;
	private Boolean pesoTalla=false;
	private Boolean visitas=false;
	private Boolean recepcion=false;
	private Boolean consentimiento=false;

	@Id
	@Column(name = "username", nullable = false, length =50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "muestra", nullable = false)
	public Boolean getMuestra() {
		return muestra;
	}
	public void setMuestra(Boolean muestra) {
		this.muestra = muestra;
	}

	@Column(name = "ecasa", nullable = false)
	public Boolean getEncuestaCasa() {
		return encuestaCasa;
	}
	public void setEncuestaCasa(Boolean encuestaCasa) {
		this.encuestaCasa = encuestaCasa;
	}

	@Column(name = "eparticipante", nullable = false)
	public Boolean getEncuestaParticipante() {
		return encuestaParticipante;
	}
	public void setEncuestaParticipante(Boolean encuestaParticipante) {
		this.encuestaParticipante = encuestaParticipante;
	}

	@Column(name = "esatisfaccion", nullable = false)
	public Boolean getEncuestaSatisfaccion() {
		return encuestaSatisfaccion;
	}
	public void setEncuestaSatisfaccion(Boolean encuestaSatisfaccion) {
		this.encuestaSatisfaccion = encuestaSatisfaccion;
	}

	@Column(name = "obsequio", nullable = false)
	public Boolean getObsequio() {
		return obsequio;
	}
	public void setObsequio(Boolean obsequio) {
		this.obsequio = obsequio;
	}

	@Column(name = "pesotalla", nullable = false)
	public Boolean getPesoTalla() {
		return pesoTalla;
	}
	public void setPesoTalla(Boolean pesoTalla) {
		this.pesoTalla = pesoTalla;
	}

	@Column(name = "visitas", nullable = false)
	public Boolean getVisitas() {
		return visitas;
	}
	public void setVisitas(Boolean visitas) {
		this.visitas = visitas;
	}

	@Column(name = "recepcion", nullable = false)
	public Boolean getRecepcion() {
		return recepcion;
	}
	public void setRecepcion(Boolean recepcion) {
		this.recepcion = recepcion;
	}

	@Column(name = "consentimiento", nullable = false)
	public Boolean getConsentimiento() {
		return consentimiento;
	}
	public void setConsentimiento(Boolean consentimiento) {
		this.consentimiento = consentimiento;
	}
}
