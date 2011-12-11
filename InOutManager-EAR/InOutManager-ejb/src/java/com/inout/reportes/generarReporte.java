/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.reportes;

import java.awt.Component;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.apache.commons.logging.Log;


/**
 * Generates report using Jasper Report.
 */
public class generarReporte {

    /** The log. */

    /** The faces messages. */

    /**
     * Instantiates a new report generator.
     */
    public generarReporte() {
    }

    /**
     * Prints report in PDF format.
     *
     * @param params the params
     * @param template the template
     * @param attachmentName the attachment name
     *
     * @return true, if prints the report
     */
    @SuppressWarnings("unchecked")
    public final boolean printReport(Map params, String template,
            String attachmentName) {
        return printReport("P", params, template, attachmentName);
    }

    /**
     * Prints report in specified format. P=PDF, H=HTML, C=CSV, E=Excel
     *
     * @param format the format
     * @param params the params
     * @param template the template
     * @param attachmentName the attachment name
     *
     * @return true, if prints the report
     */
    @SuppressWarnings("unchecked")
    public final boolean printReport(String format, Map params,
            String jasperReport, String attachmentName) {


        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ext = context.getExternalContext();
           // InputStream fis = ext.getResourceAsStream(template);

            //JasperReport jasperReport = JasperCompileManager.compileReport(fis);

            if ("P".equals(format)) {
                params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport, params);
                HttpServletResponse resp = (HttpServletResponse) ext
                        .getResponse();
                resp.setContentType("application/pdf");
                String filename = new StringBuffer(attachmentName).append(
                        ".pdf").toString();
                resp.addHeader("Content-Disposition", "inline; filename="
                        + filename);

                JasperExportManager.exportReportToPdfStream(jasperPrint, resp
                        .getOutputStream());

            } else if ("C".equals(format)) {
                params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport, params);
                HttpServletResponse resp = (HttpServletResponse) ext
                        .getResponse();

                resp.setContentType("text/plain");
                String filename = new StringBuffer(attachmentName).append(
                        ".csv").toString();
                resp.setHeader("Content-Disposition", "inline;filename="
                        + filename);

                JRCsvExporter exporter = new JRCsvExporter();
                exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT,
                        jasperPrint);
                exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM,
                        resp.getOutputStream());
                exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS,
                        Boolean.TRUE);
                exporter.exportReport();
            } else if ("E".equals(format)) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport, params);
                HttpServletResponse resp = (HttpServletResponse) ext
                        .getResponse();

                resp.setContentType("application/vnd.ms-excel");
                String filename = new StringBuffer(attachmentName).append(
                        ".xls").toString();
                resp.setHeader("Content-Disposition", "inline;filename="
                        + filename);

                JExcelApiExporter exporterXLS = new JExcelApiExporter();

                exporterXLS.setParameter(
                    JRXlsExporterParameter.JASPER_PRINT,
                    jasperPrint);
                exporterXLS.setParameter(
                    JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
                    Boolean.TRUE);
                exporterXLS.setParameter(
                    JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                    Boolean.FALSE);
                exporterXLS.setParameter(
                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    Boolean.TRUE);
                exporterXLS.setParameter(
                    JRXlsExporterParameter
                        .IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
                    Boolean.TRUE);
                // exporterXLS.setParameter(
                // JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,
                // Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
                        resp.getOutputStream());
                exporterXLS.exportReport();
            }

            context.getApplication().getStateManager().saveView(context);
            context.responseComplete();

            return true;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
        }
        return false;
    }

}
