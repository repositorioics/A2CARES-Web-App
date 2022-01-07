package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.puntos.PuntoCandidato;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 29/12/2021.
 */
@Service("puntoCandidatoService")
@Transactional
public class PuntoCandidatoService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<PuntoCandidato> getPuntosCandidatos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PuntoCandidato where pasive = '0'");
        return query.list();
    }

    public void saveOrUpdatePunto(PuntoCandidato puntoCandidato){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(puntoCandidato);
    }
}
