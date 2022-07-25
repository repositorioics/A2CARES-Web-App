package ni.org.ics.webapp.domain.laboratorio;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.core.Participante;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "mx_enfermos_recepcion_lab", catalog = "a2cares")
public class RecepcionEnfermo extends BaseMetaData implements Auditable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idRecepcion;
	private Participante participante;
	private Date fechaRecepcion;
	private String tipoTubo; //Serogologia, BHC, etc
	private Double volumen;
	private String observacion;
	private Date fis;
	private Date fif;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente
	private String tipoMuestra; //Aguda, Convaleciente
	private String estudiosAct;
    private String enviado = "0";
    private String codigo;
    private String evento;
    private String codigoBarra;


    @Id
    @Column(name = "ID_RECEPCION", nullable = false, length = 50)
	public String getIdRecepcion() {
		return idRecepcion;
	}

	public void setIdRecepcion(String idRecepcion) {
		this.idRecepcion = idRecepcion;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_RECEPCION_ENF")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @Column(name = "FECHA_RECEPCION", nullable = false)
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
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

    @Column(name = "EVENTO",nullable = true, length = 50)
    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    @JsonIgnore
    @Column(name = "ENVIADO", nullable = true, length = 1)
    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    @JsonIgnore
    @Column(name = "CODIGO_MX", nullable = true, length = 32)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }




    @JsonIgnore
    @Column(name = "CODIGO_BARRA", nullable = true, length = 64)
    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecepcionEnfermo that = (RecepcionEnfermo) o;

        if (!idRecepcion.equals(that.idRecepcion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idRecepcion.hashCode();
    }

    @Override
    public String toString() {
        return "idRecepcion='" + idRecepcion;
    }
}

