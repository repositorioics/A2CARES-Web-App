package ni.org.ics.webapp.domain.Serologia;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 14/10/2020.
 */

@Entity
@Table(name = "bhc_Bc6000", catalog = "a2cares")
public class Bhc_Bc6000 extends BaseMetaData implements Auditable, Serializable  {

   private static final long serialVersionUID = 1L;

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


    //getter and setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BHC_ID", nullable = false)
    public Integer getIdbhc_bc6000() {
        return idbhc_bc6000;
    }

    public void setIdbhc_bc6000(Integer idbhc_bc6000) {
        this.idbhc_bc6000 = idbhc_bc6000;
    }

    @Column(name = "ID_MUESTRA", nullable = false)
    public String getId_muestr() {
        return id_muestr;
    }

    public void setId_muestr(String id_muestr) {
        this.id_muestr = id_muestr;
    }

    @Column(name = "MODO_RECUE", nullable = true)
    public String getModo_recue() {
        return modo_recue;
    }

    public void setModo_recue(String modo_recue) {
        this.modo_recue = modo_recue;
    }

    @Column(name = "PANEL_PRUE", nullable = true)
    public String getPanel_prue() {
        return panel_prue;
    }

    public void setPanel_prue(String panel_prue) {
        this.panel_prue = panel_prue;
    }

    @Column(name = "FEC", nullable = true)
    public Date getFec() {
        return fec;
    }
    public void setFec(Date fec) {
        this.fec = fec;
    }
    @Column(name = "HORA", nullable = true)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Column(name = "POS_TUBO", nullable = true)
    public String getPos_tubo() {
        return pos_tubo;
    }

    public void setPos_tubo(String pos_tubo) {
        this.pos_tubo = pos_tubo;
    }

