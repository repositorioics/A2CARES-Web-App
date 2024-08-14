package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos una recepcion de muestra de enfermo en el laboratorio
 * 
 * @author Everts Morales
 **/

public class AdmisionPacientesDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

    private Integer id;
    private String  perteneceEstudio;
    private String edad;
    private String  sexo;
    private String  codigoParticipante;
    private String  febril;
    private Integer numeroHoja;
    private String nombre_usuario;
    private String identificador_equipo;
    private String fecha_registro;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;}

    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getCodigoParticipante() {
        return codigoParticipante;
    }
    public void setCodigoParticipante(String codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public String getPerteneceEstudio() {
        return perteneceEstudio;
    }
    public void setPerteneceEstudio(String perteneceEstudio) {
        this.perteneceEstudio = perteneceEstudio;
    }


    public String getFebril() {
        return febril;
    }
    public void setFebril(String febril) {
        this.febril = febril;
    }


    public Integer getNumeroHoja() {
        return numeroHoja;
    }
    public void setNumeroHoja(Integer numeroHoja) {
        this.numeroHoja = numeroHoja;
    }
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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
