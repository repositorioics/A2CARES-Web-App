package ni.org.ics.webapp.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 29/06/2024.
 */
public class BhcBc6000Dto implements Serializable {

    private Integer idbhc_bc6000;
    /*desde aqui son 34 campos con información exportada de Bc6000*/
    private String id_muestr;
    private String modo_recue;
    private String panel_prue;
    private Date fec;
    private String hora;
    private String pos_tubo;
    private String comunicado;
    private String impreso;
    private double wbc;
    private double neu;
    private double linf;
    private double mon;
    private double eos;
    private double bas;
    private double img;
    private double neu_porcentaje;
    private double linf_porcentaje;
    private double mon_porcentaje;
    private double eos_porcentaje;
    private double bas_porcentaje;
    private double img_porcentaje;
    private double rbc;
    private double hgb;
    private double hct;
    private double mcv;
    private double mch;
    private double mchc;
    private double rdw_cv;
    private double rdw_sd;
    private double plt;
    private double mpv;
    private double pdw;
    private double pct;
    private double p_lcc;
    private double p_lcr;
    private double nrbc;
    private double nrbc_porcentaje;
    /*Hasta aqui son 34 campos con información exportada de Bc6000*/

    private double wbc_bf;
    private double tc_bf;
    private double mn;
    private double mn_porcentaje;
    private double pmn;
    private double pmn_porcentaje;
    private double rbc_bf;

    private String id_pacient;
    private String nomb;
    private String sexo;
    private Date fecha_nacim;
    private double edad;
    private String tipo_pac;
    private String grupo_ref;
    private String dpto;
    private String num_cama;
    private Date fecha_extr;
    private String hora_extr;
    private Date fecha_ext;
    private String hora_entrega;
    private String medico;
    private String operador;
    private String validado_por;
    private String coments;







    public BhcBc6000Dto() {
    }

