package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Everts Morales
 **/

public class ConvalecientesEnfermoDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String idRecepcion;
    private String fechaRecepcion;
    private String participante;
	private String tipoTubo; //Serogologia, BHC, etc
	private Double volumen;
	private String observacion;
	private String fis;
	private String fif;
	private String categoria; //A,B,C
	private String consulta; //Inicial, Seguimiento, Convaleciente 1 3
	private String tipoMuestra; //Aguda, Convaleciente  1 2
    private String edadParteAnios;
    private String edadParteMeses;
    private String edadParteDias;
    private String codigoCasa;
    private String nombreCompleto;
    private String enviado;
    private String evento;
    private String codigoBarra;
    private String positivo;
    private String ultimo_evento;

    private BigInteger diasConv;
    private String telefono;
    private String barrio;
    private String nombreTutor;
    private String direccion;


    public String getIdRecepcion() {
        return idRecepcion;
    }

    public void setIdRecepcion(String idRecepcion) {
        this.idRecepcion = idRecepcion;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getTipoTubo() {
        return tipoTubo;
    }

    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFis() {
        return fis;
    }

    public void setFis(String fis) {
        this.fis = fis;
    }

    public String getFif() {
        return fif;
    }

    public void setFif(String fif) {
        this.fif = fif;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getEdadParteAnios() {
        return edadParteAnios;
    }

    public void setEdadParteAnios(String edadParteAnios) {
        this.edadParteAnios = edadParteAnios;
    }

    public String getEdadParteMeses() {
        return edadParteMeses;
    }

    public void setEdadParteMeses(String edadParteMeses) {
        this.edadParteMeses = edadParteMeses;
    }

    public String getEdadParteDias() {
        return edadParteDias;
    }

    public void setEdadParteDias(String edadParteDias) {
        this.edadParteDias = edadParteDias;
    }

    public String getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(String codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getPositivo() {
        return positivo;
    }

    public void setPositivo(String positivo) {
        this.positivo = positivo;
    }

    public String getUltimo_evento() {
        return ultimo_evento;
    }
    public void setUltimo_evento(String ultimo_evento) {
        this.ultimo_evento = ultimo_evento;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }
    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public BigInteger getDiasConv() {
        return diasConv;
    }
    public void setDiasConv (BigInteger diasConv) {
        this.diasConv = diasConv;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
