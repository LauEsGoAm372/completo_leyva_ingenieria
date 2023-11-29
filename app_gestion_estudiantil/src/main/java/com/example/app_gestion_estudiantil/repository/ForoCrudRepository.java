package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Foro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForoCrudRepository extends CrudRepository <Foro,Long>{

    Optional<Foro> findById(Long id);
    List<Foro> findAll();
}
