package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by ICS on 18/10/2020.
 */
public class BhcEnviarDto implements Serializable {

    private Integer idbhc;
    private Integer nenvios;
    private String fechaenvio;
    private String hora;
    private String sitio_destino;


    public BhcEnviarDto() {
    }

    public BhcEnviarDto(Integer idbhc, Integer nenvios, String fechaenvio, String hora) {
        this.idbhc = idbhc;
        this.nenvios = nenvios;
        this.fechaenvio = fechaenvio;
        this.hora = hora;

    }

    public Integer getIdbhc() {
        return idbhc;
    }

    public void setIdbhc(Integer idbhc) {
        this.idbhc = idbhc;
    }

    public Integer getNenvios() {
        return nenvios;
    }

    public void setNenvios(Integer nenvios) {
        this.nenvios = nenvios;
    }

    public String getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(String fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSitio_destino() {
        return sitio_destino;
    }

    public void setSitio_destino(String sitio_destino) {
        this.sitio_destino = sitio_destino;
    }
}
