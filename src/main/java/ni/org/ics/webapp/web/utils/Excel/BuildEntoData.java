package ni.org.ics.webapp.web.utils.Excel;

import ni.org.ics.webapp.domain.entomologia.CuestionarioCAP;
import ni.org.ics.webapp.domain.entomologia.CuestionarioHogar;
import ni.org.ics.webapp.domain.entomologia.CuestionarioHogarPoblacion;
import ni.org.ics.webapp.domain.entomologia.CuestionarioPuntoClave;
import ni.org.ics.webapp.web.utils.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by miguel on 19/8/2022.
 */
public class BuildEntoData {

    public static void buildExcel(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {
        String fechaInicio = model.get("fechaInicio").toString();
        String fechaFin = model.get("fechaFin").toString();
        List<CuestionarioHogar> listaCuestionario = (List<CuestionarioHogar>) model.get("cuestionarios");
        List<CuestionarioHogarPoblacion> listaPoblacion = (List<CuestionarioHogarPoblacion>) model.get("poblacion");
        List<CuestionarioPuntoClave> listaPuntosClaves = (List<CuestionarioPuntoClave>) model.get("puntosClaves");
        List<CuestionarioCAP> listacambioClimatico = (List<CuestionarioCAP>) model.get("cambioClimatico");

        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        if ((fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty())) {
            String fileName = "datos_cuestionarios_ento_"+ String.format("%s_%s", fechaInicio.replaceAll("/", "-"), fechaFin.replaceAll("/", "-")) +".xls";
            response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        } else {
            String fileName = "datos_cuestionarios_ento_" + fechaActual + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        }
        HSSFSheet sheet = workbook.createSheet("ento_cuestionario_hogar");
        Font font = workbook.createFont();
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        Font fontContent = workbook.createFont();
        fontContent.setFontName("Calibri");
        fontContent.setFontHeight((short) (11 * 20));
       /// fontContent.setColor(HSSFColor.BLACK.index);

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setFont(fontContent);

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setFont(fontContent);

        HSSFRow headerRow = sheet.createRow(0);

        for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR.length; ++i) {
            String header = Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaCuestionario.size()>0) {
            int rowCount = 1;
            for (CuestionarioHogar registro : listaCuestionario) {
                HSSFRow dataRow = sheet.createRow(rowCount++);

                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getFechaCuestionario(), "dd/MM/yyyy"), 0, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getBarrio(), 1, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDireccion(), 2, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLatitud(), 3, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLongitud(), 4, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigo(), 5, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoVivienda(), 6, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoVivienda(), 7, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getHayAmbientePERI(), 8, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraCapturaPERI(), "HH:mm"), 9, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHumedadRelativaPERI(), 10, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTemperaturaPERI(), 11, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigoPERI(), 12, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoPERI(), 13, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getHayAmbienteINTRA(), 14, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraCapturaINTRA(),"HH:mm"), 15, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHumedadRelativaINTRA(), 16, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTemperaturaINTRA(), 17, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigoINTRA(), 18, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoINTRA(), 19, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getQuienContesta(), 20, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienContestaOtro(), 21, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadContest(),22, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEscolaridadContesta(), 23, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTiempoVivirBarrio(), 24, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantasPersonasViven(), 25, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadesFemenino(), 26, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadesMasculino(), 27, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUsaronMosquitero(), 28, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienesUsaronMosquitero(), 29, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUsaronRepelente(), 30, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienesUsaronRepelente(), 31, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConoceLarvas(), 32, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAlguienVisEliminarLarvas(), 33, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienVisEliminarLarvas(), 34, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienVisEliminarLarvasOtro(), 35, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAlguienDedicaElimLarvasCasa(), 36, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienDedicaElimLarvasCasa(), 37, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTiempoElimanCridaros(), 38, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayBastanteZancudos(), 39, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueFaltaCasaEvitarZancudos(), 40, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueFaltaCasaEvitarZancudosOtros(), 41, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getGastaronDineroProductos(), 42, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueProductosCompraron(), 43, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueProductosCompraronOtros(), 44, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantoGastaron(), 45, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVisitaMinsaBTI(), 46, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezMinsaFumigo(), 47, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRiesgoCasaDengue(), 48, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getProblemasAbastecimiento(), 49, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCadaCuantoVaAgua(), 50, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCadaCuantoVaAguaOtro(), 51, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHorasSinAguaDia(), 52, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHacenBasuraHogar(), 53, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHacenBasuraHogarOtro(), 54, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRiesgoBarrioDengue(), 55, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAccionesCriaderosBarrio(), 56, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueAcciones(), 57, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueAccionesOtro(), 58, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAlguienParticipo(), 59, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienParticipo(), 60, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMayorCriaderoBarrio(), 61, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getMaterialParedes(), 62, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialParedes(), 63, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMaterialPiso(), 64, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialPiso(), 65, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMaterialTecho(), 66, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialTecho(), 67, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getRecordUser(), 68, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getRecordDate(), "dd/MM/yyyy"), 69, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoEncuesta(), 70, false, contentCellStyle, dateCellStyle);



            }

            for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR.length ; i++){
                sheet.autoSizeColumn(i);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }

        //se crea la segunda hoja para la poblacion
        sheet = workbook.createSheet("ento_cuestionario_hogar_pob");
        headerRow = sheet.createRow(0);

        for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB.length; ++i) {
            String header = Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaPoblacion.size()>0) {
            int rowCount = 1;
            for (CuestionarioHogarPoblacion registro : listaPoblacion) {
                HSSFRow dataRow = sheet.createRow(rowCount++);

                ExcelBuilder.setCellData(dataRow, obtenerCodigoCasaCuestionario(listaCuestionario, registro.getCodigoEncuesta()), 0, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodificado(), 1, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdad(), 2, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSexo(), 3, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRecordUser(), 4, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getRecordDate(), "dd/MM/yyyy"), 5, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoEncuesta(), 6, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoPoblacion(), 7, false, contentCellStyle, dateCellStyle);
            }

            for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB.length ; i++){
                sheet.autoSizeColumn(i);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, Constants.ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }

        //se crea la tercera hoja para los puntos claves
        sheet = workbook.createSheet("ento_cuestionario_punto_clave");
        headerRow = sheet.createRow(0);

        for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE.length; ++i) {
            String header = Constants.ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaPuntosClaves.size()>0) {
            int rowCount = 1;
            for (CuestionarioPuntoClave registro : listaPuntosClaves) {
                HSSFRow dataRow = sheet.createRow(rowCount++);

                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getFechaCuestionario(), "dd/MM/yyyy"), 0, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getBarrio(), 1, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getNombrePuntoClave(), 2, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDireccionPuntoClave(), 3, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoPuntoClave(), 4, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoPuntoProductividad(), 5, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoPuntoProductividadOtro(), 6, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoPuntoAglomeracion(), 7, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoPuntoAglomeracionOtro(), 8, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantasPersonasReunen(), 9, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantosDiasSemanaReunen(),10 , false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraInicioReunion(),"HH:mm:ss"), 11, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraFinReunion(), "HH:mm:ss"), 12, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPuntoGps(), 13, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLatitud(), 14, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLongitud(), 15, false, contentCellStyle, dateCellStyle);


                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigoSitio(), 16, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoSitio(), 17, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getHayAmbientePERI(), 18, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraCapturaPERI(), "HH:mm:ss"), 19, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHumedadRelativaPERI(), 20, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTemperaturaPERI(), 21, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigoPERI(), 22, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoPERI(), 23, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getHayAmbienteINTRA(), 24, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.getHoraFormateada(registro.getHoraCapturaINTRA(),"HH:mm:ss"), 25, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHumedadRelativaINTRA(), 26, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTemperaturaINTRA(), 27, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoIngresoCodigoINTRA(), 28, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoINTRA(), 29, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, registro.getNombrePersonaContesta(), 30, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMovilInfo().getUsername(), 31, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getMovilInfo().getToday(), "dd/MM/yyyy"), 32, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoCuestionario(), 33, false, contentCellStyle, dateCellStyle);
            }

            for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE.length ; i++){
                sheet.autoSizeColumn(i);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, Constants.ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }

        //se crea la hoja para la cuestionario cambio climatico CAP
        sheet = workbook.createSheet("ento_cuestionario_cambio_climatico_");
        headerRow = sheet.createRow(0);

        for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CAMBIO_CLIMATICO_CAP.length; ++i) {
            String header = Constants.ENTO_COLUMNAS_TBL_CAMBIO_CLIMATICO_CAP[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listacambioClimatico.size()>0) {
            int rowCount = 1;
            for (CuestionarioCAP registro : listacambioClimatico) {
                HSSFRow dataRow = sheet.createRow(rowCount++);

                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getFechaCuestionario(), "dd/MM/yyyy"), 0, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCodigoVivienda(), 1, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLatitud(), 2, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLongitud(), 3, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienContesta(), 4, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQuienContestaOtro(), 5, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadContest(), 6, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getGeneroObservacion(), 7, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimoGradoAprobado(), 8, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAqueSeDedica(), 9, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAqueSeDedicaOtros(), 10, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantasPersonasViven(), 11, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadesFemenino(), 12, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEdadesMasculino(), 13, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEscuchadoCambioClimatico(), 14, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueTanInformadoCambioClimatico(), 15, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getComoseHaInformado(), 16, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getComoseHaInformadoOtros(), 17, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPorqueOcurreCambioclimatico(), 18, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPorqueOcurreCambioclimaticoOtros(), 19, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPorqueProblemasOcurreCambioclimatico(), 20, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPorqueProblemasOcurreCambioclimaticoOtros(), 21, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getResponsableAccionesReducirDanos(), 22, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getResponsableAccionesReducirDanosOtros(), 23, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraCambioCliAfectaSalud(), 24, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEnfermedadesCausadasCambioClimatico(), 25, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEnfermedadesCausadasCambioClimaticoOtros(), 26, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRiesgoCasaEnfermarseDengue(), 27, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRiesgoBarrioEnfermarseDengue(), 28, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraCambCliAumentaDengueBarrio(), 29, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraZancudosVivirLugarFrio(), 30, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraZancudosVivirLugarCalor(), 31, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayZancudosBarrio(), 32, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayZancudosTrabajo(), 33, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayZancudosEscuela(), 34, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayZancudosCasa(), 35, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSabeIdentificarCriaderosCasa(), 36, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAlguienBuscaEliminaCriaderosCasa(), 37, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCadaCuantoBuscaEliminaCriaderosCasa(), 38, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezAbateMinsa(), 39, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezFumigaMinsa(), 40, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLePreocupaCambioClimatico(), 41, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPrevenirDanosCambioClimatico(), 42, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getPrevenirDanosCambioClimaticoOtros(), 43, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSeSientePreparadoEnfrentarSituaciones(), 44, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraCambioCliAfectaSalud(), 45, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAfectaraNegativamenteGeneracionCambioClimatico(), 46, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRiesgoLluviasCausanDañoCasa(), 47, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getConsideraCambioCliAfectaNegativamenteBarrio(), 48, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueTanPreparadoComunidadEnfrentarSituaciones(), 49, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezInundoBarrio(), 50, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezIncendioBarrio(), 51, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezIncendioBarrioCausas(), 52, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimaVezIncendioBarrioCausasOtros(), 53, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTiempoVivirBarrio(), 54, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimos5AñosCambioClima(), 55, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimos5AñosCambioClimaMasCalorFrios(), 56, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimos5AñosMasMenosArbolesBarrio(), 57, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimos5AñosAbastecimientoAgua(), 58, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLugarProduceMasContaminacion(), 59, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getLugarProduceMasContaminacionOtros(), 60, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAhorraEnergiaCasa(), 61, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHaceParaAhorraEnergiaCasa(), 62, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHaceParaAhorraEnergiaCasaOtros(), 63, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getAhorraAguaCasa(), 64, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHaceParaAhorraAguaCasa(), 65, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHaceParaAhorraAguaCasaOtros(), 66, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRecolectaAguaLluvia(), 67, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getParaQueRecolectaAguaLluvia(), 68, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getParaQueRecolectaAguaLluviaOtros(), 69, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getHayProblemasAbastecimientoAgua(), 70, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCadaCuantoSeVaelAgua(), 71, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCadaCuantoSeVaelAguaOtros(), 72, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCuantasHorasAlDiaSeVaelAgua(), 73, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueUsanParaCocinar(), 74, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueUsanParaCocinarOtros(), 75, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDondeConsiguenLeña(), 76, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDondeConsiguenLeñaOtros(), 77, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueParteCasaCocinanLeña(), 78, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHacenBasuraHogar(), 79, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getQueHacenBasuraHogarOtros(), 80, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSeparaPlasticosBasuraHogar(), 81, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSeparaPlasticosBasuraHogar_queHacen(), 82, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getSeparaPlasticosBasuraHogar_queHacen_Otros(), 83, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getUltimos6MesesParticipadoActividadCambioClimatico(), 84, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEnQueActividadHaParticipado(), 85, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getEnQueActividadHaParticipadoOtros(), 86, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getViviendaEsPropiaAlquilada(), 87, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getViviendaEsPropiaAlquiladaOtros(), 88, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoVivienda(), 89, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoViviendaOtros(), 90, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTieneEnergiaElectrica(), 91, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTieneMedidorEnergiaElectrica(), 92, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTieneAguaPotable(), 93, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTieneMedidorAguaPotable(), 94, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTienePozo(), 95, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoSaneamiento(), 96, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTipoSaneamientoOtros(), 97, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getBarrioTieneAlcantarillado(), 98, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getViviendaConectadaAlcantarillado(), 99, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getTieneSumidero(), 100, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDistanciaCauseCasa(), 101, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMaterialPiso(), 102, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialPiso(), 103, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMaterialTecho(), 104, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialTecho(), 105, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getMaterialParedes(), 106, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getOtroMaterialParedes(), 107, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getCalleCuadraVivienda(), 108, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getBarrio(), 109, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getDispuestoParticiparActividad(), 110, false, contentCellStyle, dateCellStyle);

                ExcelBuilder.setCellData(dataRow, ni.org.ics.webapp.web.utils.DateUtil.DateToString(registro.getRecordDate(), "dd/MM/yyyy"), 5, false, contentCellStyle, dateCellStyle);
                ExcelBuilder.setCellData(dataRow, registro.getRecordUser(), 6, false, contentCellStyle, dateCellStyle);
            }

            for (int i = 0; i < Constants.ENTO_COLUMNAS_TBL_CAMBIO_CLIMATICO_CAP.length ; i++){
                sheet.autoSizeColumn(i);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, Constants.ENTO_COLUMNAS_TBL_CAMBIO_CLIMATICO_CAP.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }

    private static String obtenerCodigoCasaCuestionario(List<CuestionarioHogar> listaCuestionario, String codigoEncuesta) {
        String codigoCasa = "";
        for(CuestionarioHogar cuest : listaCuestionario) {
            if (cuest.getCodigoEncuesta().equalsIgnoreCase(codigoEncuesta)) {
                codigoCasa = cuest.getCodigoVivienda();
                break;
            }
        }
        return codigoCasa;
    }
}
