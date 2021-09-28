package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "muestras", catalog = "a2cares")
public class Muestra extends BaseMetaData implements Serializable {

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
	private String tuboBHC;
	private String codigoBHC;
	private Double volumenBHC;
	private String razonNoBHC;
	private String otraRazonNoBHC;
	private String tuboRojo;
	private String codigoRojo;
	private Double volumenRojo;
	private String razonNoRojo;
	private String otraRazonNoRojo;
	private String terreno;
	private String pinchazos;
    private String estudiosAct;
    private String proposito;

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
    @ForeignKey(name = "FK_PARTICIPANTE_MUESTRAS")
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

    @Column(name = "TOMO_BHC", length = 1, nullable = true)
	public String getTuboBHC() {
		return tuboBHC;
	}

	public void setTuboBHC(String tuboBHC) {
		this.tuboBHC = tuboBHC;
	}

    @Column(name = "VOLUMEN_BHC", nullable = true)
	public Double getVolumenBHC() {
		return volumenBHC;
	}

	public void setVolumenBHC(Double volumenBHC) {
		this.volumenBHC = volumenBHC;
	}

    @Column(name = "CODIGO_BHC", length = 32, nullable = true)
	public String getCodigoBHC() {
		return codigoBHC;
	}

	public void setCodigoBHC(String codigoBHC) {
		this.codigoBHC = codigoBHC;
	}

    @Column(name = "RAZON_NOTOMA_BHC", length = 3, nullable = true)
	public String getRazonNoBHC() {
		return razonNoBHC;
	}

	public void setRazonNoBHC(String razonNoBHC) {
		this.razonNoBHC = razonNoBHC;
	}

    @Column(name = "OTRA_RAZON_NOTOMA_BHC", nullable = true)
	public String getOtraRazonNoBHC() {
		return otraRazonNoBHC;
	}

	public void setOtraRazonNoBHC(String otraRazonNoBHC) {
		this.otraRazonNoBHC = otraRazonNoBHC;
	}

    @Column(name = "TOMO_ROJO", length = 1, nullable = true)
	public String getTuboRojo() {
		return tuboRojo;
	}

	public void setTuboRojo(String tuboRojo) {
		this.tuboRojo = tuboRojo;
	}

    @Column(name = "CODIGO_ROJO", length = 32, nullable = true)
    public String getCodigoRojo() {
		return codigoRojo;
	}

	public void setCodigoRojo(String codigoRojo) {
		this.codigoRojo = codigoRojo;
	}

    @Column(name = "VOLUMEN_ROJO", nullable = true)
	public Double getVolumenRojo() {
		return volumenRojo;
	}

	public void setVolumenRojo(Double volumenRojo) {
		this.volumenRojo = volumenRojo;
	}

    @Column(name = "RAZON_NOTOMA_ROJO", length = 3, nullable = true)
	public String getRazonNoRojo() {
		return razonNoRojo;
	}

	public void setRazonNoRojo(String razonNoRojo) {
		this.razonNoRojo = razonNoRojo;
	}

    @Column(name = "OTRA_RAZON_NOTOMA_ROJO", nullable = true)
	public String getOtraRazonNoRojo() {
		return otraRazonNoRojo;
	}

	public void setOtraRazonNoRojo(String otraRazonNoRojo) {
		this.otraRazonNoRojo = otraRazonNoRojo;
	}

    @Column(name = "EN_TERRENO", length = 50, nullable = true)
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

    @Column(name = "NUM_PINCHAZOS", length = 1, nullable = true)
	public String getPinchazos() {
		return pinchazos;
	}

	public void setPinchazos(String pinchazos) {
		this.pinchazos = pinchazos;
	}

    @Column(name = "ESTUDIOS_ACTUALES", length = 50, nullable = true)
	public String getEstudiosAct() {
		return estudiosAct;
	}

	public void setEstudiosAct(String estudiosAct) {
		this.estudiosAct = estudiosAct;
	}

    @Column(name = "PROPOSITO", length = 32, nullable = true)
	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
}
