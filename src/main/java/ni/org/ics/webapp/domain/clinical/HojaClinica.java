package ni.org.ics.webapp.domain.clinical;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by miguel on 5/10/2021.
 */
@Entity
@Table(name = "hoja_clinica", catalog = "a2cares")
public class HojaClinica extends BaseMetaData {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int secHojaConsulta;
    private Participante codigoParticipante;
    private int numHojaConsulta;
    private Date fechaConsulta;
    private Time horaConsulta;

    //Datos de enfermería
    private BigDecimal pesoKg;
    private BigDecimal tallaCm;
    private String presion;
    private Short fciaCard;
    private BigDecimal temperaturac;
    private Short saturaciono2;

    //Datos para llenar el médico.
    private Time horaInicioConsulta;
    private String consulta;
    private String lugarAtencion;
    private String presionMed;
    private BigDecimal temMedc;
    private Short fciaRespMed;
    private Short fciaCardMed;
    private Short saturaciono2Med;
    private Date fis;
    private Date fif;
    private Date ultDiaFiebre;
    private Time horaultDiaFiebre;
    private Date ultDosisAntipiretico;
    private Time horaUltDosisAntipiretico;
    //sintomas
    //General
    private String fiebre;
    private String asomnoliento;
    private String malEstado;
    private String perdidaConsciencia;
    private String inquieto;
    private String convulsiones;
    private String letargia;
    //cabeza
    private String dolorCabeza;
    private String conjuntivitis;
    private String hemorragiaSuconjuntival;
    private String dolorRetroocular;
    //Garganta
    private String dolorGarganta;
    private String eritema;
    private String adenopatiasCervicales;
    private String exudado;
    private String petequiasMucosa;
    //Respiratorio
    private String tos;
    private String rinorrea;
    private String congestionNasal;
    private String otalgia;
    private String aleteoNasal;
    private String respiracionRapida;
    private String estridorReposo;
    private String tirajeSubcostal;
    private String sibilancias;
    private String crepitos;
    private String roncos;
    private String disnea;
    //Gastrointestinal
    private String pocoApetito;
    private String nausea;
    private String vomito12horas;
    private Short numeroVomito12h;
    private String diarrea;
    private String hepatomegalia;
    private String dolorAbdominal;
    //Osteomuscular
    private String artralgia;
    private String mialgia;
    private String lumbalgia;
    private String dolorCuello;
    private String edema;
    //Cutáneo
    private String rahsLocalizado;
    private String rahsGeneralizado;
    private String rashEritematoso;
    private String rahsMacular;
    private String rashPapular;
    private String pielMoteada;
    private String ruborFacial;
    private String cianosisCentral;
    private String ictericia;
    //Estado nutricional
    private BigDecimal imc;
    private String obeso;
    private String sobrepeso;
    private String sospechaProblema;
    private String normal;
    private String bajoPeso;
    private String bajoPesoSevero;
    //categoria
    private String categoria;
    private String cambioCategoria;
    //Manifestaciones hemorrágicas
    private String pruebaTorniquetePositiva;
    private String petequia10Pt;
    private String petequia20Pt;
    private String pielExtremidadesFrias;
    private String palidezEnExtremidades;
    private String epistaxis;
    private String gingivorragia;
    private String petequiasEspontaneas;
    private String llenadoCapilar2seg;
    private String cianosis;
    private String hipermenorrea;
    private String hematemesis;
    private Short hemoconcentracion;
    //Preguntas para todos los pacientes
    private String hospitalizado;
    private String hospitalizadoEspecificar;
    private String transfusionSangre;
    private String transfusionEspecificar;
    private String tomandoMedicamento;
    private String medicamentoEspecificar;
    //Exámenes del laboratorio
    private String bhc;
    private String serologiaArbovirus;
    private String gotaGruesa;
    private String ego;
    private String egh;
    private String otroExamenLab;
    private String otroExamanLabEspecificar;
    //Tratamiento
    private String acetaminofen;
    private String amoxicilina;
    private String dicloxacilina;
    private String penicilina;
    private String furazolidona;
    private String metronidazolTinidazol;
    private String albendazolMebendazol;
    private String sueroOral;
    private String otroTratamiento;
    private String otroTratamientoEspecificar;
    //planes, historia y diagnostico
    private String planes;
    private String historiaClinica;
    private String diagnostico;
    //Cierre
    private String telefonoEmergencia;
    private Date proximaCita;
    private Short medico;
    private Date fechaMedico;
    private Time horaMedico;
    private Short enfermeria;
    private Date fechaEnfermeria;
    private Time horaEnfermeria;
    private String estudiosParticipantes;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoja_consulta", unique = true, nullable = false)
    public int getSecHojaConsulta() {
        return this.secHojaConsulta;
    }

