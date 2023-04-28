package ni.org.ics.webapp.service;
import ni.org.ics.webapp.domain.survey.EncuestaSatisfaccionUsuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Ing. Santiago Carballo on 11/04/2023.
 */

@Service("encuestaSatisfaccionUsuarioService")
@Transactional
public class EncuestaSatisfaccionUsuarioService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdateEncuestaSatisfaccionUsuario(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaSatisfaccionUsuario);
    }
}