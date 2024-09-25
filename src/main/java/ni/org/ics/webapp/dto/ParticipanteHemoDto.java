package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by Lizandro Serrano on 26/08/2024.
 */
public class ParticipanteHemoDto implements Serializable {

    private String codigoParticipante;
    private int estado;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String nombreCompleto;
    private String edadParticipante;
    private String direccion;
    private int codigoBarrio;
    private String nombreBarrio;
    private String sexo;
    private String fechaNacimiento;

    public ParticipanteHemoDto() {
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(String codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEdadParticipante() {
        return edadParticipante;
    }

    public void setEdadParticipante(String edadParticipante) {
        this.edadParticipante = edadParticipante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(int codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
