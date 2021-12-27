package ni.org.ics.webapp.service;

import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Servicio para el objeto Participante
 * 
 * @author William Aviles
 * 
 **/

@Service("participanteProcesosService")
@Transactional
public class ParticipanteProcesosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

    /**
     * Regresa todos los Procesos de Participantes
     *
     * @return una lista de <code>ParticipanteProcesos</code>(s)
     */

    @SuppressWarnings("unchecked")
    public List<ParticipanteProcesos> getParticipantesProcesos() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM ParticipanteProcesos p");
        // Retrieve all
        return  query.list();
    }

    public void saveOrUpdateParticipanteProc(ParticipanteProcesos participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }

	/**
	 * Regresa un participante
	 * 
	 * @return un <code>Participante</code>
	 */

	public ParticipanteProcesos getParticipante(String codigo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ParticipanteProcesos par where par.codigo = :codigo");
        query.setParameter("codigo", codigo);
		ParticipanteProcesos participante = (ParticipanteProcesos) query.uniqueResult();
		return participante;
	}

}
