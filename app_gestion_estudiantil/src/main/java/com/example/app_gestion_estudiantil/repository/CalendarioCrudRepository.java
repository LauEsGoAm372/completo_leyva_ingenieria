package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Calendario;
import com.example.app_gestion_estudiantil.entity.Foro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CalendarioCrudRepository  extends CrudRepository<Calendario,Long> {
    Optional<Calendario> findById(Long id);
    List<Calendario> findAll();
}
