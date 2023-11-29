package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComentarioRepository {


    @Autowired
    private ComentarioCrudRepository comentarioCrudRepository;

    //public List<Comentarios> findAll() {
    // return (List<Comentarios>) comentariosCrudRepository.findAll();
    //}

    public Optional<Comentario> findById(Long id) {
        return comentarioCrudRepository.findById(id);
    }

    public Comentario save(Comentario e) {
        return comentarioCrudRepository.save(e);

    }
}