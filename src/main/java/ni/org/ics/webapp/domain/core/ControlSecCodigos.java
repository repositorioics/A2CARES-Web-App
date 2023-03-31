package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple objeto de dominio que representa la secuencia de codigos a imprimir
 * 
 * @author Everts Morales
 **/

@Entity
@Table(name = "controlSecCodigosImp", catalog = "a2cares")
public class ControlSecCodigos extends BaseMetaData implements Auditable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String estudio;
    private String desc_titulo_imprimir;
    private Integer ultimo_existente;
    private Integer ultimo_impreso;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "ESTUDIO", length = 50)
    public String getEstudio() {
        return estudio;
    }
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Column(name = "DESC_TITULO_IMPRIMIR", length = 50)
    public String getDesc_titulo_imprimir() {
        return desc_titulo_imprimir;
    }
    public void setDesc_titulo_imprimir(String desc_titulo_imprimir) {
        this.desc_titulo_imprimir = desc_titulo_imprimir;
    }

    @Column(name = "ULTIMO_EXISTENTE", length = 20)
    public Integer getUltimo_existente() {
        return ultimo_existente;
    }
    public void setUltimo_existente(Integer ultimo_existente) {
        this.ultimo_existente = ultimo_existente;
    }

    @Column(name = "ULTIMO_IMPRESO", length = 20)
    public Integer getUltimo_impreso() {
        return ultimo_impreso;
    }
    public void setUltimo_impreso(Integer ultimo_impreso) {
        this.ultimo_impreso = ultimo_impreso;
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ControlSecCodigos)) return false;

        ControlSecCodigos estudio = (ControlSecCodigos) o;

        return (!codigo.equals(estudio.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
