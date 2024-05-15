package ni.org.ics.webapp.domain.survey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ing. Santiago Carballo on 11/04/2023.
 */

@Entity
@Table(name = "encuesta_satisfaccion_usuario", catalog = "a2cares")
public class EncuestaSatisfaccionUsuario {
    private static final long serialVersionUID = 1L;
    private Long codigo;
    private String codigoParticipante;
    private String atencionPersonalEstudio_P1;
    private String tiempoAtencionRecibido_P2;
    private String atencionRecibidaEnfermeria_P3;
    private String atencionRecibidaDoctores_P4;
    private String ambienteDondeRecibeAtencion_P5;
    private String explicaronDiagnostico_P6;
    private String explicaronTratamiento_P7;
    private String tieneArbovirusImportanciaSeg_P8;
    private String explicaronPeligrosArbovirus_P8_1;
    private String medicoDijoCuidados_P8_2;
    private String dificultadAtencion_P9;
    private String centroSaludLejos_P9_1;
    private String costoTrasnporteElevado_P9_2;
    private String trabajoTiempo_P9_3;
    private String noQueriapasarConsulta_P9_4;
    private String otrasEspecifique_P9_5;
    private String recomendariaAmigoFamiliar_P10;
    private String atencionCalidad_P10_1;
    private String respNecesidadesSaludOportuna_P10_1;
    private String tiempoEsperaCorto_P10_1;
    private String mejorAtencionQueSeguro_P10_1;
    private String examenLabNecesarios_P10_1;
    private String pocosRequisitos_P10_1;
    private String otraP_10_1;
    private String atencionPersonalMala_P10_2;
    private String noDanRespNecesidades_P10_2;
    private String tiempoEsperaLargo_P10_2;
    private String mejorAtencionOtraUnidadSalud_P10_2;
    private String solicitaDemasiadaMx_P10_2;
    private String muchosRequisitos_P10_2;
    private String noExplicanHacenMx_P10_2;
    private String noConfianza_P10_2;
    private String otraP_10_2;
    private String comprendeProcedimientos_P11;
    private String noComodoRealizarPreg_P11_1;
    private String noRespondieronPreg_P11_1;
    private String explicacionRapida_P11_1;
    private String noDejaronHacerPreg_P11_1;
    private String otraP_11_1;
    private String identificadoEquipo;
    private char estado;
    private char pasivo;
    private Date fechaRegistro;
    private String creado;
    private String usuarioRegistro;
    private String nombre1Tutor;
    private String nombre2Tutor;
    private String apellido1Tutor;
    private String apellido2Tutor;
    private Integer codigoCasa;
    private String estudio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = true, length = 50)
    public String getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(String codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "ATENCION_PERSONAL_ESTUDIO_P1", length = 1, nullable = true)
    public String getAtencionPersonalEstudio_P1() {
        return atencionPersonalEstudio_P1;
    }
    public void setAtencionPersonalEstudio_P1(String atencionPersonalEstudio_P1) {
        this.atencionPersonalEstudio_P1 = atencionPersonalEstudio_P1;
    }

    @Column(name = "TIEMPO_ATENCION_RECIBIDO_P2", length = 1, nullable = true)
    public String getTiempoAtencionRecibido_P2() {
        return tiempoAtencionRecibido_P2;
    }

    public void setTiempoAtencionRecibido_P2(String tiempoAtencionRecibido_P2) {
        this.tiempoAtencionRecibido_P2 = tiempoAtencionRecibido_P2;
    }


    @Column(name = "ATENCION_RECIBIDA_ENFERMERIA_P3", length = 1, nullable = true)
    public String getAtencionRecibidaEnfermeria_P3() {
        return atencionRecibidaEnfermeria_P3;
    }

    public void setAtencionRecibidaEnfermeria_P3(String atencionRecibidaEnfermeria_P3) {
        this.atencionRecibidaEnfermeria_P3 = atencionRecibidaEnfermeria_P3;
    }

    @Column(name = "ATENCION_RECIBIDA_DOCTORES_P4", length = 1, nullable = true)
    public String getAtencionRecibidaDoctores_P4() {
        return atencionRecibidaDoctores_P4;
    }

    public void setAtencionRecibidaDoctores_P4(String atencionRecibidaDoctores_P4) {
        this.atencionRecibidaDoctores_P4 = atencionRecibidaDoctores_P4;
    }

    @Column(name = "AMBIENTE_DONDE_RECIBE_ATENCION_P5", length = 1, nullable = true)
    public String getAmbienteDondeRecibeAtencion_P5() {
        return ambienteDondeRecibeAtencion_P5;
    }

    public void setAmbienteDondeRecibeAtencion_P5(String ambienteDondeRecibeAtencion_P5) {
        this.ambienteDondeRecibeAtencion_P5 = ambienteDondeRecibeAtencion_P5;
    }

    @Column(name = "EXPLICARON_DIAGNOSTICO_P6", length = 1, nullable = true)
    public String getExplicaronDiagnostico_P6() {
        return explicaronDiagnostico_P6;
    }

    public void setExplicaronDiagnostico_P6(String explicaronDiagnostico_P6) {
        this.explicaronDiagnostico_P6 = explicaronDiagnostico_P6;
    }
    @Column(name = "EXPLICARON_TRATAMIENTO_P7", length = 1, nullable = true)
    public String getExplicaronTratamiento_P7() {
        return explicaronTratamiento_P7;
    }

    public void setExplicaronTratamiento_P7(String explicaronTratamiento_P7) {
        this.explicaronTratamiento_P7 = explicaronTratamiento_P7;
    }
    @Column(name = "TIENE_ARBOVIRUS_IMPORTANCIA_P8", length = 1, nullable = true)
     public String getTieneArbovirusImportanciaSeg_P8() {
        return tieneArbovirusImportanciaSeg_P8;
    }

    public void setTieneArbovirusImportanciaSeg_P8(String tieneArbovirusImportanciaSeg_P8) {
        this.tieneArbovirusImportanciaSeg_P8 = tieneArbovirusImportanciaSeg_P8;
    }

    @Column(name = "EXPLICARON_PELIGROS_ARBOVIRUS_P8_1", length = 1, nullable = true)
    public String getExplicaronPeligrosArbovirus_P8_1() {
        return explicaronPeligrosArbovirus_P8_1;
    }

    public void setExplicaronPeligrosArbovirus_P8_1(String explicaronPeligrosArbovirus_P8_1) {
        this.explicaronPeligrosArbovirus_P8_1 = explicaronPeligrosArbovirus_P8_1;
    }

    @Column(name = "MEDICO_DIJO_CUIDADOS_P8_2", length = 1, nullable = true)
    public String getMedicoDijoCuidados_P8_2() {
        return medicoDijoCuidados_P8_2;
    }

    public void setMedicoDijoCuidados_P8_2(String medicoDijoCuidados_P8_2) {
        this.medicoDijoCuidados_P8_2 = medicoDijoCuidados_P8_2;
    }
    @Column(name = "DIFICULTAD_ATENCION_P9", length = 1, nullable = true)
    public String getDificultadAtencion_P9() {
        return dificultadAtencion_P9;
    }

    public void setDificultadAtencion_P9(String dificultadAtencion_P9) {
        this.dificultadAtencion_P9 = dificultadAtencion_P9;
    }
    @Column(name = "CENTRO_SALUD_LEJOS_P9_1", length = 1, nullable = true)
    public String getCentroSaludLejos_P9_1() {
        return centroSaludLejos_P9_1;
    }

    public void setCentroSaludLejos_P9_1(String centroSaludLejos_P9_1) {
        this.centroSaludLejos_P9_1 = centroSaludLejos_P9_1;
    }
    @Column(name = "COSTO_TRANSPORTE_ELEVADO_P9_2", length = 1, nullable = true)
    public String getCostoTrasnporteElevado_P9_2() {
        return costoTrasnporteElevado_P9_2;
    }

    public void setCostoTrasnporteElevado_P9_2(String costoTrasnporteElevado_P9_2) {
        this.costoTrasnporteElevado_P9_2 = costoTrasnporteElevado_P9_2;
    }
    @Column(name = "TRABAJO_TIEMPO_P9_3", length = 1, nullable = true)
    public String geTrabajoTiempo_P9_3() {
        return trabajoTiempo_P9_3;
    }

    public void setTrabajoTiempo_P9_3(String trabajoTiempo_P9_3) {
        this.trabajoTiempo_P9_3 = trabajoTiempo_P9_3;
    }
    @Column(name = "NO_QUERIA_PASAR_CONSULTA_P9_4", length = 1, nullable = true)
    public String getNoQueriapasarConsulta_P9_4() {
        return noQueriapasarConsulta_P9_4;
    }

    public void setNoQueriapasarConsulta_P9_4(String noQueriapasarConsulta_P9_4) {
        this.noQueriapasarConsulta_P9_4 = noQueriapasarConsulta_P9_4;
    }
    @Column(name = "OTRAS_ESPECIFIQUE_P9_5", length = 100, nullable = true)
    public String getOtrasEspecifique_P9_5() {
        return otrasEspecifique_P9_5;
    }

    public void setOtrasEspecifique_P9_5(String otrasEspecifique_P9_5) {
        this.otrasEspecifique_P9_5 = otrasEspecifique_P9_5;
    }
    @Column(name = "RECOMENDARIA_AMIGO_FAMILIAR_P10", length = 1, nullable = true)
     public String getRecomendariaAmigoFamiliar_P10() {
        return recomendariaAmigoFamiliar_P10;
    }

    public void setRecomendariaAmigoFamiliar_P10(String recomendariaAmigoFamiliar_P10) {
        this.recomendariaAmigoFamiliar_P10 = recomendariaAmigoFamiliar_P10;
    }
    @Column(name = "ATENCION_CALIDAD_P10_1", length = 1, nullable = true)
    public String getAtencionCalidad_P10_1() {
        return atencionCalidad_P10_1;
    }

    public void setAtencionCalidad_P10_1(String atencionCalidad_P10_1) {
        this.atencionCalidad_P10_1 = atencionCalidad_P10_1;
    }
    @Column(name = "RESP_NECESIDADES_SALUD_OPORTUNDA_P10_1", length = 1, nullable = true)
    public String getRespNecesidadesSaludOportuna_P10_1() {
        return respNecesidadesSaludOportuna_P10_1;
    }

    public void setRespNecesidadesSaludOportuna_P10_1(String respNecesidadesSaludOportuna_P10_1) {
        this.respNecesidadesSaludOportuna_P10_1 = respNecesidadesSaludOportuna_P10_1;
    }
    @Column(name = "TIEMPO_ESPERA_CORTO_P10_1", length = 1, nullable = true)
    public String getTiempoEsperaCorto_P10_1() {
        return tiempoEsperaCorto_P10_1;
    }

    public void setTiempoEsperaCorto_P10_1(String tiempoEsperaCorto_P10_1) {
        this.tiempoEsperaCorto_P10_1 = tiempoEsperaCorto_P10_1;
    }
    @Column(name = "MEJOR_ATENCION_QUE_SEGURO_P10_1", length = 1, nullable = true)
    public String getMejorAtencionQueSeguro_P10_1() {
        return mejorAtencionQueSeguro_P10_1;
    }

    public void setMejorAtencionQueSeguro_P10_1(String mejorAtencionQueSeguro_P10_1) {
        this.mejorAtencionQueSeguro_P10_1 = mejorAtencionQueSeguro_P10_1;
    }
    @Column(name = "EXAMEN_LAB_NECESARIOS_P10_1", length = 1, nullable = true)
    public String getExamenLabNecesarios_P10_1() {
        return examenLabNecesarios_P10_1;
    }

    public void setExamenLabNecesarios_P10_1(String examenLabNecesarios_P10_1) {
        this.examenLabNecesarios_P10_1 = examenLabNecesarios_P10_1;
    }
    @Column(name = "POCOS_REQUISITOS_P10_1", length = 1, nullable = true)
    public String getPocosRequisitos_P10_1() {
        return pocosRequisitos_P10_1;
    }

    public void setPocosRequisitos_P10_1(String pocosRequisitos_P10_1) {
        this.pocosRequisitos_P10_1 = pocosRequisitos_P10_1;
    }
    @Column(name = "OTRAP_P10_1", length = 100, nullable = true)
    public String getOtraP_10_1() {
        return otraP_10_1;
    }

    public void setOtraP_10_1(String otraP_10_1) {
        this.otraP_10_1 = otraP_10_1;
    }
    @Column(name = "ATENCION_PERSONA_MALA_P10_2", length = 1, nullable = true)
    public String getAtencionPersonalMala_P10_2() {
        return atencionPersonalMala_P10_2;
    }

    public void setAtencionPersonalMala_P10_2(String atencionPersonalMala_P10_2) {
        this.atencionPersonalMala_P10_2 = atencionPersonalMala_P10_2;
    }
    @Column(name = "NO_DAN_RESP_NECESIDADES_P10_2", length = 1, nullable = true)
    public String getNoDanRespNecesidades_P10_2() {
            return noDanRespNecesidades_P10_2;
            }

            public void setNoDanRespNecesidades_P10_2(String noDanRespNecesidades_P10_2) {
        this.noDanRespNecesidades_P10_2 = noDanRespNecesidades_P10_2;
    }
    @Column(name = "TIEMPO_ESPERA_LARGO_P10_2", length = 1, nullable = true)
            public String getTiempoEsperaLargo_P10_2() {
            return tiempoEsperaLargo_P10_2;
            }

            public void setTiempoEsperaLargo_P10_2(String tiempoEsperaLargo_P10_2) {
        this.tiempoEsperaLargo_P10_2 = tiempoEsperaLargo_P10_2;
    }
    @Column(name = "MEJOR_ATENCION_OTRA_UNIDAD_SALUD_P10_2", length = 1, nullable = true)
            public String getMejorAtencionOtraUnidadSalud_P10_2() {
            return mejorAtencionOtraUnidadSalud_P10_2;
            }

            public void setMejorAtencionOtraUnidadSalud_P10_2(String mejorAtencionOtraUnidadSalud_P10_2) {
        this.mejorAtencionOtraUnidadSalud_P10_2 = mejorAtencionOtraUnidadSalud_P10_2;
    }
    @Column(name = "SOLICITA_DEMASIADA_MX_P10_2", length = 1, nullable = true)
            public String getSolicitaDemasiadaMx_P10_2() {
            return solicitaDemasiadaMx_P10_2;
            }

            public void setSolicitaDemasiadaMx_P10_2(String solicitaDemasiadaMx_P10_2) {
        this.solicitaDemasiadaMx_P10_2 = solicitaDemasiadaMx_P10_2;
    }
    @Column(name = "MUCHOS_REQUISITOS_P10_2", length = 1, nullable = true)
            public String getMuchosRequisitos_P10_2() {
            return muchosRequisitos_P10_2;
            }

            public void setMuchosRequisitos_P10_2(String muchosRequisitos_P10_2) {
        this.muchosRequisitos_P10_2 = muchosRequisitos_P10_2;
    }
    @Column(name = "NO_EXPLICAN_HACEN_MX_P10_2", length = 1, nullable = true)
            public String getNoExplicanHacenMx_P10_2() {
            return noExplicanHacenMx_P10_2;
            }

            public void setNoExplicanHacenMx_P10_2(String noExplicanHacenMx_P10_2) {
        this.noExplicanHacenMx_P10_2 = noExplicanHacenMx_P10_2;
    }
    @Column(name = "NO_CONFIANZA_P10_2", length = 1, nullable = true)
            public String getNoConfianza_P10_2() {
            return noConfianza_P10_2;
            }

            public void setNoConfianza_P10_2(String noConfianza_P10_2) {
        this.noConfianza_P10_2 = noConfianza_P10_2;
    }
    @Column(name = "OTRAP_P10_2", length = 100, nullable = true)
            public String getOtraP_10_2() {
            return otraP_10_2;
            }

            public void setOtraP_10_2(String otraP_10_2) {
        this.otraP_10_2 = otraP_10_2;
    }
    @Column(name = "COMPRENDE_PROCEDIMIENTOS_P11", length = 1, nullable = true)
            public String getComprendeProcedimientos_P11() {
            return comprendeProcedimientos_P11;
            }

            public void setComprendeProcedimientos_P11(String comprendeProcedimientos_P11) {
        this.comprendeProcedimientos_P11 = comprendeProcedimientos_P11;
    }
    @Column(name = "NO_COMODO_REALIZAR_PREG_P11_1", length = 1, nullable = true)
            public String getNoComodoRealizarPreg_P11_1() {
            return noComodoRealizarPreg_P11_1;
            }

            public void setNoComodoRealizarPreg_P11_1(String noComodoRealizarPreg_P11_1) {
        this.noComodoRealizarPreg_P11_1 = noComodoRealizarPreg_P11_1;
    }
    @Column(name = "NO_RESPONDIERON_PREG_P11_1", length = 1, nullable = true)
            public String getNoRespondieronPreg_P11_1() {
            return noRespondieronPreg_P11_1;
            }

            public void setNoRespondieronPreg_P11_1(String noRespondieronPreg_P11_1) {
        this.noRespondieronPreg_P11_1 = noRespondieronPreg_P11_1;
    }
    @Column(name = "EXPLICACION_RAPIDA_P11_1", length = 1, nullable = true)
            public String getExplicacionRapida_P11_1() {
            return explicacionRapida_P11_1;
            }

            public void setExplicacionRapida_P11_1(String explicacionRapida_P11_1) {
        this.explicacionRapida_P11_1 = explicacionRapida_P11_1;
    }
    @Column(name = "NO_DEJARON_HACER_PREG_P11_1", length = 1, nullable = true)
            public String getNoDejaronHacerPreg_P11_1() {
            return noDejaronHacerPreg_P11_1;
            }

            public void setNoDejaronHacerPreg_P11_1(String noDejaronHacerPreg_P11_1) {
        this.noDejaronHacerPreg_P11_1 = noDejaronHacerPreg_P11_1;
    }
    @Column(name = "OTRAP_P11_1", length = 100, nullable = true)
            public String getOtraP_11_1() {
            return otraP_11_1;
            }

            public void setOtraP_11_1(String otraP_11_1) {
        this.otraP_11_1 = otraP_11_1;
    }

    @Column(name = "IDENTIFICADOR_EQUIPO", nullable = false, length = 50)
    public String getIdentificadoEquipo() {
        return identificadoEquipo;
    }

    public void setIdentificadoEquipo(String identificadoEquipo) {
        this.identificadoEquipo = identificadoEquipo;
    }

    @Column(name = "estado", nullable = false, length = 15)
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Column(name = "PASIVO", nullable = false, length = 15)
    public char getPasivo() {
        return pasivo;
    }

    public void setPasivo(char pasivo) {
        this.pasivo = pasivo;
    }

    @Column(name = "FECHA_REGISTRO", nullable = false)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = "CREADO", length = 255)
    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    @Column(name = "USUARIO_REGISTRO", nullable = false)
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    @Column(name = "NOMBRE1TUTOR", length = 100)
    public String getNombre1Tutor() {
        return nombre1Tutor;
    }

    public void setNombre1Tutor(String nombre1Tutor) {
        this.nombre1Tutor = nombre1Tutor;
    }

    @Column(name = "NOMBRE2TUTOR", length = 100)
    public String getNombre2Tutor() {
        return nombre2Tutor;
    }

    public void setNombre2Tutor(String nombre2Tutor) {
        this.nombre2Tutor = nombre2Tutor;
    }

    @Column(name = "APELLIDO1TUTOR", length = 100)
    public String getApellido1Tutor() {
        return apellido1Tutor;
    }

    public void setApellido1Tutor(String apellido1Tutor) {
        this.apellido1Tutor = apellido1Tutor;
    }

    @Column(name = "APELLIDO2TUTOR", length = 100)
    public String getApellido2Tutor() {
        return apellido2Tutor;
    }

    public void setApellido2Tutor(String apellido2Tutor) {
        this.apellido2Tutor = apellido2Tutor;
    }

    @Column(name = "CODIGOCASA", nullable = true)
    public Integer getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(Integer codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    @Column(name = "ESTUDIO", length = 100)
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
}
