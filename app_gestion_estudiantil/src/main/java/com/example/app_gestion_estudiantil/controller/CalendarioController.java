package com.example.app_gestion_estudiantil.controller;

import com.example.app_gestion_estudiantil.entity.Calendario;
import com.example.app_gestion_estudiantil.service.CalendarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService calendarioService;


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestParam("categoria") String categoria,
                                           @RequestParam("contenido") String contenido,
                                           @RequestParam("fecha") Date fecha) {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Estado guardado exitosamente");

            // Convertir el objeto a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(response);

            calendarioService.guardareventito(categoria,contenido,fecha);
            return ResponseEntity.ok(jsonResponse);
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el estado");
        }
    }

    @GetMapping("/getall")
    public List<Calendario> getall(){
        return  calendarioService.getall();
    }

}
