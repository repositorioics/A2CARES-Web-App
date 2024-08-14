package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by ICS on 17/10/2020.
 */
public class CompletarDatosGBhcDto implements Serializable {

    private String nombre;
    private String fechaNac;
    private String medico;
    private String sexo;








    public CompletarDatosGBhcDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
