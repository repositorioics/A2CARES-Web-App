package ni.org.ics.webapp.web.utils.Excel;

import ni.org.ics.webapp.domain.Serologia.Bhc_Detalles_Envio;
import ni.org.ics.webapp.domain.Serologia.Serologia_Detalles_Envio;
import ni.org.ics.webapp.domain.laboratorio.MuestraEnfermoDetalleEnvio;
import ni.org.ics.webapp.language.MessageResource;
import ni.org.ics.webapp.web.utils.*;
import ni.org.ics.webapp.web.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractExcelView {

    private static final Logger logger = Logger.getLogger("ni.org.ics.webapp.web.utils.Excel.ExcelBuilder");

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String reporte = model.get("TipoReporte").toString();
        if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTE)) {
            buildExcelDocumentVigDx(model, workbook, response);
        } else if (reporte.equalsIgnoreCase(Constants.TPR_ENVIO_ENFERMO)) {
            buildExcelReporteEnvioMxEnfermo(model, workbook, response);
        } else if (reporte.equalsIgnoreCase(Constants.TPR_ENTO)) {
            BuildEntoData.buildExcel(model, workbook, response);
        }
        else if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTEBHC)) {
            buildExcelDocumentVigDxBhc(model, workbook, response);
        }

	}


    public void buildExcelDocumentVigDxBhc(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {
        List<Bhc_Detalles_Envio> listaSerologia = (List<Bhc_Detalles_Envio>) model.get("allBhc");
        logger.log(Level.INFO, "iniciando libro de excel");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "ENVIO_BHC_SOCRATES_FLORES_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();
        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                "volumen",
                "observacion",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje",
                "Procesada CSFV",
                "Puesto"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaSerologia.size()>0) {
            File archivo = new File(fileName);
            String filePath = "C:\\Users\\ICS\\Downloads" + fileName;

            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);


            int rowCount = 1;
            for (Bhc_Detalles_Envio registro : listaSerologia) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getBhc().getParticipante());

                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getBhc().getFecha(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getBhc().getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getBhc().getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getBhc().getRecordUser());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue("A2CARES");
                sheet.autoSizeColumn(5);
                Double ageAnios = Math.floor(registro.getBhc().getEdadMeses()/12);
                dataRow.createCell(6).setCellValue(ageAnios.intValue());
                sheet.autoSizeColumn(6);
                //edad Meses
                double d = registro.getBhc().getEdadMeses();
                Double edadMeses = d % 12;
                dataRow.createCell(7).setCellValue(edadMeses.intValue());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getBhcEnvio().getIdenvio());
                sheet.autoSizeColumn(8);
                dataRow.createCell(9).setCellValue(registro.getBhc().getProcesadaCSFV());
                sheet.autoSizeColumn(9);
                dataRow.createCell(10).setCellValue(registro.getBhc().getPuesto());
                sheet.autoSizeColumn(10);
            }

            File excelFile;
            excelFile = new File(filePath);
            try {
                FileOutputStream fileOuS = new FileOutputStream(excelFile);
                if (excelFile.exists()) {
                    excelFile.delete();
                    logger.log(Level.INFO, "Archivo eliminado.!");
                }
                workbook.write(fileOuS);
                fileOuS.flush();
                fileOuS.close();
                logger.log(Level.INFO, "Archivo Creado.!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void buildExcelDocumentVigDx(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {
        List<Serologia_Detalles_Envio> listaSerologia = (List<Serologia_Detalles_Envio>) model.get("allSerologia");
        logger.log(Level.INFO, "iniciando libro de excel");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_muestras_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();
        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                "volumen",
                "observacion",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaSerologia.size()>0) {
            File archivo = new File(fileName);
            String filePath = "C:\\Users\\ICS\\Downloads" + fileName;

            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);


            int rowCount = 1;
            for (Serologia_Detalles_Envio registro : listaSerologia) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getSerologia().getParticipante().toString());

                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getSerologia().getFecha(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getSerologia().getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getSerologia().getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getSerologia().getRecordUser());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue("A2CARES");
                sheet.autoSizeColumn(5);
                Double ageAnios = Math.floor(registro.getSerologia().getEdadMeses()/12);
                dataRow.createCell(6).setCellValue(ageAnios.intValue());
                sheet.autoSizeColumn(6);
                //edad Meses
                double d = registro.getSerologia().getEdadMeses();
                Double edadMeses = d % 12;
                dataRow.createCell(7).setCellValue(edadMeses.intValue());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getSerologiaEnvio().getIdenvio());
                sheet.autoSizeColumn(8);
            }

            File excelFile;
            excelFile = new File(filePath);
            try {
                FileOutputStream fileOuS = new FileOutputStream(excelFile);
                if (excelFile.exists()) {
                    excelFile.delete();
                    logger.log(Level.INFO, "Archivo eliminado.!");
                }
                workbook.write(fileOuS);
                fileOuS.flush();
                fileOuS.close();
                logger.log(Level.INFO, "Archivo Creado.!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }

    public void buildExcelReporteEnvioMxEnfermo(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {
        List<MuestraEnfermoDetalleEnvio> listaSerologia = (List<MuestraEnfermoDetalleEnvio>) model.get("allSerologia");
        List<MessageResource> tiposConsultas = (List<MessageResource>)  model.get("tiposConsultas");
        logger.log(Level.INFO, "iniciando libro de excel");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_muestras_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();
        String[] headers = new String[]{
                "codigo",
                "codigo_lab",
                "cat",
                "consulta",
                "fis",
                "fif",
                "fecha_mx",
                "volumen",
                "tubo",
                "observacion",
                "PRecepciona",
                "estudio",
                "viaje"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaSerologia.size()>0) {
            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);


            int rowCount = 1;
            for (MuestraEnfermoDetalleEnvio registro : listaSerologia) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                //codigo,
                dataRow.createCell(0).setCellValue(registro.getMuestra().getParticipante().getCodigo());
                sheet.autoSizeColumn(0);
                //codigo_lab
                dataRow.createCell(1).setCellValue(registro.getMuestra().getCodigo());
                sheet.autoSizeColumn(1);
                //cat
                dataRow.createCell(2).setCellValue(registro.getMuestra().getCategoria());
                sheet.autoSizeColumn(2);
                //consulta
                dataRow.createCell(3).setCellValue(getMessage(registro.getMuestra().getConsulta(), null, tiposConsultas));
                sheet.autoSizeColumn(3);
                //fis
                dataRow.createCell(4).setCellValue(ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getMuestra().getFis(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(4);
                //fif
                dataRow.createCell(5).setCellValue(ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getMuestra().getFif(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(5);
                //fecha_mx
                dataRow.createCell(6).setCellValue(ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getMuestra().getFechaRecepcion(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(6);
                //volumen
                dataRow.createCell(7).setCellValue(registro.getMuestra().getVolumen());
                sheet.autoSizeColumn(7);
                //tubo
                dataRow.createCell(8).setCellValue("ROJO");
                sheet.autoSizeColumn(8);
                //observacion
                dataRow.createCell(9).setCellValue(registro.getMuestra().getObservacion());
                sheet.autoSizeColumn(9);
                //PRecepciona
                dataRow.createCell(10).setCellValue(registro.getMuestra().getRecordUser());
                sheet.autoSizeColumn(10);
                //estudio
                dataRow.createCell(11).setCellValue("A2CARES");
                sheet.autoSizeColumn(11);
                //viaje
                dataRow.createCell(12).setCellValue(registro.getEnvio().getNumeroEnvio());
                sheet.autoSizeColumn(12);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
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

    public static void setHeaderTable(HSSFRow header, CellStyle style, List<String> columnas){
        int indice = 0;
        for(String columna : columnas){
            header.createCell(indice).setCellValue(columna);
            header.getCell(indice).setCellStyle(style);
            indice++;
        }
    }

    public static void setRowData(HSSFRow aRow, Object[] registro, CellStyle contentCellStyle, CellStyle dateCellStyle){
        int indice = 0;
        for(Object dato : registro){
            aRow.createCell(indice);
            boolean isDate= false;
            if (dato !=null){
                if (dato instanceof Date){
                    aRow.getCell(indice).setCellValue((Date)dato);
                    isDate = true;
                }else if (dato instanceof Integer){
                    aRow.getCell(indice).setCellValue((int)dato);
                }else if (dato instanceof Long){
                    aRow.getCell(indice).setCellValue((long)dato);
                }else if (dato instanceof Float){
                    aRow.getCell(indice).setCellValue((float)dato);
                }else if (dato instanceof Double){
                    aRow.getCell(indice).setCellValue((double)dato);
                }
                else{
                    aRow.createCell(indice).setCellValue(dato.toString());
                }
            }
            if (!isDate)
                aRow.getCell(indice).setCellStyle(contentCellStyle);
            else
                aRow.getCell(indice).setCellStyle(dateCellStyle);

            indice++;
        }
    }



    /**
     * M�todo para totalizar cada columna de datos en la hoja de datos del respectivo dx
     */
    private static void setRowTotalsDat(HSSFSheet sheet, CellStyle style, CellStyle styleTot, int rowCount, int totalColumnas, int indicePrimeraFila, int indiceUltimafila){
        HSSFRow aRowTot = sheet.createRow(rowCount);
        ExcelBuilder.createCell(aRowTot, "Total", 0, false, styleTot);

        for(int i = 1; i <= totalColumnas ; i++){
            String columnLetter = CellReference.convertNumToColString(i);
            String formula = "SUM("+columnLetter+indicePrimeraFila+":"+columnLetter+indiceUltimafila+")";
            ExcelBuilder.createCell(aRowTot, formula, i, true, style);
        }
    }

    /**
     * M�todo para crear una celda y ponerle el valor que va a contener deacuerdo al tipo de dato
     * @param row Fila en la que se crear� la celda
     * @param value Valor que se le asignar�
     * @param posicion n�mero de la columna en la fila (recordar que la primera celda tiene posici�n 0)
     * @param esFormula TRUE para indicar si la celda contendr� una f�rmula
     * @param style Estilo que se le aplicar� a la celda
     */
    public static void createCell(HSSFRow row, Object value, int posicion, boolean esFormula, CellStyle style){
        row.createCell(posicion);
        if (esFormula){
            row.getCell(posicion).setCellFormula(value.toString());
            row.getCell(posicion).setCellType(CellType.FORMULA);
        }else{
            if (value instanceof Integer){
                row.getCell(posicion).setCellValue((int)value);
                row.getCell(posicion).setCellType(CellType.NUMERIC);
            }else if (value instanceof Float){
                row.getCell(posicion).setCellValue((float)value);
                row.getCell(posicion).setCellType(CellType.NUMERIC);
            }else if (value instanceof Double){
                row.getCell(posicion).setCellValue((double)value);
                row.getCell(posicion).setCellType(CellType.NUMERIC);
            }
            else{
                row.createCell(posicion).setCellValue(value.toString());
                row.getCell(posicion).setCellType(CellType.STRING);
            }
        }
        row.getCell(posicion).setCellStyle(style);
    }

    /**
     * M�todo para crear en orientaci�n horizonta un rango de celdas en una hoja y ponerle el valor que va a contener deacuerdo al tipo de dato. Sobre una misma fila
     * @param sheet Hoja en la que se crear� el rango de celdas combinadas
     * @param row Fila en la que se crear� la celda
     * @param value Valor que se le asignar�
     * @param posicionInicio n�mero de la columna en que iniciar� la combinaci�n de celdas (recordar que la primera celda tiene posici�n 0)
     * @param posicionFin n�mero de la columna en que terminar� la combinaci�n de celdas
     * @param esFormula TRUE para indicar si la celda contendr� una f�rmula
     * @param style Estilo que se le aplicar� a cada celda dentro del rango
     */
    public static void createHorizontalCellRange(HSSFSheet sheet, HSSFRow row, Object value, int posicionInicio, int posicionFin, boolean esFormula, CellStyle style){
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), posicionInicio, posicionFin));
        createCell(row, value, posicionInicio, esFormula, style);
        //inicializando resto de celdas contenidas en el merge
        for (int i = posicionInicio+1; i <= posicionFin; i++){
            row.createCell(i);
            row.getCell(i).setCellStyle(style);
        }
    }

    /**
     * M�todo para crear en orientaci�n vertical un rango de celdas en una hoja y ponerle el valor que va a contener deacuerdo al tipo de dato. Sobre una misma columna
     * @param sheet Hoja en la que se crear� el rango de celdas combinadas
     * @param row Fila en la que se crear� la celda
     * @param value Valor que se le asignar�
     * @param posicionInicio n�mero de la columna en que iniciar� la combinaci�n de celdas (recordar que la primera celda tiene posici�n 0)
     * @param posicionFin n�mero de la columna en que terminar� la combinaci�n de celdas
     * @param columna columna sobre la que se aplicar� la combinaci�n
     * @param posicionValue posicion de la celda dentro del rango que va a contener el valor que se asignar�
     * @param esFormula TRUE para indicar si la celda contendr� una f�rmula
     * @param style Estilo que se le aplicar� a cada celda dentro del rango
     */
    public static void createVerticalCellRange(HSSFSheet sheet, HSSFRow row, Object value, int posicionInicio, int posicionFin, int columna, int posicionValue, boolean esFormula, CellStyle style){
        sheet.addMergedRegion(new CellRangeAddress(posicionInicio, posicionFin, columna, columna));
        createCell(row, value, posicionValue, esFormula, style);
    }

    /***
     * Método para crear una celda y ponerle el valor que va a contener deacuerdo al tipo de dato
     * @param aRow Fila en la que se creará la celda
     * @param dato Valor que se le asignará
     * @param indice número de la columna en la fila (recordar que la primera celda tiene posición 0)
     * @param esFormula TRUE para indicar si la celda contendrá una fórmula
     * @param contentCellStyle Estilo que se le aplicará a la celda cuándo no es fecha
     * @param dateCellStyle Estilo que se le aplicará a la celda cuándo es fecha
     */
    public static void setCellData(HSSFRow aRow, Object dato, int indice, boolean esFormula, CellStyle contentCellStyle, CellStyle dateCellStyle){
        aRow.createCell(indice);
        boolean isDate= false;
        if (dato !=null){
            if (esFormula){
                aRow.getCell(indice).setCellFormula(dato.toString());
                aRow.getCell(indice).setCellType(CellType.FORMULA);
            }else {
                if (dato instanceof Date) {
                    aRow.getCell(indice).setCellValue((Date) dato);
                    isDate = true;
                } else if (dato instanceof Integer) {
                    aRow.getCell(indice).setCellValue((Integer) dato);
                } else if (dato instanceof Long) {
                    aRow.getCell(indice).setCellValue((Long) dato);
                } else if (dato instanceof Float) {
                    aRow.getCell(indice).setCellValue((Float) dato);
                } else if (dato instanceof Double) {
                    aRow.getCell(indice).setCellValue((Double) dato);
                } else if (dato instanceof BigInteger) {
                    aRow.getCell(indice).setCellValue(((BigInteger) dato).intValue());
                } else {
                    aRow.createCell(indice).setCellValue(dato.toString());
                }
            }
        }
        if (!isDate)
            aRow.getCell(indice).setCellStyle(contentCellStyle);
        else
            aRow.getCell(indice).setCellStyle(dateCellStyle);

    }
}