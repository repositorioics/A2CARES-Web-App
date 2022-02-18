package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "cartas_consentimientos", catalog = "a2cares")
public class CartaConsentimiento extends BaseMetaData implements Auditable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String codigo;
    private Date fechaFirma;
    private Tamizaje tamizaje;
    private Participante participante;
    private String nombre1Tutor;
    private String nombre2Tutor;
    private String apellido1Tutor;
    private String apellido2Tutor;
    private String relacionFamiliarTutor;
    private String otraRelacionFamTutor;
    private String participanteOTutorAlfabeto;
    private String testigoPresente;
    private String nombre1Testigo;
    private String nombre2Testigo;
    private String apellido1Testigo;
    private String apellido2Testigo;
    private String aceptaParteA;
    private String motivoRechazoParteA;
    private String otroMotivoRechazoParteA;
    private String aceptaContactoFuturo;
    private String aceptaParteB; //Consentimiento para enviar muestras fuera de Nicaragua
    private String aceptaParteC; //Consentimiento para uso futuro
    private String version; //Indicar la versión actual al momento de registrar la carta
    private String verifTutor;
    private Date fechaRecibido;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="FECHA_FIRMA")
    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    @Column(name = "NOMBRE1_TUTOR", length = 100)
    public String getNombre1Tutor() {
        return nombre1Tutor;
    }

    public void setNombre1Tutor(String nombre1Tutor) {
        this.nombre1Tutor = nombre1Tutor;
    }

    @Column(name = "NOMBRE2_TUTOR", length = 100)
    public String getNombre2Tutor() {
        return nombre2Tutor;
    }

    public void setNombre2Tutor(String nombre2Tutor) {
        this.nombre2Tutor = nombre2Tutor;
    }

    @Column(name = "APELLIDO1_TUTOR", length = 100)
    public String getApellido1Tutor() {
        return apellido1Tutor;
    }

    public void setApellido1Tutor(String apellido1Tutor) {
        this.apellido1Tutor = apellido1Tutor;
    }

    @Column(name = "APELLIDO2_TUTOR", length = 100)
    public String getApellido2Tutor() {
        return apellido2Tutor;
    }

    public void setApellido2Tutor(String apellido2Tutor) {
        this.apellido2Tutor = apellido2Tutor;
    }

    @Column(name = "RELACION_FAMILIAR", nullable = true, length = 50)
    public String getRelacionFamiliarTutor() {
        return relacionFamiliarTutor;
    }

    public void setRelacionFamiliarTutor(String relacionFamiliar) {
        this.relacionFamiliarTutor = relacionFamiliar;
    }

    @Column(name = "OTRA_RELACION_FAMILIAR", nullable = true, length = 100)
    public String getOtraRelacionFamTutor() {
        return otraRelacionFamTutor;
    }

    public void setOtraRelacionFamTutor(String otraRelacionFamTutor) {
        this.otraRelacionFamTutor = otraRelacionFamTutor;
    }

    @Column(name = "PARTICIPANTE_TUTOR_ALFABETO", length = 1)
    public String getParticipanteOTutorAlfabeto() {
        return participanteOTutorAlfabeto;
    }

    public void setParticipanteOTutorAlfabeto(String participanteOTutorAlfabeto) {
        this.participanteOTutorAlfabeto = participanteOTutorAlfabeto;
    }

    @Column(name = "TESTIGO_PRESENTE", length = 1)
    public String getTestigoPresente() {
        return testigoPresente;
    }

    public void setTestigoPresente(String testigoPresente) {
        this.testigoPresente = testigoPresente;
    }

    @Column(name = "NOMBRE1_TESTIGO", length = 100)
    public String getNombre1Testigo() {
        return nombre1Testigo;
    }

    public void setNombre1Testigo(String nombre1Testigo) {
        this.nombre1Testigo = nombre1Testigo;
    }

    @Column(name = "NOMBRE2_TESTIGO", length = 100)
    public String getNombre2Testigo() {
        return nombre2Testigo;
    }

    public void setNombre2Testigo(String nombre2Testigo) {
        this.nombre2Testigo = nombre2Testigo;
    }

    @Column(name = "APELLIDO1_TESTIGO", length = 100)
    public String getApellido1Testigo() {
        return apellido1Testigo;
    }

    public void setApellido1Testigo(String apellido1Testigo) {
        this.apellido1Testigo = apellido1Testigo;
    }

    @Column(name = "APELLIDO2_TESTIGO", length = 100)
    public String getApellido2Testigo() {
        return apellido2Testigo;
    }

    public void setApellido2Testigo(String apellido2Testigo) {
        this.apellido2Testigo = apellido2Testigo;
    }

    @Column(name = "ACEPTA_PARTE_A", length = 1)
    public String getAceptaParteA() {
        return aceptaParteA;
    }

    public void setAceptaParteA(String aceptaParteA) {
        this.aceptaParteA = aceptaParteA;
    }

    @Column(name = "MOTIVO_RECHAZO_PARTE_A")
    public String getMotivoRechazoParteA() {
        return motivoRechazoParteA;
    }

    public void setMotivoRechazoParteA(String motivoRechazoParteA) {
        this.motivoRechazoParteA = motivoRechazoParteA;
    }

    @Column(name = "OTRO_MOT_RECHAZO_PARTE_A", nullable = true)
    public String getOtroMotivoRechazoParteA() {
        return otroMotivoRechazoParteA;
    }

    public void setOtroMotivoRechazoParteA(String otroMotivoRechazoParteA) {
        this.otroMotivoRechazoParteA = otroMotivoRechazoParteA;
    }

    @Column(name = "ACEPTA_CONTACTO_FUTURO", length = 1)
    public String getAceptaContactoFuturo() {
        return aceptaContactoFuturo;
    }

    public void setAceptaContactoFuturo(String aceptaContactoFuturo) {
        this.aceptaContactoFuturo = aceptaContactoFuturo;
    }

    @Column(name = "ACEPTA_PARTE_B", length = 1)
    public String getAceptaParteB() {
        return aceptaParteB;
    }

    public void setAceptaParteB(String aceptaParteB) {
        this.aceptaParteB = aceptaParteB;
    }

    @Column(name = "ACEPTA_PARTE_C", length = 1)
    public String getAceptaParteC() {
        return aceptaParteC;
    }

    public void setAceptaParteC(String aceptaParteC) {
        this.aceptaParteC = aceptaParteC;
    }

    @Column(name = "VERSION", length = 10)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "VERIFICA_TUTOR", nullable = true, length = 20)
    public String getVerifTutor() {
        return verifTutor;
    }

    public void setVerifTutor(String verifTutor) {
        this.verifTutor = verifTutor;
    }

    @JsonIgnore
    @Column(name = "FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaEnvio) {
        this.fechaRecibido = fechaEnvio;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_TAMIZAJE", nullable = true)
    @ForeignKey(name = "FK_TAMIZAJE_CARTACON")
    public Tamizaje getTamizaje() {
        return tamizaje;
    }

    public void setTamizaje(Tamizaje tamizaje) {
        this.tamizaje = tamizaje;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = true)
    @ForeignKey(name = "FK_PARTICIPANTE_CARTACON")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return this.codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaConsentimiento)) return false;

        CartaConsentimiento that = (CartaConsentimiento) o;

        return  (!codigo.equals(that.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
