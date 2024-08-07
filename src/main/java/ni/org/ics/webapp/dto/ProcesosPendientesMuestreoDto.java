package ni.org.ics.webapp.dto;

import ni.org.ics.webapp.domain.BaseMetaData;

import java.io.Serializable;


/**
 * Simple objeto de dominio que representa los datos de procesos pendientes de muestreo
 * 
 * @author Everts Morales
 **/

public class ProcesosPendientesMuestreoDto extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */


    private String  codigo_casa;
    private String codigo;
    private String sector;
    private String barrio;
    private String  enc_part;
    private String  enc_casa;
    private String  pyt;
    private String enc_satis;
    private String obsequio;
    private String mx;
    private String conv;


    public String getCodigo_casa() {
        return codigo_casa;
    }
    public void setCodigo_casa(String codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getEnc_part() {
        return enc_part;
    }
    public void setEnc_part(String enc_part) {
        this.enc_part = enc_part;
    }

    public String getEnc_casa() {
        return enc_casa;
    }
    public void setEnc_casa(String enc_casa) {
        this.enc_casa = enc_casa;
    }


    public String getPyt() {
        return pyt;
    }
    public void setPyt(String pyt) {
        this.pyt = pyt;
    }


    public String getEnc_satis() {
        return enc_satis;
    }
    public void setEnc_satis(String enc_satis) {
        this.enc_satis = enc_satis;
    }

    public String getObsequio() {
        return obsequio;
    }

    public void setObsequio(String obsequio) {
        this.obsequio = obsequio;
    }

    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }
    public String getConv() {
        return conv;
    }

    public void setConv(String conv) {
        this.conv = conv;
    }
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
