package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.Muestra;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 7/14/2021.
 * V1.0
 */
@Transactional
@Service("muestraService")
public class MuestraService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestras() throws Exception
    {
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra where pasive = '0' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/" + String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestrasTx() throws Exception {
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra mx where mx.pasive = '0' and recordDate >= :primerDia and mx.proposito = 'TX'");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/" + String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }
    

    public void saveOrUpdate(Muestra muestra){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(muestra);
    }

}
