package ni.org.ics.webapp.service;

import ni.org.ics.webapp.dto.ComparacionMuestrasMA;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by miguel on 7/1/2022.
 */
@Service("comparacionMuestrasService")
@Transactional
public class ComparacionMuestrasService {

    private static final int ANIO_MUESTREO = 2022;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /***
     * Obtener datos de Tubos Rojos recepcionadas del supervisor que no tienen las estaciones
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroSupNoEstacionesHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_MX as codigo, DATE_FORMAT(t1.FECHA_RECEPCION, '%d-%m-%Y') as fechaRecepcion, t1.LUGAR as lugar, t1.VOLUMEN as volumen, " +
                        "t1.OBSERVACION as observacion, t1.USUARIO_REGISTRO as usuarioRegistro "+
                "from recepcion_muestras t1 left join muestras t2 on t1.CODIGO_MX = t2.CODIGO_ROJO and date(t1.FECHA_RECEPCION) = date(t2.FECHA_REGISTRO) "+
                "where ((date(t1.FECHA_RECEPCION) = :fechaRecepcion) and " +
                "(t2.CODIGO_ROJO is null or date(t1.FECHA_RECEPCION) <> date(t2.FECHA_REGISTRO) or t2.TOMO_ROJO = '0') " +
                "and (YEAR(t1.FECHA_RECEPCION) = :anio)) ");
        query.setParameter("fechaRecepcion", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }

    /***
     * Tubos Rojos del supervisor que no tiene el laboratorio
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroSupNoLaboHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_MX as codigo, DATE_FORMAT(t1.FECHA_RECEPCION, '%d-%m-%Y') as fechaRecepcion, t1.LUGAR as lugar, t1.VOLUMEN as volumen, " +
                "t1.OBSERVACION as observacion, t1.USUARIO_REGISTRO as usuarioRegistro " +
                "from recepcion_muestras t1 left join serologia_recepcion t2 on t1.CODIGO_MX = t2.CODIGO_PARTICIPANTE and date(t1.FECHA_RECEPCION) = date(t2.FECHA_TOMA) " +
                "where ((date(t1.FECHA_RECEPCION) = :fechaRecepcion) and " +
                "(t2.CODIGO_PARTICIPANTE is null or date(t1.FECHA_RECEPCION) <> date(t2.FECHA_TOMA)) " +
                "and (YEAR(t1.FECHA_RECEPCION) = :anio)) ");
        query.setParameter("fechaRecepcion", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }

    /***
     * Tubos Rojos de las estaciones que no tiene el supervisor
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroEstacionesNoSupHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_ROJO as codigo, DATE_FORMAT(t1.FECHA_MUESTRA, '%d-%m-%Y') as fechaMuestra, t1.NUM_PINCHAZOS as pinchazos, t1.USUARIO_REGISTRO as usuarioRegistro " +
                "from muestras t1 left join recepcion_muestras t2 on t1.CODIGO_ROJO = t2.CODIGO_MX and date(t1.FECHA_MUESTRA) = date(t2.FECHA_RECEPCION) " +
                "where ((date(t1.FECHA_MUESTRA)  = :fechaSero and t1.TOMO_ROJO ='1') " +
                "and (t2.CODIGO_MX Is Null or date(t1.FECHA_MUESTRA) <> date(t2.FECHA_RECEPCION)) " +
                "and (YEAR(t1.FECHA_MUESTRA) = :anio))");
        query.setParameter("fechaSero", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }

    /***
     * Tubos Rojos de las estaciones que no tiene el laboratorio
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroEstacionesNoLabHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_ROJO as codigo, DATE_FORMAT(t1.FECHA_MUESTRA, '%d-%m-%Y') as fechaMuestra, t1.NUM_PINCHAZOS as pinchazos, t1.USUARIO_REGISTRO as usuarioRegistro " +
                "from muestras as t1 left join serologia_recepcion as t2 on t1.CODIGO_ROJO = t2.CODIGO_PARTICIPANTE and date(t1.FECHA_MUESTRA) = date(t2.FECHA_TOMA) " +
                "where ((date(t1.FECHA_MUESTRA) = :fechaSero and " +
                "t1.TOMO_ROJO = '1') and (t2.CODIGO_PARTICIPANTE Is Null or date(t1.FECHA_MUESTRA) <> date(t2.FECHA_TOMA)) " +
                "and (YEAR(t1.FECHA_MUESTRA) = :anio))");
        query.setParameter("fechaSero", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }

    /***
     * Tubos Rojos de laboratorio que no tiene el supervisor
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroLabNoSupHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_PARTICIPANTE as codigo, DATE_FORMAT(t1.FECHA_TOMA, '%d-%m-%Y') as fechaRecepcion, t1.VOLUMEN as volumen, t1.OBSERVACION as observacion, t1.USUARIO_REGISTRO as usuarioRegistro " +
                "from serologia_recepcion as t1 left join recepcion_muestras as t2 on t1.CODIGO_PARTICIPANTE = t2.CODIGO_MX and date(t1.FECHA_TOMA) = date(t2.FECHA_RECEPCION) " +
                "where ((date(t1.FECHA_TOMA) = :fechaRecSero) and " +
                "(t2.CODIGO_MX Is Null or date(t1.FECHA_TOMA) <> date(t2.FECHA_RECEPCION)) " +
                "and (YEAR(t1.FECHA_TOMA) = :anio))");
        query.setParameter("fechaRecSero", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }

    /***
     * Tubos Rojos de laboratorio que no tienen las estaciones
     * @return List<ComparacionMuestrasMA>
     */
    public List<ComparacionMuestrasMA> getCompSeroLabNoEstHoy() {
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = DateUtil.getDateWithoutTime();
        Query query = session.createSQLQuery("select t1.CODIGO_PARTICIPANTE as codigo, DATE_FORMAT(t1.FECHA_REGISTRO, '%d-%m-%Y') as fechaRecepcion, t1.VOLUMEN as volumen, t1.OBSERVACION as observacion, t1.USUARIO_REGISTRO as usuarioRegistro " +
                "from serologia_recepcion as t1 left join muestras as t2 on t1.CODIGO_PARTICIPANTE = t2.CODIGO_ROJO and date(t1.FECHA_TOMA) = date(t2.FECHA_MUESTRA) " +
                "where ((date(t1.FECHA_TOMA) = :fechaRecSero) and  " +
                "(t2.CODIGO_ROJO Is Null or date(t1.FECHA_TOMA) <> date(t2.FECHA_MUESTRA) or t2.TOMO_ROJO = '0') " +
        "and (YEAR(t1.FECHA_TOMA) = :anio))");
        query.setParameter("fechaRecSero", timeStamp);
        query.setParameter("anio", ANIO_MUESTREO);

        query.setResultTransformer(Transformers.aliasToBean(ComparacionMuestrasMA.class));
        return query.list();
    }
}
