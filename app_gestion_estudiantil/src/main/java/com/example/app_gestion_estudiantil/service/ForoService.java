package com.example.app_gestion_estudiantil.service;

import com.example.app_gestion_estudiantil.entity.Archivo;
import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.repository.ForoRepository;
import com.example.app_gestion_estudiantil.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ForoService {

    @Autowired
    private UserService userService;

    @Autowired
    private ForoRepository fororepository;

    @Autowired
    private ArchivoService archivoService;

    public void guardarForo(String categoria, String contenido, MultipartFile archivo, Long idUsuario) throws IOException, ParseException {
        // Obtener el usuario por su ID
        User user = userService.getUser2(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Crear el foro
        Foro foro = Foro.builder()
                .categoria(categoria)
                .contenido(contenido)
                .fecha(LocalDateTime.now())
                .likes(0)
                .corazoncitos(0)
                .asombros(0)
                .build();

        // Guardar el foro en la base de datos
        Foro foroGuardado = fororepository.save(foro); // El ID del foro se generará automáticamente

        // Guardar el archivo si no está vacío y asociarlo con el foro
        if (archivo != null && !archivo.isEmpty()) {
            Archivo archivoGuardado = archivoService.leerArchivo(archivo, foroGuardado);
            if (archivoGuardado != null) {
                String url = archivoGuardado.getNombre_archivo();
                // Agregar la ruta del archivo a la lista de archivos en el foro
                foroGuardado.addFile(url);
            }
        }

        // Asociar el usuario al foro
        foroGuardado.addUser(user);

        // Guardar el foro actualizado en la base de datos
        fororepository.save(foroGuardado);
    }

    public void likes(Long id) throws IOException, ParseException {
        System.out.println(id);
       Optional<Foro> r = getForo(id);;
       if (r.isPresent()) {
               Foro r2 = r.get();
                r2.setLikes(r2.getLikes() + 1);
                fororepository.save(r2);
       } else {
           throw new IllegalArgumentException("Representante no encontrado");
       }
    }

    public void corazoncitos(Long id) throws IOException, ParseException {
        Optional<Foro> r = getForo(id);;
        if (r.isPresent()) {
            Foro r2 = r.get();
            r2.setCorazoncitos(r2.getCorazoncitos() + 1);
            fororepository.save(r2);
        } else {
            throw new IllegalArgumentException("Representante no encontrado");
        }
    }
    public void asombros(Long id) throws IOException, ParseException {
        Optional<Foro> r = getForo(id);;
        if (r.isPresent()) {
            Foro r2 = r.get();
            r2.setAsombros(r2.getAsombros() + 1);
            fororepository.save(r2);
        } else {
            throw new IllegalArgumentException("Representante no encontrado");
        }
    }

    public Optional <Foro> getForo(Long id) {
        return fororepository.findById(id);
    }

    public List<Foro> getall(){
        return (List<Foro>) fororepository.findAll();
    }


}
