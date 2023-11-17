package ni.org.ics.webapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Everts Morales Reyes on 01/11/2023.
 */
public class DiferenciasHojasDigitadasDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo_participante1;
    private String codigo_participante2;

    private String codigo_supervisor1;
    private String codigo_supervisor2;

    private String fecha_consulta1;
    private String fecha_consulta2;

    private String hora_consulta1;
    private String hora_consulta2;

    private String numero_hoja1;
    private String numero_hoja2;

    private String peso_kg1;
    private String peso_kg2;

    private String talla_cm1;
    private String talla_cm2;

    private String presion1;
    private String presion2;

    private String fcia_card1;
    private String fcia_card2;

    private String temperaturac1;
    private String temperaturac2;

    private String saturacion1;
    private String saturacion2;

    private String hora_inicio_consulta1;
    private String hora_inicio_consulta2;

    private String consulta1;
    private String consulta2;

    private String lugar_atencion1;
    private String lugar_atencion2;

    private String presion_medico1;
    private String presion_medico2;

    private String temperatura_medico1;
    private String temperatura_medico2;

    private String fcia_resp1;
    private String fcia_resp2;

    private String fcia_card_medico1;
    private String fcia_card_medico2;

    private String saturaciono2_medico1;
    private String saturaciono2_medico2;

    private String fis1;
    private String fis2;

    private String fif1;
    private String fif2;

    private String ult_dia_fiebre1;
    private String ult_dia_fiebre2;

    private String hora_ult_dia_fiebre1;
    private String hora_ult_dia_fiebre2;

    private String ult_dosis_antipiretico1;
    private String ult_dosis_antipiretico2;

    private String hora_ult_dosis_antipiretico1;
    private String hora_ult_dosis_antipiretico2;

    private String fiebre1;
    private String fiebre2;

    private String asomnoliento1;
    private String asomnoliento2;

    private String malEstado1;
    private String malEstado2;

    private String perdidaConsciencia1;
    private String perdidaConsciencia2;

    private String inquieto1;
    private String inquieto2;

    private String convulsiones1;
    private String convulsiones2;

    private String letargia1;
    private String letargia2;

    //cabeza
    private String dolorCabeza1;
    private String dolorCabeza2;

    private String conjuntivitis1;
    private String conjuntivitis2;

    private String hemorragiaSuconjuntival1;
    private String hemorragiaSuconjuntival2;

    private String dolorRetroocular1;
    private String dolorRetroocular2;

    //Garganta
    private String dolorGarganta1;
    private String dolorGarganta2;

    private String eritema1;
    private String eritema2;

    private String adenopatiasCervicales1;
    private String adenopatiasCervicales2;

    private String exudado1;
    private String exudado2;

    private String petequiasMucosa1;
    private String petequiasMucosa2;
    //Respiratorio
    private String tos1;
    private String tos2;

    private String rinorrea1;
    private String rinorrea2;

    private String congestionNasal1;
    private String congestionNasal2;

    private String otalgia1;
    private String otalgia2;

    private String aleteoNasal1;
    private String aleteoNasal2;

    private String respiracionRapida1;
    private String respiracionRapida2;

    private String estridorReposo1;
    private String estridorReposo2;

    private String tirajeSubcostal1;
    private String tirajeSubcostal2;

    private String sibilancias1;
    private String sibilancias2;

    private String crepitos1;
    private String crepitos2;

    private String roncos1;
    private String roncos2;

    private String disnea1;
    private String disnea2;
    //Gastrointestinal
    private String pocoApetito1;
    private String pocoApetito2;

    private String nausea1;
    private String nausea2;

    private String vomito12horas1;
    private String vomito12horas2;

    private String numeroVomito12h1;
    private String numeroVomito12h2;

    private String diarrea1;
    private String diarrea2;

    private String hepatomegalia1;
    private String hepatomegalia2;

    private String dolorAbdominal1;
    private String dolorAbdominal2;
    //Osteomuscular
    private String artralgia1;
    private String artralgia2;

    private String mialgia1;
    private String mialgia2;

    private String lumbalgia1;
    private String lumbalgia2;

    private String dolorCuello1;
    private String dolorCuello2;

    private String edema1;
    private String edema2;
    //Cutáneo
    private String rahsLocalizado1;
    private String rahsLocalizado2;

    private String rahsGeneralizado1;
    private String rahsGeneralizado2;

    private String rashEritematoso1;
    private String rashEritematoso2;

    private String rahsMacular1;
    private String rahsMacular2;

    private String rashPapular1;
    private String rashPapular2;

    private String pielMoteada1;
    private String pielMoteada2;

    private String ruborFacia1;
    private String ruborFacia2;

    private String cianosisCentral1;
    private String cianosisCentral2;

    private String ictericia1;
    private String ictericia2;
    //Estado nutricional
    private String imc1;
    private String imc2;

    private String obeso1;
    private String obeso2;

    private String sobrepeso1;
    private String sobrepeso2;

    private String sospechaProblema1;
    private String sospechaProblema2;

    private String normal1;
    private String normal2;

    private String bajoPeso1;
    private String bajoPeso2;

    private String bajoPesoSevero1;
    private String bajoPesoSevero2;
    //categoria
    private String categoria1;
    private String categoria2;

    private String cambioCategoria1;
    private String cambioCategoria2;

    //Manifestaciones hemorrágicas
    private String pruebaTorniquetePositiva1;
    private String pruebaTorniquetePositiva2;

    private String petequia10Pt1;
    private String petequia10Pt2;

    private String petequia20Pt1;
    private String petequia20Pt2;

    private String pielExtremidadesFrias1;
    private String pielExtremidadesFrias2;

    private String palidezEnExtremidades1;
    private String palidezEnExtremidades2;

    private String epistaxis1;
    private String epistaxis2;

    private String gingivorragia1;
    private String gingivorragia2;

    private String petequiasEspontaneas1;
    private String petequiasEspontaneas2;

    private String llenadoCapilar2seg1;
    private String llenadoCapilar2seg2;

    private String cianosis1;
    private String cianosis2;

    private String hipermenorrea1;
    private String hipermenorrea2;

    private String hematemesis1;
    private String hematemesis2;

    private String hemoconcentracion1;
    private String hemoconcentracion2;
    //Preguntas para todos los pacientes
    private String hospitalizado1;
    private String hospitalizado2;

    private String hospitalizadoEspecificar1;
    private String hospitalizadoEspecificar2;

    private String transfusionSangre1;
    private String transfusionSangre2;

    private String transfusionEspecificar1;
    private String transfusionEspecificar2;

    private String tomandoMedicamento1;
    private String tomandoMedicamento2;

    private String medicamentoEspecificar1;
    private String medicamentoEspecificar2;
    //Exámenes del laboratorio
    private String bhc1;
    private String bhc2;

    private String serologiaArbovirus1;
    private String serologiaArbovirus2;

    private String gotaGruesa1;
    private String gotaGruesa2;

    private String ego1;
    private String ego2;

    private String egh1;
    private String egh2;

    private String otroExamenLab1;
    private String otroExamenLab2;

    private String otroExamanLabEspecificar1;
    private String otroExamanLabEspecificar2;
    //Tratamiento
    private String acetaminofen1;
    private String acetaminofen2;

    private String amoxicilina1;
    private String amoxicilina2;

    private String dicloxacilina1;
    private String dicloxacilina2;

    private String penicilina1;
    private String penicilina2;

    private String furazolidona1;
    private String furazolidona2;

    private String metronidazolTinidazol1;
    private String metronidazolTinidazol2;

    private String albendazolMebendazol1;
    private String albendazolMebendazol2;

    private String sueroOral1;
    private String sueroOral2;

    private String otroTratamiento1;
    private String otroTratamiento2;

    private String otroTratamientoEspecificar1;
    private String otroTratamientoEspecificar2;
    //planes, historia y diagnostico
    private String planes1;
    private String planes2;

    private String historiaClinica1;
    private String historiaClinica2;

    private String diagnostico1;
    private String diagnostico2;

    private String diagnostico11;
    private String diagnostico12;

    private String diagnostico21;
    private String diagnostico22;

    private String diagnostico31;
    private String diagnostico32;

    private String diagnostico41;
    private String diagnostico42;


    //Cierre
    private String telefonoEmergencia1;
    private String telefonoEmergencia2;

    private String proximaCita1;
    private String proximaCita2;

    private String medico1;
    private String medico2;

    private String fechaMedico1;
    private String fechaMedico2;

    private String horaMedico1;
    private String horaMedico2;

    private String enfermeria1;
    private String enfermeria2;

    private String fechaEnfermeria1;
    private String fechaEnfermeria2;

    private String horaEnfermeria1;
    private String horaEnfermeria2;




    public void setCodigo_participante1(String codigo_participante1) {
        this.codigo_participante1 = codigo_participante1;
    }
    public String getCodigo_participante1() { return codigo_participante1;  }

    public void setCodigo_participante2(String codigo_participante2) {
        this.codigo_participante2 = codigo_participante2;
    }
    public String getCodigo_participante2() { return codigo_participante2;  }

    public void setCodigo_supervisor1(String codigo_supervisor1) {
        this.codigo_supervisor1 = codigo_supervisor1;
    }
    public String getCodigo_supervisor1() { return codigo_supervisor1;  }

    public void setCodigo_supervisor2(String codigo_supervisor2) {
        this.codigo_supervisor2 = codigo_supervisor2;
    }
    public String getCodigo_supervisor2() { return codigo_supervisor2;  }

    public void setFecha_consulta1(String fecha_consulta1) {
        this.fecha_consulta1 = fecha_consulta1;
    }
    public String getFecha_consulta1() { return fecha_consulta1;  }

    public void setFecha_consulta2(String fecha_consulta2) {
        this.fecha_consulta2 = fecha_consulta2;
    }
    public String getFecha_consulta2() { return fecha_consulta2;  }

    public void setHora_consulta1(String hora_consulta1) {
        this.hora_consulta1 = hora_consulta1;
    }
    public String getHora_consulta1() { return hora_consulta1;  }

    public void setHora_inicio_consulta1(String hora_inicio_consulta1) {
        this.hora_inicio_consulta1 = hora_inicio_consulta1;
    }
    public String getHora_inicio_consulta1() { return hora_inicio_consulta1;  }
    public String getHora_inicio_consulta2() { return hora_inicio_consulta2;  }
    public void setHora_inicio_consulta2(String hora_inicio_consulta2) {
        this.hora_inicio_consulta2 = hora_inicio_consulta2;
    }

    public void setHora_consulta2(String hora_consulta2) {
        this.hora_consulta2 = hora_consulta2;
    }
    public String getHora_consulta2() { return hora_consulta2;  }

    public void setNumero_hoja1(String numero_hoja1) {
        this.numero_hoja1 = numero_hoja1;
    }
    public String getNumero_hoja1() { return numero_hoja1;  }

    public void setNumero_hoja2(String numero_hoja2) {
        this.numero_hoja2 = numero_hoja2;
    }
    public String getNumero_hoja2() { return numero_hoja2;  }

    public void setPeso_kg1 (String peso_kg1) {
        this.peso_kg1 = peso_kg1;
    }
    public String getPeso_kg1() { return peso_kg1;  }

    public void setPeso_kg2 (String peso_kg2) {
        this.peso_kg2 = peso_kg2;
    }
    public String getPeso_kg2() { return peso_kg2;  }

    public void setTalla_cm1 (String talla_cm1) {
        this.talla_cm1 = talla_cm1;
    }
    public String getTalla_cm1() { return talla_cm1;  }

    public void setTalla_cm2 (String talla_cm2) {
        this.talla_cm2 = talla_cm2;
    }
    public String getTalla_cm2() { return talla_cm2;  }

    public void setPresion1 (String presion1) {
        this.presion1 = presion1;
    }
    public String getPresion1() { return presion1;  }

    public void setPresion2 (String presion2) {
        this.presion2 = presion2;
    }
    public String getPresion2() { return presion2;  }


    public void setFcia_card1  (String fcia_card1) {
        this.fcia_card1 = fcia_card1;
    }
    public String getFcia_card1() { return fcia_card1;  }

    public void setFcia_card2  (String fcia_card2) {
        this.fcia_card2 = fcia_card2;
    }
    public String getFcia_card2() { return fcia_card2;  }

    public void setTemperaturac1 (String temperaturac1) {
        this.temperaturac1 = temperaturac1;
    }
    public String getTemperaturac1() { return temperaturac1;  }

    public void setTemperaturac2 (String temperaturac2) {
        this.temperaturac2 = temperaturac2;
    }
    public String getTemperaturac2() { return temperaturac2;  }

    public void setSaturacion1  (String saturacion1) {
        this.saturacion1 = saturacion1;
    }
    public String getSaturacion1() { return saturacion1;  }

    public void setSaturacion2 (String saturacion2) {
        this.saturacion2 = saturacion2;
    }
    public String getSaturacion2() { return saturacion2;  }

    public void setConsulta1 (String consulta1) {
        this.consulta1 = consulta1;
    }
    public String getConsulta1() { return consulta1;  }

    public void setConsulta2 (String consulta2) {
        this.consulta2 = consulta2;
    }
    public String getConsulta2() { return consulta2;  }

    public void setLugar_atencion1 (String lugar_atencion1) {
        this.lugar_atencion1 = lugar_atencion1;
    }
    public String getLugar_atencion1() { return lugar_atencion1;  }

    public void setLugar_atencion2 (String lugar_atencion2) {
        this.lugar_atencion2 = lugar_atencion2;
    }
    public String getLugar_atencion2() { return lugar_atencion2;  }

    public void setPresion_medico1  (String presion_medico1) {
        this.presion_medico1 = presion_medico1;
    }
    public String getPresion_medico1() { return presion_medico1;  }

    public void setPresion_medico2  (String presion_medico2) {
        this.presion_medico2 = presion_medico2;
    }
    public String getPresion_medico2() { return presion_medico2;  }

    public void setTemperatura_medico1 (String temperatura_medico1) {
        this.temperatura_medico1 = temperatura_medico1;
    }
    public String getTemperatura_medico1() { return temperatura_medico1;  }

    public void setTemperatura_medico2 (String temperatura_medico2) {
        this.temperatura_medico2 = temperatura_medico2;
    }
    public String getTemperatura_medico2() { return temperatura_medico2;  }

    public void setFcia_resp1  (String fcia_resp1) {
        this.fcia_resp1 = fcia_resp1;
    }
    public String getFcia_resp1() { return fcia_resp1;  }

    public void setFcia_resp2 (String fcia_resp2) {
        this.fcia_resp2 = fcia_resp2;
    }
    public String getFcia_resp2() { return fcia_resp2;  }

    public void setFcia_card_medico1(String fcia_card_medico1) {
        this.fcia_card_medico1 = fcia_card_medico1;
    }
    public String getFcia_card_medico1() { return fcia_card_medico1;  }

    public void setFcia_card_medico2(String fcia_card_medico2) {
        this.fcia_card_medico2 = fcia_card_medico2;
    }
    public String getFcia_card_medico2() { return fcia_card_medico2;  }

    public void setSaturaciono2_medico1 (String saturaciono2_medico1) {
        this.saturaciono2_medico1 = saturaciono2_medico1;
    }
    public String getSaturaciono2_medico1() { return saturaciono2_medico1;  }

    public void setSaturaciono2_medico2 (String saturaciono2_medico2) {
        this.saturaciono2_medico2 = saturaciono2_medico2;
    }
    public String getSaturaciono2_medico2() { return saturaciono2_medico2;  }

    public void setFis1  (String fis1) {
        this.fis1 = fis1;
    }
    public String getFis1() { return fis1;  }

    public void setFis2  (String fis2) {
        this.fis2 = fis2;
    }
    public String getFis2() { return fis2;  }

    public void setFif1  (String fif1) {
        this.fif1 = fif1;
    }
    public String getFif1() { return fif1;  }

    public void setFif2  (String fif2) {
        this.fif2 = fif2;
    }
    public String getFif2() { return fif2;  }


    public void setUlt_dia_fiebre1 (String ult_dia_fiebre1) {
        this.ult_dia_fiebre1 = ult_dia_fiebre1;
    }
    public String getUlt_dia_fiebre1() { return ult_dia_fiebre1;  }

    public void setUlt_dia_fiebre2 (String ult_dia_fiebre2) {
        this.ult_dia_fiebre2 = ult_dia_fiebre2;
    }
    public String getUlt_dia_fiebre2() { return ult_dia_fiebre2;  }

    public void setHora_ult_dia_fiebre1  (String hora_ult_dia_fiebre1) {
        this.hora_ult_dia_fiebre1 = hora_ult_dia_fiebre1;
    }
    public String getHora_ult_dia_fiebre1() { return hora_ult_dia_fiebre1;  }
    public void setHora_ult_dia_fiebre2  (String hora_ult_dia_fiebre2) {
        this.hora_ult_dia_fiebre2 = hora_ult_dia_fiebre2;
    }
    public String getHora_ult_dia_fiebre2() { return hora_ult_dia_fiebre2;  }

    public void setUlt_dosis_antipiretico1 (String ult_dosis_antipiretico1) {
        this.ult_dosis_antipiretico1 = ult_dosis_antipiretico1;
    }
    public String getUlt_dosis_antipiretico1() { return ult_dosis_antipiretico1;  }

    public void setUlt_dosis_antipiretico2 (String ult_dosis_antipiretico2) {
        this.ult_dosis_antipiretico2 = ult_dosis_antipiretico2;
    }
    public String getUlt_dosis_antipiretico2() { return ult_dosis_antipiretico2;  }

    public void setHora_ult_dosis_antipiretico1  (String hora_ult_dosis_antipiretico1) {
        this.hora_ult_dosis_antipiretico1 = hora_ult_dosis_antipiretico1;
    }
    public String getHora_ult_dosis_antipiretico1() { return hora_ult_dosis_antipiretico1;  }
    public void setHora_ult_dosis_antipiretico2  (String hora_ult_dosis_antipiretico2) {
        this.hora_ult_dosis_antipiretico2 = hora_ult_dosis_antipiretico2;
    }
    public String getHora_ult_dosis_antipiretico2() { return hora_ult_dosis_antipiretico2;  }

    public void setFiebre1  (String fiebre1) {
        this.fiebre1 = fiebre1;
    }
    public String getFiebre1() { return fiebre1;  }
    public void setFiebre2  (String fiebre2) {
        this.fiebre2 = fiebre2;
    }
    public String getFiebre2() { return fiebre2;  }

    public void setAsomnoliento1  (String asomnoliento1) {
        this.asomnoliento1 = asomnoliento1;
    }
    public String getAsomnoliento1() { return asomnoliento1;  }
    public void setAsomnoliento2  (String asomnoliento2) {
        this.asomnoliento2 = asomnoliento2;
    }
    public String getAsomnoliento2() { return asomnoliento2;  }

    public void setMalEstado1  (String malEstado1) {
        this.malEstado1 = malEstado1;
    }
    public String getMalEstado1() { return malEstado1;  }
    public void setMalEstado2  (String malEstado2) {
        this.malEstado2 = malEstado2;
    }
    public String getMalEstado2() { return malEstado2;  }

    public void setPerdidaConsciencia1   (String perdidaConsciencia1) {
        this.perdidaConsciencia1 = perdidaConsciencia1;
    }
    public String getPerdidaConsciencia1() { return perdidaConsciencia1;  }
    public void setPerdidaConsciencia2   (String perdidaConsciencia2) {
        this.perdidaConsciencia2 = perdidaConsciencia2;
    }
    public String getPerdidaConsciencia2() { return perdidaConsciencia2;  }

    public void setInquieto1   (String inquieto1) {
        this.inquieto1 = inquieto1;
    }
    public String getInquieto1() { return inquieto1;  }
    public void setInquieto2  (String inquieto2) {
        this.inquieto2 = inquieto2;
    }
    public String getInquieto2() { return inquieto2;  }

    public void setConvulsiones1 (String convulsiones1) {
        this.convulsiones1 = convulsiones1;
    }
    public String getConvulsiones1() { return convulsiones1;  }
    public void setConvulsiones2 (String convulsiones2) {
        this.convulsiones2 = convulsiones2;
    }
    public String getConvulsiones2() { return convulsiones2;  }

    public void setLetargia1 (String letargia1) {
        this.letargia1 = letargia1;
    }
    public String getLetargia1() { return letargia1;  }
    public void setLetargia2 (String letargia2) {
        this.letargia2 = letargia2;
    }
    public String getLetargia2() { return letargia2;  }

    public void setDolorCabeza1 (String dolorCabeza1) {
        this.dolorCabeza1 = dolorCabeza1;
    }
    public String getDolorCabeza1() { return dolorCabeza1;  }
    public void setDolorCabeza2 (String dolorCabeza2) {
        this.dolorCabeza2 = dolorCabeza2;
    }
    public String getDolorCabeza2() { return dolorCabeza2;  }

    public void setConjuntivitis1 (String conjuntivitis1) {
        this.conjuntivitis1 = conjuntivitis1;
    }
    public String getConjuntivitis1() { return conjuntivitis1;  }
    public void setConjuntivitis2 (String conjuntivitis2) {
        this.conjuntivitis2 = conjuntivitis2;
    }
    public String getConjuntivitis2() { return conjuntivitis2;  }

    public void setHemorragiaSuconjuntival1  (String hemorragiaSuconjuntival1 ) {
        this.hemorragiaSuconjuntival1 = hemorragiaSuconjuntival1;
    }
    public String getHemorragiaSuconjuntival1() { return hemorragiaSuconjuntival1;  }
    public void setHemorragiaSuconjuntival2  (String hemorragiaSuconjuntival2) {
        this.hemorragiaSuconjuntival2 = hemorragiaSuconjuntival2;
    }
    public String getHemorragiaSuconjuntival2() { return hemorragiaSuconjuntival2;  }

    public void setDolorRetroocular1  (String dolorRetroocular1) {
        this.dolorRetroocular1 = dolorRetroocular1;
    }
    public String getDolorRetroocular1() { return dolorRetroocular1;  }
    public void setDolorRetroocular2  (String dolorRetroocular2) {
        this.dolorRetroocular2 = dolorRetroocular2;
    }
    public String getDolorRetroocular2() { return dolorRetroocular2;  }

    public void setDolorGarganta1  (String dolorGarganta1) {
        this.dolorGarganta1 = dolorGarganta1;
    }
    public String getDolorGarganta1() { return dolorGarganta1;  }
    public void setDolorGarganta2 (String dolorGarganta2) {
        this.dolorGarganta2 = dolorGarganta2;
    }
    public String getDolorGarganta2() { return dolorGarganta2;  }

    public void setEritema1  (String eritema1) {
        this.eritema1 = eritema1;
    }
    public String getEritema1() { return eritema1;  }
    public void setEritema2 (String eritema2) {
        this.eritema2 = eritema2;
    }
    public String getEritema2() { return eritema2;  }

    public void setAdenopatiasCervicales1  (String adenopatiasCervicales1) {
        this.adenopatiasCervicales1 = adenopatiasCervicales1;
    }
    public String getAdenopatiasCervicales1() { return adenopatiasCervicales1;  }
    public void setAdenopatiasCervicales2  (String adenopatiasCervicales2) {
        this.adenopatiasCervicales2 = adenopatiasCervicales2;
    }
    public String getAdenopatiasCervicales2() { return adenopatiasCervicales2;  }

    public void setExudado1   (String exudado1) {
        this.exudado1 = exudado1;
    }
    public String getExudado1() { return exudado1;  }
    public void setExudado2   (String exudado2) {
        this.exudado2 = exudado2;
    }
    public String getExudado2() { return exudado2;  }

    public void setPetequiasMucosa1   (String petequiasMucosa1) {
        this.petequiasMucosa1 = petequiasMucosa1;
    }
    public String getPetequiasMucosa1() { return petequiasMucosa1;  }
    public void setPetequiasMucosa2  (String petequiasMucosa2) {
        this.petequiasMucosa2= petequiasMucosa2;
    }
    public String getPetequiasMucosa2() { return petequiasMucosa2;  }

    public void setTos1   (String tos1) {
        this.tos1= tos1;
    }
    public String getTos1() { return tos1;  }
    public void setTos2   (String tos2) {
        this.tos2= tos2;
    }
    public String getTos2() { return tos2;  }

    public void setRinorrea1   (String rinorrea1) {
        this.rinorrea1= rinorrea1;
    }
    public String getRinorrea1() { return rinorrea1;  }
    public void setRinorrea2   (String rinorrea2) {
        this.rinorrea2 = rinorrea2;
    }
    public String getRinorrea2() { return rinorrea2;  }

    public void setCongestionNasal1   (String congestionNasal1) {
        this.congestionNasal1 = congestionNasal1;
    }
    public String getCongestionNasal1() { return congestionNasal1;  }
    public void setCongestionNasal2   (String congestionNasal2) {
        this.congestionNasal2 = congestionNasal2;
    }
    public String getCongestionNasal2() { return congestionNasal2;  }

    public void setOtalgia1   (String otalgia1) {
        this.otalgia1 = otalgia1;
    }
    public String getOtalgia1() { return otalgia1;  }
    public void setOtalgia2   (String otalgia2) {
        this.otalgia2 = otalgia2;
    }
    public String getOtalgia2() { return otalgia2;  }

    public void setAleteoNasal1  (String aleteoNasal1) {
        this.aleteoNasal1 = aleteoNasal1;
    }
    public String getAleteoNasal1() { return aleteoNasal1;  }
    public void setAleteoNasal2  (String aleteoNasal2) {
        this.aleteoNasal2 = aleteoNasal2;
    }
    public String getAleteoNasal2() { return aleteoNasal2;  }

    public void setRespiracionRapida1   (String respiracionRapida1) {
        this.respiracionRapida1 = respiracionRapida1;
    }
    public String getRespiracionRapida1 () { return respiracionRapida1;  }
    public void setRespiracionRapida2  (String respiracionRapida2) {
        this.respiracionRapida2 = respiracionRapida2;
    }
    public String getRespiracionRapida2 () { return respiracionRapida2;  }

    public void setEstridorReposo1  (String estridorReposo1) {
        this.estridorReposo1 = estridorReposo1;
    }
    public String getEstridorReposo1() { return estridorReposo1;  }
    public void setEstridorReposo2  (String estridorReposo2) {
        this.estridorReposo2 = estridorReposo2;
    }
    public String getEstridorReposo2() { return estridorReposo2;  }

    public void settirajeSubcostal1  (String tirajeSubcostal1) {
        this.tirajeSubcostal1 = tirajeSubcostal1;
    }
    public String getTirajeSubcostal1() { return tirajeSubcostal1;  }
    public void settirajeSubcostal2  (String tirajeSubcostal2) {
        this.tirajeSubcostal2 = tirajeSubcostal2;
    }
    public String getTirajeSubcostal2() { return tirajeSubcostal2;  }

    public void setSibilancias1  (String sibilancias1) {
        this.sibilancias1 = sibilancias1;
    }
    public String getSibilancias1() { return sibilancias1;  }
    public String getSibilancias2() { return sibilancias2;  }
    public void setSibilancias2  (String sibilancias2) {
        this.sibilancias2 = sibilancias2;
    }




    public void setCrepitos1  (String crepitos1) {
        this.crepitos1 = crepitos1;
    }
    public String getCrepitos1() { return crepitos1;  }
    public void setCrepitos2  (String crepitos2) {
        this.crepitos2 = crepitos2;
    }
    public String getCrepitos2() { return crepitos2;  }

    public void setRoncos1  (String roncos1) {
        this.roncos1 = roncos1;
    }
    public String getRoncos1() { return roncos1;  }
    public void setRoncos2  (String roncos2) {
        this.roncos2 = roncos2;
    }
    public String getRoncos2() { return roncos2;  }

    public void setDisnea1   (String disnea1) {
        this.disnea1 = disnea1;
    }
    public String getDisnea1() { return disnea1;  }
    public void setDisnea2  (String disnea2) {
        this.disnea2 = disnea2;
    }
    public String getDisnea2() { return disnea2;  }

    public void setPocoApetito1  (String pocoApetito1) {
        this.pocoApetito1 = pocoApetito1;
    }
    public String getPocoApetito1() { return pocoApetito1;  }
    public void setPocoApetito2  (String pocoApetito2) {
        this.pocoApetito2 = pocoApetito2;
    }
    public String getPocoApetito2() { return pocoApetito2;  }

    public void setNausea1   (String nausea1) {
        this.nausea1 = nausea1;
    }
    public String getNausea1() { return nausea1;  }
    public void setNausea2  (String nausea2) {
        this.nausea2 = nausea2;
    }
    public String getNausea2() { return nausea2;  }

    public void setVomito12horas1   (String vomito12horas1) {
        this.vomito12horas1 = vomito12horas1;
    }
    public String getVomito12horas1() { return vomito12horas1;  }
    public void setVomito12horas2  (String vomito12horas2) {
        this.vomito12horas2 = vomito12horas2;
    }
    public String getVomito12horas2() { return vomito12horas2;  }

    public void setNumeroVomito12h1 (String numeroVomito12h1) {
        this.numeroVomito12h1 = numeroVomito12h1;
    }
    public String getNumeroVomito12h1() { return numeroVomito12h1;  }
    public void setNumeroVomito12h2 (String numeroVomito12h2) {
        this.numeroVomito12h2 = numeroVomito12h2;
    }
    public String getNumeroVomito12h2() { return numeroVomito12h2;  }

    public void setDiarrea1  (String diarrea1) {
        this.diarrea1 = diarrea1;
    }
    public String getDiarrea1() { return diarrea1;  }
    public void setDiarrea2  (String diarrea2) {
        this.diarrea2 = diarrea2;
    }
    public String getDiarrea2() { return diarrea2;  }

    public void setHepatomegalia1  (String hepatomegalia1) {
        this.hepatomegalia1 = hepatomegalia1;
    }
    public String getHepatomegalia1() { return hepatomegalia1;  }
    public void setHepatomegalia2  (String hepatomegalia2) {
        this.hepatomegalia2 = hepatomegalia2;
    }
    public String getHepatomegalia2() { return hepatomegalia2;  }

    public void setDolorAbdominal1  (String dolorAbdominal1 ) {
        this.dolorAbdominal1 = dolorAbdominal1;
    }
    public String getDolorAbdominal1 () { return dolorAbdominal1;  }
    public void setDolorAbdominal2  (String dolorAbdominal2) {
        this.dolorAbdominal2 = dolorAbdominal2;
    }
    public String getDolorAbdominal2() { return dolorAbdominal2;  }

    public void setArtralgia1  (String artralgia1 ) {
        this.artralgia1 = artralgia1;
    }
    public String getArtralgia1 () { return artralgia1;  }
    public void setArtralgia2  (String artralgia2) {
        this.artralgia2 = artralgia2;
    }
    public String getArtralgia2() { return artralgia2;  }


    public void setMialgial  (String mialgial ) {
        this.mialgia1 = mialgia1;
    }
    public String getMialgia1 () { return mialgia1;  }
    public void setMialgia2  (String mialgia2) {
        this.mialgia2 = mialgia2;
    }
    public String getMialgia2() { return mialgia2;  }

    public void setLumbalgia1  (String lumbalgia1 ) {
        this.lumbalgia1 = lumbalgia1;
    }
    public String getLumbalgia1 () { return lumbalgia1;  }

    public void setLumbalgia2  (String lumbalgia2) {
        this.lumbalgia2 = lumbalgia2;
    }
    public String getLumbalgia2() { return lumbalgia2;  }

    /**/
    public void setDolorCuello1  (String dolorCuello1 ) {
        this.dolorCuello1 = dolorCuello1;
    }
    public String getDolorCuello1 () { return dolorCuello1;  }

    public void setDolorCuello2  (String dolorCuello2) {
        this.dolorCuello2 = dolorCuello2;
    }
    public String getDolorCuello2() { return dolorCuello2;  }
     /**/
     public void setEdema1 (String edema1 ) {
         this.edema1 = edema1;
     }
    public String getEdema1 () { return edema1;  }

    public void setEdema2  (String edema2) {
        this.edema2 = edema2;
    }
    public String getEdema2() { return edema2;  }
    /*rahsLocalizado1*/
    public void setRahsLocalizado1 (String rahsLocalizado1 ) {
        this.rahsLocalizado1 = rahsLocalizado1;
    }
    public String getRahsLocalizado1 () { return rahsLocalizado1;  }

    public void setRahsLocalizado2  (String rahsLocalizado2) {
        this.rahsLocalizado2 = rahsLocalizado2;
    }
    public String getRahsLocalizado2() { return rahsLocalizado2;  }
    /**/
    public void setRahsGeneralizado1 (String rahsGeneralizado1 ) {
        this.rahsGeneralizado1 = rahsGeneralizado1;
    }
    public String getRahsGeneralizado1 () { return rahsGeneralizado1;  }

    public void setRahsGeneralizado2 (String rahsGeneralizado2) {
        this.rahsGeneralizado2 = rahsGeneralizado2;
    }
    public String getRahsGeneralizado2 ()  { return rahsGeneralizado2;  }
