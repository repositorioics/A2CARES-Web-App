package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by ICS on 17/10/2020.
 */
public class BhcDto implements Serializable {

    private Integer idbhc;
    private String participante;
    private String fecha;
    private char enviado;
    private Double volumen;
    private String descripcion;
    private Integer codigoCasa;
    private String observacion;
    private String sitio_destino;
    private Integer numEnvio;
    private String procesaCSFV;
    private String usuarioRegistro;
    private String estudio;
    private String puesto;
    private Integer edadMeses;







    public BhcDto() {
    }

    public Integer getIdbhc() {
        return idbhc;
    }

    public void setIdbhc(Integer idbhc) {
        this.idbhc = idbhc;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public char getEnviado() {
        return enviado;
    }

    public void setEnviado(char enviado) {
        this.enviado = enviado;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(Integer codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getSitio_destino() {
        return sitio_destino;
    }

    public void setSitio_destino(String sitio_destino) {
        this.sitio_destino = sitio_destino;
    }

    public String getProcesaCSFV() {
        return procesaCSFV;
    }

    public void setProcesaCSFV(String procesaCSFV) {
        this.procesaCSFV = procesaCSFV;
    }
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
    public int getNumEnvio() {
        return numEnvio;
    }

    public void setNumEnvio(int numEnvio) {
        this.numEnvio = numEnvio;
    }
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(int edadMeses) {
        this.edadMeses = edadMeses;
    }
}
