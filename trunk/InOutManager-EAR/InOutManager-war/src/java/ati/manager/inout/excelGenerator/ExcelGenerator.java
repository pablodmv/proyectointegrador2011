/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ati.manager.inout.excelGenerator;

import com.inout.dto.ausenciaDTO;
import com.inout.dto.horarioDTO;
import com.inout.dto.marcaDTO;
import com.inout.util.converters;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author gustavo
 */
public class ExcelGenerator {

    private WritableCellFormat title;
    private WritableCellFormat normalText;
    private WritableCellFormat normalTextBackGround;
    private static ExcelGenerator instance;

    private ExcelGenerator() {
    }

    public static ExcelGenerator getInstance() {
        if (instance == null) {
            instance = new ExcelGenerator();
        }
        return instance;
    }

    public void reportGenerator(List<marcaDTO> listMarcas) {
        String excelFilePath = System.getProperty("user.home") + "/excelReport.xls";

        WritableWorkbook workBook = null;
        int countLine = 0;
        try {

            //Creo el archivo excel
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            File excelFile = new File(excelFilePath);
            if (excelFile.exists()) {
                excelFile.delete();
            }
            workBook = Workbook.createWorkbook(excelFile, ws);

            WritableSheet sheet = this.inicializeExcel(workBook);

            //Inicializo la fila de inicio
            int row = 3;

            for (marcaDTO marca : listMarcas) {

                this.excelGenerator(sheet, row, countLine, marca);

                row++;
                countLine++;
            }

            workBook.write();
            workBook.close();

        } catch (IOException ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reportHorarioGenerator(List<horarioDTO> listaHorarios) {
        String excelFilePath = System.getProperty("user.home") + "/excelHorariosReport.xls";
        WritableWorkbook workBook = null;
        int countLine = 0;
        try {

            //Creo el archivo excel
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            File excelFile = new File(excelFilePath);
            if (excelFile.exists()) {
                excelFile.delete();
            }
            workBook = Workbook.createWorkbook(excelFile, ws);

            WritableSheet sheet = this.inicializeExcelHorarios(workBook);

            //Inicializo la fila de inicio
            int row = 3;

            for (horarioDTO horario : listaHorarios) {

                this.excelHorarioGenerator(sheet, row, countLine, horario);

                row++;
                countLine++;
            }

            workBook.write();
            workBook.close();

        } catch (IOException ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     public void reportAusenciaGenerator(List<ausenciaDTO> ausencias) {
        String excelFilePath = System.getProperty("user.home") + "/excelAusenciasReport.xls";
        WritableWorkbook workBook = null;
        int countLine = 0;
        try {

            //Creo el archivo excel
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            File excelFile = new File(excelFilePath);
            if (excelFile.exists()) {
                excelFile.delete();
            }
            workBook = Workbook.createWorkbook(excelFile, ws);

            WritableSheet sheet = this.inicializeExcelFaltas(workBook);

            //Inicializo la fila de inicio
            int row = 3;

            for (ausenciaDTO Ausencias : ausencias) {

                this.excelAusenciaGenerator(sheet, row, countLine, Ausencias);

                row++;
                countLine++;
            }

            workBook.write();
            workBook.close();

        } catch (IOException ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /**
     *
     * @param sheet
     * @param row
     * @param countLine
     * @param line
     */
    private void excelGenerator(WritableSheet sheet, int row, int countLine, marcaDTO marca) {

        // logger.info("Generando Excel.");

        try {

            int col = 0;

            Label completeNameLabel = null;
            Label docLabel = null;
            Label dateLabel = null;
            Label hourLabel = null;
            Label disLabel = null;
            Label disIdLabel = null;
            Label idRegLabel = null;
            Label idRegParLabel = null;
            Label obsLabel = null;

            if (countLine % 2 == 0) {

                if (!marca.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, marca.getPersona().getNombreCompleto(), this.normalText);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalText);
                }

                if (!marca.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, marca.getPersona().getDocumento(), this.normalText);
                } else {
                    docLabel = new Label(col++, row, "", this.normalText);
                }

                if (!marca.getFechaStr().equals("")) {
                    dateLabel = new Label(col++, row, marca.getFechaStr(), this.normalText);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalText);
                }

                if (!marca.getHora().equals("")) {
                    hourLabel = new Label(col++, row, marca.getHora(), this.normalText);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalText);
                }

                if (!marca.getDispositivo().equals("")) {
                    disLabel = new Label(col++, row, marca.getDispositivo(), this.normalText);
                } else {
                    disLabel = new Label(col++, row, "", this.normalText);
                }

                if (!marca.getIdDispositivo().equals("")) {
                    disIdLabel = new Label(col++, row, marca.getIdDispositivo(), this.normalText);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalText);
                }

                idRegLabel = new Label(col++, row, String.valueOf(marca.getId()), this.normalText);

                if (marca.getIdPareja() > 0L) {
                    idRegParLabel = new Label(col++, row, String.valueOf(marca.getIdPareja()), this.normalText);
                } else {
                    idRegParLabel = new Label(col++, row, "", this.normalText);
                }

                if (marca.getObservaciones() != null) {
                    obsLabel = new Label(col++, row, marca.getObservaciones(), this.normalText);
                } else {
                    obsLabel = new Label(col++, row, "", this.normalText);
                }

            } else {

                if (!marca.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, marca.getPersona().getNombreCompleto(), this.normalTextBackGround);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!marca.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, marca.getPersona().getDocumento(), this.normalTextBackGround);
                } else {
                    docLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!marca.getFechaStr().equals("")) {
                    dateLabel = new Label(col++, row, marca.getFechaStr(), this.normalTextBackGround);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!marca.getHora().equals("")) {
                    hourLabel = new Label(col++, row, marca.getHora(), this.normalTextBackGround);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!marca.getDispositivo().equals("")) {
                    disLabel = new Label(col++, row, marca.getDispositivo(), this.normalTextBackGround);
                } else {
                    disLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!marca.getIdDispositivo().equals("")) {
                    disIdLabel = new Label(col++, row, marca.getIdDispositivo(), this.normalTextBackGround);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                idRegLabel = new Label(col++, row, String.valueOf(marca.getId()), this.normalTextBackGround);

                if (marca.getIdPareja() > 0L) {
                    idRegParLabel = new Label(col++, row, String.valueOf(marca.getIdPareja()), this.normalTextBackGround);
                } else {
                    idRegParLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (marca.getObservaciones() != null) {
                    obsLabel = new Label(col++, row, marca.getObservaciones(), this.normalTextBackGround);
                } else {
                    obsLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

            }

            sheet.addCell(completeNameLabel);
            sheet.addCell(docLabel);
            sheet.addCell(dateLabel);
            sheet.addCell(hourLabel);
            sheet.addCell(disLabel);
            sheet.addCell(disIdLabel);
            sheet.addCell(idRegLabel);
            sheet.addCell(idRegParLabel);
            sheet.addCell(obsLabel);

        } catch (Exception ex) {
            //logger.error(ExcelReport.class.getName(),ex);
            //logger.error("número de linea: " + countLine);
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void excelHorarioGenerator(WritableSheet sheet, int row, int countLine, horarioDTO horario) {

        // logger.info("Generando Excel.");

        try {

            int col = 0;

            Label completeNameLabel = null;
            Label docLabel = null;
            Label dateLabel = null;
            Label hourLabel = null;
            Label disLabel = null;
            Label disIdLabel = null;
            Label idRegLabel = null;
            Label idRegParLabel = null;
            Label obsLabel = null;

            if (countLine % 2 == 0) {

                if (!horario.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, horario.getPersona().getNombreCompleto(), this.normalText);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalText);
                }

                if (!horario.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, horario.getPersona().getDocumento(), this.normalText);
                } else {
                    docLabel = new Label(col++, row, "", this.normalText);
                }

                if (!horario.getDiaSemStr().equals("")) {
                    dateLabel = new Label(col++, row, horario.getDiaSemStr(), this.normalText);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalText);
                }

                if (!horario.getInicio().equals("")) {
                    hourLabel = new Label(col++, row, horario.getInicio(), this.normalText);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalText);
                }

                if (!horario.getFin().equals("")) {
                    disLabel = new Label(col++, row, horario.getFin(), this.normalText);
                } else {
                    disLabel = new Label(col++, row, "", this.normalText);
                }

                if (!horario.getObservaciones().equals("")) {
                    disIdLabel = new Label(col++, row, horario.getObservaciones(), this.normalText);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalText);
                }

