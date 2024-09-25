package ni.org.ics.webapp.domain.hemodinamica;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Lizandro Serrano on 14/08/2024.
 */

@Entity
@Table(name = "hemodetalle", catalog = "a2cares")
public class HemoDetalle extends BaseMetaData {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String idHemoDetalle;
    private String densidadUrinaria;
    private  String diuresis;
    private String extremidades;
    private String fc;
    private Date fecha;
    private String fr;
    private String hora;
    private  String llenadoCapilar;
    private String nivelConciencia;
    private String pulsoCalidad;
    private  String sa;
    private String signo;
    private String tc;
    private DatosHemodinamica datoshemodinamica;
    private int personaValida;
    private String ps;
    private String pd;
    private String pp;
    private String pam;
    private String dx;
    private BigDecimal cantidadOrina;
    private BigDecimal cargasIV;
    private BigDecimal sro;

    public HemoDetalle(){}

    public HemoDetalle(String idHemoDetalle, String densidadUrinaria, String diuresis, String extremidades, String fc, Date fecha, String fr, String hora, String llenadoCapilar, String nivelConciencia, String pulsoCalidad, String sa, String signo, String tc, DatosHemodinamica datoshemodinamica, int personaValida, String ps, String pd, String pp, String pam, String dx, BigDecimal cantidadOrina, BigDecimal cargasIV, BigDecimal sro) {
        this.idHemoDetalle = idHemoDetalle;
        this.densidadUrinaria = densidadUrinaria;
        this.diuresis = diuresis;
        this.extremidades = extremidades;
        this.fc = fc;
        this.fecha = fecha;
        this.fr = fr;
        this.hora = hora;
        this.llenadoCapilar = llenadoCapilar;
        this.nivelConciencia = nivelConciencia;
        this.pulsoCalidad = pulsoCalidad;
        this.sa = sa;
        this.signo = signo;
        this.tc = tc;
        this.datoshemodinamica = datoshemodinamica;
        this.personaValida = personaValida;
        this.ps = ps;
        this.pd = pd;
        this.pp = pp;
        this.pam = pam;
        this.dx = dx;
        this.cantidadOrina = cantidadOrina;
        this.cargasIV = cargasIV;
        this.sro = sro;
    }

    /* Getter y Setter */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "idHemoDetalle", nullable = false)
    public String getIdHemoDetalle () {
        return idHemoDetalle;
    }
    public void setIdHemoDetalle (String idHemoDetalle){
        this.idHemoDetalle = idHemoDetalle;
    }

    @Column(name = "densidadUrinaria", nullable = false)
    public String getDensidadUrinaria() {
        return densidadUrinaria;
    }
    public void setDensidadUrinaria(String DensidadUrinaria) {
        this.densidadUrinaria = DensidadUrinaria;
    }

    @Column(name = "diuresis", nullable = false)
    public String getDiuresis() {
        return diuresis;
    }

    public void setDiuresis(String Diuresis) {
        this.diuresis = Diuresis;
    }

    @Column(name = "extremidades", nullable = false)
    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String Extremidades) {
        this.extremidades = Extremidades;
    }

    @Column(name = "fc", nullable = false)
    public String getFc() {
        return fc;
    }

    public void setFc(String Fc) {
        this.fc = Fc;
    }


    @Column(name = "fecha", nullable = false)
    public Date getFecha(){
        return fecha;
    }
    public  void setFecha(Date Fecha){
        this.fecha = Fecha;
    }

    @Column(name = "fr", nullable = false)
    public String getFr() {
        return fr;
    }

    public void setFr(String Fr) {
        this.fr = Fr;
    }


    @Column(name = "hora", nullable = false)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Column(name = "llenadoCapilar", nullable = false)
    public String getLlenadoCapilar() {
        return llenadoCapilar;
    }

    public void setLlenadoCapilar(String LlenadoCapilar) {
        this.llenadoCapilar = LlenadoCapilar;
    }

    @Column(name = "nivelConciencia", nullable = false)
    public String getNivelConciencia() {
        return nivelConciencia;
    }

    public void setNivelConciencia(String nivelConciencia) {
        this.nivelConciencia = nivelConciencia;
    }

    @Column(name = "pulsoCalidad", nullable = false)
    public String getPulsoCalidad() {
        return pulsoCalidad;
    }

    public void setPulsoCalidad(String PulsoCalidad) {
        this.pulsoCalidad = PulsoCalidad;
    }

    @Column(name = "sa", nullable = false)
    public String getSa() {
        return sa;
    }

    public void setSa(String Sa) {
        this.sa = Sa;
    }

    @Column(name = "signo", nullable = false)
    public String getSigno() {
        return signo;
    }
    public void setSigno(String Signo) {
        this.signo = Signo;
    }

    @Column(name = "tc", nullable = false)
    public String getTc() {
        return tc;
    }

    public void setTc(String Tc) {
        this.tc = Tc;
    }


    @ManyToOne
    @JoinColumn(name="idDatoHemo", updatable = false)
    @ForeignKey(name = "FK_idDatoHemo")
    public DatosHemodinamica getDatoshemodinamica(){
        return datoshemodinamica;
    }
    public void setDatoshemodinamica(DatosHemodinamica datoshemodinamica) {
        this.datoshemodinamica = datoshemodinamica;
    }


    @Column(name = "personaValida", nullable = false)
    public int getPersonaValida () {
        return personaValida;
    }
    public void setPersonaValida  (int personaValida) {
        this.personaValida = personaValida;
    }

    @Column(name = "ps", nullable = false)
    public String getPs(){return ps;}

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Column(name = "pd", nullable = false)
    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    @Column(name = "pp", nullable = false)
    public String getPp(){return pp;}

    public void setPp(String pp) {
        this.pp = pp;
    }
    @Column(name = "pam", nullable = false)
    public String getPam(){return pam;}

    public void setPam(String pam) {
        this.pam = pam;
    }

    @Column(name = "dx", nullable = false)
    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    @Column(name = "cantidad_orina", nullable = true)
    public BigDecimal getCantidadOrina() {
        return cantidadOrina;
    }

    public void setCantidadOrina(BigDecimal cantidadOrina) {
        this.cantidadOrina = cantidadOrina;
    }

    @Column(name = "cargas_iv", nullable = true)
    public BigDecimal getCargasIV() {
        return cargasIV;
    }

    public void setCargasIV(BigDecimal cargasIV) {
        this.cargasIV = cargasIV;
    }

    @Column(name = "suero_oral", nullable = true)
    public BigDecimal getSro() {
        return sro;
    }

    public void setSro(BigDecimal sro) {
        this.sro = sro;
    }
}//fin class
