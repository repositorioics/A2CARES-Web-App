package ni.org.ics.webapp.domain.audit;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 19/1/2022.
 */
@Entity
@Table(name = "bitacora_actualizacion_apk", catalog = "a2cares")
public class LogUpdateApk {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private Date fechaActualizacion;
    private String usuarioActualiza;
    private String dispositivo;
    private Date fechaRecibido;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="FECHA_ACTUALIZA")
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Column(name="USUARIO_ACTUALIZA", length = 50)
    public String getUsuarioActualiza() {
        return usuarioActualiza;
    }

    public void setUsuarioActualiza(String usuarioActualiza) {
        this.usuarioActualiza = usuarioActualiza;
    }

    @Column(name="IDENTIFICADOR_EQUIPO", length = 100)
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    @JsonIgnore
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }
}
