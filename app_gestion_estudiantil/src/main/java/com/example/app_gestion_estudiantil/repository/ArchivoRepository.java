package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Archivo;
import com.example.app_gestion_estudiantil.entity.Foro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArchivoRepository {

    @Autowired
    private ArchivoCrudRepository archivoCrudRepository;

    public Archivo save(Archivo a) {
        return archivoCrudRepository.save(a);
    }


}






