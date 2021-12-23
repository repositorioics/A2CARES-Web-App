package ni.org.ics.webapp.service.scancarta;

import ni.org.ics.webapp.domain.catalogs.Parte;
import ni.org.ics.webapp.domain.catalogs.Version;
import ni.org.ics.webapp.domain.core.Estudio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.scancarta.*;
import ni.org.ics.webapp.dto.ParticipantesCodigo;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.cert.Extension;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 6/27/2017.
 * V1.0
 */
@Transactional
@Service("scanCartaService")
public class ScanCartaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    //region Seccion de Catalogo Carta

    public Participante getParticipante(Integer codigo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Participante par where par.codigo = "+codigo);
        Participante participante = (Participante) query.uniqueResult();
        return participante;
    }


    public List<Extension> getExtensionVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.version.id=:idversion");
        query.setParameter("idversion",idversion);
        return query.list();
    }

    public List<Extensiones> getAllExtension(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.pasive='0' order by e.extension asc");
        return query.list();
    }

    //Obtengo la lista de la tabla ExtensionesTmp by nameUser
    public List<ExtensionesTmp> getListExtensionTmp(){
        Session session = sessionFactory.getCurrentSession();
        String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = session.createQuery("from ExtensionesTmp e where e.pasive='0' and e.recordUser=:nameUser order by e.fechaExtension desc ");
        query.setParameter("nameUser",nameUser);
        return query.list();
    }

    public List<Estudio>getEstudios(){
        Session session = sessionFactory.getCurrentSession();
        Character verdadera = '1';
        Query query = session.createQuery("from Estudio e order by e.id asc ");
        return query.list();
    }

    //endregion

//region    Version
    public void saveScanVersion(Version version) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(version);
    }

    public List<Version> getScanVersion(){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Version order by version");
        return query.list();
    }

    public List<Version>getVersionActiva(){
        Session session = sessionFactory.getCurrentSession();
        boolean verdadero = true;
        Query query = session.createQuery("from Version v where v.activo= :verdadero order by version");
        query.setParameter("verdadero",verdadero);
        return query.list();
    }

    public Version getVersionById(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query= session.createQuery("from Version v where v.idversion = :idversion");
        query.setParameter("idversion", idversion);
        return (Version) query.uniqueResult();
    }


    public List<Version> getVersionByIdestudio(Integer idestudio){
        Session session = sessionFactory.getCurrentSession();
        boolean verdadero = true;
        Query query = session.createQuery("from Version v where v.estudio.codigo =:idestudio and v.activo=:verdadero");
        query.setParameter("idestudio",idestudio);
        query.setParameter("verdadero", verdadero);
        return query.list();
    }

    public void DeshabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean f = false;
        Query query = session.createQuery("update Version v set v.activo= :f where v.idversion= :idversion");
        query.setParameter("f",f);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public void HabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean v = true;
        Query query = session.createQuery("update Version v set v.activo= :v where v.idversion= :idversion");
        query.setParameter("v",v);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public boolean checkExistVersion(String version, Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Version v where v.version= :version and v.estudio.codigo=:idcarta");
        query.setParameter("version",version);
        query.setParameter("idcarta",idcarta);
        return  query.list().size()>0;
    }



    // todo HABILITAR / DESAHABILITAR CATALOGO EXTENSION


    public void DeshabilitarExtension(Integer idextension){
        Session session = sessionFactory.getCurrentSession();
        boolean f = false;
        Query query = session.createQuery("update Extensiones e set e.activo= :f where e.id= :idextension");
        query.setParameter("f",f);
        query.setParameter("idextension", idextension);
        query.executeUpdate();
    }

    public void HabilitarExtension(Integer idextension){
        Session session = sessionFactory.getCurrentSession();
        boolean v = true;
        Query query = session.createQuery("update Extensiones e set e.activo= :v where e.id= :idextension");
        query.setParameter("v",v);
        query.setParameter("idextension", idextension);
        query.executeUpdate();
    }

