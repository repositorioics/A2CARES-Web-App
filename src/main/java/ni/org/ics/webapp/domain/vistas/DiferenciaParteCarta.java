package ni.org.ics.webapp.domain.vistas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by miguel on 7/1/2022.
 *  * Los campos que terminan con Cc se refieren a la tabla cartas_consentimientos y los que terminan con Sc a la tabla scan_participante_carta
 */
@Entity
@Table(name = "vw_diferencias_partes_cartas", catalog = "a2cares")
public class DiferenciaParteCarta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String fechaFirma;
    private String usuarioRegistro;
    private Integer edadActualMeses;
    private Integer edadMeses;
    private String aceptaParteASc;
    private String aceptaParteACc;
    private String aceptaParteBCc;
    private String aceptaParteBSc;
    private String aceptaParteCCc;
    private String aceptaParteCSc;
    private String aceptaContactoFuturoCc;
    private String aceptaContactoFuturoSc;
    private String asentimientoVerbalCc;
    private String asentimientoVerbalSc;
    private String versionCc;
    private String versionSc;

    @Id
    @Column(name = "CODIGO_PARTICIPANTE", nullable = false, length = 6)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Id
    @Column(name = "FECHA_FIRMA", nullable = false, length = 16)
    public String getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(String fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    @Column(name = "USUARIO_REGISTRO", nullable = false, length = 50)
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    @Column(name = "EDAD_ACTUAL_MESES", nullable = false)
    public Integer getEdadActualMeses() {
        return edadActualMeses;
    }

    public void setEdadActualMeses(Integer edadActualMeses) {
        this.edadActualMeses = edadActualMeses;
    }

    @Column(name = "EDAD_MESES", nullable = false)
    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
    }

    @Column(name = "ACEPTA_PARTE_A_CC", nullable = false, length = 2)
    public String getAceptaParteASc() {
        return aceptaParteASc;
    }

    public void setAceptaParteASc(String aceptaParteASc) {
        this.aceptaParteASc = aceptaParteASc;
    }

    @Column(name = "ACEPTA_PARTE_A_SC", nullable = false, length = 2)
    public String getAceptaParteACc() {
        return aceptaParteACc;
    }

    public void setAceptaParteACc(String aceptaParteACc) {
        this.aceptaParteACc = aceptaParteACc;
    }

    @Column(name = "ACEPTA_PARTE_B_CC", nullable = false, length = 2)
    public String getAceptaParteBCc() {
        return aceptaParteBCc;
    }

    public void setAceptaParteBCc(String aceptaParteBCc) {
        this.aceptaParteBCc = aceptaParteBCc;
    }

    @Column(name = "ACEPTA_PARTE_B_SC", nullable = false, length = 2)
    public String getAceptaParteBSc() {
        return aceptaParteBSc;
    }

    public void setAceptaParteBSc(String aceptaParteBSc) {
        this.aceptaParteBSc = aceptaParteBSc;
    }

    @Column(name = "ACEPTA_PARTE_C_CC", nullable = false, length = 2)
    public String getAceptaParteCCc() {
        return aceptaParteCCc;
    }

    public void setAceptaParteCCc(String aceptaParteCCc) {
        this.aceptaParteCCc = aceptaParteCCc;
    }

    @Column(name = "ACEPTA_PARTE_C_SC", nullable = false, length = 2)
    public String getAceptaParteCSc() {
        return aceptaParteCSc;
    }

    public void setAceptaParteCSc(String aceptaParteCSc) {
        this.aceptaParteCSc = aceptaParteCSc;
    }

    @Column(name = "ACEPTA_CONTACTO_FUTURO_CC", nullable = false, length = 2)
    public String getAceptaContactoFuturoCc() {
        return aceptaContactoFuturoCc;
    }

    public void setAceptaContactoFuturoCc(String aceptaContactoFuturoCc) {
        this.aceptaContactoFuturoCc = aceptaContactoFuturoCc;
    }

    @Column(name = "ACEPTA_CONTACTO_FUTURO_SC", nullable = false, length = 2)
    public String getAceptaContactoFuturoSc() {
        return aceptaContactoFuturoSc;
    }

    public void setAceptaContactoFuturoSc(String aceptaContactoFuturoSc) {
        this.aceptaContactoFuturoSc = aceptaContactoFuturoSc;
    }

    @Column(name = "ASENTIMIENTO_VERBAL_CC", nullable = false, length = 2)
    public String getAsentimientoVerbalCc() {
        return asentimientoVerbalCc;
    }

    public void setAsentimientoVerbalCc(String asentimientoVerbalCc) {
        this.asentimientoVerbalCc = asentimientoVerbalCc;
    }

    @Column(name = "ASENTIMIENTO_VERBAL_SC", nullable = false, length = 2)
    public String getAsentimientoVerbalSc() {
        return asentimientoVerbalSc;
    }

    public void setAsentimientoVerbalSc(String asentimientoVerbalSc) {
        this.asentimientoVerbalSc = asentimientoVerbalSc;
    }

    @Column(name = "VERSION_CC", nullable = false, length = 2)
    public String getVersionCc() {
        return versionCc;
    }

    public void setVersionCc(String versionCc) {
        this.versionCc = versionCc;
    }

    @Column(name = "VERSION_SC", nullable = false, length = 2)
    public String getVersionSc() {
        return versionSc;
    }

    public void setVersionSc(String versionSc) {
        this.versionSc = versionSc;
    }
}
