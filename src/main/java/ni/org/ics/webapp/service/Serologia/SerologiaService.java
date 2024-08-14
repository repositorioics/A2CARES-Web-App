package ni.org.ics.webapp.service.Serologia;

import ni.org.ics.webapp.domain.Serologia.Serologia;
import ni.org.ics.webapp.domain.Serologia.SerologiaEnvio;
import ni.org.ics.webapp.domain.Serologia.Serologia_Detalles_Envio;

import ni.org.ics.webapp.domain.Serologia.Bhc;
import ni.org.ics.webapp.domain.Serologia.BhcEnvio;
import ni.org.ics.webapp.domain.Serologia.Bhc_Detalles_Envio;

import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.dto.ParticipanteSeroDto;
import ni.org.ics.webapp.dto.SerologiaDto;
import ni.org.ics.webapp.dto.BhcDto;
import ni.org.ics.webapp.dto.BhcDto1;
import ni.org.ics.webapp.service.UsuarioService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Transformer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ICS on 15/10/2020.
 */

@Service("SerologiaService")
@Transactional
public class SerologiaService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name="usuarioService")
    private UsuarioService usuarioService;


    @SuppressWarnings("unchecked")
    public Participante getParticipante(Integer parametro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Participante where codigo =:parametro");
            query.setParameter("parametro", parametro);
            return (Participante) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

     @SuppressWarnings("unchecked")
    public List<Participante> getAllParticipantes() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Participante p order by p.codigo asc ");
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }
    // todo **  Consulta para llenar el reporte **
    public List<Serologia_Detalles_Envio>getAllSerologia(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia_Detalles_Envio se where se.serologiaEnvio.fecha between :fechaInicio and :fechaFin and se.serologiaEnvio.idenvio =:nEnvios  order by se.serologia.participante asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }
    public List<Bhc_Detalles_Envio>getAllBhc(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc_Detalles_Envio se where se.bhcEnvio.fecha between :fechaInicio and :fechaFin and se.bhcEnvio.idenvio =:nEnvios  order by se.bhc.participante asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }
    @SuppressWarnings("unchecked")
    public List<Personal> getListPersonal() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Personal p where p.pasive ='0' ");
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    //region Verificar si existe registro Guardado.
    public boolean ExisteSerologia(Date fecha, String codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Serologia s where s.fecha =:fecha and s.participante =:codigo");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    //endregion

    //region Verificar si existe registro Guardado.
    public boolean ExisteBhc(Date fecha, String codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Bhc s where s.fecha =:fecha and s.participante =:codigo");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    //endregion

    //region Métodos para Guardar Serologia
    @SuppressWarnings("unchecked")
    public void saveSerologia(Serologia obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }
    //region Métodos para Guardar Bhc
    @SuppressWarnings("unchecked")
    public void saveBhc(Bhc obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public void save_Envio_Serologia(SerologiaEnvio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public void save_Envio_Bhc(BhcEnvio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public void save_Detalles_Serologia_Envio(Serologia_Detalles_Envio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public void save_Detalles_Bhc_Envio(Bhc_Detalles_Envio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }



    //endregion

    public Serologia getSerologiaById(String idSerologia) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = Integer.parseInt(idSerologia);
        Query query = session.createQuery("FROM Serologia s where s.idSerologia=:id");
        query.setParameter("id",id);
        Serologia sero = (Serologia) query.uniqueResult();
        return sero;
    }

    public Bhc getBhcById(String idBhc) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = Integer.parseInt(idBhc);
        Query query = session.createQuery("FROM Bhc s where s.idbhc=:id");
        query.setParameter("id",id);
        Bhc sero = (Bhc) query.uniqueResult();
        return sero;
    }

    public List<Serologia>SerologiaList()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.pasive='0' order by s.idSerologia asc");
        return query.list();
    }
    public List<Bhc>BhcList()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc s where s.pasive='0' order by s.idSerologia asc");
        return query.list();
    }

    //Llena la DataTables con Serologias no enviadas
    public List<Serologia>SerologiaNoEnviada()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.enviado ='0' and s.pasive='0' order by s.recordDate asc");
        return query.list();
    }

    @SuppressWarnings("unchecked")
      public List<SerologiaDto> SerologiaNoEnviadaDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend ='0';
        Query query = session.createQuery("select s.idSerologia as idSerologia, s.enviado as enviado, p.codigo as participante, DATE_FORMAT(s.fecha, '%d/%m/%Y') as fecha, s.volumen as volumen, coalesce(s.observacion, '') as observacion, coalesce(s.descripcion, '') as descripcion, " +
                " s.codigo_casa as codigoCasa from Serologia s, Participante p where s.participante = p.codigo and s.enviado =:noSend and s.pasive = '0' order by p.codigo asc");
        query.setParameter("noSend", noSend);
        query.setResultTransformer(Transformers.aliasToBean(SerologiaDto.class));
        return query.list();
    }
    @SuppressWarnings("unchecked")
    public List<BhcDto> BhcNoEnviadaDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend ='0';
        Query query = session.createQuery("select s.idbhc as idbhc, s.enviado as enviado, p.codigo as participante, DATE_FORMAT(s.fecha, '%d/%m/%Y') as fecha, s.volumen as volumen, coalesce(s.observacion, '') as observacion, coalesce(s.descripcion, '') as descripcion, " +
                "0 as numEnvio,0 as edadMeses,0 as codigoCasa, '' as procesaCSFV,'' as usuarioRegistro,'' as estudio,'' as puesto from Bhc s, Participante p where s.participante = p.codigo and s.enviado =:noSend and s.pasive = '0' order by p.codigo asc");
        query.setParameter("noSend", noSend);
        query.setResultTransformer(Transformers.aliasToBean(BhcDto.class));
        return query.list();
    }
    @SuppressWarnings("unchecked")
    public List<BhcDto1> BhcNoEnviadaDto1() throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("call  sp_extrae_bhc_escaneadas()");
            query.setResultTransformer(Transformers.aliasToBean(BhcDto1.class));
            return  query.list() ;
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    //Actualiza el campo cerrado
    public  void ModificarEnvio(Integer idSerologia){
        Session session = sessionFactory.getCurrentSession();
        Character enviado ='1';
        Query query = session.createQuery("update Serologia s set s.enviado =:enviado where s.idSerologia =:idSerologia");
        query.setParameter("enviado",enviado);
        query.setParameter("idSerologia", idSerologia);
        query.executeUpdate();
    }

    //Actualiza el campo cerrado
    public  void ModificarEnvioBhc(Integer idBhc){
        Session session = sessionFactory.getCurrentSession();
        Character enviado ='1';
        Query query = session.createQuery("update Bhc s set s.enviado =:enviado where s.idbhc =:idBhc");
        query.setParameter("enviado",enviado);
        query.setParameter("idBhc", idBhc);
        query.executeUpdate();
    }

    public  void ModificarAllEnvios(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '1';
        Query query = session.createQuery("update Serologia c set c.enviado=:enviado where c.fecha between :fechaInicio and :fechaFin");
        query.setParameter("enviado",enviado);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        //and c.idSerologia= :idSerologia query.setParameter("idSerologia", idSerologia);
        query.executeUpdate();
    }

    public  void ModificarAllEnviosBhc(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '1';
        Query query = session.createQuery("update Bhc c set c.enviado=:enviado where c.fecha between :fechaInicio and :fechaFin");
        query.setParameter("enviado",enviado);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        //and c.idSerologia= :idSerologia query.setParameter("idSerologia", idSerologia);
        query.executeUpdate();
    }

    public List<Serologia>ObtenerSerologiasEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.fecha between :fechaInicio and :fechaFin and s.pasive='0' and s.enviado='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

    public List<Bhc>ObtenerBhcEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc s where s.fecha between :fechaInicio and :fechaFin and s.pasive='0' and s.enviado='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

