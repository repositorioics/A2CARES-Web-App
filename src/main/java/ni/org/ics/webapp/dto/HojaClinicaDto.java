package ni.org.ics.webapp.dto;

/**
 * Created by miguel on 3/12/2021.
 */
public class HojaClinicaDto {

    private String codigo;
    private String nombreCompleto;
    private Integer numHojaConsulta;
    private String usuarioRegistro;
    private String fechaConsulta;
    private String lugarAtencion;
    private String tipoConsulta;
    private String medico;
    private String enfermeria;
    private int secHojaConsulta;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getNumHojaConsulta() {
        return numHojaConsulta;
    }

    public void setNumHojaConsulta(Integer numHojaConsulta) {
        this.numHojaConsulta = numHojaConsulta;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
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

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEnfermeria() {
        return enfermeria;
    }

    public void setEnfermeria(String enfermeria) {
        this.enfermeria = enfermeria;
    }

    public int getSecHojaConsulta() {
        return secHojaConsulta;
    }

    public void setSecHojaConsulta(int secHojaConsulta) {
        this.secHojaConsulta = secHojaConsulta;
    }
}
