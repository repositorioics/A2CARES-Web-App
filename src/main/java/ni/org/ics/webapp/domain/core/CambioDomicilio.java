package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Simple objeto de dominio que representa el cambio de domicilio
 *
 * @author Ing. Santiago Carballo
 **/

@Entity
@Table(name = "cambio_domicilio", catalog = "a2cares")
public class CambioDomicilio {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String codigoParticipante;
    private String codigoNuevaCasaCohorte;
    private String codigoCasa;
    private String nombre1JefeFamilia;
    private String nombre2JefeFamilia;
    private String apellido1JefeFamilia;
    private String apellido2JefeFamilia;
    private String direccion;
    private Barrio barrio;
    private String coordenadas;
    private Double latitud;
    private Double longitud;
    private String numTelefono1;
    private String operadoraTelefono1;
    private String numTelefono2;
    private String operadoraTelefono2;
    private String numTelefono3;
    private String operadoraTelefono3;
    private String codigoMovimiento;
    private String identificadoEquipo;
    private char estado;
    private char pasivo;
    private Date fechaRegistro;
    private String creado;
    private String usuarioRegistro;
    private boolean actual;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = false, length = 50)
    public String getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(String codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name="CODIGO_NUEVA_CASA", nullable = false, length = 50)
    public String getCodigoNuevaCasaCohorte() {
        return codigoNuevaCasaCohorte;
    }

    public void setCodigoNuevaCasaCohorte(String codigoNuevaCasaCohorte) {
        this.codigoNuevaCasaCohorte = codigoNuevaCasaCohorte;
    }

    @Column(name="CODIGO_CASA", nullable = false, length = 50)
    public String getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(String codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    @Column(name = "NOMBRE1_JEFE", nullable = false, length = 100)
    public String getNombre1JefeFamilia() {
        return nombre1JefeFamilia;
    }

    public void setNombre1JefeFamilia(String nombre1JefeFamilia) {
        this.nombre1JefeFamilia = nombre1JefeFamilia;
    }

    @Column(name = "NOMBRE2_JEFE", nullable = true, length = 100)
    public String getNombre2JefeFamilia() {
        return nombre2JefeFamilia;
    }

    public void setNombre2JefeFamilia(String nombre2JefeFamilia) {
        this.nombre2JefeFamilia = nombre2JefeFamilia;
    }

    @Column(name = "APELLIDO1_JEFE", nullable = false, length = 100)
    public String getApellido1JefeFamilia() {
        return apellido1JefeFamilia;
    }

    public void setApellido1JefeFamilia(String apellido1JefeFamilia) {
        this.apellido1JefeFamilia = apellido1JefeFamilia;
    }

    @Column(name = "APELLIDO2_JEFE", nullable = true, length = 100)
    public String getApellido2JefeFamilia() {
        return apellido2JefeFamilia;
    }

    public void setApellido2JefeFamilia(String apellido2JefeFamilia) {
        this.apellido2JefeFamilia = apellido2JefeFamilia;
    }

    @Column(name = "DIRECCION", nullable = false)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_BARRIO", nullable = false)
    @ForeignKey(name = "FK_BARRIO_CAMBIO_DOMICILIO")
    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    @Column(name = "PUNTO_GPS", nullable = true, length = 100)
    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Column(name = "LATITUD", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }


    @Column(name = "TELEFONO1", nullable = true, length = 150)
    public String getNumTelefono1() {
        return numTelefono1;
    }

    public void setNumTelefono1(String numTelefono1) {
        this.numTelefono1 = numTelefono1;
    }

    @Column(name = "OPERADORA_TELEFONO1", nullable = true, length = 150)
    public String getOperadoraTelefono1() {
        return operadoraTelefono1;
    }

    public void setOperadoraTelefono1(String operadoraTelefono1) {
        this.operadoraTelefono1 = operadoraTelefono1;
    }

    @Column(name = "TELEFONO2", nullable = true, length = 150)
    public String getNumTelefono2() {
        return numTelefono2;
    }

    public void setNumTelefono2(String numTelefono2) {
        this.numTelefono2 = numTelefono2;
    }

    @Column(name = "OPERADORA_TELEFONO2", nullable = true, length = 150)
    public String getOperadoraTelefono2() {
        return operadoraTelefono2;
    }

    public void setOperadoraTelefono2(String operadoraTelefono2) {
        this.operadoraTelefono2 = operadoraTelefono2;
    }

    @Column(name = "TELEFONO3", nullable = true, length = 150)
    public String getNumTelefono3() {
        return numTelefono3;
    }

    public void setNumTelefono3(String numTelefono3) {
        this.numTelefono3 = numTelefono3;
    }

    @Column(name = "OPERADORA_TELEFONO3", nullable = true, length = 150)
    public String getOperadoraTelefono3() {
        return operadoraTelefono3;
    }

    public void setOperadoraTelefono3(String operadoraTelefono3) {
        this.operadoraTelefono3 = operadoraTelefono3;
    }

    @Column(name = "CODIGO_MOVIMIENTO", nullable = true, length = 150)
    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    @Column(name = "IDENTIFICADOR_EQUIPO", nullable = true, length = 150)
    public String getIdentificadoEquipo() {
        return identificadoEquipo;
    }

    public void setIdentificadoEquipo(String identificadoEquipo) {
        this.identificadoEquipo = identificadoEquipo;
    }

    @Column(name = "ESTADO", nullable = false, length = 15)
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Column(name = "PASIVO", nullable = false, length = 15)
    public char getPasivo() {
        return pasivo;
    }

    public void setPasivo(char pasivo) {
        this.pasivo = pasivo;
    }

    @Column(name = "FECHA_REGISTRO", nullable = false)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = "CREADO", length = 255)
    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    @Column(name = "USUARIO_REGISTRO", nullable = false)
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    @Column(name = "ACTUAL", nullable = true)
    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
}
