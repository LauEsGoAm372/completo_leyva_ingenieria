package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Archivo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ArchivoCrudRepository extends CrudRepository<Archivo,Long> {

}
