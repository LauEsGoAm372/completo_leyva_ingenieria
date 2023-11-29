package com.example.app_gestion_estudiantil.controller;

import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.service.ForoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/foros")
public class ForoController {

    @Autowired
    private ForoService foroService;


    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEstado(@RequestParam("categoria") String categoria,
                                           @RequestParam("contenido") String contenido,
                                           @RequestParam(value = "archivo", required = false) MultipartFile archivo,
                                           @RequestParam("idUsuario") Long idUsuario) {
        try {
            // Crear un objeto de respuesta en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Estado guardado exitosamente");

            // Convertir el objeto a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(response);

            // Devolver la respuesta JSON
            System.out.println("Archivo recibido: " + archivo);
            foroService.guardarForo(categoria,contenido, archivo, idUsuario);
            return ResponseEntity.ok(jsonResponse);
        } catch (IOException | ParseException e) {
            System.out.println("Archivo recibido: " + archivo);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el estado");
        }

    }
    @PostMapping("/likes")
    public ResponseEntity<?> likes(@RequestBody Map<String, Long> payload) {
        try {
            Long id = payload.get("id");
            foroService.likes(id);
            return ResponseEntity.ok("Ok");
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al votar");
        }
    }

    @PostMapping("/corazoncitos")
    public ResponseEntity<?> corazoncitos(@RequestBody Map<String, Long> payload) {
        try {
            Long id = payload.get("id");
            foroService.corazoncitos(id);
            return ResponseEntity.ok("Ok");
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al votar\"}");
        }
    }
    @PostMapping("/asombros")
    public ResponseEntity<?> asombros(@RequestBody Map<String, Long> payload) {
        try {
            Long id = payload.get("id");
            foroService.asombros(id);
            return ResponseEntity.ok("Ok");
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al votar\"}");
        }
    }

    @PutMapping("/actualizar2")
    public Foro actualizarForo2(@RequestBody Foro foro) {
        System.out.println("Esto en el controlador de editar");
        return foro;
    }

    @GetMapping("/getall")
    public List<Foro> getall(){
        return  foroService.getall();
    }

}





