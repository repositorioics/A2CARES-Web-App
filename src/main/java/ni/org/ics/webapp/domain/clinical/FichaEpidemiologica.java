package ni.org.ics.webapp.domain.clinical;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * Created by miguel on 5/10/2021.
 */
@Entity
@Table(name = "fichaEpidemiologica", catalog = "a2cares")
public class FichaEpidemiologica extends BaseMetaData {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int secFichaEpidemiologica;

    private Participante codigoParticipante;
    private int numFichaEpidemiologica;
    private Date fechaConsulta;
    private Time horaConsulta;

    //Datos Generales

    private String categoria;
    private String inicial;
    private String convaleciente;
    private String silais;
    private String municipio;
    private String unidad_de_salud;
    private int nro_Expediente;

    //Datos Personales

    private String ocupacion;
    private String telefonos;

   //Datos Clinicos y de Laboratorio

    private Date fis;
    private Date fif;
    private Date ftm;
    private BigDecimal temperaturac;
    private String presion;
    private String medico;
    private String codMedico;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha_epidemiologica", unique = true, nullable = false)
    public int getSecFichaEpidemiologica() {
        return this.secFichaEpidemiologica;
    }
    public void setSecFichaEpidemiologica(int secFichaEpidemiologica) {
        this.secFichaEpidemiologica = secFichaEpidemiologica;
    }

    @ManyToOne
    @JoinColumn(name = "codigo_participante", nullable = false)
    @ForeignKey(name = "FK_FICHAEPIDEMIOLOGICA_PARTICIPANTE")
    public Participante getCodigoParticipante() {
        return this.codigoParticipante;
    }
    public void setCodigoParticipante(Participante codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "numero_hoja_fichaE", unique = true, nullable = false)
    public int getNumFichaEpidemiologica() {
        return this.numFichaEpidemiologica;
    }
    public void setNumFichaEpidemiologica(int numFichaEpidemiologica) {
        this.numFichaEpidemiologica = numFichaEpidemiologica;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_consulta", nullable = false)
    public Date getFechaConsulta() {
        return this.fechaConsulta;
    }
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    @Basic
    @Column(name = "hora_consulta", nullable = false)
    public Time getHoraConsulta() {
        return horaConsulta;
    }
    public void setHoraConsulta(Time horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    @Column(name = "categoria", nullable = false)
    public String getCategoria() {
        return this.categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Column(name = "inicial", nullable = false)
    public String getInicial() {
        return this.inicial;
    }
    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    @Column(name = "convaleciente", nullable = false)
    public String getConvaleciente() {
        return this.convaleciente;
    }
    public void setConvaleciente(String convaleciente) {
        this.convaleciente = convaleciente;
    }

    @Column(name = "silais", nullable = false)
    public String getSilais() {
        return this.silais;
    }
    public void setSilais(String silais) {
        this.silais = silais;
    }

    @Column(name = "municipio", nullable = false)
    public String getMunicipio() {
        return this.municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Column(name = "unidad_de_salud", nullable = false)
    public String getUnidad_de_salud() {
        return this.unidad_de_salud;
    }
    public void setUnidad_de_salud(String unidad_de_salud) {
        this.unidad_de_salud = unidad_de_salud;
    }

    @Column(name = "nro_Expediente", unique = true, nullable = false)
    public int getNro_Expediente() {
        return this.nro_Expediente;
    }
    public void setNro_Expediente(int nro_Expediente) {
        this.nro_Expediente = nro_Expediente;
    }

    @Column(name = "ocupacion", nullable = false)
    public String getOcupacion() {
        return this.ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Column(name = "telefonos", nullable = false)
    public String getTelefonos() {
        return this.telefonos;
    }
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fis", length = 22)
    public Date getFis() {
        return this.fis;
    }
    public void setFis(Date fis) {
        this.fis = fis;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fif", length = 22)
    public Date getFif() {
        return this.fif;
    }
    public void setFif(Date fif) {
        this.fif = fif;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ftm", length = 22)
    public Date getFtm() {
        return this.ftm;
    }
    public void setFtm(Date ftm) {
        this.ftm = ftm;
    }

    @Column(name = "temperaturac", precision = 4)
    public BigDecimal getTemperaturac() {
        return this.temperaturac;
    }
    public void setTemperaturac(BigDecimal temperaturac) {
        this.temperaturac = temperaturac;
    }

    @Column(name = "presion", length = 8)
    public String getPresion() {
        return this.presion;
    }
    public void setPresion(String presion) {
        this.presion = presion;
    }

    @Column(name = "medico")
    public String getMedico() {
        return this.medico;
    }
    public void setMedico(String medico) {
        this.medico = medico;
    }

    @Column(name = "codMedico")
    public String getCodMedico() {
        return this.codMedico;
    }
    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
    }
}
