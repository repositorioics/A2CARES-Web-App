package ni.org.ics.webapp.web.utils;

/**
 * Created by ICS_Inspiron3 on 26/08/2019.
 */
public class Constants {
    public static final String TPR_REPORTECARTA  = "REPORTECARTA";
    public static final String TPR_ENVIOREPORTE  = "ENVIOREPORTE";
    public static final String TPR_REPORTERETIRO = "RETIROREPORTE";
    public static final String TPR_DATOSGENERALES = "DATOSGENERALES";
    public static final String TPR_ENVIO_ENFERMO  = "ENVIO_ENFERMO";

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
}
