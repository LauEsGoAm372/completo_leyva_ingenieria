package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Calendario;
import com.example.app_gestion_estudiantil.entity.Foro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class CalendarioRepository {
    @Autowired
    private CalendarioCrudRepository calendarioCrudRepository;
    public List<Calendario> findAll() {
        return (List<Calendario>)calendarioCrudRepository.findAll();
    }

    public Optional<Calendario> findById(Long id) {
        return calendarioCrudRepository.findById(id);
    }

    public Calendario save(Calendario e) {
        return calendarioCrudRepository.save(e);
    }
}