/*rashEritematoso1*/
    public void setRashEritematoso1 (String rashEritematoso1 ) {
    this.rashEritematoso1 = rashEritematoso1;
}
    public String getRashEritematoso1 () { return rashEritematoso1;  }

    public void setRashEritematoso2  (String rashEritematoso2) {
        this.rashEritematoso2 = rashEritematoso2;
    }
    public String getRashEritematoso2 ()  { return rashEritematoso2;  }
    /*rahsMacular1*/
    public void setRahsMacular1 (String rahsMacular1 ) {
        this.rahsMacular1 = rahsMacular1;
    }
    public String getRahsMacular1 () { return rahsMacular1;  }

    public void setRahsMacular2  (String rahsMacular2) {
        this.rahsMacular2 = rahsMacular2;
    }
    public String getRahsMacular2 ()  { return rahsMacular2;  }
    /*rashPapular1*/
    public void setRashPapular1 (String rashPapular1 ) {
        this.rashPapular1 = rashPapular1;
    }
    public String getRashPapular1 () { return rashPapular1;  }

    public void setRashPapular2  (String rashPapular2) {
        this.rashPapular2 = rashPapular2;
    }
    public String getRashPapular2 ()  { return rashPapular2;  }
    /*pielMoteada1*/
    public void setPielMoteada1 (String pielMoteada1 ) {
        this.pielMoteada1 = pielMoteada1;
    }
    public String getPielMoteada1 () { return pielMoteada1;  }

    public void setPielMoteada2  (String pielMoteada2) {
        this.pielMoteada2 = pielMoteada2;
    }
    public String getPielMoteada2() { return pielMoteada2;  }
     /*ruborFacia1*/
     public void setRuborFacia1 (String ruborFacia1 ) {
         this.ruborFacia1 = ruborFacia1;
     }
    public String getRuborFacia1 () { return ruborFacia1;  }

    public void setRuborFacia2  (String ruborFacia2) {
        this.ruborFacia2 = ruborFacia2;
    }
    public String getRuborFacia2() { return ruborFacia2;  }
    /*cianosisCentra1*/
    public void setCianosisCentral1 (String cianosisCentral1 ) {
        this.cianosisCentral1 = cianosisCentral1;
    }
    public String getCianosisCentral1 () { return cianosisCentral1;  }

    public void setCianosisCentral2  (String cianosisCentral2) {
        this.cianosisCentral2 = cianosisCentral2;
    }
    public String getCianosisCentral2() { return cianosisCentral2;  }
     /*ictericia1*/
     public void setIctericia1 (String ictericia1 ) {
         this.ictericia1 = ictericia1;
     }
    public String getIctericia1 () { return ictericia1;  }

    public void setIctericia2  (String ictericia2) {
        this.ictericia2 = ictericia2;
    }
    public String getIctericia2() { return ictericia2;  }
    /*imc1*/
    public void setImc1 (String imc1 ) {
        this.imc1 = imc1;
    }
    public String getImc1 () { return imc1;  }

    public void setImc2  (String imc2) {
        this.imc2 = imc2;
    }
    public String getImc2() { return imc2;  }
    /*obeso1*/
    public void setObeso1 (String obeso1 ) {
        this.obeso1 = obeso1;
    }
    public String getObeso1 () { return obeso1;  }

    public void setObeso2  (String obeso2) {
        this.obeso2 = obeso2;
    }
    public String getObeso2() { return obeso2;  }
    /*sobrepeso1*/
    public void setSobrepeso1 (String sobrepeso1 ) {
        this.sobrepeso1 = sobrepeso1;
    }
    public String getSobrepeso1 () { return sobrepeso1;  }

    public void setSobrepeso2  (String sobrepeso2) {
        this.sobrepeso2 = sobrepeso2;
    }
    public String getSobrepeso2() { return sobrepeso2;  }
    /*sospechaProblema1*/
    public void setSospechaProblema1 (String sospechaProblema1 ) {
        this.sospechaProblema1 = sospechaProblema1;
    }
    public String getSospechaProblema1() { return sospechaProblema1;  }

    public void setSospechaProblema2  (String sospechaProblema2) {
        this.sospechaProblema2 = sospechaProblema2;
    }
    public String getSospechaProblema2() { return sospechaProblema2;  }
     /*normal1*/
     public void setNormal1 (String normal1 ) {
         this.normal1 = normal1;
     }
    public String getNormal1() { return normal1;  }

    public void setNormal2  (String normal2) {
        this.normal2 = normal2;
    }
    public String getNormal2() { return normal2;  }
     /*bajoPeso1*/
     public void setBajoPeso1 (String bajoPeso1 ) {
         this.bajoPeso1 = bajoPeso1;
     }
    public String getBajoPeso1() { return bajoPeso1;  }

    public void setBajoPeso2  (String bajoPeso2) {
        this.bajoPeso2 = bajoPeso2;
    }
    public String getBajoPeso2() { return bajoPeso2;  }
     /*bajoPesoSevero1*/
     public void setBajoPesoSevero1 (String bajoPesoSevero1 ) {
         this.bajoPesoSevero1 = bajoPesoSevero1;
     }
    public String getBajoPesoSevero1() { return bajoPesoSevero1;  }

    public void setBajoPesoSevero2  (String bajoPesoSevero2) {
        this.bajoPesoSevero2 = bajoPesoSevero2;
    }
    public String getBajoPesoSevero2() { return bajoPesoSevero2;  }
     /*categoria1*/
     public void setCategoria1 (String categoria1 ) {
         this.categoria1 = categoria1;
     }
    public String getCategoria1() { return categoria1;  }

    public void setCategoria2  (String categoria2) {
        this.categoria2 = categoria2;
    }
    public String getCategoria2() { return categoria2;  }
    /*cambioCategoria1*/
    public void setCambioCategoria1 (String cambioCategoria1 ) {
        this.cambioCategoria1 = cambioCategoria1;
    }
    public String getCambioCategoria1() { return cambioCategoria1;  }

    public void setCambioCategoria2  (String cambioCategoria2) {
        this.cambioCategoria2 = cambioCategoria2;
    }
    public String getCambioCategoria2() { return cambioCategoria2;  }
     /*pruebaTorniquetePositiva1*/
     public void setPruebaTorniquetePositiva1 (String pruebaTorniquetePositiva1 ) {
         this.pruebaTorniquetePositiva1 = pruebaTorniquetePositiva1;
     }
    public String getPruebaTorniquetePositiva1() { return pruebaTorniquetePositiva1;  }

    public void setPruebaTorniquetePositiva2  (String cambioCategoria2) {
        this.pruebaTorniquetePositiva2 = pruebaTorniquetePositiva2;
    }
    public String getPruebaTorniquetePositiva2() { return pruebaTorniquetePositiva2;  }
     /*petequia10Pt1*/
     public void setPetequia10Pt1 (String petequia10Pt1 ) {
         this.petequia10Pt1 = petequia10Pt1;
     }
    public String getPetequia10Pt1() { return petequia10Pt1;  }

    public void setPetequia10Pt2  (String petequia10Pt2) {
        this.petequia10Pt2 = petequia10Pt2;
    }
    public String getPetequia10Pt2() { return petequia10Pt2;  }
     /*petequia20Pt1*/
     public void setPetequia20Pt1 (String petequia20Pt1 ) {
         this.petequia20Pt1 = petequia20Pt1;
     }
    public String getPetequia20Pt1() { return petequia20Pt1;  }

    public void setPetequia20Pt2  (String petequia20Pt2) {
        this.petequia20Pt2 = petequia20Pt2;
    }
    public String getPetequia20Pt2() { return petequia20Pt2;  }
     /*pielExtremidadesFrias1*/
     public void setPielExtremidadesFrias1 (String pielExtremidadesFrias1 ) {
         this.pielExtremidadesFrias1 = pielExtremidadesFrias1;
     }
    public String getPielExtremidadesFrias1() { return pielExtremidadesFrias1;  }

    public void setPielExtremidadesFrias2  (String pielExtremidadesFrias2) {
        this.pielExtremidadesFrias2 = pielExtremidadesFrias2;
    }
    public String getPielExtremidadesFrias2() { return pielExtremidadesFrias2;  }
    /*palidezEnExtremidades1*/
    public void setPalidezEnExtremidades1(String palidezEnExtremidades1 ) {
        this.palidezEnExtremidades1 = palidezEnExtremidades1;
    }
    public String getPalidezEnExtremidades1() { return palidezEnExtremidades1;  }

    public void setPalidezEnExtremidades2  (String palidezEnExtremidades2) {
        this.palidezEnExtremidades2 = palidezEnExtremidades2;
    }
    public String getPalidezEnExtremidades2() { return palidezEnExtremidades2;  }
    /*epistaxis1*/
    public void setEpistaxis1(String epistaxis1 ) {
        this.epistaxis1 = epistaxis1;
    }
    public String getEpistaxis1() { return epistaxis1;  }

    public void setEpistaxis2  (String epistaxis2) {
        this.epistaxis2 = epistaxis2;
    }
    public String getEpistaxis2() { return epistaxis2;  }
    /*gingivorragia1*/
    public void setGingivorragia1(String gingivorragia1 ) {
        this.gingivorragia1 = gingivorragia1;
    }
    public String getGingivorragia1() { return gingivorragia1;  }

    public void setGingivorragia2  (String gingivorragia2) {
        this.gingivorragia2 = gingivorragia2;
    }
    public String getGingivorragia2() { return gingivorragia2;  }
    /*petequiasEspontaneas1*/
    public void setPetequiasEspontaneas1(String petequiasEspontaneas1 ) {
        this.petequiasEspontaneas1 = petequiasEspontaneas1;
    }
    public String getPetequiasEspontaneas1() { return petequiasEspontaneas1;  }

    public void setPetequiasEspontaneas2  (String petequiasEspontaneas2) {
        this.petequiasEspontaneas2 = petequiasEspontaneas2;
    }
    public String getPetequiasEspontaneas2() { return petequiasEspontaneas2;  }
    /*llenadoCapilar2seg1*/
    public void setLlenadoCapilar2seg1(String llenadoCapilar2seg1 ) {
        this.llenadoCapilar2seg1 = llenadoCapilar2seg1;
    }
    public String getLlenadoCapilar2seg1() { return llenadoCapilar2seg1;  }

    public void setLlenadoCapilar2seg2  (String llenadoCapilar2seg2) {
        this.llenadoCapilar2seg2 = llenadoCapilar2seg2;
    }
    public String getLlenadoCapilar2seg2() { return llenadoCapilar2seg2;  }
    /*cianosis1*/
    public void setCianosis1(String cianosis1 ) {
        this.cianosis1 = cianosis1;
    }
    public String getCianosis1() { return cianosis1;  }

    public void setCianosis2  (String cianosis2) {
        this.cianosis2 = cianosis2;
    }
    public String getCianosis2() { return cianosis2;  }
    /*hipermenorrea1*/
    public void setHipermenorrea1(String hipermenorrea1 ) {
        this.hipermenorrea1 = hipermenorrea1;
    }
    public String getHipermenorrea1() { return hipermenorrea1;  }

    public void setHipermenorrea2  (String hipermenorrea2) {
        this.hipermenorrea2 = hipermenorrea2;
    }
    public String getHipermenorrea2() { return hipermenorrea2;  }
    /*hematemesis1*/
    public void setHematemesis1(String hematemesis1 ) {
        this.hematemesis1 = hematemesis1;
    }
    public String getHematemesis1() { return hematemesis1;  }

    public void setHematemesis2  (String hematemesis2) {
        this.hematemesis2 = hematemesis2;
    }
    public String getHematemesis2() { return hematemesis2;  }
    /*hemoconcentracion1*/
    public void setHemoconcentracion1(String hemoconcentracion1 ) {
        this.hemoconcentracion1 = hemoconcentracion1;
    }
    public String getHemoconcentracion1() { return hemoconcentracion1;  }

    public void setHemoconcentracion2  (String hemoconcentracion2) {
        this.hemoconcentracion2 = hemoconcentracion2;
    }
    public String getHemoconcentracion2() { return hemoconcentracion2;  }
    /*hospitalizado1*/
    public void setHospitalizado1(String hospitalizado1 ) {
        this.hospitalizado1 = hospitalizado1;
    }
    public String getHospitalizado1() { return hospitalizado1;  }

    public void setHospitalizado2  (String hospitalizado2) {
        this.hospitalizado2 = hospitalizado2;
    }
    public String getHospitalizado2() { return hospitalizado2;  }
    /*hospitalizadoEspecificar1*/
    public void setHospitalizadoEspecificar1(String hospitalizadoEspecificar1 ) {
        this.hospitalizadoEspecificar1 = hospitalizadoEspecificar1;
    }
    public String getHospitalizadoEspecificar1() { return hospitalizadoEspecificar1;  }

    public void setHospitalizadoEspecificar2  (String hospitalizadoEspecificar2) {
        this.hospitalizadoEspecificar2 = hospitalizadoEspecificar2;
    }
    public String getHospitalizadoEspecificar2() { return hospitalizadoEspecificar2;  }
    /*transfusionSangre1*/
    public void setTransfusionSangre1(String transfusionSangre1 ) {
        this.transfusionSangre1 = transfusionSangre1;
    }
    public String getTransfusionSangre1() { return transfusionSangre1;  }

    public void setTransfusionSangre2  (String transfusionSangre2) {
        this.transfusionSangre2 = transfusionSangre2;
    }
    public String getTransfusionSangre2() { return transfusionSangre2;  }
    /*transfusionEspecificar1*/
    public void setTransfusionEspecificar1(String transfusionEspecificar1 ) {
        this.transfusionEspecificar1 = transfusionEspecificar1;
    }
    public String getTransfusionEspecificar1() { return transfusionEspecificar1;  }

    public void setTransfusionEspecificar2  (String transfusionEspecificar2) {
        this.transfusionEspecificar2 = transfusionEspecificar2;
    }
    public String getTransfusionEspecificar2() { return transfusionEspecificar2;  }
    /*tomandoMedicamento1*/
    public void setTomandoMedicamento1(String tomandoMedicamento1 ) {
        this.tomandoMedicamento1 = tomandoMedicamento1;
    }
    public String getTomandoMedicamento1() { return tomandoMedicamento1;  }

    public void setTomandoMedicamento2  (String tomandoMedicamento2) {
        this.tomandoMedicamento2 = tomandoMedicamento2;
    }
    public String getTomandoMedicamento2() { return tomandoMedicamento2;  }
    /*medicamentoEspecificar1*/
    public void setMedicamentoEspecificar1(String medicamentoEspecificar1 ) {
        this.medicamentoEspecificar1 = medicamentoEspecificar1;
    }
    public String getMedicamentoEspecificar1() { return medicamentoEspecificar1;  }

    public void setMedicamentoEspecificar2  (String medicamentoEspecificar2) {
        this.medicamentoEspecificar2 = medicamentoEspecificar2;
    }
    public String getMedicamentoEspecificar2() { return medicamentoEspecificar2;  }
     /*bhc1*/
     public void setBhc1(String bhc1 ) {
         this.bhc1 = bhc1;
     }
    public String getBhc1() { return bhc1;  }

    public void setBhc2 (String bhc2) {
        this.bhc2 = bhc2;
    }
    public String getBhc2() { return bhc2;  }
     /*serologiaArbovirus1*/
     public void setSerologiaArbovirus1(String serologiaArbovirus1 ) {
         this.serologiaArbovirus1 = serologiaArbovirus1;
     }
    public String getSerologiaArbovirus1() { return serologiaArbovirus1;  }

    public void setSerologiaArbovirus2 (String serologiaArbovirus2) {
        this.serologiaArbovirus2 = serologiaArbovirus2;
    }
    public String getSerologiaArbovirus2() { return serologiaArbovirus2;  }
     /*gotaGruesa1*/
     public void setGotaGruesa1(String gotaGruesa1 ) {
         this.gotaGruesa1 = gotaGruesa1;
     }
    public String getGotaGruesa1() { return gotaGruesa1;  }

    public void setGotaGruesa2 (String gotaGruesa2) {
        this.gotaGruesa2 = gotaGruesa2;
    }
    public String getGotaGruesa2() { return gotaGruesa2;  }
     /*ego1*/
     public void setEgo1(String ego1 ) {
         this.ego1 = ego1;
     }
    public String getEgo1() { return ego1;  }

    public void setEgo2 (String   ego2) {
        this.ego2 = ego2;
    }
    public String getEgo2() { return ego2;  }
     /*egh1*/
     public void setEgh1(String egh1 ) {
         this.egh1 = egh1;
     }
    public String getEgh1() { return egh1;  }

    public void setEgh2 (String   egh2) {
        this.egh2 = egh2;
    }
    public String getEgh2() { return egh2;  }
     /*otroExamenLab1*/
     public void setOtroExamenLab1(String otroExamenLab1 ) {
         this.otroExamenLab1 = otroExamenLab1;
     }
    public String getOtroExamenLab1() { return otroExamenLab1;  }

    public void setOtroExamenLab2 (String   otroExamenLab2) {
        this.otroExamenLab2 = otroExamenLab2;
    }
    public String getOtroExamenLab2() { return otroExamenLab2;  }
     /*otroExamanLabEspecificar1*/
     public void setOtroExamanLabEspecificar1(String otroExamanLabEspecificar1 ) {
         this.otroExamanLabEspecificar1 = otroExamanLabEspecificar1;
     }
    public String getOtroExamanLabEspecificar1() { return otroExamanLabEspecificar1;  }

    public void setOtroExamanLabEspecificar2 (String  otroExamanLabEspecificar2) {
        this.otroExamanLabEspecificar2 = otroExamanLabEspecificar2;
    }
    public String getOtroExamanLabEspecificar2() { return otroExamanLabEspecificar2;  }
     /*acetaminofen1*/
     public void setAcetaminofen1(String acetaminofen1 ) {
         this.acetaminofen1 = acetaminofen1;
     }
    public String getAcetaminofen1() { return acetaminofen1;  }

    public void setAcetaminofen2 (String  acetaminofen2) {
        this.acetaminofen2 = acetaminofen2;
    }
    public String getAcetaminofen2() { return acetaminofen2;  }
     /*amoxicilina1*/
    public void setAmoxicilina1(String amoxicilina1 ) {
        this.amoxicilina1 = amoxicilina1;
    }
    public String getAmoxicilina1() { return amoxicilina1;  }

    public void setAmoxicilina2 (String   amoxicilina2) {
        this.amoxicilina2 = amoxicilina2;
    }
    public String getAmoxicilina2() { return amoxicilina2;  }

    /*dicloxacilina1*/
    public void setDicloxacilina1(String dicloxacilina1 ) {
        this.dicloxacilina1 = dicloxacilina1;
    }
    public String getDicloxacilina1() { return dicloxacilina1;  }

    public void setDicloxacilina2 (String   dicloxacilina2) {
        this.dicloxacilina2 = dicloxacilina2;
    }
    public String getDicloxacilina2() { return dicloxacilina2;  }
    /*penicilina1*/
    public void setPenicilina1(String penicilina1 ) {
        this.penicilina1 = penicilina1;
    }
    public String getPenicilina1() { return penicilina1;  }

    public void setPenicilina2 (String  penicilina2) {
        this.penicilina2 = penicilina2;
    }
    public String getPenicilina2() { return penicilina2;  }
    /*furazolidona1*/
    public void setFurazolidona1(String furazolidona1 ) {
        this.furazolidona1 = furazolidona1;
    }
    public String getFurazolidona1() { return furazolidona1;  }

    public void setFurazolidona2 (String   furazolidona2) {
        this.furazolidona2 = furazolidona2;
    }
    public String getFurazolidona2() { return furazolidona2;  }
    /*metronidazolTinidazol1*/
    public void setMetronidazolTinidazol1(String metronidazolTinidazol1 ) {
        this.metronidazolTinidazol1 = metronidazolTinidazol1;
    }
    public String getMetronidazolTinidazol1() { return metronidazolTinidazol1;  }

    public void setMetronidazolTinidazol2 (String   metronidazolTinidazol2) {
        this.metronidazolTinidazol2 = metronidazolTinidazol2;
    }
    public String getMetronidazolTinidazol2() { return metronidazolTinidazol2;  }
    /*albendazolMebendazol1*/
    public void setAlbendazolMebendazol1(String albendazolMebendazol1 ) {
        this.albendazolMebendazol1 = albendazolMebendazol1;
    }
    public String getAlbendazolMebendazol1() { return albendazolMebendazol1;  }

    public void setAlbendazolMebendazol2 (String   albendazolMebendazol2) {
        this.albendazolMebendazol2 = albendazolMebendazol2;
    }
    public String getAlbendazolMebendazol2() { return albendazolMebendazol2;  }
    /*sueroOral1*/
    public void setSueroOral1(String sueroOral1 ) {
        this.sueroOral1 = sueroOral1;
    }
    public String getSueroOral1() { return sueroOral1;  }

    public void setSueroOral2 (String   sueroOral2) {
        this.sueroOral2 = sueroOral2;
    }
    public String getSueroOral2() { return sueroOral2;  }
    /*otroTratamiento1*/
    public void setOtroTratamiento1(String otroTratamiento1 ) {
        this.otroTratamiento1 = otroTratamiento1;
    }
    public String getOtroTratamiento1() { return otroTratamiento1;  }

    public void setOtroTratamiento2 (String   otroTratamiento2) {
        this.otroTratamiento2 = otroTratamiento2;
    }
    public String getOtroTratamiento2() { return otroTratamiento2;  }
    /*otroTratamientoEspecificar1*/
    public void setOtroTratamientoEspecificar1(String otroTratamientoEspecificar1 ) {
        this.otroTratamientoEspecificar1 = otroTratamientoEspecificar1;
    }
    public String getOtroTratamientoEspecificar1() { return otroTratamientoEspecificar1;  }

    public void setOtroTratamientoEspecificar2 (String   otroTratamientoEspecificar2) {
        this.otroTratamientoEspecificar2 = otroTratamientoEspecificar2;
    }
    public String getOtroTratamientoEspecificar2() { return otroTratamientoEspecificar2;  }
    /*planes1*/
    public void setPlanes1(String planes1 ) {
        this.planes1 = planes1;
    }
    public String getPlanes1() { return planes1;  }

    public void setPlanes2(String   planes2) {
        this.planes2 = planes2;
    }
    public String getPlanes2() { return planes2;  }
    /*historiaClinica1*/
    public void setHistoriaClinica1(String historiaClinica1 ) {
        this.historiaClinica1 = historiaClinica1;
    }
    public String getHistoriaClinica1() { return historiaClinica1;  }

    public void setHistoriaClinica2(String   historiaClinica2) {
        this.historiaClinica2 = historiaClinica2;
    }
    public String getHistoriaClinica2() { return historiaClinica2;  }
    /*diagnostico1*/
    public void setDiagnostico1(String diagnostico1 ) {
        this.diagnostico1 = diagnostico1;
    }
    public String getDiagnostico1() { return diagnostico1;  }

    public void setDiagnostico2(String   diagnostico2) {
        this.diagnostico2 = diagnostico2;
    }
    public String getDiagnostico2() { return diagnostico2;  }
    /*diagnostico11*/
    public void setDiagnostico11(String diagnostico11 ) {
        this.diagnostico11 = diagnostico11;
    }
    public String getDiagnostico11() { return diagnostico11;  }

    public void setDiagnostico12(String   diagnostico12) {
        this.diagnostico12 = diagnostico12;
    }
    public String getDiagnostico12() { return diagnostico12;  }
    /*diagnostico21*/
    public void setDiagnostico21(String diagnostico21 ) {
        this.diagnostico21 = diagnostico21;
    }
    public String getDiagnostico21() { return diagnostico21;  }

    public void setDiagnostico22(String  diagnostico22) {
        this.diagnostico22 = diagnostico22;
    }
    public String getDiagnostico22() { return diagnostico22;  }
    /*diagnostico31*/
    public void setDiagnostico31(String diagnostico31 ) {
        this.diagnostico31 = diagnostico31;
    }
    public String getDiagnostico31() { return diagnostico31;  }

    public void setDiagnostico32(String  diagnostico32) {
        this.diagnostico32 = diagnostico32;
    }
    public String getDiagnostico32() { return diagnostico32;  }
    /*diagnostico41*/
    public void setDiagnostico41(String diagnostico41 ) {
        this.diagnostico41 = diagnostico41;
    }
    public String getDiagnostico41() { return diagnostico41;  }

    public void setDiagnostico42(String   diagnostico42) {
        this.diagnostico42 = diagnostico42;
    }
    public String getDiagnostico42() { return diagnostico42;  }
   /*codigo_supervisor1*/
 /*  public void setCodigo_supervisor(String codigo_supervisor1 ) {
       this.codigo_supervisor1 = codigo_supervisor1;
   }
    public String getCodigo_supervisor1() { return codigo_supervisor1;  }

    public void setCodigo_supervisor2(String  codigo_supervisor2) {
        this.codigo_supervisor2 = codigo_supervisor2;
    }
    public String getCodigo_supervisor2() { return codigo_supervisor2;  }
    /*telefonoEmergencia1*/
    public void setTelefonoEmergencia1(String telefonoEmergencia1 ) {
        this.telefonoEmergencia1 = telefonoEmergencia1;
    }
    public String getTelefonoEmergencia1() { return telefonoEmergencia1;  }

    public void setTelefonoEmergencia2(String   telefonoEmergencia2) {
        this.telefonoEmergencia2 = telefonoEmergencia2;
    }
    public String getTelefonoEmergencia2() { return telefonoEmergencia2;  }
    /*proximaCita1*/
    public void setProximaCita1(String proximaCita1 ) {
        this.proximaCita1 = proximaCita1;
    }
    public String getProximaCita1() { return proximaCita1;  }

    public void setProximaCita2(String  proximaCita2) {
        this.proximaCita2 = proximaCita2;
    }
    public String getProximaCita2() { return proximaCita2;  }
    /*medico1*/
    public void setMedico1(String medico1 ) {
        this.medico1 = medico1;
    }
    public String getMedico1() { return medico1;  }

    public void setMedico2(String  medico2) {
        this.medico2 = medico2;
    }
    public String getMedico2() { return medico2;  }
    /*fechaMedico1*/
    public void setFechaMedico1(String fechaMedico1 ) {
        this.fechaMedico1 = fechaMedico1;
    }
    public String getFechaMedico1() { return fechaMedico1;  }

    public void setFechaMedico2(String   fechaMedico2) {
        this.fechaMedico2 = fechaMedico2;
    }
    public String getFechaMedico2() { return fechaMedico2;  }
    /*horaMedico1*/
    public void setHoraMedico1(String horaMedico1 ) {
        this.horaMedico1 = horaMedico1;
    }
    public String getHoraMedico1() { return horaMedico1;  }

    public void setHoraMedico2(String   horaMedico2) {
        this.horaMedico2 = horaMedico2;
    }
    public String getHoraMedico2() { return horaMedico2;  }
    /*enfermeria1*/
    public void setEnfermeria1(String enfermeria1 ) {
        this.enfermeria1 = enfermeria1;
    }
    public String getEnfermeria1() { return enfermeria1;  }

    public void setEnfermeria2(String  enfermeria2) {
        this.enfermeria2 = enfermeria2;
    }
    public String getEnfermeria2() { return enfermeria2;  }
    /*fechaEnfermeria1*/
    public void setFechaEnfermeria1(String fechaEnfermeria1 ) {
        this.fechaEnfermeria1 = fechaEnfermeria1;
    }
    public String getFechaEnfermeria1() { return fechaEnfermeria1;  }

    public void setFechaEnfermeria2(String   fechaEnfermeria2) {
        this.fechaEnfermeria2 = fechaEnfermeria2;
    }
    public String getFechaEnfermeria2() { return fechaEnfermeria2;  }
    /*horaEnfermeria1*/

    public void setHoraEnfermeria1(String horaEnfermeria1 ) {
        this.horaEnfermeria1 = horaEnfermeria1;
    }
    public String getHoraEnfermeria1() { return horaEnfermeria1;  }

    public void setHoraEnfermeria2(String  horaEnfermeria2) {
        this.horaEnfermeria2 = horaEnfermeria2;
    }
    public String getHoraEnfermeria2() { return horaEnfermeria2;  }


}
