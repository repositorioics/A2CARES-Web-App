package ni.org.ics.webapp.domain.scancarta;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.catalogs.*;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 18/05/2021.
 */

@Entity
@Table(name = "scan_catalog_extensiones", catalog = "a2cares", uniqueConstraints = { @UniqueConstraint(columnNames = "ID_EXTENSION") })
public class Extensiones extends BaseMetaData implements Serializable {


    private Integer id;
    private String fecha_extension;
    private ni.org.ics.webapp.domain.catalogs.Version version;
    private String extension;
    private boolean activo;

    public Extensiones() {}

    public Extensiones(Integer id, ni.org.ics.webapp.domain.catalogs.Version version, String extension, String fecha_extension) {
        this.id = id;
        this.version = version;
        this.extension = extension;
        this.fecha_extension = fecha_extension;
    }

    @Id
    @Column(name = "ID_EXTENSION", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "IDVERSION", nullable = false)
    @ForeignKey(name = "FK_IDVERSION")
    public ni.org.ics.webapp.domain.catalogs.Version getVersion() {
        return version;
    }

    public void setVersion(ni.org.ics.webapp.domain.catalogs.Version version) {
        this.version = version;
    }

    @Column(name = "EXTENSION", nullable = false)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Column(name = "FECHA_EXTENSION")
    public String getFecha_extension() {
        return fecha_extension;
    }

    public void setFecha_extension(String fecha_extension) {
        this.fecha_extension = fecha_extension;
    }

    @Column(name = "ACTIVO", nullable = true)
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
