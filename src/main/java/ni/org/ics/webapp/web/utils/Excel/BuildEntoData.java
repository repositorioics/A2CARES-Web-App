package ni.org.ics.webapp.web.utils.Excel;

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
        fontContent.setColor(HSSFColor.BLACK.index);

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
