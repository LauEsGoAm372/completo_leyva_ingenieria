package com.example.app_gestion_estudiantil.controller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class ArchivoController {

    @GetMapping("/api/archivo/bajar/{nombreArchivo}")
    public ResponseEntity<Resource> mostrarArchivo(@PathVariable String nombreArchivo) throws IOException {
        String rutaArchivo = "/home/laura/Imágenes/imagenes_spring/" + nombreArchivo; // Reemplaza esto con la ruta real de tu archivo
        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            System.out.println("Si existe");
            Resource resource = new FileSystemResource(archivo);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + archivo.getName());
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(archivo.toPath()));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(archivo.length())
                    .contentType(MediaType.parseMediaType(Files.probeContentType(archivo.toPath())))
                    .body(resource);
        } else {
            // El archivo no existe
            System.out.println("no existe");
            return ResponseEntity.notFound().build();
        }
    }
}
