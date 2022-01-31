package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos una orden de laboratorio del médico
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "muestras_enfermos", catalog = "a2cares")
public class MuestraEnfermo extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idMuestra;
	private Participante participante;
	private Date fechaMuestra;
	private String horaMuestra;
	private String tipoTubo; //Serogologia, BHC, etc
	private Double volumen;
	private String observacion;
	private Date fis;
	private Date fif;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente
	private String tipoMuestra; //Aguda, Convaleciente
	private String estudiosAct;

    @Id
    @Column(name = "ID_MUESTRA", nullable = false, length = 50)
	public String getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(String idMuestra) {
		this.idMuestra = idMuestra;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_MUESTRAS_ENF")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @Column(name = "FECHA_MUESTRA", nullable = false)
	public Date getFechaMuestra() {
		return fechaMuestra;
	}

	public void setFechaMuestra(Date fechaMuestra) {
		this.fechaMuestra = fechaMuestra;
	}

    @Column(name = "HORA_MUESTRA", length = 32, nullable = true)
	public String getHoraMuestra() {
		return horaMuestra;
	}

	public void setHoraMuestra(String horaMuestra) {
		this.horaMuestra = horaMuestra;
	}

    @Column(name = "TIPO_TUBO", length = 32, nullable = true)
	public String getTipoTubo() {
		return tipoTubo;
	}

	public void setTipoTubo(String tipoTubo) {
		this.tipoTubo = tipoTubo;
	}

    @Column(name = "VOLUMEN", nullable = true)
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

    @Column(name = "FIS", nullable = false)
	public Date getFis() {
		return fis;
	}

	public void setFis(Date fis) {
		this.fis = fis;
	}

    @Column(name = "FIF", nullable = false)
	public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}

    @Column(name = "CATEGORIA", length = 2)
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

    @Column(name = "CONSULTA", length = 2)
	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

    @Column(name = "FASE_MUESTRA", length = 2)
	public String getTipoMuestra() {
		return tipoMuestra;
	}

	public void setTipoMuestra(String tipoMuestra) {
		this.tipoMuestra = tipoMuestra;
	}

    @Column(name = "ESTUDIOS_ACTUALES", length = 32)
	public String getEstudiosAct() {
		return estudiosAct;
	}

	public void setEstudiosAct(String estudiosAct) {
		this.estudiosAct = estudiosAct;
	}

    @Column(name = "OBSERVACION", length = 255)
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
