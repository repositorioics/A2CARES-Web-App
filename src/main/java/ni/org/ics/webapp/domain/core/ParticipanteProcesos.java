package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Miguel Salinas on 21/7/2021.
 */
@Entity
@Table(name = "participantes_procesos", catalog = "a2cares", uniqueConstraints = { @UniqueConstraint(columnNames = "codigo") })
public class ParticipanteProcesos extends BaseMetaData implements Serializable, Auditable {

    @Id
    @Column(name = "codigo", nullable = false, length = 6)
    private String codigo;

    @Column(name = "retirado", nullable = false, length = 1)
    private Integer retirado;

    @Column(name = "pendiente_peso_talla", nullable = false, length = 2)
    private String pendientePyT;

    @Column(name = "pendiente_enc_part", nullable = false, length = 2)
    private String pendienteEncPart;

    @Column(name = "pendiente_enc_casa", nullable = false, length = 2)
    private String pendienteEnCasa;

    @Column(name = "pendiente_mx_ma", nullable = false, length = 2)
    private String pendienteMxMA;

    @Column(name = "pendiente_mx_tx", nullable = false, length = 2)
    private String pendienteMxTx;

    @Column(name = "pendiente_obsequio", nullable = false, length = 2)
    private String pendienteObseq;

    @Column(name = "esat_usuario", nullable = false, length = 2)
    private String esatUsuario;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getRetirado() {
        return retirado;
    }

    public void setRetirado(Integer retirado) {
        this.retirado = retirado;
    }

    public String getPendientePyT() {
        return pendientePyT;
    }

    public void setPendientePyT(String pendientePyT) {
        this.pendientePyT = pendientePyT;
    }

    public String getPendienteEncPart() {
        return pendienteEncPart;
    }

    public void setPendienteEncPart(String pendienteEncPart) {
        this.pendienteEncPart = pendienteEncPart;
    }

    public String getPendienteEnCasa() {
        return pendienteEnCasa;
    }

    public void setPendienteEnCasa(String pendienteEnCasa) {
        this.pendienteEnCasa = pendienteEnCasa;
    }

    public String getPendienteMxMA() {
        return pendienteMxMA;
    }

    public void setPendienteMxMA(String pendienteMxMA) {
        this.pendienteMxMA = pendienteMxMA;
    }

    public String getPendienteMxTx() {
        return pendienteMxTx;
    }

    public void setPendienteMxTx(String pendienteMxTx) {
        this.pendienteMxTx = pendienteMxTx;
    }


    public String getPendienteObseq() {
        return pendienteObseq;
    }

    public void setPendienteObseq(String pendienteObseq) {
        this.pendienteObseq = pendienteObseq;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "ParticipanteProcesos{" +
                "codigo=" + codigo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipanteProcesos that = (ParticipanteProcesos) o;

        if (!codigo.equals(that.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    public String getEsatUsuario() {
        return esatUsuario;
    }

    public void setEsatUsuario(String esatUsuario) {
        this.esatUsuario = esatUsuario;
    }
}