                idRegLabel = new Label(col++, row, String.valueOf(horario.getId()), this.normalText);



            } else {

                if (!horario.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, horario.getPersona().getNombreCompleto(), this.normalTextBackGround);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!horario.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, horario.getPersona().getDocumento(), this.normalTextBackGround);
                } else {
                    docLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!horario.getDiaSemStr().equals("")) {
                    dateLabel = new Label(col++, row, horario.getDiaSemStr(), this.normalTextBackGround);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!horario.getInicio().equals("")) {
                    hourLabel = new Label(col++, row, horario.getInicio(), this.normalTextBackGround);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!horario.getFin().equals("")) {
                    disLabel = new Label(col++, row, horario.getFin(), this.normalTextBackGround);
                } else {
                    disLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!horario.getObservaciones().equals("")) {
                    disIdLabel = new Label(col++, row, horario.getObservaciones(), this.normalTextBackGround);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                idRegLabel = new Label(col++, row, String.valueOf(horario.getId()), this.normalTextBackGround);



            }

            sheet.addCell(completeNameLabel);
            sheet.addCell(docLabel);
            sheet.addCell(dateLabel);
            sheet.addCell(hourLabel);
            sheet.addCell(disLabel);
            sheet.addCell(disIdLabel);
            sheet.addCell(idRegLabel);