    public void setSecHojaConsulta(int secHojaConsulta) {
        this.secHojaConsulta = secHojaConsulta;
    }

    @ManyToOne
    @JoinColumn(name = "codigo_participante", nullable = false)
    @ForeignKey(name = "FK_HOJACLINICA_PARTICIPANTE")
    public Participante getCodigoParticipante() {
        return this.codigoParticipante;
    }

    public void setCodigoParticipante(Participante codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "numero_hoja", unique = true, nullable = false)
    public int getNumHojaConsulta() {
        return this.numHojaConsulta;
    }

    public void setNumHojaConsulta(int numHojaConsulta) {
        this.numHojaConsulta = numHojaConsulta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_consulta", nullable = false)
    public Date getFechaConsulta() {
        return this.fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    @Basic
    @Column(name = "hora_consulta", nullable = false)
    public Time getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(Time horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    @Column(name = "peso_kg", precision = 5)
    public BigDecimal getPesoKg() {
        return this.pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    @Column(name = "talla_cm", precision = 5)
    public BigDecimal getTallaCm() {
        return this.tallaCm;
    }

    public void setTallaCm(BigDecimal tallaCm) {
        this.tallaCm = tallaCm;
    }

    @Column(name = "presion", length = 8)
    public String getPresion() {
        return this.presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    @Column(name = "fcia_card")
    public Short getFciaCard() {
        return this.fciaCard;
    }

    public void setFciaCard(Short fciaCard) {
        this.fciaCard = fciaCard;
    }

    @Column(name = "temperaturac", precision = 4)
    public BigDecimal getTemperaturac() {
        return this.temperaturac;
    }

    public void setTemperaturac(BigDecimal temperaturac) {
        this.temperaturac = temperaturac;
    }

    @Column(name = "saturaciono2")
    public Short getSaturaciono2() {
        return this.saturaciono2;
    }

    public void setSaturaciono2(Short saturaciono2) {
        this.saturaciono2 = saturaciono2;
    }

    @Basic
    @Column(name = "hora_inicio_consulta")
    public Time getHoraInicioConsulta() {
        return horaInicioConsulta;
    }

    public void setHoraInicioConsulta(Time horaInicioConsulta) {
        this.horaInicioConsulta = horaInicioConsulta;
    }

    @Column(name = "consulta", length = 16)
    public String getConsulta() {
        return this.consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    @Column(name = "lugar_atencion", length = 8)
    public String getLugarAtencion() {
        return this.lugarAtencion;
    }

    public void setLugarAtencion(String lugarAtencion) {
        this.lugarAtencion = lugarAtencion;
    }

    @Column(name = "presion_medico", length = 7)
    public String getPresionMed() {
        return presionMed;
    }

    public void setPresionMed(String presionMed) {
        this.presionMed = presionMed;
    }

    @Column(name = "temperatura_medico", precision = 4)
    public BigDecimal getTemMedc() {
        return this.temMedc;
    }

    public void setTemMedc(BigDecimal temMedc) {
        this.temMedc = temMedc;
    }

    @Column(name = "fcia_resp")
    public Short getFciaRespMed() {
        return fciaRespMed;
    }

    public void setFciaRespMed(Short fciaRespMed) {
        this.fciaRespMed = fciaRespMed;
    }

    @Column(name = "fcia_card_medico")
    public Short getFciaCardMed() {
        return fciaCardMed;
    }

    public void setFciaCardMed(Short fciaCardMed) {
        this.fciaCardMed = fciaCardMed;
    }

    @Column(name = "saturaciono2_medico")
    public Short getSaturaciono2Med() {
        return saturaciono2Med;
    }

    public void setSaturaciono2Med(Short saturaciono2Med) {
        this.saturaciono2Med = saturaciono2Med;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fis", length = 22)
    public Date getFis() {
        return this.fis;
    }

    public void setFis(Date fis) {
        this.fis = fis;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fif", length = 22)
    public Date getFif() {
        return this.fif;
    }

    public void setFif(Date fif) {
        this.fif = fif;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ult_dia_fiebre", length = 29)
    public Date getUltDiaFiebre() {
        return this.ultDiaFiebre;
    }

    public void setUltDiaFiebre(Date ultDiaFiebre) {
        this.ultDiaFiebre = ultDiaFiebre;
    }

    @Basic
    @Column(name = "hora_ult_dia_fiebre", length = 16)
    public Time getHoraultDiaFiebre() {
        return horaultDiaFiebre;
    }

    public void setHoraultDiaFiebre(Time horaultDiaFiebre) {
        this.horaultDiaFiebre = horaultDiaFiebre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ult_dosis_antipiretico", length = 29)
    public Date getUltDosisAntipiretico() {
        return this.ultDosisAntipiretico;
    }

    public void setUltDosisAntipiretico(Date ultDosisAntipiretico) {
        this.ultDosisAntipiretico = ultDosisAntipiretico;
    }

    @Basic
    @Column(name = "hora_ult_dosis_antipiretico", length = 16)
    public Time getHoraUltDosisAntipiretico() {
        return this.horaUltDosisAntipiretico;
    }

    public void setHoraUltDosisAntipiretico(Time horaUltDosisAntipiretico) {
        this.horaUltDosisAntipiretico = horaUltDosisAntipiretico;
    }

    @Column(name = "fiebre", length = 1)
    public String getFiebre() {
        return this.fiebre;
    }

    public void setFiebre(String fiebre) {
        this.fiebre = fiebre;
    }

    @Column(name = "asomnoliento", length = 1)
    public String getAsomnoliento() {
        return this.asomnoliento;
    }

    public void setAsomnoliento(String asomnoliento) {
        this.asomnoliento = asomnoliento;
    }

    @Column(name = "mal_estado", length = 1)
    public String getMalEstado() {
        return this.malEstado;
    }

    public void setMalEstado(String malEstado) {
        this.malEstado = malEstado;
    }

    @Column(name = "perdida_consciencia", length = 1)
    public String getPerdidaConsciencia() {
        return this.perdidaConsciencia;
    }

    public void setPerdidaConsciencia(String perdidaConsciencia) {
        this.perdidaConsciencia = perdidaConsciencia;
    }

    @Column(name = "inquieto", length = 1)
    public String getInquieto() {
        return this.inquieto;
    }

    public void setInquieto(String inquieto) {
        this.inquieto = inquieto;
    }

    @Column(name = "convulsiones", length = 1)
    public String getConvulsiones() {
        return this.convulsiones;
    }

    public void setConvulsiones(String convulsiones) {
        this.convulsiones = convulsiones;
    }

   @Column(name = "letargia", length = 1)
    public String getLetargia() {
        return this.letargia;
    }

    public void setLetargia(String letargia) {
        this.letargia = letargia;
    }

    @Column(name = "dolor_cabeza", length = 1)
    public String getDolorCabeza() {
        return this.dolorCabeza;
    }

    public void setDolorCabeza(String dolorCabeza) {
        this.dolorCabeza = dolorCabeza;
    }

    @Column(name = "conjuntivitis", length = 1)
    public String getConjuntivitis() {
        return this.conjuntivitis;
    }

    public void setConjuntivitis(String conjuntivitis) {
        this.conjuntivitis = conjuntivitis;
    }

    @Column(name = "hemorragia_suconjuntival", length = 1)
    public String getHemorragiaSuconjuntival() {
        return this.hemorragiaSuconjuntival;
    }

    public void setHemorragiaSuconjuntival(String hemorragiaSuconjuntival) {
        this.hemorragiaSuconjuntival = hemorragiaSuconjuntival;
    }

    @Column(name = "dolor_retroocular", length = 1)
    public String getDolorRetroocular() {
        return this.dolorRetroocular;
    }

    public void setDolorRetroocular(String dolorRetroocular) {
        this.dolorRetroocular = dolorRetroocular;
    }

    @Column(name = "dolor_garganta", length = 1)
    public String getDolorGarganta() {
        return this.dolorGarganta;
    }

    public void setDolorGarganta(String dolorGarganta) {
        this.dolorGarganta = dolorGarganta;
    }

    @Column(name = "eritema", length = 1)
    public String getEritema() {
        return this.eritema;
    }

    public void setEritema(String eritema) {
        this.eritema = eritema;
    }

    @Column(name = "adenopatias_cervicales", length = 1)
    public String getAdenopatiasCervicales() {
        return this.adenopatiasCervicales;
    }

    public void setAdenopatiasCervicales(String adenopatiasCervicales) {
        this.adenopatiasCervicales = adenopatiasCervicales;
    }

    @Column(name = "exudado", length = 1)
    public String getExudado() {
        return this.exudado;
    }

    public void setExudado(String exudado) {
        this.exudado = exudado;
    }

    @Column(name = "petequias_mucosa", length = 1)
    public String getPetequiasMucosa() {
        return this.petequiasMucosa;
    }

    public void setPetequiasMucosa(String petequiasMucosa) {
        this.petequiasMucosa = petequiasMucosa;
    }

    @Column(name = "tos", length = 1)
    public String getTos() {
        return this.tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    @Column(name = "rinorrea", length = 1)
    public String getRinorrea() {
        return this.rinorrea;
    }

    public void setRinorrea(String rinorrea) {
        this.rinorrea = rinorrea;
    }

    @Column(name = "congestion_nasal", length = 1)
    public String getCongestionNasal() {
        return this.congestionNasal;
    }

    public void setCongestionNasal(String congestionNasal) {
        this.congestionNasal = congestionNasal;
    }

    @Column(name = "otalgia", length = 1)
    public String getOtalgia() {
        return this.otalgia;
    }

    public void setOtalgia(String otalgia) {
        this.otalgia = otalgia;
    }

    @Column(name = "aleteo_nasal", length = 1)
    public String getAleteoNasal() {
        return this.aleteoNasal;
    }

    public void setAleteoNasal(String aleteoNasal) {
        this.aleteoNasal = aleteoNasal;
    }

    @Column(name = "respiracion_rapida", length = 1)
    public String getRespiracionRapida() {
        return this.respiracionRapida;
    }

    public void setRespiracionRapida(String respiracionRapida) {
        this.respiracionRapida = respiracionRapida;
    }

    @Column(name = "estridor_reposo", length = 1)
    public String getEstridorReposo() {
        return this.estridorReposo;
    }

    public void setEstridorReposo(String estridorReposo) {
        this.estridorReposo = estridorReposo;
    }

    @Column(name = "tiraje_subcostal", length = 1)
    public String getTirajeSubcostal() {
        return this.tirajeSubcostal;
    }

    public void setTirajeSubcostal(String tirajeSubcostal) {
        this.tirajeSubcostal = tirajeSubcostal;
    }

    @Column(name = "sibilancias", length = 1)
    public String getSibilancias() {
        return this.sibilancias;
    }

    public void setSibilancias(String sibilancias) {
        this.sibilancias = sibilancias;
    }

    @Column(name = "crepitos", length = 1)
    public String getCrepitos() {
        return this.crepitos;
    }

    public void setCrepitos(String crepitos) {
        this.crepitos = crepitos;
    }

    @Column(name = "roncos", length = 1)
    public String getRoncos() {
        return this.roncos;
    }

    public void setRoncos(String roncos) {
        this.roncos = roncos;
    }

    @Column(name = "disnea", length = 1)
    public String getDisnea() {
        return this.disnea;
    }

    public void setDisnea(String disnea) {
        this.disnea = disnea;
    }

    @Column(name = "poco_apetito", length = 1)
    public String getPocoApetito() {
        return this.pocoApetito;
    }

    public void setPocoApetito(String pocoApetito) {
        this.pocoApetito = pocoApetito;
    }

    @Column(name = "nausea", length = 1)
    public String getNausea() {
        return this.nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    @Column(name = "vomito_12horas", length = 1)
    public String getVomito12horas() {
        return this.vomito12horas;
    }

    public void setVomito12horas(String vomito12horas) {
        this.vomito12horas = vomito12horas;
    }

    @Column(name = "numero_vomito_12h")
    public Short getNumeroVomito12h() {
        return this.numeroVomito12h;
    }

    public void setNumeroVomito12h(Short numeroVomito12h) {
        this.numeroVomito12h = numeroVomito12h;
    }


    @Column(name = "diarrea", length = 1)
    public String getDiarrea() {
        return this.diarrea;
    }

    public void setDiarrea(String diarrea) {
        this.diarrea = diarrea;
    }

    @Column(name = "hepatomegalia", length = 1)
    public String getHepatomegalia() {
        return this.hepatomegalia;
    }

    public void setHepatomegalia(String hepatomegalia) {
        this.hepatomegalia = hepatomegalia;
    }

    @Column(name = "dolor_abdominal", length = 1)
    public String getDolorAbdominal() {
        return this.dolorAbdominal;
    }

    public void setDolorAbdominal(String dolorAbdominal) {
        this.dolorAbdominal = dolorAbdominal;
    }

    @Column(name = "artralgia", length = 1)
    public String getArtralgia() {
        return this.artralgia;
    }

    public void setArtralgia(String artralgia) {
        this.artralgia = artralgia;
    }

    @Column(name = "mialgia", length = 1)
    public String getMialgia() {
        return this.mialgia;
    }

    public void setMialgia(String mialgia) {
        this.mialgia = mialgia;
    }

    @Column(name = "lumbalgia", length = 1)
    public String getLumbalgia() {
        return this.lumbalgia;
    }

    public void setLumbalgia(String lumbalgia) {
        this.lumbalgia = lumbalgia;
    }

    @Column(name = "dolor_cuello", length = 1)
    public String getDolorCuello() {
        return this.dolorCuello;
    }

    public void setDolorCuello(String dolorCuello) {
        this.dolorCuello = dolorCuello;
    }

    @Column(name = "edema", length = 1)
    public String getEdema() {
        return this.edema;
    }

    public void setEdema(String edema) {
        this.edema = edema;
    }

    @Column(name = "rahs_localizado", length = 1)
    public String getRahsLocalizado() {
        return this.rahsLocalizado;
    }

    public void setRahsLocalizado(String rahsLocalizado) {
        this.rahsLocalizado = rahsLocalizado;
    }

    @Column(name = "rahs_generalizado", length = 1)
    public String getRahsGeneralizado() {
        return this.rahsGeneralizado;
    }

    public void setRahsGeneralizado(String rahsGeneralizado) {
        this.rahsGeneralizado = rahsGeneralizado;
    }

    @Column(name = "rash_eritematoso", length = 1)
    public String getRashEritematoso() {
        return this.rashEritematoso;
    }

    public void setRashEritematoso(String rashEritematoso) {
        this.rashEritematoso = rashEritematoso;
    }

    @Column(name = "rahs_macular", length = 1)
    public String getRahsMacular() {
        return this.rahsMacular;
    }

    public void setRahsMacular(String rahsMacular) {
        this.rahsMacular = rahsMacular;
    }

    @Column(name = "rash_papular", length = 1)
    public String getRashPapular() {
        return this.rashPapular;
    }

    public void setRashPapular(String rashPapular) {
        this.rashPapular = rashPapular;
    }

    @Column(name = "piel_moteada", length = 1)
    public String getPielMoteada() {
        return this.pielMoteada;
    }

    public void setPielMoteada(String pielMoteada) {
        this.pielMoteada = pielMoteada;
    }

    @Column(name = "rubor_facial", length = 1)
    public String getRuborFacial() {
        return this.ruborFacial;
    }

    public void setRuborFacial(String ruborFacial) {
        this.ruborFacial = ruborFacial;
    }

    @Column(name = "cianosis_central", length = 1)
    public String getCianosisCentral() {
        return this.cianosisCentral;
    }

    public void setCianosisCentral(String cianosisCentral) {
        this.cianosisCentral = cianosisCentral;
    }

    @Column(name = "ictericia", length = 1)
    public String getIctericia() {
        return this.ictericia;
    }

    public void setIctericia(String ictericia) {
        this.ictericia = ictericia;
    }

    @Column(name = "imc", precision = 5)
    public BigDecimal getImc() {
        return this.imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    @Column(name = "obeso", length = 1)
    public String getObeso() {
        return this.obeso;
    }

    public void setObeso(String obeso) {
        this.obeso = obeso;
    }

    @Column(name = "sobrepeso", length = 1)
    public String getSobrepeso() {
        return this.sobrepeso;
    }

    public void setSobrepeso(String sobrepeso) {
        this.sobrepeso = sobrepeso;
    }

    @Column(name = "sospecha_problema", length = 1)
    public String getSospechaProblema() {
        return this.sospechaProblema;
    }

    public void setSospechaProblema(String sospechaProblema) {
        this.sospechaProblema = sospechaProblema;
    }

    @Column(name = "normal", length = 1)
    public String getNormal() {
        return this.normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    @Column(name = "bajo_peso", length = 1)
    public String getBajoPeso() {
        return this.bajoPeso;
    }

    public void setBajoPeso(String bajoPeso) {
        this.bajoPeso = bajoPeso;
    }

    @Column(name = "bajo_peso_severo", length = 1)
    public String getBajoPesoSevero() {
        return this.bajoPesoSevero;
    }

    public void setBajoPesoSevero(String bajoPesoSevero) {
        this.bajoPesoSevero = bajoPesoSevero;
    }

    @Column(name = "categoria", length = 2)
    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Column(name = "cambio_categoria", length = 1)
    public String getCambioCategoria() {
        return this.cambioCategoria;
    }

    public void setCambioCategoria(String cambioCategoria) {
        this.cambioCategoria = cambioCategoria;
    }

    @Column(name = "prueba_torniquete_positiva", length = 1)
    public String getPruebaTorniquetePositiva() {
        return this.pruebaTorniquetePositiva;
    }

    public void setPruebaTorniquetePositiva(String pruebaTorniquetePositiva) {
        this.pruebaTorniquetePositiva = pruebaTorniquetePositiva;
    }

    @Column(name = "petequia_10_pt", length = 1)
    public String getPetequia10Pt() {
        return this.petequia10Pt;
    }

    public void setPetequia10Pt(String petequia10Pt) {
        this.petequia10Pt = petequia10Pt;
    }

    @Column(name = "petequia_20_pt", length = 1)
    public String getPetequia20Pt() {
        return this.petequia20Pt;
    }

    public void setPetequia20Pt(String petequia20Pt) {
        this.petequia20Pt = petequia20Pt;
    }

    @Column(name = "piel_extremidades_frias", length = 1)
    public String getPielExtremidadesFrias() {
        return this.pielExtremidadesFrias;
    }

    public void setPielExtremidadesFrias(String pielExtremidadesFrias) {
        this.pielExtremidadesFrias = pielExtremidadesFrias;
    }

    @Column(name = "palidez_en_extremidades", length = 1)
    public String getPalidezEnExtremidades() {
        return this.palidezEnExtremidades;
    }

    public void setPalidezEnExtremidades(String palidezEnExtremidades) {
        this.palidezEnExtremidades = palidezEnExtremidades;
    }

    @Column(name = "epistaxis", length = 1)
    public String getEpistaxis() {
        return this.epistaxis;
    }

    public void setEpistaxis(String epistaxis) {
        this.epistaxis = epistaxis;
    }

    @Column(name = "gingivorragia", length = 1)
    public String getGingivorragia() {
        return this.gingivorragia;
    }

    public void setGingivorragia(String gingivorragia) {
        this.gingivorragia = gingivorragia;
    }

    @Column(name = "petequias_espontaneas", length = 1)
    public String getPetequiasEspontaneas() {
        return this.petequiasEspontaneas;
    }

    public void setPetequiasEspontaneas(String petequiasEspontaneas) {
        this.petequiasEspontaneas = petequiasEspontaneas;
    }

    @Column(name = "llenado_capilar_2seg", length = 1)
    public String getLlenadoCapilar2seg() {
        return this.llenadoCapilar2seg;
    }

    public void setLlenadoCapilar2seg(String llenadoCapilar2seg) {
        this.llenadoCapilar2seg = llenadoCapilar2seg;
    }

    @Column(name = "cianosis", length = 1)
    public String getCianosis() {
        return this.cianosis;
    }

    public void setCianosis(String cianosis) {
        this.cianosis = cianosis;
    }

    @Column(name = "hipermenorrea", length = 1)
    public String getHipermenorrea() {
        return this.hipermenorrea;
    }

    public void setHipermenorrea(String hipermenorrea) {
        this.hipermenorrea = hipermenorrea;
    }

    @Column(name = "hematemesis", length = 1)
    public String getHematemesis() {
        return this.hematemesis;
    }

    public void setHematemesis(String hematemesis) {
        this.hematemesis = hematemesis;
    }

    @Column(name = "hemoconcentracion")
    public Short getHemoconcentracion() {
        return this.hemoconcentracion;
    }

    public void setHemoconcentracion(Short hemoconcentracion) {
        this.hemoconcentracion = hemoconcentracion;
    }

    @Column(name = "hospitalizado", length = 1)
    public String getHospitalizado() {
        return this.hospitalizado;
    }

    public void setHospitalizado(String hospitalizado) {
        this.hospitalizado = hospitalizado;
    }

    @Column(name = "hospitalizado_especificar", length = 500) // 500
    public String getHospitalizadoEspecificar() {
        return this.hospitalizadoEspecificar;
    }

    public void setHospitalizadoEspecificar(String hospitalizadoEspecificar) {
        this.hospitalizadoEspecificar = hospitalizadoEspecificar;
    }

    @Column(name = "transfusion_sangre", length = 1)
    public String getTransfusionSangre() {
        return this.transfusionSangre;
    }

    public void setTransfusionSangre(String transfusionSangre) {
        this.transfusionSangre = transfusionSangre;
    }

    @Column(name = "transfusion_especificar", length = 500) //500
    public String getTransfusionEspecificar() {
        return this.transfusionEspecificar;
    }

    public void setTransfusionEspecificar(String transfusionEspecificar) {
        this.transfusionEspecificar = transfusionEspecificar;
    }

    @Column(name = "tomando_medicamento", length = 1)
    public String getTomandoMedicamento() {
        return this.tomandoMedicamento;
    }

    public void setTomandoMedicamento(String tomandoMedicamento) {
        this.tomandoMedicamento = tomandoMedicamento;
    }

    @Column(name = "medicamento_especificar", length = 500) //500
    public String getMedicamentoEspecificar() {
        return this.medicamentoEspecificar;
    }

    public void setMedicamentoEspecificar(String medicamentoEspecificar) {
        this.medicamentoEspecificar = medicamentoEspecificar;
    }

    @Column(name = "bhc", length = 1)
    public String getBhc() {
        return this.bhc;
    }

    public void setBhc(String bhc) {
        this.bhc = bhc;
    }

    @Column(name = "serologia_arbovirus", length = 1)
    public String getSerologiaArbovirus() {
        return this.serologiaArbovirus;
    }

    public void setSerologiaArbovirus(String serologiaArbovirus) {
        this.serologiaArbovirus = serologiaArbovirus;
    }

    @Column(name = "gota_gruesa", length = 1)
    public String getGotaGruesa() {
        return this.gotaGruesa;
    }

    public void setGotaGruesa(String gotaGruesa) {
        this.gotaGruesa = gotaGruesa;
    }

    @Column(name = "ego", length = 1)
    public String getEgo() {
        return this.ego;
    }

    public void setEgo(String ego) {
        this.ego = ego;
    }

    @Column(name = "egh", length = 1)
    public String getEgh() {
        return this.egh;
    }

    public void setEgh(String egh) {
        this.egh = egh;
    }

    @Column(name = "otro_examen_lab", length = 32)
    public String getOtroExamenLab() {
        return this.otroExamenLab;
    }

    public void setOtroExamenLab(String otroExamenLab) {
        this.otroExamenLab = otroExamenLab;
    }

    @Column(name = "otro_examan_especificar", length = 1)
    public String getOtroExamanLabEspecificar() {
        return otroExamanLabEspecificar;
    }

    public void setOtroExamanLabEspecificar(String otroExamanLabEspecificar) {
        this.otroExamanLabEspecificar = otroExamanLabEspecificar;
    }

    @Column(name = "acetaminofen", length = 1)
    public String getAcetaminofen() {
        return this.acetaminofen;
    }

    public void setAcetaminofen(String acetaminofen) {
        this.acetaminofen = acetaminofen;
    }

    @Column(name = "amoxicilina", length = 1)
    public String getAmoxicilina() {
        return this.amoxicilina;
    }

    public void setAmoxicilina(String amoxicilina) {
        this.amoxicilina = amoxicilina;
    }

    @Column(name = "dicloxacilina", length = 1)
    public String getDicloxacilina() {
        return this.dicloxacilina;
    }

    public void setDicloxacilina(String dicloxacilina) {
        this.dicloxacilina = dicloxacilina;
    }

    @Column(name = "penicilina", length = 1)
    public String getPenicilina() {
        return this.penicilina;
    }

    public void setPenicilina(String penicilina) {
        this.penicilina = penicilina;
    }

    @Column(name = "furazolidona", length = 1)
    public String getFurazolidona() {
        return this.furazolidona;
    }

    public void setFurazolidona(String furazolidona) {
        this.furazolidona = furazolidona;
    }

    @Column(name = "metronidazol_tinidazol", length = 1)
    public String getMetronidazolTinidazol() {
        return this.metronidazolTinidazol;
    }

    public void setMetronidazolTinidazol(String metronidazolTinidazol) {
        this.metronidazolTinidazol = metronidazolTinidazol;
    }

    @Column(name = "albendazol_mebendazol", length = 1)
    public String getAlbendazolMebendazol() {
        return this.albendazolMebendazol;
    }

    public void setAlbendazolMebendazol(String albendazolMebendazol) {
        this.albendazolMebendazol = albendazolMebendazol;
    }

    @Column(name = "suero_oral", length = 1)
    public String getSueroOral() {
        return this.sueroOral;
    }

    public void setSueroOral(String sueroOral) {
        this.sueroOral = sueroOral;
    }

    @Column(name = "otro_tratamiento", length = 1)
    public String getOtroTratamiento() {
        return otroTratamiento;
    }

    public void setOtroTratamiento(String otroTratamiento) {
        this.otroTratamiento = otroTratamiento;
    }

    @Column(name = "otro_tratamiento_especificar", length = 500)
    public String getOtroTratamientoEspecificar() {
        return otroTratamientoEspecificar;
    }

    public void setOtroTratamientoEspecificar(String otroTratamientoEspecificar) {
        this.otroTratamientoEspecificar = otroTratamientoEspecificar;
    }

    @Column(name = "planes")
    public String getPlanes() {
        return this.planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    @Column(name = "historia_clinica")
    public String getHistoriaClinica() {
        return this.historiaClinica;
    }

    public void setHistoriaClinica(String historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    @Column(name = "diagnostico", length = 500)
    public String getDiagnostico() {
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Column(name = "telefono_emergencia", length = 32)
    public String getTelefonoEmergencia() {
        return this.telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "proxima_cita", length = 13)
    public Date getProximaCita() {
        return this.proximaCita;
    }

    public void setProximaCita(Date proximaCita) {
        this.proximaCita = proximaCita;
    }

    @Column(name = "medico")
    public Short getMedico() {
        return this.medico;
    }

    public void setMedico(Short usuarioMedico) {
        this.medico = usuarioMedico;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_medico")
    public Date getFechaMedico() {
        return fechaMedico;
    }

    public void setFechaMedico(Date fechaMedico) {
        this.fechaMedico = fechaMedico;
    }

    @Basic
    @Column(name = "hora_medico")
    public Time getHoraMedico() {
        return horaMedico;
    }

    public void setHoraMedico(Time horaMedico) {
        this.horaMedico = horaMedico;
    }

    @Column(name = "enfermeria", nullable = true)
    public Short getEnfermeria() {
        return this.enfermeria;
    }

    public void setEnfermeria(Short usuarioEnfermeria) {
        this.enfermeria = usuarioEnfermeria;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_enfermeria")
    public Date getFechaEnfermeria() {
        return fechaEnfermeria;
    }

    public void setFechaEnfermeria(Date fechaEnfermeria) {
        this.fechaEnfermeria = fechaEnfermeria;
    }

    @Basic
    @Column(name = "hora_enfermeria")
    public Time getHoraEnfermeria() {
        return horaEnfermeria;
    }

    public void setHoraEnfermeria(Time horaEnfermeria) {
        this.horaEnfermeria = horaEnfermeria;
    }

    @Column(name = "estudios_participantes")
    public String getEstudiosParticipantes() {
        return estudiosParticipantes;
    }

    public void setEstudiosParticipantes(String estudiosParticipantes) {
        this.estudiosParticipantes = estudiosParticipantes;
    }

}
