package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.laboratorio.RecepcionEnfermo;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by miguel on 29/1/2022.
 */
@Transactional
@Service("recepcionEnfermoService")
public class RecepcionEnfermoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<RecepcionEnfermo> getRecepcionsEnfermos() throws Exception
    {
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionEnfermo where pasive = '0' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/" + String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }

    public void saveOrUpdateRecepcionEnfermo(RecepcionEnfermo Recepcion){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Recepcion);
    }

}