//                sheet.addCell(idRegParLabel);
//                sheet.addCell(obsLabel);

        } catch (Exception ex) {
            //logger.error(ExcelReport.class.getName(),ex);
            //logger.error("número de linea: " + countLine);
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



     private void excelAusenciaGenerator(WritableSheet sheet, int row, int countLine, ausenciaDTO ausencia) {

        // logger.info("Generando Excel.");

        try {

            int col = 0;

            Label completeNameLabel = null;
            Label docLabel = null;
            Label dateLabel = null;
            Label hourLabel = null;
            Label disLabel = null;
            Label disIdLabel = null;
            Label idRegLabel = null;
            Label idRegParLabel = null;
            Label obsLabel = null;

            if (countLine % 2 == 0) {

                if (!ausencia.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, ausencia.getPersona().getNombreCompleto(), this.normalText);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalText);
                }

                if (!ausencia.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, ausencia.getPersona().getDocumento(), this.normalText);
                } else {
                    docLabel = new Label(col++, row, "", this.normalText);
                }

                if (!ausencia.getFechaStr().equals("")) {
                    dateLabel = new Label(col++, row, ausencia.getFechaStr(), this.normalText);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalText);
                }

                if (ausencia.getMotivo()!=null && !ausencia.getMotivo().getMotivo().equals("")) {
                    hourLabel = new Label(col++, row, ausencia.getMotivo().getMotivo(), this.normalText);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalText);
                }

                if (!ausencia.getObservacion().equals("")) {
                    disLabel = new Label(col++, row, ausencia.getObservacion(), this.normalText);
                } else {
                    disLabel = new Label(col++, row, "", this.normalText);
                }

                if (ausencia.getHorario()!=null && !ausencia.getHorario().toString().equals("")) {
                    disIdLabel = new Label(col++, row, ausencia.getHorario().toString(), this.normalText);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalText);
                }

                idRegLabel = new Label(col++, row, String.valueOf(ausencia.getID()), this.normalText);



            } else {

                if (!ausencia.getPersona().getNombreCompleto().equals("")) {
                    completeNameLabel = new Label(col++, row, ausencia.getPersona().getNombreCompleto(), this.normalTextBackGround);
                } else {
                    completeNameLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!ausencia.getPersona().getDocumento().equals("")) {
                    docLabel = new Label(col++, row, ausencia.getPersona().getDocumento(), this.normalTextBackGround);
                } else {
                    docLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!ausencia.getFechaStr().equals("")) {
                    dateLabel = new Label(col++, row, ausencia.getFechaStr(), this.normalTextBackGround);
                } else {
                    dateLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (ausencia.getMotivo()!=null && !ausencia.getMotivo().getMotivo().equals("")) {
                    hourLabel = new Label(col++, row, ausencia.getMotivo().getMotivo(), this.normalTextBackGround);
                } else {
                    hourLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (!ausencia.getObservacion().equals("")) {
                    disLabel = new Label(col++, row, ausencia.getObservacion(), this.normalTextBackGround);
                } else {
                    disLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                if (ausencia.getHorario()!=null && !ausencia.getHorario().toString().equals("")) {
                    disIdLabel = new Label(col++, row, ausencia.getHorario().toString(), this.normalTextBackGround);
                } else {
                    disIdLabel = new Label(col++, row, "", this.normalTextBackGround);
                }

                idRegLabel = new Label(col++, row, String.valueOf(ausencia.getID()), this.normalTextBackGround);



            }

            sheet.addCell(completeNameLabel);
            sheet.addCell(docLabel);
            sheet.addCell(dateLabel);
            sheet.addCell(hourLabel);
            sheet.addCell(disLabel);
            sheet.addCell(disIdLabel);
            sheet.addCell(idRegLabel);
//                sheet.addCell(idRegParLabel);
//                sheet.addCell(obsLabel);

        } catch (Exception ex) {
            //logger.error(ExcelReport.class.getName(),ex);
            //logger.error("número de linea: " + countLine);
            Logger.getLogger(ExcelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private WritableSheet inicializeExcel(WritableWorkbook w) throws Exception {

        // Initialize excel parameters and objects
        int row = 0;
        int col = 0;

        WritableSheet sheet = w.createSheet("Hoja", 0);

        // Initialize column width
        int columna = 0;
        sheet.setPageSetup(PageOrientation.LANDSCAPE);
        sheet.mergeCells(0, 0, 1, 0);//Desde (0,0), cantidad columnas(1), hasta fila(0)
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 60);

        //Create Cell Formats

        // Create a TITLE cell format for Arial 18, bold, underlined
        WritableFont arial12;
        arial12 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        this.title = new WritableCellFormat(arial12);
        this.title.setAlignment(Alignment.CENTRE);
        this.title.setBackground(Colour.DARK_RED);


        // Create a NORMALTEXT cell format for arial 10, normal, alignment
        WritableFont arial10;
        arial10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        this.normalText = new WritableCellFormat(arial10);
        this.normalText.setAlignment(Alignment.CENTRE);
        this.normalText.setVerticalAlignment(VerticalAlignment.CENTRE);


        //Create a NORMALTEXT with Background color
        this.normalTextBackGround = new WritableCellFormat(arial10);
        this.normalTextBackGround.setAlignment(Alignment.CENTRE);
        this.normalTextBackGround.setBackground(Colour.ICE_BLUE);

        Label label = null;
        String data = "";

        //Main Title
        data = "Reporte de Registro de Movimientos";

        label = new Label(col, row, data, title);
        sheet.addCell(label);

        // First line
        data = "Nombre Completo";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Documento";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Fecha";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Hora";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Dispositivo";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Id Dispositivo";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Id Registro";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Id Registro Pareja";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Observaciones";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        return sheet;

    }

    private WritableSheet inicializeExcelHorarios(WritableWorkbook w) throws Exception {

        // Initialize excel parameters and objects
        int row = 0;
        int col = 0;

        WritableSheet sheet = w.createSheet("Hoja", 0);

        // Initialize column width
        int columna = 0;
        sheet.setPageSetup(PageOrientation.LANDSCAPE);
        sheet.mergeCells(0, 0, 1, 0);//Desde (0,0), cantidad columnas(1), hasta fila(0)
        sheet.setColumnView(columna++, 35);



        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 60);

        //Create Cell Formats

        // Create a TITLE cell format for Arial 18, bold, underlined
        WritableFont arial13;
        arial13 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        this.title = new WritableCellFormat(arial13);
        this.title.setAlignment(Alignment.CENTRE);
        this.title.setBackground(Colour.DARK_RED);


        // Create a NORMALTEXT cell format for arial 10, normal, alignment
        WritableFont arial10;
        arial10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        this.normalText = new WritableCellFormat(arial10);
        this.normalText.setAlignment(Alignment.CENTRE);
          this.normalText.setVerticalAlignment(VerticalAlignment.CENTRE);


        //Create a NORMALTEXT with Background color
        this.normalTextBackGround = new WritableCellFormat(arial10);
        this.normalTextBackGround.setAlignment(Alignment.CENTRE);
        this.normalTextBackGround.setBackground(Colour.ICE_BLUE);

        Label label = null;
        String data = "";

        //Main Title
        data = "Reporte de horarios de personal";

        label = new Label(col, row, data, title);
        sheet.addCell(label);

        // First line
        data = "Nombre Completo";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Documento";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Día";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Hora Inicio";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Hora fin";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Observaciones";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Id Registro";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);



        return sheet;

    }

    private WritableSheet inicializeExcelFaltas(WritableWorkbook w) throws Exception {

        // Initialize excel parameters and objects
        int row = 0;
        int col = 0;

        WritableSheet sheet = w.createSheet("Hoja", 0);

        // Initialize column width
        int columna = 0;
        sheet.setPageSetup(PageOrientation.LANDSCAPE);
        sheet.mergeCells(0, 0, 1, 0);//Desde (0,0), cantidad columnas(1), hasta fila(0)
        sheet.setColumnView(columna++, 35);



        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 35);
        sheet.setColumnView(columna++, 30);
        sheet.setColumnView(columna++, 60);

        //Create Cell Formats

        // Create a TITLE cell format for Arial 18, bold, underlined
        WritableFont arial13;
        arial13 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        this.title = new WritableCellFormat(arial13);
        this.title.setAlignment(Alignment.CENTRE);
        this.title.setBackground(Colour.DARK_RED);


        // Create a NORMALTEXT cell format for arial 10, normal, alignment
        WritableFont arial10;
        arial10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        this.normalText = new WritableCellFormat(arial10);
        this.normalText.setAlignment(Alignment.CENTRE);
          this.normalText.setVerticalAlignment(VerticalAlignment.CENTRE);


        //Create a NORMALTEXT with Background color
        this.normalTextBackGround = new WritableCellFormat(arial10);
        this.normalTextBackGround.setAlignment(Alignment.CENTRE);
        this.normalTextBackGround.setBackground(Colour.ICE_BLUE);

        Label label = null;
        String data = "";

        //Main Title
        data = "Reporte faltas de personal";

        label = new Label(col, row, data, title);
        sheet.addCell(label);

        // First line
        data = "Nombre Completo";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Documento";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Fecha";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Motivo ausencia";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Observaciones";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Horario";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);

        data = "Id Registro";
        row = 2;
        label = new Label(col++, row, data, title);
        sheet.addCell(label);



        return sheet;

    }

}
