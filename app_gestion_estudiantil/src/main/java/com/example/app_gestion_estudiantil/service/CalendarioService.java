package com.example.app_gestion_estudiantil.service;

import com.example.app_gestion_estudiantil.entity.Archivo;
import com.example.app_gestion_estudiantil.entity.Calendario;
import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.repository.CalendarioRepository;
import com.example.app_gestion_estudiantil.repository.ForoRepository;
import com.example.app_gestion_estudiantil.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service

public class CalendarioService {

    @Autowired
    private CalendarioRepository calendariorepository;

    public void guardareventito(String categoria, String contenido, Date fecha) throws IOException, ParseException {
        Calendario calendario = Calendario.builder()
                .categoria(categoria)
                .contenido(contenido)
                .fecha(fecha)
                .build();

        Calendario cGuardado = calendariorepository.save(calendario); // El ID del foro se generará automáticamente
        calendariorepository.save(cGuardado);
    }

    public List<Calendario> getall(){
        return (List<Calendario>) calendariorepository.findAll();
    }

}
