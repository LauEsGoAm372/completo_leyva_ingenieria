package com.example.app_gestion_estudiantil.service;

import com.example.app_gestion_estudiantil.entity.Comentario;
import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.repository.ComentarioRepository;
import com.example.app_gestion_estudiantil.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ComentarioService {

    @Autowired
    private UserService userService;
    @Autowired
    private ComentarioRepository comentariorepository;
    @Autowired
    private ForoService foroService;


    public void guardarComentarios(String contenido, Long idUsuario, Long idforo) throws IOException, ParseException {
        // Obtener el usuario por su ID
        User user = userService.getUser2(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Foro foro =foroService.getForo(idforo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        foro.addComentarios(contenido);
        // Crear el comentario
        Comentario comentario = Comentario.builder()
                .contenido(contenido)
                .fecha(LocalDateTime.now())
                .reacciones(0)
                .user(user)
                .build();
        // Guardar el comentario en la base de datos

        comentariorepository.save(comentario);

        System.out.println("comentario agregado"+ contenido);
    }

    public Comentario getComentarios(Long id) {
        Optional<Comentario> e = comentariorepository.findById(id);
        return e.orElse(null);
    }
}