    public Integer getIdbhc_bc6000() {
        return idbhc_bc6000;
    }
    public void setIdbhc_bc6000(Integer idbhc_bc6000) {
        this.idbhc_bc6000 = idbhc_bc6000;
    }
    public String getId_muestr() {
        return id_muestr;
    }
    public void setId_muestr(String id_muestr) {
        this.id_muestr = id_muestr;
    }
    public String getModo_recue() {
        return modo_recue;
    }
    public void setModo_recue(String modo_recue) {
        this.modo_recue = modo_recue;
    }
    public String getPanel_prue() {
        return panel_prue;
    }
    public void setPanel_prue(String panel_prue) {
        this.panel_prue = panel_prue;
    }
    public Date getFec() {
        return fec;
    }
    public void setFec(Date fec) {
        this.fec = fec;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getPos_tubo() {
        return pos_tubo;
    }
    public void setPos_tubo(String pos_tubo) {
        this.pos_tubo = pos_tubo;
    }
    public String getComunicado() {
        return comunicado;
    }
    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }
    public String getImpreso() {
        return impreso;
    }
    public void setImpreso(String impreso) {
        this.impreso = impreso;
    }
    public double getWbc() {
        return wbc;
    }
    public void setWbc(double wbc) {
        this.wbc = wbc;
    }
    public double getNeu() {
        return neu;
    }
    public void setNeu(double neu) {
        this.neu = neu;
    }
    public double getLinf() {
        return linf;
    }
    public void setLinf(double linf) {
        this.linf = linf;
    }
    public double getMon() {
        return mon;
    }
    public void setMon(double mon) {
        this.mon = mon;
    }
    public double getEos() {
        return eos;
    }
    public void setEos(double eos) {
        this.eos = eos;
    }
    public double getBas() {
        return bas;
    }
    public void setBas(double bas) {
        this.bas = bas;
    }
    public double getImg() {
        return img;
    }
    public void setImg(double img) {
        this.img = img;
    }
    //%
    public double getNeu_porcentaje() {
        return neu_porcentaje;
    }
    public void setNeu_porcentaje(double neu_porcentaje) {
        this.neu_porcentaje = neu_porcentaje;
    }
    public double getLinf_porcentaje() {
        return linf_porcentaje;
    }
    public void setLinf_porcentaje(double linf_porcentaje) {
        this.linf_porcentaje = linf_porcentaje;
    }
    public double getMon_porcentaje() {
        return mon_porcentaje;
    }
    public void setMon_porcentaje(double mon_porcentaje) {
        this.mon_porcentaje = mon_porcentaje;
    }
    public double getEos_porcentaje() {
        return eos_porcentaje;
    }
    public void setEos_porcentaje(double eos_porcentaje) {
        this.eos = eos_porcentaje;
    }
    public double getBas_porcentaje() {
        return bas_porcentaje;
    }
    public void setBas_porcentaje(double bas_porcentaje) {
        this.bas_porcentaje = bas_porcentaje;
    }
    public double getImg_porcentaje() {
        return img_porcentaje;
    }
    public void setImg_porcentaje(double img_porcentaje) {
        this.img_porcentaje = img_porcentaje;
    }
    public double getRbc() {
        return rbc;
    }
    public void setRbc(double rbc) {
        this.rbc = rbc;
    }
    public double getHgb() {
        return hgb;
    }
    public void setHgb(double hgb) {
        this.hgb = hgb;
    }
    public double getHct() {
        return hct;
    }
    public void setHct(double hct) {
        this.hct = hct;
    }
    public double getMcv() {
        return mcv;
    }
    public void setMcv(double mcv) {
        this.mcv = mcv;
    }
    public double getMch() {
        return mch;
    }
    public void setMch(double mch) {
        this.mch = mch;
    }
    public double getMchc() {
        return mchc;
    }
    public void setMchc(double mchc) {
        this.mchc = mchc;
    }
    public double getRdw_cv() {
        return rdw_cv;
    }
    public void setRdw_cv(double rdw_cv) {
        this.rdw_cv = rdw_cv;
    }
    public double getRdw_sd() {
        return rdw_sd;
    }
    public void setRdw_sd(double rdw_sd) {
        this.rdw_sd = rdw_sd;
    }
    public double getPlt() {
        return plt;
    }
    public void setPlt(double plt) {
        this.plt = plt;
    }
    public double getMpv() {
        return mpv;
    }
    public void setMpv(double mpv) {
        this.mpv = mpv;
    }
    public double getPdw() {
        return pdw;
    }
    public void setPdw (double pdw) {
        this.pdw = pdw;
    }
    public double getPct() {
        return pct;
    }
    public void setPct (double pct) {
        this.pct = pct;
    }
    public double getP_lcc() {
        return p_lcc;
    }
    public void setP_lcc (double p_lcc) {
        this.p_lcc = p_lcc;
    }
    public double getP_lcr() {
        return p_lcr;
    }
    public void setP_lcr (double p_lcr) {
        this.p_lcr = p_lcr;
    }
    public double getNrbc() {
        return nrbc;
    }
    public void setNrbc (double nrbc) {
        this.nrbc = nrbc;
    }
    public double getNrbc_porcentaje() {
        return nrbc_porcentaje;
    }
    public void setNrbc_porcentaje (double nrbc_porcentaje) {
        this.nrbc_porcentaje = nrbc_porcentaje;
    }
    public double getWbc_bf() {
        return wbc_bf;
    }
    public void setWbc_bf (double wbc_bf) {
        this.wbc_bf = wbc_bf;
    }
    public double getTc_bf() {
        return tc_bf;
    }
    public void setTc_bf (double tc_bf) {
        this.tc_bf = tc_bf;
    }
    public double getMn() {
        return mn;
    }
    public void setMn (double mn) {
        this.mn = mn;
    }
    public double getMn_porcentaje() {
        return mn_porcentaje;
    }
    public void setMn_porcentaje (double mn_porcentaje) {
        this.mn_porcentaje = mn_porcentaje;
    }
    public double getPmn() {
        return pmn;
    }
    public void setPmn (double pmn) {
        this.pmn = pmn;
    }
    public double getPmn_porcentaje() {
        return pmn_porcentaje;
    }
    public void setPmn_porcentaje (double pmn_porcentaje) {
        this.pmn_porcentaje = pmn_porcentaje;
    }
    public double getRbc_bf() {
        return rbc_bf;
    }
    public void setRbc_bf (double rbc_bf) {
        this.rbc_bf = rbc_bf;
    }
    public String getId_pacient() {
        return id_pacient;
    }
    public void setId_pacient (String id_pacient) {
        this.id_pacient = id_pacient;
    }
    public String getNomb() {
        return nomb;
    }
    public void setNomb (String nomb) {
        this.nomb = nomb;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo (String sexo) {
        this.sexo = sexo;
    }
    public Date getFecha_nacim() {
        return fecha_nacim;
    }
    public void setFecha_nacim (Date fecha_nacim) {
        this.fecha_nacim = fecha_nacim;
    }
    public double getEdad() {
        return edad;
    }
    public void setEdad(double edad) {
        this.edad = edad;
    }
    public String getTipo_pac() {
        return tipo_pac;
    }
    public void setTipo_pac(String tipo_pac) {
        this.tipo_pac = tipo_pac;
    }
    public String getGrupo_ref() {
        return grupo_ref;
    }
    public void setGrupo_ref(String grupo_ref) {
        this.grupo_ref = grupo_ref;
    }
    public String getDpto() {
        return dpto;
    }
    public void setDpto(String dpto) {
        this.dpto = dpto;
    }
    public String getNum_cama() {
        return num_cama;
    }
    public void setNum_cama(String num_cama) {
        this.num_cama = num_cama;
    }
    public Date getFecha_extr() {
        return fecha_extr;
    }
    public void setFecha_extr(Date fecha_extr) {
        this.fecha_extr = fecha_extr;
    }
    public String getHora_extr() {
        return hora_extr;
    }
    public void setHora_extr(String hora_extr) {
        this.hora_extr = hora_extr;
    }
    public Date getFecha_ext() {
        return fecha_ext;
    }
    public void setFecha_ext(Date fecha_ext) {
        this.fecha_ext = fecha_ext;
    }
    public String getHora_entrega() {
        return hora_entrega;
    }
    public void setHora_entrega(String hora_entrega) {
        this.hora_entrega = hora_entrega;
    }
    public String getMedico() {
        return medico;
    }
    public void setMedico(String medico) {
        this.medico = medico;
    }
    public String getOperador() {
        return operador;
    }
    public void setOperador(String operador) {
        this.operador = operador;
    }
    public String getValidado_por() {
        return validado_por;
    }
    public void setValidado_por(String validado_por) {
        this.validado_por = validado_por;
    }
    public String getComents() {
        return coments;
    }
    public void setComents(String coments) {
        this.coments = coments;
    }
}
