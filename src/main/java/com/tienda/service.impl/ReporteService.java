
package com.tienda.service.impl;

import com.tienda.service.IReporteService;
import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
public class ReporteService implements IReporteService {

    @Autowired
    DataSource dataSource;

    @Override
    public ResponseEntity<Resource> ObtenerReporte(String reporte, Map<String, Object> parametros, String tipo) throws IOException {
        try {
            // Determina el estilo para mostrar o descargar el archivo según el tipo
            String estilo = tipo.equals("vPdf") ? "inline; " : "attachment; ";
            String reportePath = "reportes";
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            // Carga la plantilla Jasper desde el classpath
            InputStream elReporte = new ClassPathResource(reportePath + File.separator + reporte + ".jasper").getInputStream();

            // Llena el informe Jasper con los parámetros y la conexión a la base de datos
            var reportJasper = JasperFillManager.fillReport(elReporte, parametros, dataSource.getConnection());

            // Determina el tipo de contenido y el nombre del archivo de salida
            MediaType mediaType = tipo.equals("Pdf") || tipo.equals("vPdf") ? MediaType.APPLICATION_PDF : null;
            String archivoSalida = tipo.equals("Pdf") || tipo.equals("vPdf") ? reporte + ".pdf" : "";

            // Exporta el informe a PDF y almacenar el resultado en el ByteArrayOutputStream
            JasperExportManager.exportReportToPdfStream(reportJasper, salida);
            byte[] data = salida.toByteArray();

            // Configura los encabezados para la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", estilo + "filename=\"" + archivoSalida + "\"");

            // Crea la respuesta HTTP con el archivo generado y los encabezados adecuados
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(mediaType)
                    .body(new InputStreamResource(new ByteArrayInputStream(data)));

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }

        return null;
    }
}
