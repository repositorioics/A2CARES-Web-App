package ni.org.ics.webapp.domain.survey;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de peso y talla de los
 * participantes en el estudio
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "encuestas_pesotalla", catalog = "a2cares")
public class EncuestaPesoTalla extends BaseMetaData implements Auditable {

	/**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private String codigo;
	private Participante participante;
    private String tomoMedidaSn;
    private String razonNoTomoMedidas;
	private Double peso1;
	private Double peso2;
	private Double peso3;
	private Double talla1;
	private Double talla2;
	private Double talla3;
	private Double imc1;
	private Double imc2;
	private Double imc3;
	private Double difPeso;
	private Double difTalla;


    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_PESOTALLA")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "TOMA_MEDIDAS", length = 1)
    public String getTomoMedidaSn() {
        return tomoMedidaSn;
    }

    public void setTomoMedidaSn(String tomoMedidaSn) {
        this.tomoMedidaSn = tomoMedidaSn;
    }

    @Column(name = "RAZON_NO_TOMA_MEDIDAS")
    public String getRazonNoTomoMedidas() {
        return razonNoTomoMedidas;
    }

    public void setRazonNoTomoMedidas(String razonNoTomoMedidas) {
        this.razonNoTomoMedidas = razonNoTomoMedidas;
    }

    @Column(name = "PESO1", nullable = true)
	public Double getPeso1() {
		return peso1;
	}

	public void setPeso1(Double peso1) {
		this.peso1 = peso1;
	}

	@Column(name = "PESO2", nullable = true)
	public Double getPeso2() {
		return peso2;
	}

	public void setPeso2(Double peso2) {
		this.peso2 = peso2;
	}

	@Column(name = "PESO3", nullable = true)
	public Double getPeso3() {
		return peso3;
	}

	public void setPeso3(Double peso3) {
		this.peso3 = peso3;
	}

	@Column(name = "TALLA1", nullable = true)
	public Double getTalla1() {
		return talla1;
	}

	public void setTalla1(Double talla1) {
		this.talla1 = talla1;
	}

	@Column(name = "TALLA2", nullable = true)
	public Double getTalla2() {
		return talla2;
	}

	public void setTalla2(Double talla2) {
		this.talla2 = talla2;
	}

	@Column(name = "TALLA3", nullable = true)
	public Double getTalla3() {
		return talla3;
	}

	public void setTalla3(Double talla3) {
		this.talla3 = talla3;
	}

	@Column(name = "IMC1", nullable = true)
	public Double getImc1() {
		return imc1;
	}

	public void setImc1(Double imc1) {
		this.imc1 = imc1;
	}

	@Column(name = "IMC2", nullable = true)
	public Double getImc2() {
		return imc2;
	}

	public void setImc2(Double imc2) {
		this.imc2 = imc2;
	}

	@Column(name = "IMC3", nullable = true)
	public Double getImc3() {
		return imc3;
	}

	public void setImc3(Double imc3) {
		this.imc3 = imc3;
	}

	@Column(name = "DIF_PESO", nullable = true)
	public Double getDifPeso() {
		return difPeso;
	}

	public void setDifPeso(Double difPeso) {
		this.difPeso = difPeso;
	}

	@Column(name = "DIF_TALLA", nullable = true)
	public Double getDifTalla() {
		return difTalla;
	}

	public void setDifTalla(Double difTalla) {
		this.difTalla = difTalla;
	}

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaPesoTalla{" + participante.getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaPesoTalla)) return false;

        EncuestaPesoTalla that = (EncuestaPesoTalla) o;

        return  (!participante.equals(that.participante));
    }

    @Override
    public int hashCode() {
        return participante.getCodigo().hashCode();
    }
}
