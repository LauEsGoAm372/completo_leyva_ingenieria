package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Comentario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioCrudRepository extends CrudRepository<Comentario,Long> {

}
