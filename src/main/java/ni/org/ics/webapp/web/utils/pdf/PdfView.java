package ni.org.ics.webapp.web.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import ni.org.ics.webapp.domain.Retiros.Retiros;
import ni.org.ics.webapp.domain.Serologia.*;
import ni.org.ics.webapp.domain.catalogs.Razones_Retiro;
import ni.org.ics.webapp.domain.core.Participante;
import ni.org.ics.webapp.domain.core.ParticipanteProcesos;
import ni.org.ics.webapp.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.webapp.domain.hemodinamica.HemoDetalle;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoEnvio;
import ni.org.ics.webapp.domain.personal.Personal;
import ni.org.ics.webapp.domain.scancarta.DetalleParte;
import ni.org.ics.webapp.domain.scancarta.ParticipanteCarta;
import ni.org.ics.webapp.domain.scancarta.ParticipanteExtension;
import ni.org.ics.webapp.dto.ControlAsistenciaDto;
import ni.org.ics.webapp.dto.ConvalecientesEnfermoDto;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.web.utils.Constants;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
        } else
        if (model.get("TipoReporte").equals(Constants.TPR_ENVIOREPORTE)){
            ReporteEnvio(model, document, writer);
        } else
        if (model.get("TipoReporte").equals(Constants.TPR_ENVIOREPORTEBHC)){
            ReporteEnvioBhc(model, document, writer);
        } else
        if (model.get("TipoReporte").equals(Constants.TPR_ENVIOREPORTEBHC1)){
            ReporteEnvioBhc1(model, document, writer);
        } else
        if (model.get("TipoReporte").equals(Constants.TPR_REPORTERETIRO)){
            ReporteRetiro(model, document, writer);
        } else
        if (model.get("TipoReporte").equals(Constants.TPR_DATOSGENERALES)){
            datosGenerales(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_ENVIO_ENFERMO)){
            ReporteEnvioMxEnfermo(model, document, writer);
        }

        if (model.get("TipoReporte").equals(Constants.TPR_CONVALECIENTES_ENFERMO)){
            ReporteConvalecientesMxEnfermo(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_CONTROL_ASISTENCIA)){
            ReporteControlAsistencia(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_IMPRIMIR_BHC_BC6000)){
            IMPRIMIR_BHC_BC6000(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_HEMOREPORTE)){
            ReporteHemodinamica(model,document,writer);
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

    /***
     * Obtiene la descripcion de un elemeento por catKey dentro de un catalogo
     * @param catKey 1,2,3,4 etc
     * @param languaje null por defecto para espaniol
     * @param catalogo Lista con todas las opciones
     * @return valor del item
     */
    private String getMessage(String catKey, String languaje, List<MessageResource> catalogo){
        for(MessageResource message : catalogo){
            if (message.getCatKey().equalsIgnoreCase(catKey)){
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
    //region  Reporte IMPRIMR BHC BC6000
    private void IMPRIMIR_BHC_BC6000(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        Bhc_Bc6000 obj= (Bhc_Bc6000) model.get("obj");
        Participante part= (Participante) model.get("part");
        document.newPage();
        document.open();

        Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font bf12 = new Font(Font.TIMES_ROMAN, 10);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font timesRomanBoldItalic12 = new Font(Font.COURIER, 12, Font.BOLD);
        Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);

        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(DateUtil.DateToString(obj.getFec(), "dd/MM/yyyy"));
        fecha.setAlignment(Element.ALIGN_RIGHT);
       // document.add(fecha);
        Paragraph encabezado = new Paragraph("Laboratorio Clínico",FontFactory.getFont("COURIER", 16, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado);
        Paragraph encabezado2 = new Paragraph("Centro de Salud Sócrates Flores Vivas", FontFactory.getFont("COURIER",16, java.awt.Font.BOLD));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado2);
        Paragraph encabezado3 = new Paragraph("Resultados Biometría Hemática Completa", FontFactory.getFont("COURIER",16, java.awt.Font.BOLD));
        encabezado3.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado3);

        LineSeparator ls1 = new LineSeparator();
        ls1.setLineWidth(0.5f);
        document.add(new Chunk(ls1));

        /*String imageFile = "C:\\Users\\ICS\\Documents\\GitHub\\estudios-ics\\estudios-ics\\src\\main\\webapp\\resources\\img\\logo-login.png";
        Image image1 = Image.getInstance(imageFile);
        image1.setWidthPercentage(30);
        image1.setAlignment(0);
        image1.setCompressionLevel(5);
        document.add(image1);*/

        PdfPTable table = new PdfPTable(new float[]{10,50,10,10});
        table.setWidthPercentage(96f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Nombre: ",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getNomb(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Código: ",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getId_muestr(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
/**/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);
/**/
        PdfPTable table1 = new PdfPTable(new float[]{120,70,40,70,115});
        table1.setWidthPercentage(96f);
        PdfPCell cell1;
        cell1 = new PdfPCell(new Phrase("Fecha Nacimiento: ",miaNormal));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(0);
        table1.addCell(cell1);
        cell1 = new PdfPCell(new Phrase(part.getFechaNac().toLocaleString().substring(0,10),mia));
        cell1.setBorder(0);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell1);

        cell1 = new PdfPCell(new Phrase("Sexo: ",miaNormal));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(0);
        table1.addCell(cell1);
        cell1 = new PdfPCell(new Phrase(obj.getSexo(),mia));
        cell1.setBorder(0);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.addCell(cell1);

        cell1 = new PdfPCell(new Phrase("Médico:__________ ",miaNormal));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(0);
        table1.addCell(cell1);
        document.add(table1);
        /****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        PdfPTable table2 = new PdfPTable(new float[]{180,60,40,50});
        table2.setWidthPercentage(96f);
        PdfPCell cell2;
        cell2 = new PdfPCell(new Phrase("Hora que se recibe la muestra:_______ ",miaNormal));
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setBorder(0);
        table2.addCell(cell2);
        cell2 = new PdfPCell(new Phrase( " AM/PM",mia));
        cell2.setBorder(0);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell2);

        cell2 = new PdfPCell(new Phrase("Modo: ",miaNormal));
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setBorder(0);
        table2.addCell(cell2);
        cell2 = new PdfPCell(new Phrase(obj.getModo_recue(),mia));
        cell2.setBorder(0);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        table2.addCell(cell2);

        document.add(table2);


        /****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table3 = new PdfPTable(new float[]{150,90});
        table3.setWidthPercentage(96f);
        PdfPCell cell3;
        cell3 = new PdfPCell(new Phrase("Fecha y Hora de realización del Examen:",miaNormal));
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setBorder(0);
        table3.addCell(cell3);
        String hrtemp = "";
        int hrnum=0;
        if(Integer.valueOf(obj.getHora().substring(0,2).trim()) > 12){
            hrnum = Integer.valueOf(obj.getHora().substring(0,2).trim()) - 12;
            hrtemp = String.valueOf(hrnum) + obj.getHora().substring(2,5).trim() + " PM";
        }
        if(Integer.valueOf(obj.getHora().substring(0,2).trim()) <= 12){
            hrnum = Integer.valueOf(obj.getHora().substring(0,2).trim());
            hrtemp = String.valueOf(hrnum) + obj.getHora().substring(2,5).trim()+ " AM";
            if (hrnum == 12){
                hrtemp = String.valueOf(hrnum) + obj.getHora().substring(2,5).trim()+ " PM";
            }
        }
        cell3 = new PdfPCell(new Phrase(obj.getFec() + " " + hrtemp ,mia));
        cell3.setBorder(0);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        table3.addCell(cell3);

        document.add(table3);
        /****/


        /****/
        LineSeparator ls2 = new LineSeparator();
        ls2.setLineWidth(0.5f);
        document.add(new Chunk(ls2));
        /****/
        PdfPTable table4 = new PdfPTable(new float[]{10,50,10,20});
        table4.setWidthPercentage(96f);
        PdfPCell cell4;
        cell4 = new PdfPCell(new Phrase("LEU: ",miaNormal));
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setBorder(0);
        table4.addCell(cell4);
        cell4 = new PdfPCell(new Phrase(String.valueOf(obj.getWbc()) + " x 10^3/µL ",mia));
        cell4.setBorder(0);
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(cell4);

        cell4 = new PdfPCell(new Phrase("ERI: ",miaNormal));
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setBorder(0);
        table4.addCell(cell4);
        cell4 = new PdfPCell(new Phrase(String.valueOf(obj.getRbc())+ " x10^6/µL ",mia));
        cell4.setBorder(0);
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table4.addCell(cell4);
        document.add(table4);

        /*****/
        /****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table5 = new PdfPTable(new float[]{10,50,10,20});
        table5.setWidthPercentage(96f);
        PdfPCell cell5;
        cell5 = new PdfPCell(new Phrase(" ",miaNormal));
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setBorder(0);
        table5.addCell(cell5);
        cell5 = new PdfPCell(new Phrase( "x10^3/µL\t  %",mia));
        cell5.setBorder(0);
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        table5.addCell(cell5);

        cell5 = new PdfPCell(new Phrase("HGB: ",miaNormal));
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setBorder(0);
        table5.addCell(cell5);
        cell5 = new PdfPCell(new Phrase(String.valueOf(obj.getHgb())+ " g/Dl  ",mia));
        cell5.setBorder(0);
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        table5.addCell(cell5);
        document.add(table5);

        /*****/
         /****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table6 = new PdfPTable(new float[]{10,50,10,20});
        table6.setWidthPercentage(96f);
        PdfPCell cell6;
        cell6 = new PdfPCell(new Phrase("LIN:",miaNormal));
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setBorder(0);
        table6.addCell(cell6);
        cell6 = new PdfPCell(new Phrase(String.valueOf(obj.getLinf()) + "      " + String.valueOf(obj.getLinf_porcentaje()) ,mia));
        cell6.setBorder(0);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(cell6);

        cell6 = new PdfPCell(new Phrase("HCT: ",miaNormal));
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setBorder(0);
        table6.addCell(cell6);
        cell6 = new PdfPCell(new Phrase(String.valueOf(obj.getHct())+ " % ",mia));
        cell6.setBorder(0);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        table6.addCell(cell6);
        document.add(table6);
        /*****/
        /****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table7 = new PdfPTable(new float[]{10,50,10,20});
        table7.setWidthPercentage(96f);
        PdfPCell cell7;
        cell7 = new PdfPCell(new Phrase("NEU:",miaNormal));
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell7.setBorder(0);
        table7.addCell(cell7);
        cell7 = new PdfPCell(new Phrase(String.valueOf(obj.getNeu()) + "      " + String.valueOf(obj.getNeu_porcentaje()) ,mia));
        cell7.setBorder(0);
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(cell7);

        cell7 = new PdfPCell(new Phrase("VCM: ",miaNormal));
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell7.setBorder(0);
        table7.addCell(cell7);
        cell7 = new PdfPCell(new Phrase(String.valueOf(obj.getMcv())+ " fL ",mia));
        cell7.setBorder(0);
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        table7.addCell(cell7);
        document.add(table7);
        /*****/
/****/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table8 = new PdfPTable(new float[]{10,50,10,20});
        table8.setWidthPercentage(96f);
        PdfPCell cell8;
        cell8 = new PdfPCell(new Phrase("MON:",miaNormal));
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell8.setBorder(0);
        table8.addCell(cell8);
        cell8 = new PdfPCell(new Phrase(String.valueOf(obj.getMon()) + "      " + String.valueOf(obj.getMon_porcentaje()),mia));
        cell8.setBorder(0);
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(cell8);

        cell8 = new PdfPCell(new Phrase("HCM: ",miaNormal));
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell8.setBorder(0);
        table8.addCell(cell8);
        cell8 = new PdfPCell(new Phrase(String.valueOf(obj.getMch())+" pg  ",mia));
        cell8.setBorder(0);
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        table8.addCell(cell8);
        document.add(table8);

        /**/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table9 = new PdfPTable(new float[]{10,50,10,20});
        table9.setWidthPercentage(96f);
        PdfPCell cell9;
        cell9 = new PdfPCell(new Phrase("EOS:",miaNormal));
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell9.setBorder(0);
        table9.addCell(cell9);
        cell9 = new PdfPCell(new Phrase(String.valueOf(obj.getEos())+ "       " + String.valueOf(obj.getEos_porcentaje()),mia));
        cell9.setBorder(0);
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(cell9);

        cell9 = new PdfPCell(new Phrase("CHCM: ",miaNormal));
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell9.setBorder(0);
        table9.addCell(cell9);
        cell9 = new PdfPCell(new Phrase(String.valueOf(obj.getMchc())+" g/dL  ",mia));
        cell9.setBorder(0);
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        table9.addCell(cell9);
        document.add(table9);

        /**/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table10 = new PdfPTable(new float[]{10,50,10,20});
        table10.setWidthPercentage(96f);
        PdfPCell cell10;
        cell10 = new PdfPCell(new Phrase("BAS:",miaNormal));
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell10.setBorder(0);
        table10.addCell(cell10);
        cell10 = new PdfPCell(new Phrase(String.valueOf(obj.getBas()) + "      " + String.valueOf(obj.getBas_porcentaje()),mia));
        cell10.setBorder(0);
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(cell10);

        cell10 = new PdfPCell(new Phrase("PLT: ",miaNormal));
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell10.setBorder(0);
        table10.addCell(cell10);
        cell10 = new PdfPCell(new Phrase(String.valueOf(obj.getPlt())+" x10^3/µLT  ",mia));
        cell10.setBorder(0);
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        table10.addCell(cell10);
        document.add(table10);

        /**/
      /*  document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
       /* PdfPTable table11 = new PdfPTable(new float[]{10,50,10,20});
        table11.setWidthPercentage(96f);
        PdfPCell cell11;
        cell11 = new PdfPCell(new Phrase("BAS:",miaNormal));
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell11.setBorder(0);
        table11.addCell(cell11);
        cell11 = new PdfPCell(new Phrase(String.valueOf(obj.getBas()) + "",mia));
        cell11.setBorder(0);
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(cell11);

        cell11 = new PdfPCell(new Phrase("PLT: ",miaNormal));
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell11.setBorder(0);
        table11.addCell(cell11);
        cell11 = new PdfPCell(new Phrase(String.valueOf(obj.getPlt())+" x10^3/µLT  ",mia));
        cell11.setBorder(0);
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        table11.addCell(cell11);
        document.add(table11);*/

        /**/
        document.add(table);
        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        /****/
        /****/
        PdfPTable table12 = new PdfPTable(new float[]{10,50,10,20});
        table12.setWidthPercentage(96f);
        PdfPCell cell12;
        cell12 = new PdfPCell(new Phrase(" ",miaNormal));
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell12.setBorder(0);
        table12.addCell(cell12);
        cell12 = new PdfPCell(new Phrase( "",mia));
        cell12.setBorder(0);
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        table12.addCell(cell12);

        cell12 = new PdfPCell(new Phrase("VMP: ",miaNormal));
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell12.setBorder(0);
        table12.addCell(cell12);
        cell12 = new PdfPCell(new Phrase(String.valueOf(obj.getMpv())+" fl ",mia));
        cell12.setBorder(0);
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        table12.addCell(cell12);
        document.add(table12);


        /****/
        LineSeparator ls3 = new LineSeparator();
        ls3.setLineWidth(0.5f);
        document.add(new Chunk(ls3));
        /****/

        PdfPTable table13 = new PdfPTable(new float[]{80});
        table13.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Hora que se entrega el resultado: ________________AM / PM",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table13.addCell(cell);
        document.add(table13);

        table = new PdfPTable(new float[]{80});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Persona a quien se entrega el resultado: _______________",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        document.add(table);

        /**/

        /****/
        /**/
        PdfPTable table19 = new PdfPTable(new float[]{48});
        table19.setWidthPercentage(96f);
        table19.addCell(createCell("         ", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table19);
        document.add(table19);
        document.add(table19);

        /****/
        PdfPTable table15 = new PdfPTable(new float[]{10});
        table15.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Bioanalista:",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table15.addCell(cell);

        document.add(table15);


        PdfPTable table16 = new PdfPTable(new float[]{10});
        table16.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("     Número:",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table16.addCell(cell);
        document.add(table16);

        /**/

        /****/



        /*table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Asentimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getAsentimiento().toString(),"SCANCARTA"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);*/
        //AQUI TAMBIEN VALIDAR

        /*table = new PdfPTable(new float[]{38,58});
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
        }*/

        /*table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Observación: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        String observa = (obj.().equals("")) ? "Ninguna" : obj.getObservacion().toString();
        cell = new PdfPCell(new Phrase(observa,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        /*** Razón anulada ***/
        /*if(obj.isAnulada() == true) {
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
        }*/

        document.close();

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
    private void ReporteEnvioBhc1(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<BhcEnvio> datos_Envio = (List<BhcEnvio>)  model.get("BhcEnviadas");

        List<Bhc_Detalles_Envio> Detalles_Muestras_Serologia = (List<Bhc_Detalles_Envio>) model.get("allBhc");
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
        String enviado_hacia = "";
        String hora_envio = "";
        String temp="";
        for (BhcEnvio obj : datos_Envio){
            enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
            enviado_hacia = this.getCatalogoSitio("" + obj.getSitio_destino(), "CAT_SITIOS_DESTINO_SEROLOGIA");
            hora_envio = obj.getHora();
            temp= ""+ obj.getTemperatura();
        }

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteEnvioBhc1 footer = new HeaderFooterReporteEnvioBhc1(f1, f2.concat(" Hora: "+hora_envio), numeroEnvios, yearsNow, enviado_desde, temp);
        writer.setPageEvent(footer);

        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_LEFT);
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

        cell = new PdfPCell(new Phrase("PROCESADAS CSSFV",mia));
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
        for (Bhc_Detalles_Envio obj : Detalles_Muestras_Serologia) {
            cont++;
            cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //codigo
            cell = new PdfPCell(new Phrase( obj.getBhc().getParticipante().toString(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //volumen
            cell = new PdfPCell(new Phrase(""+obj.getBhc().getVolumen(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //edad
            double d = obj.getBhc().getEdadMeses();
            Double edad = Math.floor(d/12);

            cell = new PdfPCell(new Phrase(""+edad.intValue(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            Double edadMeses = d % 12;

            cell = new PdfPCell(new Phrase(""+edadMeses.intValue(), miaNormal));
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
            cell = new PdfPCell(new Phrase(obj.getBhc().getDescripcion(), FontObservacion));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //observacion
            cell = new PdfPCell(new Phrase(obj.getBhc().getProcesadaCSFV(), FontObservacion));
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

        y = y - table.getTotalHeight() - 10;
        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(""+ Detalles_Muestras_Serologia.size(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 45;

        table = new PdfPTable(new float[]{20,20,60});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(" ",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Sitio Destino: C.S.F.V SOCRATES FLORES"   ,miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        document.close();
    }
    //region todo Reporte Envio de Muestras
    private void ReporteEnvioBhc(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<BhcEnvio> datos_Envio = (List<BhcEnvio>)  model.get("BhcEnviadas");

        List<Bhc_Detalles_Envio> Detalles_Muestras_Serologia = (List<Bhc_Detalles_Envio>) model.get("allBhc");
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
        String enviado_hacia = "";
        String hora_envio = "";
        String temp="";
        for (BhcEnvio obj : datos_Envio){
            enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
            enviado_hacia = this.getCatalogoSitio("" + obj.getSitio_destino(), "CAT_SITIOS_DESTINO_SEROLOGIA");
            hora_envio = obj.getHora();
            temp= ""+ obj.getTemperatura();
        }

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteEnvioBhc footer = new HeaderFooterReporteEnvioBhc(f1, f2.concat(" Hora: "+hora_envio), numeroEnvios, yearsNow, enviado_desde, temp);
        writer.setPageEvent(footer);

        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_LEFT);
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
        for (Bhc_Detalles_Envio obj : Detalles_Muestras_Serologia) {
            cont++;
            cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //codigo
            cell = new PdfPCell(new Phrase( obj.getBhc().getParticipante().toString(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //volumen
            cell = new PdfPCell(new Phrase(""+obj.getBhc().getVolumen(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //edad
            double d = obj.getBhc().getEdadMeses();
            Double edad = Math.floor(d/12);

            cell = new PdfPCell(new Phrase(""+edad.intValue(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            Double edadMeses = d % 12;

            cell = new PdfPCell(new Phrase(""+edadMeses.intValue(), miaNormal));
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
            cell = new PdfPCell(new Phrase(obj.getBhc().getDescripcion(), FontObservacion));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //observacion
            cell = new PdfPCell(new Phrase(obj.getBhc().getObservacion(), FontObservacion));
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

        y = y - table.getTotalHeight() - 10;
        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(""+ Detalles_Muestras_Serologia.size(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 45;

        table = new PdfPTable(new float[]{20,20,60});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(" ",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Sitio Destino: C.S.F.V SOCRATES FLORES"   ,miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        document.close();
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
                Double edad = Math.floor(d/12);

                cell = new PdfPCell(new Phrase(""+edad.intValue(), miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                Double edadMeses = d % 12;

                cell = new PdfPCell(new Phrase(""+edadMeses.intValue(), miaNormal));
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

//region REPORTE ENVIO MUESTRAS DE ENFERMOS
private void ReporteEnvioMxEnfermo(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
    List<MuestraEnfermoEnvio> datos_Envio = (List<MuestraEnfermoEnvio>)  model.get("SerologiasEnviadas");
    List<MessageResource> tiposConsultas = (List<MessageResource>)  model.get("tiposConsultas");

    List<MuestraEnfermoDetalleEnvio> Detalles_Muestras_Serologia = (List<MuestraEnfermoDetalleEnvio>) model.get("allSerologia");
    messageSitio = (List<MessageResource>) model.get("sitios");

    Integer numeroEnvios = (Integer) model.get("nEnvios");
    String f1 = (String) model.get("fechaInicio");
    String f2 = (String) model.get("fechaFin");

    document.setPageSize(PageSize.LEGAL.rotate());
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
    for (MuestraEnfermoEnvio obj : datos_Envio){
        enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
        hora_envio = obj.getHora();
        temp= ""+ obj.getTemperatura();
    }

    //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
    HeaderFooterReporteEnvioEnfermo footer = new HeaderFooterReporteEnvioEnfermo(f1, f2, hora_envio, numeroEnvios, yearsNow, enviado_desde, temp);
    writer.setPageEvent(footer);

    Font miaNormal = new Font(Font.COURIER,11, Font.NORMAL);
    Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
    Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
    Font miaEstudio = new Font(Font.COURIER,9);
    Date objDate = new Date();
    DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
    fecha.setAlignment(Element.ALIGN_RIGHT);
    float y = 450f; //posicion coordenada y en la pagina.. mientras mas disminuye mas se acerca al fin (botton) de la pagina
    PdfPTable table = new PdfPTable(new float[]{3,15,5,11,11,11,11,8,6,19});
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

    cell = new PdfPCell(new Phrase("CAT",mia));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Consulta",mia));
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("FIS",mia));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("FIF",mia));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Toma Mx",mia));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    //cell.setColspan(2);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("VOLUMEN",mia));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("TUBO",mia));
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
    for (MuestraEnfermoDetalleEnvio obj : Detalles_Muestras_Serologia) {
        //num
        cont++;
        cell = new PdfPCell(new Phrase(""+cont, miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //codigo
        cell = new PdfPCell(new Phrase(obj.getMuestra().getCodigo(), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //cat
        cell = new PdfPCell(new Phrase(obj.getMuestra().getCategoria(), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //consulta
        cell = new PdfPCell(new Phrase(getMessage(obj.getMuestra().getConsulta(), null, tiposConsultas), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //Fis
        cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getMuestra().getFis(), Constants.STRING_FORMAT_DD_MM_YYYY), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //Fif
        cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getMuestra().getFif(), Constants.STRING_FORMAT_DD_MM_YYYY), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        //Tomamx
        cell = new PdfPCell(new Phrase( DateUtil.DateToString(obj.getMuestra().getFechaRecepcion(), Constants.STRING_FORMAT_DD_MM_YYYY), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        /*cell = new PdfPCell(new Phrase("10:00 a.m.", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);*/
        //volumen
        cell = new PdfPCell(new Phrase(""+obj.getMuestra().getVolumen(), miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        //tubo
        cell = new PdfPCell(new Phrase("ROJO", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        //observacion
        cell = new PdfPCell(new Phrase(obj.getMuestra().getObservacion(), FontObservacion));
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

    table = new PdfPTable(new float[]{30,40,30});
    table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
    cell = new PdfPCell(new Phrase("Entrega Bioanalista: _________________",miaEstudio));
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(0);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase(""));
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setBorder(PdfPCell.NO_BORDER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("",miaEstudio));
    cell.setBorder(0);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Cod. Bioanalista: _____________",miaEstudio));
    cell.setVerticalAlignment(Element.ALIGN_LEFT);
    cell.setBorder(0);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Firma: ______________",miaEstudio));
    cell.setVerticalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(0);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Recibe CNDR: ______________",miaEstudio));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(0);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase(""));
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setBorder(PdfPCell.NO_BORDER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("",miaEstudio));
    cell.setBorder(0);
    table.addCell(cell);


    cell = new PdfPCell(new Phrase("Recibe Conductor: ______________",miaEstudio));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(0);
    table.addCell(cell);

    table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
    document.close();
}

    //region REPORTE CONTROL ASISTENCIA DE PERSONAL
    private void ReporteControlAsistencia(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<ControlAsistenciaDto> datos_Envio1 = (List<ControlAsistenciaDto>)  model.get("controlAsist");
       // List<MessageResource> tiposConsultas = (List<MessageResource>)  model.get("tiposConsultas");

        List<ControlAsistenciaDto> datos_Envio = (List<ControlAsistenciaDto>) model.get("controlAsist");
        messageSitio = (List<MessageResource>) model.get("sitios");

        Integer numeroEnvios = (Integer) model.get("nEnvios");
        String f1 = (String) model.get("fechaInicio");
        String f2 = (String) model.get("fechaFin");

        document.setPageSize(PageSize.LEGAL.rotate());
        //document = new Document(PageSize.A4,20,20,20,20);
        document.newPage();

        document.open();
        //  Date fecha_inicio = DateUtil.StringToDate(f1,"dd/MM/yyyy");
        //   Calendar calendar2 = Calendar.getInstance();
        //  calendar2.setTime(fecha_inicio);
        // int yearsNow  = calendar2.get(Calendar.YEAR);

        String enviado_desde = "";
        String hora_envio = "";
        String temp="";
      /*  for (ConvalecientesEnfermoDto obj : datos_Envio){
            enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
            hora_envio = obj.getHora();
            temp= ""+ obj.getTemperatura();
        }*/

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteControlAsistencia footer = new HeaderFooterReporteControlAsistencia();
        writer.setPageEvent(footer);
/* ENCABEZADO */
    /*    float y1 = document.getPageSize().getHeight() - 60;
        float x1 = document.getPageSize().getWidth() - (document.getPageSize().getWidth() - 50);

        PdfPTable table1 = new PdfPTable(new float[]{50, 50});
        table1.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

        PdfPCell cell1 = new PdfPCell(new Phrase("ESTUDIO A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        table1.addCell(cell1);



        cell1 = new PdfPCell(new Phrase("Lista de Convalecientes de Mx Enfermos", FontFactory.getFont("COURIER", 16, java.awt.Font.ITALIC)));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        table1.addCell(cell1);*/


        Font miaNormal = new Font(Font.COURIER,11, Font.NORMAL);
        Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_RIGHT);
        float y = 520f; //posicion coordenada y en la pagina.. mientras mas disminuye mas se acerca al fin (botton) de la pagina
        PdfPTable table = new PdfPTable(new float[]{5,4,3,5,15,7,10,15,15,15});
        table.setHeaderRows(1);
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        PdfPCell cell;
        Paragraph paragraph1 = new Paragraph();
        addEmptyLine(paragraph1, 1);
        document.add(paragraph1);



        cell = new PdfPCell(new Phrase("NOMBRE_COMPLETO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CASA",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("HORA_ENTRADA",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("HORA_SALIDA",mia));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("FECHA ASISTENCIA",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("IDENTIFICADOR EQUIPO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("LATITUD",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("LONGITUD",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("FECHA REGISTRO",mia));
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
        for ( ControlAsistenciaDto obj : datos_Envio) {
            //num
            cont++;
           /* cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);*/
            //codigo
            //cat
            cell = new PdfPCell(new Phrase(obj.getNombre_completo().toString() , miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getHoraentrada().toString(), miaNormal)  );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            //consulta
            cell = new PdfPCell(new Phrase((obj.getHorasalida()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //Fis
            cell = new PdfPCell(new Phrase(obj.getFechaasistencia().toString() ,miaNormal ));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase( (obj.getIdentificador_equipo()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //Tomamx
            cell = new PdfPCell(new Phrase( (obj.getLatitud().toString()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        /*cell = new PdfPCell(new Phrase("10:00 a.m.", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);*/
            //volumen
            cell = new PdfPCell(new Phrase(""+obj.getLongitud().toString(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //tubo
            cell = new PdfPCell(new Phrase(obj.getFecha_registro(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            if (table.getTotalHeight() > 450) {     //580
                table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
                document.newPage();
                y = 500f;
                table =new PdfPTable(new float[]{5,4,3,5,15,7,10,15,15,15});
                table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
            }
        }
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 10;
        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Total: "+ datos_Envio.size(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 45;

        table = new PdfPTable(new float[]{30,40,30});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Responsable de Lista: _________________",miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);



        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        document.close();
    }
    //endregion

    //region REPORTE CONVALECIENTES DE MUESTRAS DE ENFERMOS
    private void ReporteConvalecientesMxEnfermo(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        List<ConvalecientesEnfermoDto> datos_Envio1 = (List<ConvalecientesEnfermoDto>)  model.get("ConvalecientesPrint");
        List<MessageResource> tiposConsultas = (List<MessageResource>)  model.get("tiposConsultas");

        List<ConvalecientesEnfermoDto> datos_Envio = (List<ConvalecientesEnfermoDto>) model.get("ConvalecientesPrint");
        messageSitio = (List<MessageResource>) model.get("sitios");

        Integer numeroEnvios = (Integer) model.get("nEnvios");
        String f1 = (String) model.get("fechaInicio");
        String f2 = (String) model.get("fechaFin");

        document.setPageSize(PageSize.LEGAL.rotate());
        //document = new Document(PageSize.A4,20,20,20,20);
        document.newPage();

        document.open();
      //  Date fecha_inicio = DateUtil.StringToDate(f1,"dd/MM/yyyy");
     //   Calendar calendar2 = Calendar.getInstance();
      //  calendar2.setTime(fecha_inicio);
       // int yearsNow  = calendar2.get(Calendar.YEAR);

        String enviado_desde = "";
        String hora_envio = "";
        String temp="";
      /*  for (ConvalecientesEnfermoDto obj : datos_Envio){
            enviado_desde = this.getCatalogoSitio("" + obj.getSitio(), "CAT_SITIOS_ENVIO_SEROLOGIA");
            hora_envio = obj.getHora();
            temp= ""+ obj.getTemperatura();
        }*/

        //se inicializa y setea el manejador de evento para el encabezado y pie de pagina
        HeaderFooterReporteConvEnfermo footer = new HeaderFooterReporteConvEnfermo();
         writer.setPageEvent(footer);
/* ENCABEZADO */
    /*    float y1 = document.getPageSize().getHeight() - 60;
        float x1 = document.getPageSize().getWidth() - (document.getPageSize().getWidth() - 50);

        PdfPTable table1 = new PdfPTable(new float[]{50, 50});
        table1.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

        PdfPCell cell1 = new PdfPCell(new Phrase("ESTUDIO A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        table1.addCell(cell1);



        cell1 = new PdfPCell(new Phrase("Lista de Convalecientes de Mx Enfermos", FontFactory.getFont("COURIER", 16, java.awt.Font.ITALIC)));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setBorder(PdfPCell.NO_BORDER);
        table1.addCell(cell1);*/


        Font miaNormal = new Font(Font.COURIER,11, Font.NORMAL);
        Font FontObservacion = new Font(Font.COURIER,9, Font.NORMAL);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaEstudio = new Font(Font.COURIER,9);
        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
        fecha.setAlignment(Element.ALIGN_RIGHT);
        float y = 520f; //posicion coordenada y en la pagina.. mientras mas disminuye mas se acerca al fin (botton) de la pagina
        PdfPTable table = new PdfPTable(new float[]{5,4,3,5,15,7,10,15,15,15});
        table.setHeaderRows(1);
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        PdfPCell cell;
        Paragraph paragraph1 = new Paragraph();
        addEmptyLine(paragraph1, 1);
        document.add(paragraph1);



        cell = new PdfPCell(new Phrase("CÓDIGO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CASA",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CAT",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("DIAS CONV",mia));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE PARTICIPANTE",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("TELEFONO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("BARRIO",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NOMBRE TUTOR",mia));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("DIRECCION",mia));
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
        for (ConvalecientesEnfermoDto obj : datos_Envio) {
            //num
            cont++;
           /* cell = new PdfPCell(new Phrase(""+cont, miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);*/
            //codigo
            //cat
            cell = new PdfPCell(new Phrase(obj.getParticipante().toString() , miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getCodigoCasa().toString(), miaNormal)  );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            //consulta
            cell = new PdfPCell(new Phrase((obj.getCategoria()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            //Fis
            cell = new PdfPCell(new Phrase(obj.getDiasConv().toString() ,miaNormal ));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase( (obj.getNombreCompleto()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //Tomamx
            cell = new PdfPCell(new Phrase( (obj.getTelefono()), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        /*cell = new PdfPCell(new Phrase("10:00 a.m.", miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);*/
            //volumen
            cell = new PdfPCell(new Phrase(""+obj.getBarrio(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //tubo
            cell = new PdfPCell(new Phrase(obj.getNombreTutor(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getDireccion(), miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //observacion
            cell = new PdfPCell(new Phrase(obj.getObservacion(), FontObservacion));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            if (table.getTotalHeight() > 450) {     //580
                table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
                document.newPage();
                y = 500f;
                table =new PdfPTable(new float[]{5,4,3,5,15,7,10,15,15,15});
                table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
            }
        }
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 10;
        table = new PdfPTable(new float[]{100});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Total: "+ datos_Envio.size(),miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - table.getTotalHeight() - 45;

        table = new PdfPTable(new float[]{30,40,30});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase("Responsable de Lista: _________________",miaEstudio));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("",miaEstudio));
        cell.setBorder(0);
        table.addCell(cell);



        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        document.close();
    }
    //endregion

    private void ReporteHemodinamica(Map<String, Object> model, Document document, PdfWriter writer) throws DocumentException {
        DatosHemodinamica obj = (DatosHemodinamica) model.get("obj");
        List<HemoDetalle> ListDetail = (List<HemoDetalle>) model.get("detalle");
        messageExtremidades = (List<MessageResource>) model.get("extremidades");
        document.setPageSize(PageSize.A4.rotate());
        document.setMargins(10,10,200,0);
        document.open();
        PdfPTable table;
        String clasifica = ListDetail.get(0).getSigno();
        String centroName = this.getDescripcionCatalogo(""+obj.getUnidadSalud(),"CAT_CENTRO_SALUD");
        HeaderAndFooterHemodinamica event = new HeaderAndFooterHemodinamica(obj, clasifica, centroName);
        writer.setPageEvent(event);
        int maximoCol = 18; //18 es correcto
        Double tablas = Math.ceil((double)ListDetail.size()/maximoCol);
        int conDetalle = 0;
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font helv_7 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
        for (int t = 1; t <= tablas.intValue() ; t++) {
            if (conDetalle > 0 && conDetalle % 18 == 0) {// 18 es correcto
                document.newPage();
            }
            /*********************************************/
            table = new PdfPTable(new float[]{3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f}); //19
            table.setWidthPercentage(100);
            table.addCell(new Phrase("Fecha", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Hora ", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Nivel de Consciencia ", helv_7));
            table.completeRow();
            table.addCell(new Phrase("P/A mmHg", helv_7));
            table.completeRow();
            table.addCell(new Phrase("PP mmHg", helv_7));
            table.completeRow();
            table.addCell(new Phrase("PAM mmHg", helv_7));
            table.completeRow();
            table.addCell(new Phrase("FC por Minuto", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Fr por Minuto", helv_7));
            table.completeRow();
            table.addCell(new Phrase("T°C", helv_7));
            table.completeRow();
            table.addCell(new Phrase("SA02%", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Extremidades", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Llenado Capilar (seg)", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Pulso (Calidad)", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Diuresis Ml/Kg/Hr", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Cantidad Orina (Ml)", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Cargas I.V Ml/Kg/Hr", helv_7));
            table.completeRow();
            table.addCell(new Phrase("S.R.O", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Densidad Urinaria", helv_7));
            table.completeRow();
            table.addCell(new Phrase("Validado por", helv_7));
            table.completeRow();
            /*********************************************/

            for (int i = 0; i <= maximoCol; i++) {
                if (i == 0) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(DateUtil.DateToString(l.getFecha(), "dd/MM/yyyy"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 1) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getHora(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 2) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getNivelConciencia(), "NIVELCONCIENCIA"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 3) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    String part1 = "", part2 = "";
                    Integer alerta;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            for (int indice = 0; indice < l.getPs().length(); indice++) {
                                String string = l.getPs();
                                String[] parts = string.split("/");
                                alerta = parts.length;
                                part1 = parts[0];
                                part2 = (alerta > 1) ? parts[1] : l.getPd();
                            }
                            cell1[celda].setPhrase(new Phrase(Integer.parseInt(part1) + "/" + Integer.parseInt(part2), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 4) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getPp(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 5) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getPam(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 6) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getFc(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 7) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getFr(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 8) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getTc(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 9) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getSa(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 10) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getExtremidades(), "EXTREMIDADES"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 11) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getLlenadoCapilar(), "LLENADOCAPILAR"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 12) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {

                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getPulsoCalidad(), "PULSOCALIDAD"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 13) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getDiuresis(), "DIURESIS"), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }

                if (i == 14) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            String cantiOrina = (l.getCantidadOrina() == null) ? "" : l.getCantidadOrina().toString();
                            cell1[celda].setPhrase(new Phrase(cantiOrina, dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 15) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            String carga = (l.getCargasIV() == null) ? "" : l.getCargasIV().toString();
                            cell1[celda].setPhrase(new Phrase(carga, dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }

                if (i == 16) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            String intrav = (l.getSro() == null) ? "" : l.getSro().toString();
                            cell1[celda].setPhrase(new Phrase(intrav, dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }

                if (i == 17) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getDensidadUrinaria(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 18) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(""+l.getPersonaValida(), dataFont));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
            }
            conDetalle = maximoCol * t;
            document.add(table);
        }
        document.close();
    }

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

/***
 * Clase para manejar el encabezado y pie de pagina del reporte de envios BHC
 */
class HeaderFooterReporteEnvioBhc extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
    Font miaEstudio = new Font(Font.COURIER,9);
    private String fechaInicio;
    private String fechaFin;
    private Integer numeroEnvios;
    private Integer anioActual;
    private String sitio;
    private String temperatura;

    HeaderFooterReporteEnvioBhc() {
    }

    HeaderFooterReporteEnvioBhc(String fechaInicio, String fechaFin, Integer numeroEnvios, Integer anioActual, String sitio, String temperatura) {
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
        Paragraph encabezado2 = new Paragraph("RECEPCIÓN DE MUESTRAS BHC.", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
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

class HeaderFooterReporteEnvioBhc1 extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
    Font miaEstudio = new Font(Font.COURIER,9);
    private String fechaInicio;
    private String fechaFin;
    private Integer numeroEnvios;
    private Integer anioActual;
    private String sitio;
    private String temperatura;

    HeaderFooterReporteEnvioBhc1() {
    }

    HeaderFooterReporteEnvioBhc1(String fechaInicio, String fechaFin, Integer numeroEnvios, Integer anioActual, String sitio, String temperatura) {
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
        Paragraph encabezado2 = new Paragraph("REPORTE DE BHC", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
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

/***
 * Clase para manejar el encabezado y pie de pagina del reporte de envios serologia
 */
class HeaderFooterReporteEnvioEnfermo extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
    Font courier10Normal = new Font(Font.COURIER, 10);
    private String fechaInicio;
    private String fechaFin;
    private Integer numeroEnvios;
    private Integer anioActual;
    private String sitio;
    private String temperatura;
    private String horaEnvio;

    HeaderFooterReporteEnvioEnfermo() {
    }

    HeaderFooterReporteEnvioEnfermo(String fechaInicio, String fechaFin, String horaEnvio, Integer numeroEnvios, Integer anioActual, String sitio, String temperatura) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroEnvios = numeroEnvios;
        this.anioActual = anioActual;
        this.sitio = sitio;
        this.temperatura = temperatura;
        this.horaEnvio = horaEnvio;
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();

        /* ENCABEZADO */
        float y = document.getPageSize().getHeight() - 60;
        float x = document.getPageSize().getWidth() - (document.getPageSize().getWidth() - 50);

        PdfPTable table = new PdfPTable(new float[]{50, 50});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

        PdfPCell cell = new PdfPCell(new Phrase("ESTUDIO A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(this.getSitio(), FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Envío de muestras Serológicas", FontFactory.getFont("COURIER", 16, java.awt.Font.ITALIC)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", courier10Normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 60;
/*
        Paragraph encabezado1 = new Paragraph("Estudio A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado1.setAlignment(Element.ALIGN_CENTER);
        Paragraph encabezado2 = new Paragraph(this.getSitio(), FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        Paragraph encabezado3 = new Paragraph("ENVIO DE MUESTRAS DE ENFERMOS", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
        encabezado3.setAlignment(Element.ALIGN_CENTER);

        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado1, x, y, 0);
        y = y - 20;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado2, x, y, 0);

        y = y - 20;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado3, x, y, 0);
        y = y - 15;
*/
        table = new PdfPTable(new float[]{25, 25, 15, 17, 18});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
       /* cell = new PdfPCell(new Phrase("Fecha Inicio: "+ this.getFechaInicio(), courier10Normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);*/
        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha : " + this.getFechaFin(), courier10Normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Viaje: " + this.getNumeroEnvios() + " \n ", courier10Normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Hora: " + this.getHoraEnvio() + " \n ", courier10Normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Temperatura: " + temperatura, courier10Normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 10;

        table = new PdfPTable(1);
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
        cell = new PdfPCell(new Phrase(" ", courier10Normal));
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

    public String getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(String horaEnvio) {
        this.horaEnvio = horaEnvio;
    }
}


   // ***
   //         * Clase para manejar el encabezado y pie de pagina del reporte de Convalecientes
    //*/
    class HeaderFooterReporteConvEnfermo extends PdfPageEventHelper {
        Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
        Font courier10Normal = new Font(Font.COURIER,10);
        private String fechaInicio;
        private String fechaFin;
        private Integer numeroEnvios;
        private Integer anioActual;
        private String sitio;
        private String temperatura;
        private String horaEnvio;


        HeaderFooterReporteConvEnfermo( ) {

        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

            String fecha = dateFormat.format(new Date());

        /* ENCABEZADO */
            float y = document.getPageSize().getHeight() - 30;
            float x = document.getPageSize().getWidth() - (document.getPageSize().getWidth() - 50);

            PdfPTable table = new PdfPTable(new float[]{50, 50});
            table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

            PdfPCell cell = new PdfPCell(new Phrase("ESTUDIO A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Listado de Casos en Seguimiento " +  fecha , FontFactory.getFont("COURIER", 16, java.awt.Font.ITALIC)));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", courier10Normal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
            y = y - 30;
/*
        Paragraph encabezado1 = new Paragraph("Estudio A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado1.setAlignment(Element.ALIGN_CENTER);
        Paragraph encabezado2 = new Paragraph(this.getSitio(), FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        Paragraph encabezado3 = new Paragraph("ENVIO DE MUESTRAS DE ENFERMOS", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
        encabezado3.setAlignment(Element.ALIGN_CENTER);

        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado1, x, y, 0);
        y = y - 20;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado2, x, y, 0);

        y = y - 20;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, encabezado3, x, y, 0);
        y = y - 15;
*/
        /*    table = new PdfPTable(new float[]{25, 25, 15, 17, 18});
            table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
       /* cell = new PdfPCell(new Phrase("Fecha Inicio: "+ this.getFechaInicio(), courier10Normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);*/
        /*    cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha : "+ this.getFechaFin(), courier10Normal));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Viaje: "+ this.getNumeroEnvios() +" \n ", courier10Normal));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Hora: "+ this.getHoraEnvio() +" \n ", courier10Normal));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Temperatura: "+ temperatura, courier10Normal));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
            y = y - 10;

            table = new PdfPTable(1);
            table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));
            cell = new PdfPCell(new Phrase(" ", courier10Normal));
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

}
// ***
//         * Clase para manejar el encabezado y pie de pagina del reporte de Convalecientes
//*/
class HeaderFooterReporteControlAsistencia extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 12, Font.NORMAL);
    Font courier10Normal = new Font(Font.COURIER,10);
    private String fechaInicio;
    private String fechaFin;
    private Integer numeroEnvios;
    private Integer anioActual;
    private String sitio;
    private String temperatura;
    private String horaEnvio;


   /* HeaderFooterReporteConvEnfermo( ) {

    }
*/
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        String fecha = dateFormat.format(new Date());

        /* ENCABEZADO */
        float y = document.getPageSize().getHeight() - 30;
        float x = document.getPageSize().getWidth() - (document.getPageSize().getWidth() - 50);

        PdfPTable table = new PdfPTable(new float[]{50, 50});
        table.setTotalWidth(document.getPageSize().getRight() - document.getPageSize().getLeft(84));

        PdfPCell cell = new PdfPCell(new Phrase("ESTUDIO A2CARES", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("", FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Listado de Control de Asistencia " +  fecha , FontFactory.getFont("COURIER", 16, java.awt.Font.ITALIC)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(" ", courier10Normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        table.writeSelectedRows(0, -1, 42, y, writer.getDirectContent());
        y = y - 30;

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
}





class HeaderAndFooterHemodinamica extends PdfPageEventHelper{

    Font fontTitulo = new Font(Font.HELVETICA, 14, Font.BOLD);
    Font fontSubTitulo = new Font(Font.HELVETICA, 12, Font.BOLD);

    Font fontLabel = new Font(Font.HELVETICA, 10, Font.BOLD);
    Font fontLabelNormal = new Font(Font.HELVETICA, 10, Font.NORMAL);
    Font fontSmall = new Font(Font.HELVETICA, 8.5f, Font.NORMAL);
    Font fontData = new Font(Font.HELVETICA, 9, Font.UNDEFINED);
    protected PdfTemplate total;
    protected BaseFont helv;
    DatosHemodinamica datosHemodinamica;
    String clasificacion,centroName;

    HeaderAndFooterHemodinamica() {}

    HeaderAndFooterHemodinamica(DatosHemodinamica datosHemodinamica,String clasificacion, String centroName){
        this.datosHemodinamica = datosHemodinamica;
        this.clasificacion = clasificacion;
        this.centroName = centroName;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        super.onEndPage(writer, document);
        PdfPTable table;
        PdfPCell cell;
        Rectangle page = document.getPageSize();
        table = new PdfPTable(new float[]{86f,16f});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("MINISTERIO DE SALUD.",fontTitulo));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Codigo"));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, 585, writer.getDirectContent());

        table = new PdfPTable(new float[]{86f,16f});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("HOJA DE EVALUACION HEMODINAMICA.",fontSubTitulo));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(datosHemodinamica.getParticipante().getCodigo()));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        table.writeSelectedRows(0, -1, 42, 570, writer.getDirectContent());

        table = new PdfPTable(new float[]{86f,16f});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("PACIENTES HOSPITALIZADOS CON DENGUE.",fontSubTitulo));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.writeSelectedRows(0,-1, 42,558, writer.getDirectContent());

        /****************************** 1er Fila *********************************/
        table = new PdfPTable(new float[]{5f, 12.5f, 11.5f, 22.5f, 10.5f, 9.5f, 9.5f, 12.5f});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Silais:",fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Managua", fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unidad de Salud:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(centroName, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Municipio:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Managua",fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Sector:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getParticipante().getCasa().getBarrio().getNombre(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,530, writer.getDirectContent());
/****************************** 2da Fila *********************************/
        table = new PdfPTable(new float[]{6, 15, 10, 10, 9, 10, 11});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Dirección:", fontLabel));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getDireccion(), fontData));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Fecha:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(DateUtil.DateToString(datosHemodinamica.getFecha(), "dd/MM/yyyy"), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,515, writer.getDirectContent());

        /****************************** 3ra Fila *********************************/
        table = new PdfPTable(new float[]{16, 37, 13, 37});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Número de expediente:", fontLabel));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getnExpediente(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("No. Teléfonico:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        String tel;
        if (datosHemodinamica.getTelefono() != null) {
            tel = datosHemodinamica.getTelefono();
        } else {
            tel = "--";
        }
        cell = new PdfPCell(new Phrase(tel, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,500, writer.getDirectContent());


        /****************************** Fin 4ta Fila *********************************/
        table = new PdfPTable(1);
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Valoración del Estado nutricional en los niños/niñas", fontSmall ));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        table.writeSelectedRows(0,-1, 10,460, writer.getDirectContent());
        /****************************** *** *********************************/

        table = new PdfPTable(new float[]{15,32,5,17,5,7, 5, 7, 5, 5, 4, 5});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Nombre y Apellidos:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getParticipante().getNombreCompleto(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Edad:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getEdad(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Peso:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);
        Double pes = Double.valueOf(datosHemodinamica.getPeso().toString());
        cell = new PdfPCell(new Phrase(pes + " Kg", fontData));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Talla:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getTalla().toString() + " cm", fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("ASC:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        double x = (double) Math.round(datosHemodinamica.getAsc() * 100d) / 100d;
        cell = new PdfPCell(new Phrase(Double.valueOf(x).toString(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("IMC:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getImc().toString(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,480, writer.getDirectContent());


        table = new PdfPTable(new float[]{15, 15, 5, 10, 8, 10, 8, 8});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Rango de Presión PS/SD.", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);
        String psdmin, psdmed, psdmax;
        if (datosHemodinamica.getSdMin() != null || datosHemodinamica.getSdMin() == "") {
            psdmin = datosHemodinamica.getSdMin();
            psdmed = datosHemodinamica.getSdMed();
            psdmax = datosHemodinamica.getSdMax();
        } else {
            psdmin = "--";
            psdmed = "--";
            psdmax = "--";
        }
        cell = new PdfPCell(new Phrase(psdmin + " /, " + psdmed + " /, " + psdmax, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("PAM", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        String pammin, pammed, pammax;
        if (datosHemodinamica.getPamMin()!=null || datosHemodinamica.getPamMed() != null || datosHemodinamica.getPamMax()!=null){
            pammin =datosHemodinamica.getPamMin();
            pammed=datosHemodinamica.getPamMed();
            pammax = datosHemodinamica.getPamMax();
        }else{
            pammin ="--";
            pammed ="--";
            pammax = "--";
        }
        cell = new PdfPCell(new Phrase(pammin + " /, " + pammed + " /, " + pammax, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rangos de FC:", fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);
        String min, med, max, frmin, frmax;
        if (datosHemodinamica.getFcMin() != null) {
            min = datosHemodinamica.getFcMin().toString();
            med = datosHemodinamica.getFcMed().toString();
            max = datosHemodinamica.getFcProm().toString();
            frmin = datosHemodinamica.getFrMin().toString();
            frmax = datosHemodinamica.getFrMax().toString();
        } else {
            min = "--";
            med = "--";
            max = "--";
            frmin = "--";
            frmax = "--";
        }
        cell = new PdfPCell(new Phrase(min + " / " + med + " / " + max, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Rango FR: ", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(frmin + " / " + frmax, fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,450, writer.getDirectContent());

/************************************* Fin 6ta Fila **********************************************/
        table = new PdfPTable(new float[]{15, 15, 5, 10, 8, 12, 8, 8});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("mínima/,media/,máxima", fontSmall));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("mínima/,media/,máxima", fontSmall));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("mínima/,máximo/,promedio", fontSmall));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("mínimo/máximo", fontSmall));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,438, writer.getDirectContent());

        table = new PdfPTable(new float[]{15, 8, 10, 10, 10, 10});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        cell = new PdfPCell(new Phrase("Fecha inicio de enfermedad:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(DateUtil.DateToString(datosHemodinamica.getFie(), "dd/MM/yyyy"), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Días de enfermedad:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getDiasenf().toString(), fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Días de hospitalización:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("--", fontData));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,426, writer.getDirectContent());


        table = new PdfPTable(new float[]{22,80});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        table.setWidthPercentage(100f);
        cell = new PdfPCell(new Phrase("Clasificacion clínica del Dengue:", fontLabel));
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(clasificacion, fontLabel));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.BOTTOM);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,409, writer.getDirectContent());

        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        table = new PdfPTable(new float[]{10,18,14,14,14,14,16});
        table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        //table.setSpacingBefore(5f);
        cell = new PdfPCell(new Phrase("Creado por: ", fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell= new PdfPCell(new Phrase(datosHemodinamica.getRecordUser(), fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Impreso : ", fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(dateformat.format(objDate) + " " + hourFormat.format(objDate), fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("N° Evento: ", fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(datosHemodinamica.getNumeroEvento(),fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Página " + writer.getCurrentPageNumber() + " de " + datosHemodinamica.getNumeroPagina(), fontLabelNormal));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        table.writeSelectedRows(0,-1,10,40, writer.getDirectContent());

        /*PdfPTable foot = new PdfPTable(3);
        for (int k = 1; k <= 6; ++k)
            foot.addCell("foot " + k);
        foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());*/

        /*Paragraph encabezado = new Paragraph("MINISTERIO DE SALUD.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, encabezado, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);*/
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);

    }
}


