
package com.tienda.controllers;

import com.tienda.service.IReporteService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    
    @Autowired
    IReporteService reporteService;
      // Endpoint para generar informes de usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<Resource> usuarios(@RequestParam String tipo) throws IOException {
  
        // El parámetro "tipo" indica el tipo de informe deseado (PDF, Excel, CSV, etc.)
        return reporteService.ObtenerReporte("usuarios", null, tipo);
    }
  // Endpoint para generar informes de ventas
@GetMapping("/venta")
public ResponseEntity<Resource> ventas( @RequestParam String tipo,Integer cantidadMinima,Integer cantidadMaxima) throws IOException {
  
    // Crea un mapa de parámetros para el informe
    Map<String, Object> parametros = new HashMap<>();
    
    // Agrega los valores de cantidad mínima y máxima al mapa de parámetros
    parametros.put("CantidadMinimaReporte", cantidadMinima);
    parametros.put("CantidadMaximaReporte", cantidadMaxima);
    
    // Llama al servicio para obtener el informe con los parámetros proporcionados
    return reporteService.ObtenerReporte("Venta", parametros, tipo);
}
}
