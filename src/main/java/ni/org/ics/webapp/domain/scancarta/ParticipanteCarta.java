package ni.org.ics.webapp.domain.scancarta;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.catalogs.Version;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "scan_participante_carta", catalog = "a2cares", uniqueConstraints = { @UniqueConstraint(columnNames = "idparticipantecarta") })
public class ParticipanteCarta extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idparticipantecarta;
    private Participante participante;
    private Version version;
    private String personal;
    private Date fechacarta;
    private String asentimiento;
    private Integer relfam;
    private String quienfirma;
    private String nombre2Firma;
    private String apellido1Firma;
    private String apellido2Firma;
    private String proyecto;
    private boolean contactoFuturo;
    private String observacion;
    private Integer tipoasentimiento;

    private boolean testigopresent;
    private String nombre1testigo;
    private String nombre2testigo;
    private String apellido1testigo;
    private String apellido2testigo;

    private String edadyears;
    private String edadmeses;
    private String edaddias;

    private boolean anulada;
    private String pq_anulada;

    @Id
    @Column(name = "IDPARTICIPANTECARTA", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdparticipantecarta() {
        return idparticipantecarta;
    }

    public void setIdparticipantecarta(Integer idparticipantecarta) {
        this.idparticipantecarta = idparticipantecarta;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_IDPARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @ManyToOne
    @JoinColumn(name = "IDVERSION", nullable = false)
    @ForeignKey(name = "FK_PC_VERSION")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }


    @Column(name = "FECHA_CARTA", nullable = false)
    public Date getFechacarta() {
        return fechacarta;
    }

    public void setFechacarta(Date fechacarta) {
        this.fechacarta = fechacarta;
    }


    @Column(name = "ASENTIMIENTO", nullable = true)
    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    @Column(name = "PERSONAL", nullable = false, length = 50)
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    @Column(name = "RELACION_FAMILIAR", nullable = false)
    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }


    @Column(name = "NOMBRE1TUTOR", nullable = false)
    public String getQuienfirma() {
        return quienfirma;
    }

    public void setQuienfirma(String quienfirma) {
        this.quienfirma = quienfirma;
    }

    @Column(name = "NOMBRE2TUTOR", nullable = false)
    public String getNombre2Firma() {
        return nombre2Firma;
    }

    public void setNombre2Firma(String nombre2Firma) {
        this.nombre2Firma = nombre2Firma;
    }
    @Column(name = "APELLIDO1TUTOR", nullable = false)
    public String getApellido1Firma() {
        return apellido1Firma;
    }

    public void setApellido1Firma(String apellido1Firma) {
        this.apellido1Firma = apellido1Firma;
    }
    @Column(name = "APELLIDO2TUTOR", nullable = false)
    public String getApellido2Firma() {
        return apellido2Firma;
    }

    public void setApellido2Firma(String apellido2Firma) {
        this.apellido2Firma = apellido2Firma;
    }

    @Column(name = "PROYECTO", nullable = false)
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
    @Column(name = "CONTACTO_FUTURO", nullable = true)
    public boolean getContactoFuturo() {
        return contactoFuturo;
    }

    public void setContactoFuturo(boolean contactoFuturo) {
        this.contactoFuturo = contactoFuturo;
    }

    @Column(name = "TIPO_ASENTIMIENTO", nullable = true)
    public Integer getTipoasentimiento() {
        return tipoasentimiento;
    }

    public void setTipoasentimiento(Integer tipoasentimiento) {
        this.tipoasentimiento = tipoasentimiento;
    }

    @Column(name="OBSERVACION", nullable = true, length = 255)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Transient
    public boolean isEsContacto() {
        return this.contactoFuturo == true;
    }

    @Column(name="TESTIGO_PRESENT")
    public boolean isTestigopresent() {
        return testigopresent;
    }

    public void setTestigopresent(boolean testigopresent) {
        this.testigopresent = testigopresent;
    }
    @Column(name="NOMBRE1TESTIGO", nullable = true, length = 50)
    public String getNombre1testigo() {
        return nombre1testigo;
    }

    public void setNombre1testigo(String nombre1testigo) {
        this.nombre1testigo = nombre1testigo;
    }
    @Column(name="NOMBRE2TESTIGO", nullable = true, length = 50)
    public String getNombre2testigo() {
        return nombre2testigo;
    }

    public void setNombre2testigo(String nombre2testigo) {
        this.nombre2testigo = nombre2testigo;
    }
    @Column(name="APELLIDO1TESTIGO", nullable = true, length = 50)
    public String getApellido1testigo() {
        return apellido1testigo;
    }

    public void setApellido1testigo(String apellido1testigo) {
        this.apellido1testigo = apellido1testigo;
    }
    @Column(name="APELLIDO2TESTIGO", nullable = true, length = 50)
    public String getApellido2testigo() {
        return apellido2testigo;
    }

    public void setApellido2testigo(String apellido2testigo) {
        this.apellido2testigo = apellido2testigo;
    }

    @Column(name="EDAD_YEAR")
    public String getEdadyears() {
        return edadyears;
    }

    public void setEdadyears(String edadyears) {
        this.edadyears = edadyears;
    }
    @Column(name="EDAD_MESES")
    public String getEdadmeses() {
        return edadmeses;
    }

    public void setEdadmeses(String edadmeses) {
        this.edadmeses = edadmeses;
    }

    @Column(name="EDAD_DIAS")
    public String getEdaddias() {
        return edaddias;
    }

    public void setEdaddias(String edaddias) {
        this.edaddias = edaddias;
    }

    @Column(name = "ANULADA")
    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    @Column(name = "PQ_ANULADA", nullable = true, length = 255)
    public String getPq_anulada() {
        return pq_anulada;
    }

    public void setPq_anulada(String pq_anulada) {
        this.pq_anulada = pq_anulada;
    }
}
