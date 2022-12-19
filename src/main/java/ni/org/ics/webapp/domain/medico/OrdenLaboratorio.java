package ni.org.ics.webapp.domain.medico;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
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
@Table(name = "ordenes_laboratorio", catalog = "a2cares")
public class OrdenLaboratorio extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idOrden;
	private Participante participante;
	private Date fechaOrden;
	private String tipoOrden; //Serogologia, BHC, etc
	private Date fis;
	private Date fif;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente
	private String tipoMuestra; //Aguda, Convaleciente
	private String estudiosAct;
    private String observacion;

    @Id
    @Column(name = "ID_ORDEN", unique = true, nullable = false)
	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_ORDENLAB_PARTICIPANTE")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ORDEN", nullable = false)
	public Date getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

    @Column(name = "TIPO_ORDEN", length = 2)
	public String getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

    @Temporal(TemporalType.DATE)
    @Column(name = "FIS", nullable = false)
	public Date getFis() {
		return fis;
	}

	public void setFis(Date fis) {
		this.fis = fis;
	}

    @Temporal(TemporalType.DATE)
    @Column(name = "FIF", nullable = true)
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
