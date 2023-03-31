package ni.org.ics.webapp.domain.entomologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Everts Morales on 20/03/2023.
 */
@Entity
@Table(name = "ento_doble_digitacion_cuestionario", catalog = "a2cares" , uniqueConstraints = { @UniqueConstraint(columnNames = "codigo_encuesta") })
public class Tabla_Doble_Digitacion extends BaseMetaData implements Auditable {

    private String codigoEncuesta;

   // Fecha de la encuesta
    private Date fechaEncuesta;

    private Integer codigo_barrio;
   // Numero de libreta de inspección dos digitos
    private Integer numLibretaIns;
   //Número de folio de libreta de inspección dos digitos
    private Integer numFolioLibretaIns;
   //Inspector-a entomólogo-a
    private String inspector_a_Ento;
    //Digitador 1
    private String digitador1;
    //Digitador 2
    private String digitador2;
    //Código CV: <cuatro dígitos>
    private String codigoCV;
    //Código PC: <cuatro dígitos>
    private String codigoPC;
    //Código PERI: <Cuatro dígitos>
    private String codigoPERI;
    //Código INTRA: <Cuatro dígitos>
    private String codigoINTRA;





    @Id
    @Column(name = "codigo_encuesta", nullable = false, length = 36)
    public String getCodigoEncuesta() {    return codigoEncuesta; }
    public void setCodigoEncuesta(String codigoEncuesta) { this.codigoEncuesta = codigoEncuesta;
    }

    @Column(name = "fecha_encuesta", nullable = false)
    public Date getFechaEncuesta() {
        return fechaEncuesta;
    }
    public void setFechaEncuesta(Date fechaEncuesta) {
        this.fechaEncuesta = fechaEncuesta;
    }

    @Column(name = "codigo_barrio", nullable = false)
    public Integer getCodigo_barrio() {
        return codigo_barrio;
    }
    public void setCodigo_barrio(Integer codigo_barrio) {
        this.codigo_barrio = codigo_barrio;
    }

    @Column(name = "numLibretaIns", nullable = false)
    public Integer getNumLibretaIns() {
        return numLibretaIns;
    }
    public void setNumLibretaIns(Integer numLibretaIns) {
        this.numLibretaIns = numLibretaIns;
    }

    @Column(name = "numFolioLibretaIns", nullable = false)
    public Integer getNumFolioLibretaIns() {
        return numFolioLibretaIns;
    }
    public void setNumFolioLibretaIns(Integer numFolioLibretaIns) {
        this.numFolioLibretaIns = numFolioLibretaIns;
    }

    @Column(name = "inspector_a_Ento",  length = 50)
    public String getInspector_a_Ento() {
        return inspector_a_Ento;
    }
    public void setInspector_a_Ento(String inspector_a_Ento) {
        this.inspector_a_Ento = inspector_a_Ento;
    }

    @Column(name = "digitador1",  length = 50)
    public String getDigitador1() {
        return digitador1;
    }
    public void setDigitador1(String digitador1) {
        this.digitador1 = digitador1;
    }

    @Column(name = "digitador2",  length = 50)
    public String getDigitador2() {
        return digitador2;
    }
    public void setDigitador2(String digitador2) {
        this.digitador2 = digitador2;
    }

    @Column(name = "codigo_CV", nullable = false, length = 4)
    public String getCodigoCV() {
        return codigoCV;
    }
    public void setCodigoCV(String codigoCV) {
        this.codigoCV = codigoCV;
    }

    @Column(name = "codigo_PC", nullable = false, length = 4)
    public String getCodigoPC() {
        return codigoPC;
    }
    public void setCodigoPC(String codigoPC) {    this.codigoPC = codigoPC;}

    @Column(name = "codigo_PERI", nullable = false, length = 4)
    public String getCodigoPERI() {
        return codigoPERI;
    }
    public void setCodigoPERI(String codigoPERI) {    this.codigoPERI = codigoPERI;}

    @Column(name = "codigo_INTRA", nullable = false, length = 4)
    public String getCodigoINTRA() {
        return codigoINTRA;
    }
    public void setCodigoINTRA(String codigoINTRA) {this.codigoINTRA = codigoINTRA;}


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoEncuesta='" + codigoEncuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tabla_Doble_Digitacion)) return false;

        Tabla_Doble_Digitacion that = (Tabla_Doble_Digitacion) o;

        if (!codigoEncuesta.equals(that.codigoEncuesta)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoEncuesta.hashCode();
    }
}
