package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Everts Morales
 **/

public class ControlAsistenciaDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private Integer id;
    private String fechaasistencia;
    private String fecha_registro;
    private String horaentrada;
    private String horasalida;
    private Double latitud;
    private Double longitud;
    private String nombre_usuario;
    private String nombre_completo;
    private String identificador_equipo;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;}

    public String getFechaasistencia() {  return fechaasistencia;   }
    public void setFechaasistencia(String fechaasistencia) {
        this.fechaasistencia = fechaasistencia;
    }


    public String getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }
    public String getIdentificador_equipo() {
        return identificador_equipo;
    }

    public void setIdentificador_equipo(String identificador_equipo) {
        this.identificador_equipo = identificador_equipo;
    }
    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
