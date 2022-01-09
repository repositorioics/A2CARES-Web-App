package ni.org.ics.webapp.web.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
//import com.sun.javafx.font.*;

import ni.org.ics.webapp.domain.Serologia.SerologiaEnvio;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.scancarta.DetalleParte;
import ni.org.ics.webapp.domain.scancarta.ParticipanteCarta;
import ni.org.ics.webapp.domain.scancarta.ParticipanteExtension;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import sun.font.FontFamily;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.io.FileOutputStream;
import java.io.File;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */
public class PdfView extends AbstractPdfView {

    List<MessageResource> messageReports = new ArrayList<MessageResource>();
    List<MessageResource> messageExtremidades = new ArrayList<MessageResource>();
    List<MessageResource> messagerelFam = new ArrayList<MessageResource>();
    List<MessageResource> messagenivel = new ArrayList<MessageResource>();
    List<MessageResource> messagellenado = new ArrayList<MessageResource>();
    List<MessageResource> messagepulso = new ArrayList<MessageResource>();
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

    //region Reporte Envio de Muestras
    private void ReporteEnvio(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<SerologiaEnvio> SerologiasEnviadas = (List<SerologiaEnvio>)  model.get("SerologiasEnviadas");
        List<Participante> participanteList = (List<Participante>) model.get("allParticipantes");
        Integer numeroEnvios = (Integer) model.get("nEnvios");
        String f1 = (String) model.get("fechaInicio");
        String f2 = (String) model.get("fechaFin");

        document.newPage();
        document.open();
        Date fecha_inicio = DateUtil.StringToDate(f1,"dd/MM/yyyy");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(fecha_inicio);
        int yearsNow  = calendar2.get(Calendar.YEAR);

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteEnvio footer = new HeaderFooterReporteEnvio(f1, f2, numeroEnvios, yearsNow);
        writer.setPageEvent(footer);

        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_RIGHT);

        float y = 650f; //posicion coordenada y en la pagina.. mientras mas disminuye mas se acerca al fin (botton) de la pagina
        PdfPTable table = new PdfPTable(new float[]{5,10,10,15,23,25});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

        PdfPCell cell;

        Paragraph paragraph1 = new Paragraph();
        addEmptyLine(paragraph1, 1);
        document.add(paragraph1);

        table.setWidthPercentage(98f);
        table.setHeaderRows(1);

        cell = new PdfPCell(new Phrase("N°",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CÓDIGO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("VOLUMEN",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("EDAD",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("DESCRIPCIÓN",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("OBSERVACIONES",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        if (SerologiasEnviadas.size()==0){
            table = new PdfPTable(1);
            table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
            cell = new PdfPCell(new Phrase("No hay información!",mia));
            cell.setBorder(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        }
        int cont=0;
        for (SerologiaEnvio obj : SerologiasEnviadas) {
            cont++;
            cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            /*if  (obj.getSerologia().getParticipante()==null){//nuevo ingreso
                cell = new PdfPCell(new Phrase("NEW PARTICIPANT", miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //volumen
                cell = new PdfPCell(new Phrase("" + obj.getSerologia().getVolumen(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // edad años
                cell = new PdfPCell(new Phrase("-", mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // edad meses
                cell = new PdfPCell(new Phrase("-", mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // estudios
                cell = new PdfPCell(new Phrase("-", miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //observacion
                cell = new PdfPCell(new Phrase(obj.getSerologia().getObservacion(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if (table.getTotalHeight() > 580) {
                    table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
                    document.newPage();
                    y = 650f;
                    table = new PdfPTable(new float[]{5,10,10,15,23,25});
                    table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
                }*/
            //}else {// participante existe en BD
                //codigo
                cell = new PdfPCell(new Phrase(obj.getSerologia().getParticipante().toString(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //volumen
                cell = new PdfPCell(new Phrase("" + obj.getSerologia().getVolumen(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                //edad
                double d =  obj.getSerologia().getEdadMeses();
                double edad = d/12;
                if (edad ==0){
                    cell = new PdfPCell(new Phrase("-", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else if(edad>1 ){
                    cell = new PdfPCell(new Phrase(df.format(edad)+" años.", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else{
                    cell = new PdfPCell(new Phrase(edad+" meses.", miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                //Descripcion
                cell = new PdfPCell(new Phrase(obj.getSerologia().getDescripcion(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                //observacion
                cell = new PdfPCell(new Phrase(obj.getSerologia().getObservacion(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                if (table.getTotalHeight() > 580) {
                    table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
                    document.newPage();
                    y = 650f;
                    table = new PdfPTable(new float[]{5,10,10,15,23,25});
                    table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
                }
            //}
        }

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 10;

        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Total: "+SerologiasEnviadas.size(),miaEstudio));
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


    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messageExtremidades){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

    private String getDescripcionCatalogoProyect(String codigo,String catroot){
        for(MessageResource rnv : messageproyecto){
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

    HeaderFooterReporteEnvio() {
    }

    HeaderFooterReporteEnvio(String fechaInicio, String fechaFin, Integer numeroEnvios, Integer anioActual) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroEnvios = numeroEnvios;
        this.anioActual = anioActual;
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();

        /*ENCABEZADO**/
        Paragraph encabezado = new Paragraph("CENTRO DE SALUD SÓCRATES FLORES VIVAS.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
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
        PdfPCell cell = new PdfPCell(new Phrase("MUESTREO ANUAL COHORTE FAMILIA " + anioActual,FontFactory.getFont("COURIER", 14, java.awt.Font.ITALIC)));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Viaje: "+ this.getNumeroEnvios() +" \n ",FontFactory.getFont("COURIER", 14, java.awt.Font.ITALIC, Color.black)));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        ///writeSelectedRows(filaInicial, filaFinal, X, y, canvas).
        //0 = primera fila, -1 = todas las filas, 42 = margen izquierdo, y = valor dinamico segun lo que se va agregando a la pagina, canvas= el documento
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 45;

        table = new PdfPTable(new float[]{50,50});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Fecha Inicio: "+ this.getFechaInicio(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha Fin: "+ this.getFechaFin(),miaEstudio));
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
}

