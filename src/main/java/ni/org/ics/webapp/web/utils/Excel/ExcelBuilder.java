package ni.org.ics.webapp.web.utils.Excel;

import ni.org.ics.webapp.domain.Serologia.Serologia_Detalles_Envio;
import ni.org.ics.webapp.web.utils.pdf.Constants;
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
        }

        /*if (reporte.equalsIgnoreCase("DXVIG")) {
            buildExcelDocumentVigDx(model, workbook);
        }else if (reporte.equalsIgnoreCase("DXEXAMS")) {
            ExcelDocumentDxExams excelDocumentDxExams = new ExcelDocumentDxExams(model, workbook);
            excelDocumentDxExams.buildExcel();
        }else if (reporte.equalsIgnoreCase("RESULTDX")){
            buildExcelResultDx(model, workbook);
        }else if (reporte.equalsIgnoreCase("TIEMPOSMX")){
            ExcelDocumentTiemposProc excelDocumentTiemposProc = new ExcelDocumentTiemposProc(model, workbook);
            excelDocumentTiemposProc.buildExcel();
        }*/
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
                dataRow.createCell(0).setCellValue(registro.getSerologia().getParticipante());
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

    public void buildExcelResultDx(Map<String, Object> model, HSSFWorkbook workbook){
        List<String> columnas = (List<String>) model.get("columnas");
        List<Object[]> datos = (List<Object[]>) model.get("datos");
        String tipoReporte =  model.get("reporte").toString();

        // create style for title cells
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setFontHeight((short)(16*20));
        font.setColor(HSSFColor.BLACK.index);
        titleStyle.setFont(font);

        // create style for header cells
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeight((short)(11*20));
        font.setColor(HSSFColor.BLACK.index);

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setFont(font);

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setFont(font);

        CellStyle noDataCellStyle = workbook.createCellStyle();
        noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        noDataCellStyle.setFont(font);

        font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeight((short)(11*20));
        font.setColor(HSSFColor.RED.index);

        CellStyle alertCellStyle = workbook.createCellStyle();
        alertCellStyle.setBorderBottom(BorderStyle.THIN);
        alertCellStyle.setBorderTop(BorderStyle.THIN);
        alertCellStyle.setBorderLeft(BorderStyle.THIN);
        alertCellStyle.setBorderRight(BorderStyle.THIN);
        alertCellStyle.setFont(font);

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(tipoReporte);
        sheet.setDefaultColumnWidth(30);

        HSSFRow header = sheet.createRow(3);
        setHeaderTable(header, headerStyle, columnas);
        int rowCount = 4;
        for (Object[] registro : datos) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            setRowData(aRow, registro, contentCellStyle, dateCellStyle);
        }

        if (datos.size() <= 0) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, columnas.size() - 1));
            aRow.createCell(0).setCellValue(model.get("sinDatos").toString());
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }else{
            //estilo para celdas de totales en la �ltima fila
            Font font2 = workbook.createFont();
            font2.setFontName("Arial");
            font2.setFontHeight((short) (10 * 20));
            font2.setColor(HSSFColor.BLACK.index);
            font2.setBold(true);
            CellStyle totalCellStyle = workbook.createCellStyle();
            totalCellStyle.setAlignment(HorizontalAlignment.CENTER);
            totalCellStyle.setBorderBottom(BorderStyle.THIN);
            totalCellStyle.setBorderTop(BorderStyle.THIN);
            totalCellStyle.setBorderLeft(BorderStyle.THIN);
            totalCellStyle.setBorderRight(BorderStyle.THIN);
            totalCellStyle.setFont(font2);
            //totalColumnas restar 2 para omitir la primer columna que tiene la entidad y la �ltima que tiene el %P
            setRowTotalsDat(sheet, contentCellStyle, totalCellStyle, rowCount++, columnas.size()-2, 5, datos.size()+4);
        }
        //validar positividad
        for(int i=4; i < rowCount-1; i++){
            HSSFRow aRow = sheet.getRow(i);
            //validar valores positivos
            for (int j=2; j < columnas.size()-5; j++){
                HSSFCell aCell = aRow.getCell(j);
                Double valor = aCell.getNumericCellValue();
                if (valor>0){
                    aCell.setCellStyle(alertCellStyle);
                }
            }
            HSSFCell aCell = aRow.getCell(columnas.size()-1); //en la �ltima que esta la positividad
            Double valor = aCell.getNumericCellValue();
            if (valor>0){
                aCell.setCellStyle(alertCellStyle);
            }
        }
        //ajustar el ancho de la celda al tamanio del texto
        for(int i =0;i<columnas.size();i++){
            sheet.autoSizeColumn(i);
        }

        HSSFRow titulo = sheet.createRow(0);
        titulo.createCell(0).setCellValue(model.get("titulo").toString());
        titulo.getCell(0).setCellStyle(titleStyle);

        HSSFRow subtitulo = sheet.createRow(1);
        subtitulo.createCell(0).setCellValue(model.get("subtitulo").toString());
        subtitulo.getCell(0).setCellStyle(titleStyle);

        HSSFRow rangos = sheet.createRow(2);
        rangos.createCell(0).setCellValue(model.get("rangoFechas").toString());
        rangos.getCell(0).setCellStyle(titleStyle);
    }

    public HSSFWorkbook buildExcel(Map<String, Object> model){
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<Object[]> listaDxPos = (List<Object[]>) model.get("listaDxPos");
        List<Object[]> listaDxNeg = (List<Object[]>) model.get("listaDxNeg");
        List<Object[]> listaDxInadec = (List<Object[]>) model.get("listaDxInadec");

        List<String> columnas = (List<String>) model.get("columnas");
        boolean incluirMxInadecuadas = (boolean)model.get("incluirMxInadecuadas");
        String tipoReporte =  model.get("tipoReporte").toString();
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(tipoReporte);
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)(11*20));
        font.setColor(HSSFColor.BLACK.index);
        headerStyle.setFont(font);

        //Cell style for content cells
        font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeight((short)(11*20));
        font.setColor(HSSFColor.BLACK.index);

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setFont(font);

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setFont(font);

        CellStyle noDataCellStyle = workbook.createCellStyle();
        noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        noDataCellStyle.setFont(font);

        //tabla con dx positivos
        // create header row
        HSSFRow header = sheet.createRow(3);
        setHeaderTable(header, headerStyle, columnas);
        // create data rows
        int rowCount = 4;
        int filaInicioNeg = 0;

        for (Object[] registro : listaDxPos) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            setRowData(aRow, registro, contentCellStyle, dateCellStyle);
        }
        if (listaDxPos.size()<=0){
            HSSFRow aRow = sheet.createRow(rowCount++);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(),0,columnas.size()-1));
            aRow.createCell(0).setCellValue(model.get("sinDatos").toString());
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }

        //tabla con dx negativos
        rowCount+=2; // PARA DEJAR UNA FILA EN BLANCO ENTRE AMBAS TABLAS
        filaInicioNeg = rowCount++;
        HSSFRow headerPos = sheet.createRow(rowCount++);
        setHeaderTable(headerPos, headerStyle, columnas);
        for (Object[] registro : listaDxNeg) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            setRowData(aRow, registro, contentCellStyle, dateCellStyle);
        }
        if (listaDxNeg.size()<=0){
            HSSFRow aRow = sheet.createRow(rowCount);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(),0,columnas.size()-1));
            aRow.createCell(0).setCellValue(model.get("sinDatos").toString());
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
        for(int i =0;i<columnas.size();i++){
            sheet.autoSizeColumn(i);
        }

        // create style for title cells
        CellStyle titleStyle = workbook.createCellStyle();
        font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setFontHeight((short)(16*20));
        font.setColor(HSSFColor.BLACK.index);
        titleStyle.setFont(font);

        // create style for filters cells
        CellStyle filterStyle = workbook.createCellStyle();
        font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setFontHeight((short)(14*20));
        font.setColor(HSSFColor.BLACK.index);
        filterStyle.setFont(font);

        HSSFRow titulo = sheet.createRow(0);
        titulo.createCell(1).setCellValue(model.get("titulo").toString());
        titulo.getCell(1).setCellStyle(titleStyle);

        HSSFRow subtitulo = sheet.createRow(1);
        subtitulo.createCell(1).setCellValue(model.get("subtitulo").toString());
        subtitulo.getCell(1).setCellStyle(titleStyle);

        HSSFRow filtros = sheet.createRow(2);
        filtros.createCell(1).setCellValue(model.get("tablaPos").toString());
        filtros.getCell(1).setCellStyle(filterStyle);

        HSSFRow filtrosNeg = sheet.createRow(filaInicioNeg);
        filtrosNeg.createCell(1).setCellValue(model.get("tablaNeg").toString());
        filtrosNeg.getCell(1).setCellStyle(filterStyle);

        if (incluirMxInadecuadas){
            // create a new Excel sheet
            HSSFSheet sheetInadec = workbook.createSheet("MX INADEC");
            sheetInadec.setDefaultColumnWidth(30);
            //tabla con dx muestras inadecuadas
            // create header row
            HSSFRow headerInadec = sheetInadec.createRow(3);
            setHeaderTable(headerInadec, headerStyle, columnas);
            // create data rows
            rowCount = 4;

            for (Object[] registro : listaDxInadec) {
                HSSFRow aRow = sheetInadec.createRow(rowCount++);
                setRowData(aRow, registro, contentCellStyle, dateCellStyle);
            }
            if (listaDxInadec.size()<=0){
                HSSFRow aRow = sheetInadec.createRow(rowCount);
                sheetInadec.addMergedRegion(new CellRangeAddress(rowCount, rowCount,0,columnas.size()-1));
                aRow.createCell(0).setCellValue(model.get("sinDatos").toString());
                aRow.getCell(0).setCellStyle(noDataCellStyle);
            }
            for(int i =0;i<columnas.size();i++){
                sheetInadec.autoSizeColumn(i);
            }

            HSSFRow tituloInadec = sheetInadec.createRow(0);
            tituloInadec.createCell(1).setCellValue(model.get("titulo").toString());
            tituloInadec.getCell(1).setCellStyle(titleStyle);

            HSSFRow subtituloInadec = sheetInadec.createRow(1);
            subtituloInadec.createCell(1).setCellValue(model.get("subtitulo").toString());
            subtituloInadec.getCell(1).setCellStyle(titleStyle);

            HSSFRow filtroInadec = sheetInadec.createRow(2);
            filtroInadec.createCell(1).setCellValue(model.get("tablaMxInadec").toString());
            filtroInadec.getCell(1).setCellStyle(filterStyle);

        }

        return workbook;
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

}