//endregion

    //region Catalogo Partes - ScanCarta
    public void SaveParte(Parte parte){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(parte);
    }

    public boolean CheckequalsParte(String nombreParte, Integer idversion) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte p where p.parte= :nombreParte and p.version.idversion= :idversion" );
        query.setParameter("nombreParte",nombreParte);
        query.setParameter("idversion",idversion);
        return query.list().size() > 0;
    }

    public boolean CheckequalsExtension(String nombreExtension, Integer idversion) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.extension= :nombreExtension and e.version.idversion= :idversion" );
        query.setParameter("nombreExtension",nombreExtension);
        query.setParameter("idversion",idversion);
        return query.list().size() > 0;
    }

    public List<Parte>getListParte(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte order by parte desc");
        return query.list();
    }

    public Parte getParteById(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where idparte= :idparte");
        query.setParameter("idparte",idparte);
        return (Parte) query.uniqueResult();
    }
    public List<Parte> getParteByVersionId(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where version.idversion= :idversion");
        query.setParameter("idversion",idversion);
        return  query.list();
    }

    public void DesHabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        boolean f = false;
        Query query = session.createQuery("update Parte p set p.activo= :f where p.idparte= :idparte");
        query.setParameter("f",f);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }

    public  void HabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        boolean t = true;
        Query query = session.createQuery("update Parte p set p.activo= :t where p.idparte= :idparte");
        query.setParameter("t",t);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }
    //endregion

    /* OBTENER VERSION POR CARTA  */
    public List<Version> getVersioCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        boolean t = true;
        Query query = session.createQuery("from Version v where v.estudio.codigo= :idcarta and v.activo= :t");
        query.setParameter("idcarta",idcarta);
        query.setParameter("t",t);
        return query.list();
    }

    public List<Parte>getParte(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("from Parte p where p.version.id = :idversion");
        query.setParameter("idversion",idversion);
        //query.setParameter("t",t);
        return query.list();

    }
    public List<Parte>getParteList(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte order by parte asc");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public ParticipanteCarta getCartasParticipante(Integer parametro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from ParticipanteCarta p where p.idparticipantecarta =:parametro");
            query.setParameter("parametro", parametro);
            return (ParticipanteCarta) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public boolean SiExisteParticipanteCarta(Integer idversion, Integer idparticipante, String fechaCarta)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date fecha = DateUtil.StringToDate(fechaCarta, "dd/MM/yyyy");
            System.out.println("fecha: "+fecha);
            Query query =session.createQuery("from ParticipanteCarta pc where pc.fechacarta =:fecha and version.idversion =:idversion and participante.codigo =:idparticipante");
            query.setParameter("idversion",idversion);
            query.setParameter("idparticipante",idparticipante);
            query.setParameter("fecha",fecha);
            return  query.list().size()>0;
        }
        catch (Exception e){
            throw e;
        }
    }

    // Toma los tecnicos de campos que realizan
    public List<Personal>getPersonal(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.pasive='0'");
        return query.list();
    }

    public List<ParticipanteCarta> getScanCartasByParticipante(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCarta pc where pc.participante.codigo=:parametro");
        query.setParameter("parametro",parametro);
        return query.list();
    }

    //Metodo para obtener cartaparticipante by idparticipanteCarta
    public ParticipanteCarta getScanCartasByIdParticipanteCarta(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCarta pc where pc.idparticipantecarta = :parametro");
        query.setParameter("parametro",parametro);
        return (ParticipanteCarta) query.uniqueResult();
    }

    //Verifico si la Version tiene Extension con pasive=0;
    public boolean tieneExtensionByVersion(Integer idVersion)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Extensiones e where e.version.id=:idVersion and e.pasive='0'");
            query.setParameter("idVersion", idVersion);
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public List<Parte> getParteParticipante(Integer idparticipantecarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte  where participantecarta.idparticipantecarta = :idparticipantecarta");
        query.setParameter("idparticipantecarta",idparticipantecarta);
        return  query.list();
    }

    public List<DetalleParte>getDetalleParteList(Integer idParticipanteCarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte where participantecarta.idparticipantecarta = :idParticipanteCarta");
        query.setParameter("idParticipanteCarta",idParticipanteCarta);
        return  query.list();
    }
    //OBTENER Extension por idParticipantExtension para editar
    public ParticipanteExtension getByIDDetalleParte(Integer idParticipantExtension){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension d where d.idParticipantExtension= :idParticipantExtension");
        query.setParameter("idParticipantExtension",idParticipantExtension);
        return (ParticipanteExtension) query.uniqueResult();
    }

    //region todo Metodo para anular CARTA, PARTE y EXTENSION de un Participante
    public boolean updateAnular(Integer idParticipanteCarta, String pqAnulada)throws Exception{
        try{
        Session session = sessionFactory.getCurrentSession();
        boolean Si = true;
        Query query = session.createQuery("update ParticipanteCarta pc set pc.anulada= :Si , pc.pq_anulada = :pqAnulada where idparticipantecarta = :idParticipanteCarta");
        query.setParameter("Si",Si);
        query.setParameter("pqAnulada", pqAnulada);
        query.setParameter("idParticipanteCarta", idParticipanteCarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }
    }
    // Anular la Partes de una Carta
    public boolean UpdateParteAnulada(Integer idparticipantecarta){
        try{
            Session session = sessionFactory.getCurrentSession();
            boolean si = true;
            Query query = session.createQuery("update DetalleParte dp set dp.anulada=:si where dp.participantecarta.idparticipantecarta=:idparticipantecarta");
            query.setParameter("si",si);
            query.setParameter("idparticipantecarta",idparticipantecarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }
    }
    // Anular la Extension de una Carta
    public boolean UpdateExtensionAnulada(Integer idPartcipanteCarta){
        try {
            Session session = sessionFactory.getCurrentSession();
            boolean si = true;
            Query query = session.createQuery("update ParticipanteExtension pe set pe.anulada=:si  where pe.participantecarta.idparticipantecarta=:idPartcipanteCarta");
            query.setParameter("si",si);
            query.setParameter("idPartcipanteCarta",idPartcipanteCarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }

    }
    //endregion
    //Metodo para eliminar partes
    public void DeleteParteParticipante(Integer idparticipantecarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE from DetalleParte where participantecarta.idparticipantecarta= :idparticipantecarta");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Estudio> getAllEstudios()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Estudio e order by e.codigo");
            return  (List<Estudio>) query.list();
        }catch (Exception e){
            return null;
        }
    }

    //METODO PARA OBTENER LA LISTA DE LOS DETALLES
    @SuppressWarnings("unchecked")
    public List<DetalleParte>getDetalleByIdParticipanteCarta(Integer idparticipantecarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte dp  where dp.participantecarta.id=:idparticipantecarta order by dp.iddetalle asc");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        return query.list();
    }

    // obtener las Extensiones por idVersion
    public List<Extensiones>getExtension(Integer idversion)throws Exception {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Extensiones e where e.version.idversion=:idversion and e.isActive=true ");
            query.setParameter("idversion", idversion);
            return query.list();
        }catch (Exception e){
            return null;
        }
    }
    // Metodo para la poblar la tabla en Extension.jsp
     public List<Extensiones>getExtensionsByVersion(Integer idversion)throws Exception {
      try{
          Session session = sessionFactory.getCurrentSession();
          Query query = session.createQuery("from Extensiones e where e.version.idversion=:idversion and e.pasive='0'");
          query.setParameter("idversion", idversion);
          return query.list();
          }catch (Exception e){
               return null;
           }
     }

    public void saveOrUpdateScanCarta(ParticipanteCarta scanCarta) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(scanCarta);
    }

    public void saveParteCarta(DetalleParte detalle){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detalle);
    }

    public void saveORupdateExtension(Extensiones extensiones){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(extensiones);
    }



    public Extensiones getExtensionById(Integer idextension){
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.id=:idextension ");
        query.setParameter("idextension",idextension);
        return (Extensiones)query.uniqueResult();
    }

    public boolean ActualizarAcepta(Integer idDetalle, boolean newacepta)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("update DetalleParte dp set dp.acepta= :newacepta where dp.iddetalle= :idDetalle");
            query.setParameter("idDetalle",idDetalle);
            query.setParameter("newacepta", newacepta);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
}
    //region    PARTICIPANTE EXTENSION OFICIAL
    public void saveParticpanteExtension(ParticipanteExtension obj)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }


    // , Integer idVersion, Integer idParticipante
    public boolean VerificaExtension(String dinicial, String dfinal, Integer idExtension)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date finicial = DateUtil.StringToDate(dinicial, "dd/MM/yyyy");
            Date ffinal =  DateUtil.StringToDate(dfinal+ " 23:59:59", "dd/MM/yyyy HH:mm:ss"); //DateUtil.StringToDate(dfinal, "dd/MM/yyyy");
            Query query = session.createQuery("from ParticipanteExtension pe where pe.recordDate between :finicial and :ffinal and pe.extensiones.id=:idExtension");
            query.setParameter("finicial", finicial);
            query.setParameter("ffinal", ffinal);
            query.setParameter("idExtension", idExtension);
            /*query.setParameter("idVersion", idVersion);
            query.setParameter("idParticipante", idParticipante);
            and pe.extensiones.version.id=:idVersion and pe.participantecarta.participante.codigo=:idParticipante
            */
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    // Metodo participante extension by id_participante_carta
    public ParticipanteExtension getParticipanteExtensionById(int idparticipanteExtension){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.idParticipantExtension=:idparticipanteExtension  ");
        query.setParameter("idparticipanteExtension",idparticipanteExtension);
        return (ParticipanteExtension) query.uniqueResult();
    }

    public List<ParticipanteExtension>getAllPartExt(Integer idparticipantecarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.participantecarta.idparticipantecarta= :idparticipantecarta");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        return query.list();
    }

    // Obtener todas las extensiones.
    public List<ParticipanteExtension>getAllPartipantExtension(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.anulada=false  order by pe.fechaExtension desc ");
        return query.list();
    }


    public Integer cantExtensionByCarta(Integer idParticipanteCartaExtension)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (*) from ParticipanteExtension pe where pe.participantecarta.id=:idParticipanteCartaExtension");
        query.setParameter("idParticipanteCartaExtension",idParticipanteCartaExtension);
        Integer result = Integer.valueOf(String.valueOf(query.uniqueResult()));
        return result;
    }

    //endregion

    //region    CARTAS TEMPORAL
    // Guardar Carta en Tabla Temporal
    public void guardarCartaTMP(ParticipanteCartaTmp temporal)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(temporal);
    }

    public void saveParteCartaTMP(DetalleParteTmp detalle){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detalle);
    }

    //Metodo para obtener el listado de la tabla ParticipanteCartaTmp Temporal By NameUser
    public List<ParticipanteCartaTmp>getAllParticipanteCartaTmp()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = session.createQuery("from ParticipanteCartaTmp tm where tm.pasive='0' and tm.recordUser=:nameUser order by tm.id asc ");
        query.setParameter("nameUser",nameUser);
        return query.list();
    }

    public ParticipanteCartaTmp getAllParticipanteCartaTmpById(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCartaTmp tm where tm.id=:id");
        query.setParameter("id",id);
        return (ParticipanteCartaTmp) query.uniqueResult();
    }

    public List<DetalleParteTmp>getDetalleParteTmp()throws Exception{
       Session session = sessionFactory.getCurrentSession();
       Query query = session.createQuery("from DetalleParteTmp tm order by tm.id asc ");
       return query.list();
    }

    public List<DetalleParteTmp>getDetalleParteTmpById(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp tm where tm.participantecartatmp.id=:id ");
        query.setParameter("id",id);
        return query.list();
    }

    //Obtengo partes aceptadas de la carta Temporal
    public List<DetalleParteTmp>getDetalleParteTmpByAccept(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp tm where participantecartatmp.id=:id and tm.acepta=true");
        query.setParameter("id",id);
        return query.list();
    }
    //Obtengo partes aceptadas de la carta oficial
    public List<DetalleParte>getDetalleParteByAccept(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte tm where participantecarta.idparticipantecarta=:id and tm.acepta=true");
        query.setParameter("id",id);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public boolean SiExisteParticipanteCartaTMP(Integer idversion, Integer idparticipante, String fechaCarta)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date fecha = DateUtil.StringToDate(fechaCarta,"dd/MM/yyyy");
            Query query =session.createQuery("from ParticipanteCartaTmp pc where pc.fechacarta =:fecha and version.idversion =:idversion and idparticipante =:idparticipante ");
            query.setParameter("idversion",idversion);
            query.setParameter("idparticipante",idparticipante);
            query.setParameter("fecha",fecha);
            return  query.list().size()>0;
        }
        catch (Exception e){
            throw e;
        }
    }

    public Participante getCodigoParticipante(int codigo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante p where p.id=:codigo");
        query.setParameter("codigo",codigo);
        return (Participante) query.uniqueResult();
    }

    // GUARDAR/ACTUALIZAR EXTENSIONES TEMPORALES
    public void guardarExtensionTmp(ExtensionesTmp tmp)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tmp);
    }


    // Metodo para obtener Extension Temporal para Editar
    public ExtensionesTmp getExtensionTmpById(Integer idextensionTmp){
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp e where e.idParticipantExtensiontmp =:idextensionTmp ");
        query.setParameter("idextensionTmp",idextensionTmp);
        return (ExtensionesTmp)query.uniqueResult();
    }

    public boolean verificaSiyaTieneExtension(int codigo, int idExtension, String fechaext)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date finicial = DateUtil.StringToDate(fechaext, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(fechaext + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            Query query = session.createQuery("from ExtensionesTmp et where et.fechaExtension between :finicial and :ffinal and et.extensiones.id =:idExtension and et.participantecartatmp.id =:codigo");
            query.setParameter("finicial", finicial);
            query.setParameter("ffinal", ffinal);
            query.setParameter("idExtension", idExtension);
            query.setParameter("codigo", codigo);
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    //endregion



    //Metodo para verificar si la version tiene parte principal
    public List<Parte> listadoPartesPrincipales(int idversion)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        boolean si = true;
        Query query = session.createQuery("from Parte p where p.version.id=:idversion ");
        query.setParameter("idversion", idversion);
        return query.list();
    }

    public List<DetalleParteTmp>getList_Detalle_Parte_Tmp(Integer idParticipanteCartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp dt where dt.participantecartatmp.id = :idParticipanteCartatmp");
        query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
        return  query.list();
    }

    // METODO PARA OBTENER LA PARTE PRINCIPAL POR VERSION
    public Parte getPartePrincipal(int idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean si = true;
        Query query = session.createQuery("from Parte p where p.activo=:si and p.principal=:si and p.version.idversion=:idversion");
        query.setParameter("idversion", idversion);
        query.setParameter("si", si);
        return (Parte) query.uniqueResult();
    }

    //region BUSCAR NOMBRE Y APELLIDOS TUTOR
    public List<String> getNombre1(String nombre1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre1Tutor from Participante p where p.nombre1Tutor like :nombre1");
        query.setParameter("nombre1", '%' + nombre1 + '%');
        return query.list();
    }
    public List<String> getNombre2(String nombre2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre2Tutor from Participante p where p.nombre2Tutor like :nombre2");
        query.setParameter("nombre2", '%' + nombre2 + '%');
        return query.list();
    }
    public List<String> getApellido1(String apellido1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido1Tutor from Participante p where p.apellido1Tutor like :apellido1");
        query.setParameter("apellido1", '%' + apellido1 + '%');
        return query.list();
    }
    public List<String> getApellido2(String apellido2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido2Tutor from Participante p where p.apellido2Tutor like :apellido2");
        query.setParameter("apellido2", '%' + apellido2 + '%');
        return query.list();
    }
    //endregion

    //region Eliminar todos Temporales por participantecartaTmp


    public int deleteDetalleParteTmp(Integer idParticipanteCartaTmp){
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from DetalleParteTmp dp where dp.participantecartatmp.id =:idParticipanteCartaTmp");
            query.setParameter("idParticipanteCartaTmp", idParticipanteCartaTmp);
            int response =  query.executeUpdate();
            return response;
        }catch (Exception e){
            return 0;
        }
    }

    // Desact ExtensionesTmp by IdCartaTmp
    public int disableExtnsionTmpByIdCartaTmp(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ExtensionesTmp e set e.pasive='1' where e.participantecartatmp.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }
    // Dessact Detalles_Partes_Temp participantecartatmp.id
    public int disableDetailPartesTmpByIdCartaTmp(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update DetalleParteTmp p set p.pasive='1' where p.participantecartatmp.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }
    // Dessact ParticipanteCartaTmp participantecartatmp.id
    public int disableParticipanteCartaTmpById(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ParticipanteCartaTmp p set p.pasive='1' where p.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }



    //Verifica si Existe ExtensionesTmp By ParticipanteCarta
    public boolean isExistExtensionesById(int id)throws  Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp etmp where etmp.participantecartatmp.id=:id");
        query.setParameter("id", id);
        return query.list().size() > 0;
    }

    // Obtengo la lista de Extension Temporal by idParticipanteCartatmp para pasarla a las tabla Oficial
    public List<ExtensionesTmp>getListExtensionTmpByParticipanteCartaId(int idParticipanteCartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp et where et.participantecartatmp.id=:idParticipanteCartatmp and et.pasive='0'");
        query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
        return query.list();
    }

    public boolean Borrar_Participante_Carta_Extension(Integer idParticipanteCartatmp){
        try{
            Session session= sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from ExtensionesTmp et where et.participantecartatmp.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    // Elimina los registros de la tabla DetalleParteTmp por idParticipanteCartatmp
    public boolean Borrar_Detalle_Partes_tmp(Integer idParticipanteCartatmp)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from DetalleParteTmp dpt where dpt.participantecartatmp.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp", idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean Borrar_Participante_Carta_Tmp(Integer idParticipanteCartatmp)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from ParticipanteCartaTmp pct where pct.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp", idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //endregion

    // Búsca participante por codigo, este metodo viene de DomicilioService
    @SuppressWarnings("unchecked")
    public ParticipantesCodigo getCodigos(Integer parametro)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT cast( p.codigo as string ) as codigo, cast(c.codigo as string) as casaP, coalesce( pp.estudio, 'Vacio') as estudio, " +
                    "(SELECT cc.codigoCHF from ParticipanteCohorteFamilia pc, CasaCohorteFamilia cc where pc.casaCHF = cc.codigoCHF and pc.participante = p.codigo) as casaFamilia " +
                    "FROM Participante p, Casa c, ParticipanteProcesos pp where p.casa = c.codigo and p.codigo = pp.codigo  and p.codigo =:parametro");
            query.setParameter("parametro", parametro);
            query.setResultTransformer(Transformers.aliasToBean(ParticipantesCodigo.class));
            return (ParticipantesCodigo) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }
}
