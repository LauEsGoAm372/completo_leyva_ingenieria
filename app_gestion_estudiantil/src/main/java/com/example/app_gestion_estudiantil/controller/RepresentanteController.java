package com.example.app_gestion_estudiantil.controller;
import com.example.app_gestion_estudiantil.entity.Representante;
import com.example.app_gestion_estudiantil.service.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/representantes")
public class RepresentanteController {

    @Autowired
    private RepresentanteService representanteService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEstado(@RequestParam("categoria")String categoria,
                                           @RequestParam("firstname")String firstname,
                                           @RequestParam("description")String description,
                                           @RequestParam(value = "archivo", required = false) MultipartFile archivo){
        try {
            representanteService.guardarRepresentante(categoria,firstname, description, archivo);
            return ResponseEntity.ok("Estado guardado exitosamente");
        }catch(IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el estado");
        }
    }

    @GetMapping("/getall")
    public List<Representante> getall(){
        return  representanteService.getall();
    }

    @PostMapping("/voto")
    public ResponseEntity<?> guardarvoto(@RequestParam("id") Long id, @RequestParam("repre") Long repre) {
        try {
            representanteService.votar(id, repre);
            return ResponseEntity.ok("Ok");
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al votar");
        }
    }

    @PostMapping("/calificar")
    public ResponseEntity<?> calificar(@RequestParam("repre") Long repre, @RequestParam("nota") String nota) {
        try {
            representanteService.calificar(repre, nota);
            return ResponseEntity.ok("Ok");
        } catch (IOException | ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al votar");
        }
    }
}