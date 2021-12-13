package ni.org.ics.webapp.dto;

/**
 * Created by miguel on 3/12/2021.
 */
public class HojaClinicaDto {

    private Integer codigo;
    private String nombreCompleto;
    private String fechaConsulta;
    private String lugarAtencion;
    private Short medico;
    private Short enfermeria;
    private int secHojaConsulta;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getLugarAtencion() {
        return lugarAtencion;
    }

    public void setLugarAtencion(String lugarAtencion) {
        this.lugarAtencion = lugarAtencion;
    }

    public Short getMedico() {
        return medico;
    }

    public void setMedico(Short medico) {
        this.medico = medico;
    }

    public Short getEnfermeria() {
        return enfermeria;
    }

    public void setEnfermeria(Short enfermeria) {
        this.enfermeria = enfermeria;
    }

    public int getSecHojaConsulta() {
        return secHojaConsulta;
    }

    public void setSecHojaConsulta(int secHojaConsulta) {
        this.secHojaConsulta = secHojaConsulta;
    }
}
