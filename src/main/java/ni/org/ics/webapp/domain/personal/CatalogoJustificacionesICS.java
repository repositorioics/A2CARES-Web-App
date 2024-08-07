package ni.org.ics.webapp.domain.personal;

import ni.org.ics.webapp.domain.BaseMetaData;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos demograficos de la casa
 * donde habitan 1 o mas participantes de los estudios
 * 
 * @author Everts Morales
 **/

@Entity
@Table(name = "catalogo_justificaciones_ics", catalog = "a2cares")
public class CatalogoJustificacionesICS extends BaseMetaData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */


    private String id;
    private String descripcion;
    private String unidad;



    @Id
    @Column(name = "ID", nullable = false)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "DESCRIPCION", nullable = true, length = 100)
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "UNIDAD", nullable = true, length = 100)
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
}
