package ni.org.ics.webapp.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lizandro Serrano on 30/08/2024.
 */
public class HemoDetalleDto implements Serializable{

    private String idHemoDetalle;
    private String identificadorEquipo;
    private char estado;
    private char pasivo;
    private Date fechaRegistro;
    private String usuarioRegistro;
    private String signo;
    private String dx;
    private Date fecha;
    private String hora;
    private String fecha_hora;
    private String nivelConciencia;
    private String ps;
    private String pd;
    private String pp;
    private String pam;
    private String fc;
    private String fr;
    private String tc;
    private  String sa;
    private String extremidades;
    private  String llenadoCapilar;
    private String pulsoCalidad;
    private  String diuresis;
    private String densidadUrinaria;
    private int personaValida;
    private String idDatoHemo;
    private BigDecimal cantidadOrina;
    private BigDecimal cargasIV;
    private BigDecimal sro;
    private String numeroEvento;

    public HemoDetalleDto(){}

    public HemoDetalleDto(String idHemoDetalle, String identificadorEquipo, char estado, char pasivo, Date fechaRegistro, String usuarioRegistro, String signo, String dx, Date fecha, String hora, String fecha_hora, String nivelConciencia, String ps, String pd, String pp, String pam, String fc, String fr, String tc, String sa, String extremidades, String llenadoCapilar, String pulsoCalidad, String diuresis, String densidadUrinaria, int personaValida, String idDatoHemo, BigDecimal cantidadOrina, BigDecimal cargasIV, BigDecimal sro, String numeroEvento) {
        this.idHemoDetalle = idHemoDetalle;
        this.identificadorEquipo = identificadorEquipo;
        this.estado = estado;
        this.pasivo = pasivo;
        this.fechaRegistro = fechaRegistro;
        this.usuarioRegistro = usuarioRegistro;
        this.signo = signo;
        this.dx = dx;
        this.fecha = fecha;
        this.hora = hora;
        this.fecha_hora = fecha_hora;
        this.nivelConciencia = nivelConciencia;
        this.ps = ps;
        this.pd = pd;
        this.pp = pp;
        this.pam = pam;
        this.fc = fc;
        this.fr = fr;
        this.tc = tc;
        this.sa = sa;
        this.extremidades = extremidades;
        this.llenadoCapilar = llenadoCapilar;
        this.pulsoCalidad = pulsoCalidad;
        this.diuresis = diuresis;
        this.densidadUrinaria = densidadUrinaria;
        this.personaValida = personaValida;
        this.idDatoHemo = idDatoHemo;
        this.cantidadOrina = cantidadOrina;
        this.cargasIV = cargasIV;
        this.sro = sro;
        this.numeroEvento = numeroEvento;
    }

    public String getIdHemoDetalle() {
        return idHemoDetalle;
    }

    public void setIdHemoDetalle(String idHemoDetalle) {
        this.idHemoDetalle = idHemoDetalle;
    }

    public String getIdentificadorEquipo() {
        return identificadorEquipo;
    }

    public void setIdentificadorEquipo(String identificadorEquipo) {
        this.identificadorEquipo = identificadorEquipo;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public char getPasivo() {
        return pasivo;
    }

    public void setPasivo(char pasivo) {
        this.pasivo = pasivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getNivelConciencia() {
        return nivelConciencia;
    }

    public void setNivelConciencia(String nivelConciencia) {
        this.nivelConciencia = nivelConciencia;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getPam() {
        return pam;
    }

    public void setPam(String pam) {
        this.pam = pam;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String extremidades) {
        this.extremidades = extremidades;
    }

    public String getLlenadoCapilar() {
        return llenadoCapilar;
    }

    public void setLlenadoCapilar(String llenadoCapilar) {
        this.llenadoCapilar = llenadoCapilar;
    }

    public String getPulsoCalidad() {
        return pulsoCalidad;
    }

    public void setPulsoCalidad(String pulsoCalidad) {
        this.pulsoCalidad = pulsoCalidad;
    }

    public String getDiuresis() {
        return diuresis;
    }

    public void setDiuresis(String diuresis) {
        this.diuresis = diuresis;
    }

    public String getDensidadUrinaria() {
        return densidadUrinaria;
    }

    public void setDensidadUrinaria(String densidadUrinaria) {
        this.densidadUrinaria = densidadUrinaria;
    }

    public int getPersonaValida() {
        return personaValida;
    }

    public void setPersonaValida(int personaValida) {
        this.personaValida = personaValida;
    }

    public String getIdDatoHemo() {
        return idDatoHemo;
    }

    public void setIdDatoHemo(String idDatoHemo) {
        this.idDatoHemo = idDatoHemo;
    }

    public BigDecimal getCantidadOrina() {
        return cantidadOrina;
    }

    public void setCantidadOrina(BigDecimal cantidadOrina) {
        this.cantidadOrina = cantidadOrina;
    }

    public BigDecimal getCargasIV() {
        return cargasIV;
    }

    public void setCargasIV(BigDecimal cargasIV) {
        this.cargasIV = cargasIV;
    }

    public BigDecimal getSro() {
        return sro;
    }

    public void setSro(BigDecimal sro) {
        this.sro = sro;
    }

    public String getNumeroEvento() {
        return numeroEvento;
    }

    public void setNumeroEvento(String numeroEvento) {
        this.numeroEvento = numeroEvento;
    }
}
