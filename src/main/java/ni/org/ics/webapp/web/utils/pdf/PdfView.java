package ni.org.ics.webapp.web.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import ni.org.ics.webapp.domain.Retiros.Retiros;
import ni.org.ics.webapp.domain.Serologia.SerologiaEnvio;
import ni.org.ics.webapp.domain.Serologia.Serologia_Detalles_Envio;
import ni.org.ics.webapp.domain.catalogs.Razones_Retiro;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.scancarta.DetalleParte;
import ni.org.ics.webapp.domain.scancarta.ParticipanteCarta;
import ni.org.ics.webapp.domain.scancarta.ParticipanteExtension;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

//import com.sun.javafx.font.*;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */
public class PdfView extends AbstractPdfView {

    List<MessageResource> messageReports = new ArrayList<MessageResource>();
    List<MessageResource> messageExtremidades = new ArrayList<MessageResource>();
    List<MessageResource> messagerelFam = new ArrayList<MessageResource>();
    List<MessageResource> messageSitio = new ArrayList<MessageResource>();
    List<MessageResource> message_causa_retiro = new ArrayList<MessageResource>();
    List<MessageResource> coordinador = new ArrayList<MessageResource>();
    List<MessageResource> messagediuresis = new ArrayList<MessageResource>();
    /*ReporteCarta
    List<MessageResource> messagerelFam = new ArrayList<MessageResource>();

    List<MessageResource>messagescancarta = new ArrayList<MessageResource>();*/
    List<MessageResource> messageproyecto = new ArrayList<MessageResource>();
    @Override
    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        messageReports = (List<MessageResource>) model.get("labels");

        if (model.get("TipoReporte").equals(Constants.TPR_REPORTECARTA)){
            ReporteCarta(model, document, writer);
        }

        if (model.get("TipoReporte").equals(Constants.TPR_ENVIOREPORTE)){
            ReporteEnvio(model, document, writer);
        }

