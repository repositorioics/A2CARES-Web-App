package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa un barrio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "admision_pacientes", catalog = "a2cares")
public class AdmisionPacientes extends BaseMetaData implements Auditable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String  perteneceEstudio;
    private String edad;
    private String  sexo;

    private String  febril;
    private Integer numeroHoja;
    private String  codigoParticipante;

    @Id
    @Column(name = "ID", nullable = false)
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "PERTENECE_ESTUDIO", nullable = true, length = 150)
    public String getPerteneceEstudio() {
        return perteneceEstudio;
    }
    public void setPerteneceEstudio(String perteneceEstudio) {
        this.perteneceEstudio = perteneceEstudio;
    }

    @Column(name = "EDAD",nullable = true )
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Column(name = "SEXO", nullable = true, length = 20)
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "FEBRIL", nullable = true, length = 10)
    public String getFebril() {
        return febril;
    }
    public void setFebril(String febril) {
        this.febril = febril;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = true, length = 4)
    public String getcodigoParticipante() {
        return codigoParticipante;
    }
    public void setcodigoParticipante(String codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "NUMERO_HOJA", nullable = true )
    public Integer getNumeroHoja() {
        return numeroHoja;
    }
    public void setNumeroHoja(Integer numeroHoja) {
        this.numeroHoja = numeroHoja;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

    @Override
    public String toString() {
        return "'" + id + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdmisionPacientes)) return false;

        AdmisionPacientes admision_pacientes = (AdmisionPacientes) o;

        return (!id.equals(admision_pacientes.id));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