    @Column(name = "COMUNICADO", nullable = true)
    public String getComunicado() {
        return comunicado;
    }

    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }

    @Column(name = "IMPRESO", nullable = true)
    public String getImpreso() {
        return impreso;
    }

    public void setImpreso(String impreso) {
        this.impreso = impreso;
    }

    @Column(name = "wbc", nullable = true)
    public double getWbc() {
        return wbc;
    }
    public void setWbc(double wbc) {
        this.wbc = wbc;
    }

    @Column(name = "NEU", nullable = true)
    public double getNeu() {
        return neu;
    }
    public void setNeu(double neu) {
        this.neu = neu;
    }
    @Column(name = "LINF", nullable = true)
    public double getLinf() {
        return linf;
    }
    public void setLinf(double linf) {
        this.linf = linf;
    }
    @Column(name = "MON", nullable = true)
    public double getMon() {
        return mon;
    }
    public void setMon(double mon) {
        this.mon = mon;
    }
    @Column(name = "EOS", nullable = true)
    public double getEos() {
        return eos;
    }
    public void setEos(double eos) {
        this.eos = eos;
    }
    @Column(name = "BAS", nullable = true)
    public double getBas() {
        return bas;
    }
    public void setBas(double bas) {
        this.bas = bas;
    }
    @Column(name = "IMG", nullable = true)
    public double getImg() {
        return img;
    }
    public void setImg(double img) {
        this.img = img;
    }
    //%
    @Column(name = "NEU_PORCENTAJE", nullable = true)
    public double getNeu_porcentaje() {
        return neu_porcentaje;
    }
    public void setNeu_porcentaje(double neu_porcentaje) {
        this.neu_porcentaje = neu_porcentaje;
    }
    @Column(name = "LINF_PORCENTAJE", nullable = true)
    public double getLinf_porcentaje() {
        return linf_porcentaje;
    }
    public void setLinf_porcentaje(double linf_porcentaje) {
        this.linf_porcentaje = linf_porcentaje;
    }
    @Column(name = "MON_PORCENTAJE", nullable = true)
    public double getMon_porcentaje() {
        return mon_porcentaje;
    }
    public void setMon_porcentaje(double mon_porcentaje) {
        this.mon_porcentaje = mon_porcentaje;
    }
    @Column(name = "EOS_PORCENTAJE", nullable = true)
    public double getEos_porcentaje() {
        return eos_porcentaje;
    }
    public void setEos_porcentaje(double eos_porcentaje) {
        this.eos = eos_porcentaje;
    }
    @Column(name = "BAS_PORCENTAJE", nullable = true)
    public double getBas_porcentaje() {
        return bas_porcentaje;
    }
    public void setBas_porcentaje(double bas_porcentaje) {
        this.bas_porcentaje = bas_porcentaje;
    }
    @Column(name = "IMG_PORCENTAJE", nullable = true)
    public double getImg_porcentaje() {
        return img_porcentaje;
    }
    public void setImg_porcentaje(double img_porcentaje) {
        this.img_porcentaje = img_porcentaje;
    }
    @Column(name = "RBC", nullable = true)
    public double getRbc() {
        return rbc;
    }
    public void setRbc(double rbc) {
        this.rbc = rbc;
    }
    @Column(name = "HGB", nullable = true)
    public double getHgb() {
        return hgb;
    }
    public void setHgb(double hgb) {
        this.hgb = hgb;
    }
    @Column(name = "HCT", nullable = true)
    public double getHct() {
        return hct;
    }
    public void setHct(double hct) {
        this.hct = hct;
    }
    @Column(name = "MCV", nullable = true)
    public double getMcv() {
        return mcv;
    }
    public void setMcv(double mcv) {
        this.mcv = mcv;
    }
    @Column(name = "MCH", nullable = true)
    public double getMch() {
        return mch;
    }
    public void setMch(double mch) {
        this.mch = mch;
    }
    @Column(name = "MCHC", nullable = true)
    public double getMchc() {
        return mchc;
    }
    public void setMchc(double mchc) {
        this.mchc = mchc;
    }
    @Column(name = "RDW_CV", nullable = true)
    public double getRdw_cv() {
        return rdw_cv;
    }
    public void setRdw_cv(double rdw_cv) {
        this.rdw_cv = rdw_cv;
    }
    @Column(name = "RDW_SD", nullable = true)
    public double getRdw_sd() {
        return rdw_sd;
    }
    public void setRdw_sd(double rdw_sd) {
        this.rdw_sd = rdw_sd;
    }
    @Column(name = "PLT", nullable = true)
    public double getPlt() {
        return plt;
    }
    public void setPlt(double plt) {
        this.plt = plt;
    }
    @Column(name = "MPV", nullable = true)
    public double getMpv() {
        return mpv;
    }
    public void setMpv(double mpv) {
        this.mpv = mpv;
    }
    @Column(name = "PDW", nullable = true)
    public double getPdw() {
        return pdw;
    }
    public void setPdw (double pdw) {
        this.pdw = pdw;
    }
    @Column(name = "PCT", nullable = true)
    public double getPct() {
        return pct;
    }
    public void setPct (double pct) {
        this.pct = pct;
    }
    @Column(name = "P_LCC", nullable = true)
    public double getP_lcc() {
        return p_lcc;
    }
    public void setP_lcc (double p_lcc) {
        this.p_lcc = p_lcc;
    }
    @Column(name = "P_LCR", nullable = true)
    public double getP_lcr() {
        return p_lcr;
    }
    public void setP_lcr (double p_lcr) {
        this.p_lcr = p_lcr;
    }
    @Column(name = "NRBC", nullable = true)
    public double getNrbc() {
        return nrbc;
    }
    public void setNrbc (double nrbc) {
        this.nrbc = nrbc;
    }
    @Column(name = "NRBC_PORCENTAJE", nullable = true)
    public double getNrbc_porcentaje() {
        return nrbc_porcentaje;
    }
    public void setNrbc_porcentaje (double nrbc_porcentaje) {
        this.nrbc_porcentaje = nrbc_porcentaje;
    }
    @Column(name = "WBC_BF", nullable = true)
    public double getWbc_bf() {
        return wbc_bf;
    }
    public void setWbc_bf (double wbc_bf) {
        this.wbc_bf = wbc_bf;
    }
    @Column(name = "TC_BF", nullable = true)
    public double getTc_bf() {
        return tc_bf;
    }
    public void setTc_bf (double tc_bf) {
        this.tc_bf = tc_bf;
    }
    @Column(name = "MN", nullable = true)
    public double getMn() {
        return mn;
    }
    public void setMn (double mn) {
        this.mn = mn;
    }
    @Column(name = "MN_PORCENTAJE", nullable = true)
    public double getMn_porcentaje() {
        return mn_porcentaje;
    }
    public void setMn_porcentaje (double mn_porcentaje) {
        this.mn_porcentaje = mn_porcentaje;
    }
    @Column(name = "PMN", nullable = true)
    public double getPmn() {
        return pmn;
    }
    public void setPmn (double pmn) {
        this.pmn = pmn;
    }
    @Column(name = "PMN_PORCENTAJE", nullable = true)
    public double getPmn_porcentaje() {
        return pmn_porcentaje;
    }
    public void setPmn_porcentaje (double pmn_porcentaje) {
        this.pmn_porcentaje = pmn_porcentaje;
    }
    @Column(name = "RBC_BF", nullable = true)
    public double getRbc_bf() {
        return rbc_bf;
    }
    public void setRbc_bf (double rbc_bf) {
        this.rbc_bf = rbc_bf;
    }
    @Column(name = "ID_PACIENT", nullable = true)
    public String getId_pacient() {
        return id_pacient;
    }
    public void setId_pacient (String id_pacient) {
        this.id_pacient = id_pacient;
    }
    @Column(name = "NOMBRE", nullable = true)
    public String getNomb() {
        return nomb;
    }
    public void setNomb (String nomb) {
        this.nomb = nomb;
    }
    @Column(name = "SEXO", nullable = true)
    public String getSexo() {
        return sexo;
    }
    public void setSexo (String sexo) {
        this.sexo = sexo;
    }
    @Column(name = "FECHA_NACIMIENTO", nullable = true)
    public Date getFecha_nacim() {
        return fecha_nacim;
    }
    public void setFecha_nacim (Date fecha_nacim) {
        this.fecha_nacim = fecha_nacim;
    }
    @Column(name = "EDAD", nullable = true)
    public double getEdad() {
        return edad;
    }
    public void setEdad(double edad) {
        this.edad = edad;
    }
    @Column(name = "TIPO_PAC", nullable = true)
    public String getTipo_pac() {
        return tipo_pac;
    }
    public void setTipo_pac(String tipo_pac) {
        this.tipo_pac = tipo_pac;
    }
    @Column(name = "GRUPO_REF", nullable = true)
    public String getGrupo_ref() {
        return grupo_ref;
    }
    public void setGrupo_ref(String grupo_ref) {
        this.grupo_ref = grupo_ref;
    }

    @Column(name = "DPTO", nullable = true)
    public String getDpto() {
        return dpto;
    }
    public void setDpto(String dpto) {
        this.dpto = dpto;
    }
    @Column(name = "NUM_CAMA", nullable = true)
    public String getNum_cama() {
        return num_cama;
    }
    public void setNum_cama(String num_cama) {
        this.num_cama = num_cama;
    }
    @Column(name = "FECHA_EXTR", nullable = true)
    public Date getFecha_extr() {
        return fecha_extr;
    }
    public void setFecha_extr(Date fecha_extr) {
        this.fecha_extr = fecha_extr;
    }
    @Column(name = "HORA_EXTR", nullable = true)
    public String getHora_extr() {
        return hora_extr;
    }
    public void setHora_extr(String hora_extr) {
        this.hora_extr = hora_extr;
    }
    @Column(name = "FECHA_EXT", nullable = true)
    public Date getFecha_ext() {
        return fecha_ext;
    }
    public void setFecha_ext(Date fecha_ext) {
        this.fecha_ext = fecha_ext;
    }
    @Column(name = "HORA_ENTREGA", nullable = true)
    public String getHora_entrega() {
        return hora_entrega;
    }
    public void setHora_entrega(String hora_entrega) {
        this.hora_entrega = hora_entrega;
    }
    @Column(name = "MEDICO", nullable = true)
    public String getMedico() {
        return medico;
    }
    public void setMedico(String medico) {
        this.medico = medico;
    }
    @Column(name = "OPERADOR", nullable = true)
    public String getOperador() {
        return operador;
    }
    public void setOperador(String operador) {
        this.operador = operador;
    }
    @Column(name = "VALIDADO_POR", nullable = true)
    public String getValidado_por() {
        return validado_por;
    }
    public void setValidado_por(String validado_por) {
        this.validado_por = validado_por;
    }
    @Column(name = "COMENTS", nullable = true)
    public String getComents() {
        return coments;
    }
    public void setComents(String coments) {
        this.coments = coments;
    }
    //fin setter

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bhc_Bc6000)) return false;

        Bhc_Bc6000 bhc = (Bhc_Bc6000) o;

        if (!idbhc_bc6000.equals(bhc.idbhc_bc6000)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idbhc_bc6000.hashCode();
    }
    @Override
    public String toString() {
        return "idbhc_bc6000=" + idbhc_bc6000;
    }

}
