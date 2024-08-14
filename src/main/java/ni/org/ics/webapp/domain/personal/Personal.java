package ni.org.ics.webapp.domain.personal;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.language.MessageResource;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by miguel on 9/12/2021.
 */
@Entity
@Table(name = "personal", catalog = "a2cares")
public class Personal extends BaseMetaData implements Auditable {
    private Integer codigo;
    private Integer idPersona;
    private String nombre;
    private MessageResource cargo;
    private String area;

    @Id
    @Column(name = "SEQ", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "CODIGO_PERSONAL", nullable = false, length = 4)
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    @Column(name = "NOMBRE_COMPLETO", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JoinColumn(name="CAT_CARGO", updatable = true, referencedColumnName = "messageKey")
    @ForeignKey(name = "Fk_Personal_Cargo")
    public MessageResource getCargo() {
        return cargo;
    }

    public void setCargo(MessageResource cargo) {
        this.cargo = cargo;
    }

    @Column(name = "AREA", nullable = false)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personal personal = (Personal) o;

        if (!codigo.equals(personal.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
