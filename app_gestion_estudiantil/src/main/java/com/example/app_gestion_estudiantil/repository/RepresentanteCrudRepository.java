package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Representante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepresentanteCrudRepository  extends CrudRepository<Representante,Long> {
    List<Representante> findAll();

    Optional<Representante> findById(Long id);
}
