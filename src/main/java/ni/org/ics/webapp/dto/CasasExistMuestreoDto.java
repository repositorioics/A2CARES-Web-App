package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by Everts Morales Reyes.
 */
public class CasasExistMuestreoDto implements Serializable {


    private String codigo_casa;
    private String codigo_participante;
    private Integer indice;


    public CasasExistMuestreoDto() {
    }

    public String getCodigo_casa() {
        return codigo_casa;
    }
    public void setCodigo_casa(String codigo_casa) {
        this.codigo_casa = codigo_casa;
    }

    public String getCodigo_participante() {
        return codigo_participante;
    }
    public void setCodigo_participante(String codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public Integer getIndice() {
        return indice;
    }
    public void setIndice(Integer indice) {
        this.indice = indice;
    }


}
