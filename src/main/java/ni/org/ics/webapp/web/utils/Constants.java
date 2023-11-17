package ni.org.ics.webapp.web.utils;

/**
 * Created by ICS_Inspiron3 on 26/08/2019.
 */
public class Constants {
    public static final String TPR_REPORTECARTA  = "REPORTECARTA";
    public static final String TPR_ENVIOREPORTE  = "ENVIOREPORTE";
    public static final String TPR_ENVIOREPORTEBHC  = "ENVIOREPORTEBHC";
    public static final String TPR_REPORTERETIRO = "RETIROREPORTE";
    public static final String TPR_DATOSGENERALES = "DATOSGENERALES";
    public static final String TPR_ENVIO_ENFERMO  = "ENVIO_ENFERMO";
    public static final String TPR_CONVALECIENTES_ENFERMO  = "CONVALECIENTES_ENFERMO";
    public static final String TPR_CONTROL_ASISTENCIA  = "CONTROL_ASISTENCIA";

    public static final String TIPO_TUBO_SEROLOGIA = "R";

    public static final String STRING_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String STRING_FORMAT_DD_MM_YYYY_HH24 = "dd/MM/yyyy HH:mm:ss";

    public static final String NO_DATA = "ND";

    /**
     * (Sufijo del estudio).(Código del participane).(Rojo ó PBMC)(AñoTomaMuestra).(Evento de enfermedad).(Aguda ó Convaleciente)
     * (A2).(0001).(R ó P)(21).(A,B,C,D,etc).(1 ó 2)     *
     **/
    public static final String CODIGO_MX_FORMAT = "A2.%s.%s%s.%s.%s"; //A2.0001.R22.A.1

    public static final String CODIGO_BARRA_FORMAT = "%s%s %s"; //16/12/202117/12/2021 A2.0001.R21.A.1
    public static final String CODIGO_BARRA_FORMAT_PRINT = "%s*1*1"; //16/12/202117/12/2021 *A2.0001.R21.A.1*1*1
    public static final String CODIGO_BARRA_FORMAT_PRINTB = "100%s*1*2"; //16/12/202117/12/2021 *A2.0001.R21.A.1*1*1

    /*Entomologia*/

    public static final String TPR_ENTO = "ENTOMOLOGIA";
    public static final String[] ENTO_COLUMNAS_TBL_CUEST_HOGAR = new String[]{"fecha_cuestionario","codigo_barrio","direccion","latitud","longitud","tipo_ingreso_codigo","codigo_vivienda","tipo_vivienda", "hay_ambiente_peri","hora_captura_peri","porcentaje_humedad_peri","temperatura_peri","tipo_ingreso_cod_peri","codigo_peri","hay_ambiente_intra","hora_captura_intra","porcentaje_humedad_intra","temperatura_intra","tipo_ingreso_cod_intra","codigo_intra",
          "quien_contesta", "quien_contesta_otro", "quien_contesta_edad", "quien_contesta_escolaridad", "tiempo_vivir_barrio", "cuantas_personas_viven", "edades_femenino", "edades_masculino", "usaron_mosquitero", "quienes_usaron_mosquitero", "usaron_repelente", "quienes_usaron_repelente", "conoce_larvas", "alguien_vis_elim_larvas", "quien_vis_elim_larvas", "quien_vis_elim_larvas_otro", "alguien_dedica_elim_larvas", "quien_dedica_elim_larvas", "tiempo_eliminan_criaderos", "hay_bastante_zancudos", "falta_evitar_zancudos", "falta_evitar_zancudos_otros", "gastaron_dinero_productos", "que_productos_compraron", "que_productos_compraron_otros", "cuanto_gastaron_productos", "ultima_vez_minsa_bti", "ultima_vez_minsa_fumigo", "riesgo_enfermar_dengue_casa", "hay_problema_agua", "cada_cuanto_va_agua", "cada_cuanto_va_agua_otro", "horas_sin_agua", "que_hacen_basura", "que_hacen_basura_otro", "riesgo_enfermar_dengue_barrio", "acciones_barrio_elim_zancudos", "que_acciones", "que_acciones_otro", "alguien_participo_acciones", "quien_participo_acciones", "mayor_criadero_barrio",
            "material_paredes", "otro_material_paredes","material_piso", "otro_material_piso","material_techo", "otro_material_techo","usuario_registro", "fecha_registro","codigo_cuestionario"
    };
    public static final String[] ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB = new String[]{"codigo_casa", "codificado", "edad", "sexo", "usuario_registro", "fecha_registro", "codigo_cuestionario", "codigo_poblacion"};
    public static final String[] ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE = new String[]{"fecha_cuestionario","codigo_barrio","nombre_punto_clave","direccion_punto_clave","tipo_punto_clave","tipo_punto_clave_prod","tipo_punto_clave_prod_otro","tipo_punto_clave_aglo","tipo_punto_clave_aglo_otro","cuantas_personas_reunen","cuantos_dias_sem_reunen","hora_inicio_reunion","hora_fin_reunion","punto_gps","latitud","longitud","tipo_ingreso_cod_sitio","codigo_sitio","hay_ambiente_peri","hora_captura_peri","porcentaje_humedad_peri","temperatura_peri","tipo_ingreso_cod_peri","codigo_peri","hay_ambiente_intra","hora_captura_intra","porcentaje_humedad_intra","temperatura_intra","tipo_ingreso_cod_intra","codigo_intra","nombre_contesta_cuestionario","usuario_registro", "fecha_registro","codigo_cuestionario"};

}
