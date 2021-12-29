package ni.org.ics.webapp.domain.core;

import ni.org.ics.webapp.domain.BaseMetaData;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * Created by Miguel Salinas on 28/12/2021.
 **/

@Entity
@Table(name = "razones_datos_pendientes", catalog = "a2cares")
public class RazonNoData extends BaseMetaData {


	/**
	 * 
	 */

    private Integer id;
	private String codigoParticipante;
	private String razon;
	private String otraRazon;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_NODATA", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = true, length = 6)
	public String getCodigoParticipante() {
		return codigoParticipante;
	}

	public void setCodigoParticipante(String codigo) {
		this.codigoParticipante = codigo;
	}

    @Column(name="RAZON", nullable = true, length = 3)
	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

    @Column(name="OTRA_RAZON", nullable = true)
	public String getOtraRazon() {
		return otraRazon;
	}

	public void setOtraRazon(String otraRazon) {
		this.otraRazon = otraRazon;
	}
}
