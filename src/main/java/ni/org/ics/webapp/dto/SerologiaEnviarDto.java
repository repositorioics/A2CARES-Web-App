package ni.org.ics.webapp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 18/10/2020.
 */
public class SerologiaEnviarDto implements Serializable {

    private Integer idserologia;
    private Integer nenvios;
    private String fechaenvio;
    private String hora;
    private String sitio_destino;


    public SerologiaEnviarDto() {
    }

    public SerologiaEnviarDto(Integer idserologia, Integer nenvios, String fechaenvio, String hora) {
        this.idserologia = idserologia;
        this.nenvios = nenvios;
        this.fechaenvio = fechaenvio;
        this.hora = hora;

    }

    public Integer getIdserologia() {
        return idserologia;
    }

    public void setIdserologia(Integer idserologia) {
        this.idserologia = idserologia;
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
