package ni.org.ics.webapp.dto;

import java.io.Serializable;

/**
 * Created by Everts Morales Reyes.
 */
public class ControlSecCodigosDto implements Serializable {


    private Integer codigo;
    private String estudio;
    private String desc_titulo_imprimir;
    private Integer ultimo_existente;
    private Integer ultimo_impreso;

    public ControlSecCodigosDto() {
    }

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEstudio() {
        return estudio;
    }
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getDesc_titulo_imprimir() {
        return desc_titulo_imprimir;
    }
    public void setDesc_titulo_imprimir(String desc_titulo_imprimir) {
        this.desc_titulo_imprimir = desc_titulo_imprimir;
    }

    public Integer getUltimo_existente() {
        return ultimo_existente;
    }
    public void setUltimo_existente(Integer desc_titulo_imprimir) {
        this.ultimo_existente = ultimo_existente;
    }

    public Integer getUltimo_impreso() {
        return ultimo_impreso;
    }
    public void setUltimo_impreso(Integer ultimo_impreso) {
        this.ultimo_impreso = ultimo_impreso;
    }
}
