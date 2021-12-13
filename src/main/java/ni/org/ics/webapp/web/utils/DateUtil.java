package ni.org.ics.webapp.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Miguel Salinas on 7/3/2017.
 * V1.0
 */
public final class DateUtil {

    /**
    * Convierte un string a Date segun el formato indicado
    * @param strFecha cadena a convertir
    * @param formato formato solicitado
    * @return Fecha
    * @throws java.text.ParseException
    */
    public static Date StringToDate(String strFecha, String formato) throws ParseException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
            return simpleDateFormat.parse(strFecha);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * Convierte una Date a String, segun el formato indicado
     * @param dtFecha Fecha a convertir
     * @param format formato solicitado
     * @return String
     */
    public static String DateToString(Date dtFecha, String format)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(dtFecha!=null)
            return simpleDateFormat.format(dtFecha);
        else
            return null;
    }

    /* - inicio de la function fecha */
    public static String obtenerEdad(Date fechan) {
        Calendar today = Calendar.getInstance();
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        fechaNacimiento.setTime(fechan);
        int age = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int month = (age)*12 + today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);

        if(today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)){
            month = month - 1;
        }

        if(month == 0) {
            Long tDias = (today.getTimeInMillis() - fechaNacimiento.getTimeInMillis())  / (1000 * 60 * 60 * 24);
            return new StringBuffer().append(tDias).append(" dias").toString();
        }
        else if(age == 0) {
            age = today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            if(age == 0) {
                age = today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                return new StringBuffer().append(age).append(" dias").toString();
            }else {
                int diaFechaActual = today.get(Calendar.DAY_OF_MONTH);
                int diaFechaNac = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                if (diaFechaActual < diaFechaNac) {
                    age = age - 1;
                    return new StringBuffer().append(age).append(" meses").toString();
                } else {
                    return new StringBuffer().append(age).append(" meses").toString();
                }

            }
        } else if (month > 0 && month < 12) {
            return new StringBuffer().append(month).append(" meses").toString();

        }else {
            if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
            return new StringBuffer().append(age).append(" años").toString();
        }
       /* Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        if (age == 0) {
            age = (today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH));

            if (age == 0) {
                age = (today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH));
                return new StringBuffer().append(age).append(" dias").toString();
            } else {
                return new StringBuffer().append(age).append(" meses").toString();
            }
        } else {
            if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
            return new StringBuffer().append(age).append(" años").toString();
        }*/
    }

    /* ** fin de la funcion fecha*/


    public static String onTimeSet(String hora) {

        String partes[] = hora.replaceAll(" ",":").split(":");
        int hour = Integer.valueOf(partes[0]);
        int minute = Integer.valueOf(partes[1]);
        String ampm = partes[2];

        Calendar mCalen = Calendar.getInstance();
        mCalen.set(Calendar.HOUR, hour);
        mCalen.set(Calendar.MINUTE, minute);
        if (ampm.equalsIgnoreCase("PM")) {
            mCalen.set(Calendar.AM_PM, Calendar.PM);
        } else {
            mCalen.set(Calendar.AM_PM, Calendar.AM);
        }

        int hourOfDay_local = mCalen.get(Calendar.HOUR_OF_DAY);
        int minute_local = mCalen.get(Calendar.MINUTE);

        return hourOfDay_local+":"+ (minute_local<10?"0":"")+minute_local+":00";

    }
}