//region Catalogos
    /*
    public List<Envio>getAllEnvios(){
        Session session = sessionFactory.getCurrentSession();
        Character pasivoNo = '0';
        Query query = session.createQuery("from  Envio e where e.pasive= :pasivoNo");
        query.setParameter("pasivoNo",pasivoNo);
        return query.list();
    }*/

//endregion

    @SuppressWarnings("unchecked")
    public Personal getPersonal() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Personal p where p.pasive='0' ");
            return (Personal) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

    public List<SerologiaEnvio> getAllSeroEnviadas()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from SerologiaEnvio");
            return query.list();
        }catch (Exception ex){
            throw ex;
        }
    }

    public ParticipanteSeroDto getDatosParticipanteById(String parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as idparticipante, concat(p.nombre1,' ', p.nombre2,' ', p.apellido1,' ', p.apellido2) as nombreCompleto, p.casa.codigo as codigo_casa, pp.retirado as estado, p.fechaNac as fechaNacimiento " +
                " from Participante p, ParticipanteProcesos pp where pp.codigo=p.codigo and p.codigo=:parametro");
        query.setParameter("parametro", parametro);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteSeroDto.class));
        return (ParticipanteSeroDto)query.uniqueResult();
    }

    public ParticipanteProcesos getParticipanteprocesos(String parametro){
        Session session = sessionFactory.getCurrentSession();
        Query  query = session.createQuery("from ParticipanteProcesos pp where pp.codigo=:parametro");
        query.setParameter("parametro",parametro);
        return (ParticipanteProcesos) query.uniqueResult();
    }

 //10401 = 8 no 12


    public List<SerologiaEnvio> getSerologiaEnvioByDates(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SerologiaEnvio se where se.fecha between :fechaInicio and :fechaFin and se.idenvio =:nEnvios");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }
    public List<BhcEnvio> getBhcEnvioByDates(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from BhcEnvio se where se.fecha between :fechaInicio and :fechaFin and se.idenvio =:nEnvios");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }
}
