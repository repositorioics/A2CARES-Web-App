package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by ICS on 17/10/2020.
 */
public class BhcBc6000PrintDto implements Serializable {

    private Integer idbhc;
    private String participante_cod;
    private String fecha;
    private String hora;
    private String impreso;
    private String estudio;
    private String puesto;








    public BhcBc6000PrintDto() {
    }

    public Integer getIdbhc() {
        return idbhc;
    }

    public void setIdbhc(Integer idbhc) {
        this.idbhc = idbhc;
    }

    public String getParticipante_cod() {
        return participante_cod;
    }

    public void setParticipante_cod(String participante_cod) {
        this.participante_cod = participante_cod;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImpreso() {
        return impreso;
    }

    public void setImpreso(String impreso) {
        this.impreso = impreso;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

}