        if (model.get("TipoReporte").equals(Constants.TPR_REPORTERETIRO)){
            ReporteRetiro(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_DATOSGENERALES)){
            datosGenerales(model, document, writer);
        }
    }

    private PdfPCell createCell(String text, Font f, int border){
        PdfPCell cell = new PdfPCell(new Phrase(text, f));
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }

    private PdfPCell createCell(String text, Font f, int border, int colspan){
        PdfPCell cell = new PdfPCell(new Phrase(text, f));
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }

    private PdfPCell createCellUnderline(String text, Font f, int border) {
        Chunk chunk2 = new Chunk(text, f);
        chunk2.setUnderline(1f, -2f);
        PdfPCell cell = new PdfPCell(new Phrase(chunk2));
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }
    private PdfPCell createCellUnderline(String text, Font f, int border, int colspan) {
        Chunk chunk2 = new Chunk(text, f);
        chunk2.setUnderline(1f, -2f);
        PdfPCell cell = new PdfPCell(new Phrase(chunk2));
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }


    private String getMessage(String messageKey, String languaje){
        for(MessageResource message : messageReports){
            if (message.getMessageKey().equalsIgnoreCase(messageKey)){
                if (languaje!=null && languaje.equalsIgnoreCase("en"))
                    return message.getEnglish();
                else return message.getSpanish();
            }
        }
        return "";
    }


    // create TableNull
    private static PdfPTable createTableNull(String text){
        Font etiquetas = FontFactory.getFont(FontFactory.HELVETICA,9);
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        PdfPCell cel = new PdfPCell();
        cel.addElement(new Phrase(text,etiquetas));
        table.addCell(cel);
        table.completeRow();
        return table;
    }
    //region todo Reporte ScanCarta
    private void ReporteCarta(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        ParticipanteCarta obj= (ParticipanteCarta) model.get("obj");
        ParticipanteProcesos procesos = (ParticipanteProcesos) model.get("procesos");
        List<DetalleParte> ListDetailPart = (List<DetalleParte>) model.get("dp");
        List<ParticipanteExtension> ListDetailExtension = (List<ParticipanteExtension>) model.get("getListParticipanteExtension");
        messagerelFam = (List<MessageResource>) model.get("relFam");
        document.newPage();
        document.open();
        //region Encabezados
        if (ListDetailPart.size()<=0){
            Paragraph encabezado = new Paragraph("COMPLETE LA INFORMACIÓN, PARA VIZUALIZAR EL REPORTE.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
            document.add(encabezado);
        }
        Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font bf12 = new Font(Font.TIMES_ROMAN, 10);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font timesRomanBoldItalic12 = new Font(Font.COURIER, 12, Font.BOLD);
        Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);

        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(DateUtil.DateToString(obj.getFechacarta(), "dd/MM/yyyy"));
        fecha.setAlignment(Element.ALIGN_RIGHT);
        document.add(fecha);
        Paragraph encabezado = new Paragraph("INSTITUTO DE CIENCIAS SOSTENIBLES.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado);
        Paragraph encabezado2 = new Paragraph("CARTA DE CONSENTIMIENTO.", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado2);

        LineSeparator ls1 = new LineSeparator();
        ls1.setLineWidth(0.5f);
        document.add(new Chunk(ls1));

        /*String imageFile = "C:\\Users\\ICS\\Documents\\GitHub\\estudios-ics\\estudios-ics\\src\\main\\webapp\\resources\\img\\logo-login.png";
        Image image1 = Image.getInstance(imageFile);
        image1.setWidthPercentage(30);
        image1.setAlignment(0);
        image1.setCompressionLevel(5);
        document.add(image1);*/

        PdfPTable table = new PdfPTable(new float[]{18,18});
        table.setWidthPercentage(96f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Código: ",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getCodigo().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("1. Datos Generales", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Familia.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre de la Madre: ", miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        String nom2Madre  = (obj.getParticipante().getNombre2Madre()   == "") ? "" : obj.getParticipante().getNombre2Madre();
        String ape21madre = (obj.getParticipante().getApellido2Madre() == "") ? "" : obj.getParticipante().getApellido2Madre();
        String Madre = obj.getParticipante().getNombre1Madre()+" "+ nom2Madre+" "+obj.getParticipante().getApellido1Madre()+" "+ape21madre;
        cell = new PdfPCell(new Phrase(Madre,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre del Padre: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String apePadre2  = (obj.getParticipante().getApellido2Padre() == null) ? "" :obj.getParticipante().getApellido2Padre();
        String nomb2Padre = (obj.getParticipante().getNombre2Padre()   == "") ? "" :obj.getParticipante().getNombre2Padre();
        String Padre = obj.getParticipante().getNombre1Padre()+" "+ nomb2Padre + " "+ obj.getParticipante().getApellido1Padre() +" "+ apePadre2;
        cell = new PdfPCell(new Phrase(Padre,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre del Tutor :",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getTutor(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{40,60});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Hubo Testigo?", miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        String huboTestigo = (obj.isTestigopresent() == true)?"Si": "No";
        cell = new PdfPCell(new Phrase(huboTestigo, mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{40,60});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre Testigo:", miaNormal));
        cell.setBorder(1);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(obj.getNombre1testigo()+" "+ obj.getApellido1testigo(), mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{56,40});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Relación del tutor con el participante: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getParticipante().getRelacionFamiliarTutor().toUpperCase(),"CAT_RF_TUTOR"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Datos del Participante.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombres y Apellidos: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getNombreCompleto().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{20,20,20,40});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Sexo: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getSexo().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Edad: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(obj.getEdadyears().toString().concat(obj.getEdadmeses().toString().concat(obj.getEdaddias().toString())),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);


        document.add(table);


        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Fecha de Nacimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getParticipante().getFechaNac(),"dd/MM/yyyy"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

//endregion

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Carta, Versión, Partes aceptadas y no aceptadas.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);

        // text watermark 337, 500, 45
        String isAnulada = (obj.isAnulada() == true)? "Anulada" : "";
        PdfContentByte canvas = writer.getDirectContentUnder();
        Phrase watermark = new Phrase(isAnulada, new Font(Font.ITALIC, 180, Font.NORMAL, Color.LIGHT_GRAY));
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 330, 425, 45);

            /* prueba rowspan*/
        table = new PdfPTable(new float[]{18,18,18});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("1", mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //2
        cell = new PdfPCell(new Phrase("2", mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //3
        cell = new PdfPCell(new Phrase("3", mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);


         /* fin prueba rowspan*/

//region foreach
        if (ListDetailPart.size() > 0){
            table = new PdfPTable(new float[]{18,18,18});
            cell = new PdfPCell(new Phrase(obj.getVersion().getEstudio().getNombre(), mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Integer numRowSpan = ListDetailPart.size()+2;
            cell.setRowspan(numRowSpan);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getVersion().getVersion(), mia));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("PARTE",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("ACEPTA",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (DetalleParte objParte : ListDetailPart){
                cell = new PdfPCell(new Phrase(objParte.getParte().getParte().toString(),mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase( (objParte.getAcepta().toString() == "true" )? "Si":"No" , mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            document.add(table);
        }
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);

        if (ListDetailExtension.size()>0) {
            table.addCell(createCellUnderline("Extesiones, "+obj.getVersion().getEstudio().getNombre()+", "+obj.getVersion().getVersion(), timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
            document.add(table);
            table = new PdfPTable(new float[]{25,15,30,30});
            cell = new PdfPCell(new Phrase("EXTENSION",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("FECHA",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("TUTOR",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("TESTIGO",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            for (ParticipanteExtension pex : ListDetailExtension) {
                cell = new PdfPCell(new Phrase(pex.getExtensiones().getExtension(), mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(DateUtil.DateToString(pex.getFechaExtension(), "dd/MM/yyyy"), mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                String nombres = pex.getNombre1Tutor().toUpperCase();
                nombres += (pex.getNombre2Tutor() != null) ? " "+pex.getNombre2Tutor().toUpperCase() : "";

                String apellidos = pex.getApellido1Tutor().toUpperCase();
                apellidos += (pex.getApellido2Tutor() != null) ? pex.getApellido2Tutor().toUpperCase() : "";

                cell = new PdfPCell(new Phrase(nombres + " " + apellidos, mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                String nombresTestigo = pex.getNombre1Testigo().toUpperCase();
                nombresTestigo += (pex.getNombre2Testigo() != null) ? " " + pex.getNombre2Testigo().toUpperCase() : "";

                String apellidosTestigo = pex.getApellido1Testigo().toUpperCase();
                apellidosTestigo += (pex.getApellido2Testigo() != null) ? " " + pex.getApellido2Testigo().toUpperCase() : "";

                cell = new PdfPCell(new Phrase(nombresTestigo + " " + apellidosTestigo, mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }
        document.add(table);
        document.add(new Phrase("\n"));
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Carta Firmada Por: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String QuienFirma = obj.getQuienfirma()+" "+obj.getNombre2Firma()+" "+obj.getApellido1Firma()+" "+obj.getApellido2Firma();
        cell = new PdfPCell(new Phrase(QuienFirma,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Relación familiar: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getRelfam().toString(),"CAT_RF_TUTOR"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Proyecto: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        //AQUI VALIDAR QUE NO VAYA VACIO
        String pro =  (this.getDescripcionCatalogoScan(obj.getProyecto().toString(),"CAT_SCAN_PROYECTO") == "")?"No Aplica" : this.getDescripcionCatalogoScan(obj.getProyecto().toString(),"CAT_SCAN_PROYECTO");//getDescripcionCatalogoScan(obj.getProyecto().toString(),"CAT_SCAN_PROYECTO");
        cell = new PdfPCell(new Phrase(pro,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Asentimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getAsentimiento().toString(),"SCANCARTA"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        //AQUI TAMBIEN VALIDAR

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Tipo Asentimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String tAsentimiento;
        if (this.getDescripcionCatalogoScan(obj.getTipoasentimiento().toString(), "CAT_TIPO_ASENT") == null){
            tAsentimiento = "No Aplica";
        }
        else {
            tAsentimiento = this.getDescripcionCatalogoScan(obj.getTipoasentimiento().toString(), "CAT_TIPO_ASENT");
        }
        cell = new PdfPCell(new Phrase(tAsentimiento, mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Contacto Futuro: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        boolean contact = obj.getContactoFuturo();
        if (contact){
            cell = new PdfPCell(new Phrase("Aceptó",mia));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            document.add(table);
        }else {
            cell = new PdfPCell(new Phrase("No Aceptó",mia));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            document.add(table);
        }

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Observación: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        String observa = (obj.getObservacion().equals("")) ? "Ninguna" : obj.getObservacion().toString();
        cell = new PdfPCell(new Phrase(observa,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        /*** Razón anulada ***/
        if(obj.isAnulada() == true) {
            table = new PdfPTable(new float[]{38,58});
            table.setWidthPercentage(96f);
            cell = new PdfPCell(new Phrase("Anulada por: ", miaNormal));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getPq_anulada(), mia));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            document.add(table);
        }

        document.add(new Phrase("\n"));
        Font font = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL, Color.BLACK);
        Date fechaNow = new Date();
        DateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hFormat = new SimpleDateFormat("HH:mm");
        table = new PdfPTable(new float[]{10,10,5,10,5});
        table.setWidthPercentage(96);
        table.addCell(createCell("Creado por: ", font, Rectangle.NO_BORDER));
        table.addCell(createCell(obj.getRecordUser(), font, Rectangle.NO_BORDER));
        table.addCell(createCell("Impreso : ", font, Rectangle.NO_BORDER));
        table.addCell(createCell(dateformat.format(objDate) + " " + hourFormat.format(objDate), font, Rectangle.NO_BORDER));
        int pageN = writer.getPageNumber();
        table.addCell(createCell("Página: "+ writer.getCurrentPageNumber(), font, Rectangle.NO_BORDER));
        document.add(table);
        document.close();

    }// fin del reporte carta Scam
    //endregion

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public PdfPCell createCell(String content, int colspan, int rowspan, int border) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(border);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    //region todo Reporte Envio de Muestras
    private void ReporteEnvio(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<SerologiaEnvio> datos_Envio = (List<SerologiaEnvio>)  model.get("SerologiasEnviadas");

        List<Serologia_Detalles_Envio> Detalles_Muestras_Serologia = (List<Serologia_Detalles_Envio>) model.get("allSerologia");
        messageSitio = (List<MessageResource>) model.get("sitios");

        Integer numeroEnvios = (Integer) model.get("nEnvios");
        String f1 = (String) model.get("fechaInicio");
        String f2 = (String) model.get("fechaFin");

        //document = new Document(PageSize.A4,20,20,20,20);
        document.newPage();

        document.open();
        Date fecha_inicio = DateUtil.StringToDate(f1,"dd/MM/yyyy");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(fecha_inicio);
        int yearsNow  = calendar2.get(Calendar.YEAR);

        String enviado_desde = "";
        String hora_envio = "";
        String temp="";
        for (SerologiaEnvio obj : datos_Envio){
            enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
            hora_envio = obj.getHora();
            temp= ""+ obj.getTemperatura();
        }

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteEnvio footer = new HeaderFooterReporteEnvio(f1, f2.concat(" Hora: "+hora_envio), numeroEnvios, yearsNow, enviado_desde, temp);
        writer.setPageEvent(footer);

        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_RIGHT);
        float y = 650f; //posicion coordenada y en la pagina.. mientras mas disminuye mas se acerca al fin (botton) de la pagina
        PdfPTable table = new PdfPTable(new float[]{5,10,10,7,8,23,25});
        table.setHeaderRows(1);
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        PdfPCell cell;
        Paragraph paragraph1 = new Paragraph();
        addEmptyLine(paragraph1, 1);
        document.add(paragraph1);

        cell = new PdfPCell(new Phrase("N°",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CÓDIGO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("VOLUMEN",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("EDAD\nAños|Meses",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("DESCRIPCIÓN",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("OBSERVACIONES",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        if (datos_Envio.size()==0){
            table = new PdfPTable(1);
            table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
            cell = new PdfPCell(new Phrase("No hay información!",mia));
            cell.setBorder(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        }
        int cont=0;
        for (Serologia_Detalles_Envio obj : Detalles_Muestras_Serologia) {
            cont++;
            cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
                //codigo
                cell = new PdfPCell(new Phrase( obj.getSerologia().getParticipante(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //volumen
                cell = new PdfPCell(new Phrase(""+obj.getSerologia().getVolumen(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //edad
                double d = obj.getSerologia().getEdadMeses();
                double edad = d/12;

                cell = new PdfPCell(new Phrase(""+Math.round(edad), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                String e = ""+df.format(edad);
                String[] arrayString = e.split(Pattern.quote(","));
                String part1 = "0."+arrayString[1];
                double number = Double.parseDouble(part1);
                double result = (number/0.08333);
                cell = new PdfPCell(new Phrase(""+Math.round(result), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                /*if (edad ==0){
                    cell = new PdfPCell(new Phrase("-", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else if(edad>=1.0 ){
                    cell = new PdfPCell(new Phrase(df.format(edad)+" años.", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else{
                    cell = new PdfPCell(new Phrase(df.format(edad)+" meses.", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }*/
                //Descripcion
                cell = new PdfPCell(new Phrase(obj.getSerologia().getDescripcion(), FontObservacion));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //observacion
                cell = new PdfPCell(new Phrase(obj.getSerologia().getObservacion(), FontObservacion));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if (table.getTotalHeight() > 580) {
                    table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
                    document.newPage();
                    y = 650f;
                    table = new PdfPTable(new float[]{5,10,10,7,8,23,25});
                    table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
                }

        }
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 10;
        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Total: "+ Detalles_Muestras_Serologia.size(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 45;

        table = new PdfPTable(new float[]{40,20,40});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Entregado Por: ",miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(1);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Recibido Por",miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(1);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        document.close();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    //endregion


    //region todo **  Reporte Retiro  **
    private void ReporteRetiro(Map<String, Object> model, Document document, PdfWriter writer)throws Exception {
        Retiros retiros = (Retiros) model.get("retiros");
        message_causa_retiro = (List<MessageResource>) model.get("causas_retiros");
        coordinador = (List<MessageResource>) model.get("coordinador_estudio");
        messagerelFam = (List<MessageResource>) model.get("relFam");
        Personal p = (Personal) model.get("personal");
        Personal supervisor = (Personal) model.get("supervisor");
        List<Razones_Retiro> razonesRetiroList = (List<Razones_Retiro>) model.get("listaDerazones");

        /* init razones por grupos*/
        List<Razones_Retiro> razon_gupo_1 = (List<Razones_Retiro>) model.get("listaDeRazonesGrupo_1");
        List<Razones_Retiro> razon_gupo_2 = (List<Razones_Retiro>) model.get("listaDeRazonesGrupo_2");
        List<Razones_Retiro> razon_gupo_3 = (List<Razones_Retiro>) model.get("listaDeRazonesGrupo_3");
        List<Razones_Retiro> razon_gupo_4 = (List<Razones_Retiro>) model.get("listaDeRazonesGrupo_4");
        /* fin razones por grupos*/

        Font respuesta_negritas = new Font(Font.COURIER, 10, Font.BOLDITALIC);
        Font miaNormal = new Font(Font.COURIER, 12, Font.NORMAL);
        Font letra_pequena = new Font(Font.COURIER, 10, Font.NORMAL, Color.black);
        Font notas_pequena = new Font(Font.COURIER, 8, Font.ITALIC, Color.black);
        Font notas_pequena_subrayada = new Font(Font.COURIER, 8, Font.UNDERLINE, Color.black);


        document.newPage();
        document.open();

        //region todo Encabezado
        Paragraph encabezado = new Paragraph("INSTITUTO DE CIENCIAS SOSTENIBLES.", FontFactory.getFont("COURIER", 18, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado);
        Paragraph encabezado2 = new Paragraph("DOCUMENTACIÓN DE PARTICIPANTES RETIRADOS DEL ESTUDIO A2CARES.", FontFactory.getFont("COURIER", 14, java.awt.Font.ITALIC));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado2);


        LineSeparator ls1 = new LineSeparator();
        ls1.setLineWidth(0.5f);
        document.add(new Chunk(ls1));
/*
        String espacio = "\n";
        Paragraph paragraph = new Paragraph(espacio);
        document.add(paragraph);*/

        PdfPTable table = new PdfPTable(new float[]{35, 50});
        table.setWidthPercentage(98f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Tipo de estudio del que se retira: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("A2CARES", respuesta_negritas));
        cell.setBorder(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{29, 65});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Nombre del Participante: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        String nombreParticipante = retiros.getParticipante().getNombre1();
        if (retiros.getParticipante().getNombre2() != null)
            nombreParticipante = nombreParticipante + " " + retiros.getParticipante().getNombre2().toUpperCase();
        nombreParticipante = nombreParticipante + " " + retiros.getParticipante().getApellido1().toUpperCase();
        if (retiros.getParticipante().getApellido2() != null)
            nombreParticipante = nombreParticipante + " " + retiros.getParticipante().getApellido2().toUpperCase();

        cell = new PdfPCell(new Phrase(nombreParticipante, respuesta_negritas));
        cell.setBorder(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);


        table = new PdfPTable(new float[]{25, 10, 20, 20});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Código del Participante: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        //cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(retiros.getParticipante().getCodigo(), respuesta_negritas));
        //cell.setBorder(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase("Fecha del Retiro: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(DateUtil.DateToString(retiros.getFecha_retiro(), "dd/MM/yyyy"), respuesta_negritas));
        //cell.setBorder(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);


        //tutor
        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Padre o tutor con quien habia comunicación sobre el retiro: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{10, 35, 15, 30});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Nombre: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(retiros.getParticipante().getTutor(), respuesta_negritas));
        cell.setBorder(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase("Parentesco: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        String relafam = this.getDescripcionCatalogoScan(retiros.getRelfam().toString(), "CAT_RF_TUTOR");
        cell = new PdfPCell(new Phrase(relafam, respuesta_negritas));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(2);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Personal del estudio con quien se ha comunicado el participante y/o el padre del participante: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{10, 40, 10, 15});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Nombre: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(p.getNombre(), respuesta_negritas));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(2);
        table.addCell(cell);
        document.add(table);


        cell = new PdfPCell(new Phrase("Código: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(retiros.getPersona_documenta().toString(), respuesta_negritas));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(2);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Personal del estudio que llena el documento: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{10, 40, 10, 15});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Nombre: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(supervisor.getNombre(), respuesta_negritas));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(2);
        table.addCell(cell);
        document.add(table);


        cell = new PdfPCell(new Phrase("Código: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(retiros.getMedico_supervisor().toString(), respuesta_negritas));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(2);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Causas por la que no desea continuar participación: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("A. Decisión del padre o tutor: \n", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);
//endregion

        /* INICION CAUSAS DEL RETIRO */
        int fila_1 = 0;
        for (Razones_Retiro loop : razon_gupo_1) {
            table = new PdfPTable(new float[]{5, 7, 80});
            fila_1++;
            cell = new PdfPCell(new Phrase("" + fila_1 + ".", letra_pequena));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                cell = new PdfPCell(new Phrase("X", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            } else {
                cell = new PdfPCell(new Phrase("", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            }
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                Paragraph parrafo_descripcion = new Paragraph(new Phrase(loop.getDescripcion()+": ", letra_pequena));
                Chunk otros_motivos = new Chunk(retiros.getOtros_motivo(), notas_pequena_subrayada);
                parrafo_descripcion.add(otros_motivos);
                cell = new PdfPCell(parrafo_descripcion);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }else{
                cell = new PdfPCell(new Phrase(loop.getDescripcion(), letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }
        }// fin grupo 1

        // inicio grupo 2
        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("B. Por Decisión nuestra: \n", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        int fila_2 = 0;
        for (Razones_Retiro loop : razon_gupo_2) {
            table = new PdfPTable(new float[]{5, 7, 80});
            fila_2++;
            cell = new PdfPCell(new Phrase("" + fila_2 + ".", letra_pequena));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                cell = new PdfPCell(new Phrase("X", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            } else {
                cell = new PdfPCell(new Phrase("", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            }
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                Paragraph parrafo_descripcion = new Paragraph(new Phrase(loop.getDescripcion()+": ", letra_pequena));
                Chunk otros_motivos = new Chunk(retiros.getOtros_motivo(), notas_pequena_subrayada);
                parrafo_descripcion.add(otros_motivos);
                cell = new PdfPCell(parrafo_descripcion);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }else {
                cell = new PdfPCell(new Phrase(loop.getDescripcion(), letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }
        }// fin grupo

        // inicio grupo 3
        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("C. Por tiempo de Expiración de participación: \n", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);
        int fila_3 = 0;
        for (Razones_Retiro loop : razon_gupo_3) {
            table = new PdfPTable(new float[]{5, 7, 80});
            fila_3++;
            cell = new PdfPCell(new Phrase("" + fila_3 + ".", letra_pequena));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                cell = new PdfPCell(new Phrase("X", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            } else {
                cell = new PdfPCell(new Phrase("", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            }
            cell = new PdfPCell(new Phrase(loop.getDescripcion(), letra_pequena));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);

        }//fin grupo 3

        // inicio grupo 4
        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("D. Por tiempo de Expiración de participación: \n", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);
        int fila_4 = 0;
        for (Razones_Retiro loop : razon_gupo_4) {
            table = new PdfPTable(new float[]{5, 7, 80});
            fila_4++;
            cell = new PdfPCell(new Phrase("" + fila_4 + ".", letra_pequena));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                cell = new PdfPCell(new Phrase("X", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            } else {
                cell = new PdfPCell(new Phrase("", letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(2);
                table.addCell(cell);
                document.add(table);
            }
            if (loop.getMotivo().equals(retiros.getMotivo())) {
                Paragraph parrafo_descripcion = new Paragraph(new Phrase(loop.getDescripcion()+": ", letra_pequena));
                Chunk otros_motivos = new Chunk(retiros.getOtros_motivo(), notas_pequena_subrayada);
                String dateFallecido = (DateUtil.DateToString(retiros.getFecha_fallecido(),"dd/MM/yyyy")==null)?"":" Fecha Fallecido: "+ DateUtil.DateToString(retiros.getFecha_fallecido(),"dd/MM/yyyy");
                Chunk fecha_fallecido = new Chunk(dateFallecido, notas_pequena_subrayada);
                parrafo_descripcion.add(otros_motivos);
                parrafo_descripcion.add(fecha_fallecido);
                cell = new PdfPCell(parrafo_descripcion);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }else {
                cell = new PdfPCell(new Phrase(loop.getDescripcion(), letra_pequena));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);
                document.add(table);
            }
        }
        /* FIN CAUSAS DEL RETIRO */

        /* ENTREGO CARNET */
        table = new PdfPTable(new float[]{7, 3, 7, 3, 7});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Entregó carnet: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);


        String loEntrego = "";
        if (retiros.getDevolvio_carnet() == '1') {
            loEntrego = "X";
            cell = new PdfPCell(new Phrase(loEntrego, respuesta_negritas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(2);
            table.addCell(cell);
        } else {
            loEntrego = "";
            cell = new PdfPCell(new Phrase(loEntrego, respuesta_negritas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(2);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("Si", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        if (retiros.getDevolvio_carnet() == '0') {
            loEntrego = "X";
            cell = new PdfPCell(new Phrase(loEntrego, respuesta_negritas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(2);
            table.addCell(cell);
        } else {
            loEntrego = "";
            cell = new PdfPCell(new Phrase(loEntrego, respuesta_negritas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(2);
            table.addCell(cell);
        }

        cell = new PdfPCell(new Phrase("No", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        String para1 = "\n";
        Paragraph paragraph1 = new Paragraph(para1);
        document.add(paragraph1);
        table = new PdfPTable(new float[]{10, 40});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Observación: ", letra_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase(retiros.getObservaciones(), notas_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{90});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("Check lista de procedimientos para niños retirados antes de poner este formulario en carpeta del participante: ", notas_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{50,50});
        table.setWidthPercentage(98f);
        cell = new PdfPCell(new Phrase("__ Señala el retiro en la base de datos y bloquear los datos de la persona en la base de datos." +
                "\n__ Confirme la revisón (sello y firma) de\n" +
                " "+ coordinador.get(0).getSpanish() , notas_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        cell = new PdfPCell(new Phrase("__ Ponga el original de esta hoja en el expediente de respaldo.", notas_pequena));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        document.add(table);

        String para0="\n";
        Paragraph paragraph0 = new Paragraph(para0);
        document.add(paragraph0);

        table = new PdfPTable(new float[]{40,20,40});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(coordinador.get(0).getSpanish() +"\n Coordinadora ",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(1);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaNormal));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Padre o Tutor",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(1);
        table.addCell(cell);
        document.add(table);

        document.close();
    }

    //endregion


    //region datos generales

    private void datosGenerales(Map<String, Object> model,
                                Document document,
                                PdfWriter writer) throws Exception{

        List<DatosGeneralesParticipante> datosParticipantes = (List<DatosGeneralesParticipante>) model.get("datos");
        if (datosParticipantes.size()<=0){
            Paragraph h1 = new Paragraph(this.getMessage("noResults", null), new Font(Font.TIMES_ROMAN, 13, Font.BOLD));
            document.add(h1);
        }
        for (DatosGeneralesParticipante datosParticipante : datosParticipantes) {
            document.newPage();
            MyFooter footer = new MyFooter();
            footer.setMensajes(datosParticipante.getMensajes());
            writer.setPageEvent(footer);
            Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
            Font timesRomanBold13 = new Font(Font.TIMES_ROMAN, 13, Font.BOLD);
            Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
            Font timesRomanBoldItalic12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLDITALIC);

            Paragraph h1 = new Paragraph(this.getMessage("title.report.file", null), timesRomanBold13);
            document.add(h1);
            LineSeparator ls = new LineSeparator(0.5f, 100, null, 0, -5);
            ls.setLineWidth(0.5f);
            document.add(new Chunk(ls));

            PdfPTable table = new PdfPTable(new float[]{10, 13, 10, 67});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.code", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCodigo(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.date", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaIngreso(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t1 = new Paragraph(this.getMessage("lbl.general.data", null), timesRomanBoldItalic12);
            t1.setSpacingAfter(5f);
            document.add(t1);

            table = new PdfPTable(new float[]{23, 77});
            table.setWidthPercentage(96);
            table.addCell(createCellUnderline(this.getMessage("lbl.family", null), timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(this.getMessage("lbl.family.boss", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getJefeFamilia(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.tutor", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTutor(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.father", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getPadre(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.mother", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getMadre(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{23, 10, 25, 15, 27});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.family.relationship", null), timesRomanBold12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(datosParticipante.getRelFamTutor(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.housing.type", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTipoVivienda(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.neighborhood", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getBarrio(), timesRomanNormal12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(this.getMessage("lbl.block", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getManzana(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{23, 77});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.address", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDireccion(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.phone", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTelefonos(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCellUnderline(this.getMessage("lbl.contact", null), timesRomanBoldItalic12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.contact.explanation", null), timesRomanBoldItalic12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.name", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getNombreContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.neighborhood", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getBarrioContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.phone", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTelefonosContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.address", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDireccionContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t2 = new Paragraph(this.getMessage("lbl.child.data", null), timesRomanBoldItalic12);
            t2.setSpacingAfter(5f);
            document.add(t2);

            table = new PdfPTable(new float[]{29, 71});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.names.surnames", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getNombreCompleto(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{8, 8, 23, 17, 7, 8, 15, 14});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.age", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEdad(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.birthdate", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaNacimiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.gender", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getSexo(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.student", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEstudiante(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{10, 19, 10, 61});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.turn", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTurno(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.school", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEscuela(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{29, 71});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.residence.time", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTiempoResidencia(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{65, 35});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.stay.area", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getPermanecerTresAnios(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{56, 44});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.where.child.attends", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDondeAsiste(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{56, 44});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.health.unit.attends", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getUnidadAsiste(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{85, 15});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.willing.attends.cssf", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getAsistirCSSF(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t3 = new Paragraph(this.getMessage("lbl.clinical.epi.data", null), timesRomanBoldItalic12);
            t3.setSpacingAfter(5f);
            document.add(t3);

            table = new PdfPTable(new float[]{53, 5, 10, 32});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.chronic disease", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEnfermedadCronica(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.wich", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCualEnfermedadCronica(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.take.treatment", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTomaTratamiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.wich", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCualTratamiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.diagnosed.dengue", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTieneDxDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.when", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaDxDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.hospitalized.dengue", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getHospitalizadoDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.when", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaHospitalizadoDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            LineSeparator ls2 = new LineSeparator();
            ls2.setLineWidth(0.5f);
            document.add(new Chunk(ls2));

            table = new PdfPTable(new float[]{20, 80});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.digitador", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDigitador(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);
        }
    }


    //endregion



    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messageExtremidades){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

    private String getCatalogoSitio(String codigo,String catroot){
        for(MessageResource rnv : messageSitio){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

    private String getDescripcionCatalogoScan(String codigo,String catroot){
        for (MessageResource rnv : messagerelFam){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }
}



class MyFooter extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 8, Font.ITALIC);

    private List<String> mensajes = new ArrayList<String>();
    private int numberPage;

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        int posicion = 5;
        for(String mensaje : mensajes) {
            Phrase footer = new Phrase("* "+mensaje, ffont);

            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    footer,
                    (document.left()) / 2 + document.leftMargin(),//(document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - posicion, 0);
            posicion+=10;
        }

        if (numberPage > 0) {
            Phrase footer = new Phrase("* " + String.valueOf(numberPage), ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    footer,
                    (document.left()) / 2 + document.leftMargin(),//(document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - posicion, 0);
        }
    }
    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }
}




/***
 * Clase para manejar el encabezado y pie de pagina del reporte de envios serologia
 */
class HeaderFooterReporteEnvio extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
    Font miaEstudio = new Font(Font.COURIER,9);
    private String fechaInicio;
    private String fechaFin;
    private Integer numeroEnvios;
    private Integer anioActual;
    private String sitio;
    private String temperatura;

    HeaderFooterReporteEnvio() {
    }

    HeaderFooterReporteEnvio(String fechaInicio, String fechaFin, Integer numeroEnvios, Integer anioActual, String sitio, String temperatura) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroEnvios = numeroEnvios;
        this.anioActual = anioActual;
        this.sitio = sitio;
        this.temperatura= temperatura;
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();

        /* ENCABEZADO */

        Paragraph encabezado = new Paragraph(sitio,FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        Paragraph encabezado2 = new Paragraph("RECEPCIÓN DE MUESTRAS TUBOS ROJOS.", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
        encabezado2.setAlignment(Element.ALIGN_CENTER);

        float y = document.getPageSize().getHeight() - 60;

        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, encabezado, 300, y, 0);
        y = y - 20;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, encabezado2, 300, y, 0);
        y = y - 15;
        PdfPTable table = new PdfPTable(new float[]{80,20});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        PdfPCell cell = new PdfPCell(new Phrase("MUESTREO ANUAL A2CARES " + anioActual,FontFactory.getFont("COURIER", 14, java.awt.Font.ITALIC)));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Viaje: "+ this.getNumeroEnvios() +" \n ",FontFactory.getFont("COURIER", 14, java.awt.Font.ITALIC, Color.black)));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        ///writeSelectedRows(filaInicial, filaFinal, X, y, canvas).
        //0 = primera fila, -1 = todas las filas, 42 = margen izquierdo, y = valor dinamico segun lo que se va agregando a la pagina, canvas= el documento
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 45;

        table = new PdfPTable(new float[]{30,50,20});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Fecha Inicio: "+ this.getFechaInicio(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Fin: "+ this.getFechaFin(),miaEstudio));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Temperatura: "+ temperatura,miaEstudio));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 15;

        table = new PdfPTable(1);
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(" ",miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.BOTTOM);
        cell.setBorderWidth(1.5f);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());

        /*PIE**/
        int numberPage = writer.getCurrentPageNumber();
        int posicion = 5;
        if (numberPage > 0) {
            Phrase footer = new Phrase("Página: " + String.valueOf(numberPage), ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    footer,
                    (document.left()) / 2 + document.leftMargin(),
                    document.bottom() - posicion, 0);
        }
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getNumeroEnvios() {
        return numeroEnvios;
    }

    public void setNumeroEnvios(Integer numeroEnvios) {
        this.numeroEnvios = numeroEnvios;
    }

    public Integer getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(Integer anioActual) {
        this.anioActual = anioActual;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }
}


