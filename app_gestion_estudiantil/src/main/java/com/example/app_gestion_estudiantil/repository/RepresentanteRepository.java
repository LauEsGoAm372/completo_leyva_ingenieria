package com.example.app_gestion_estudiantil.repository;

import com.example.app_gestion_estudiantil.entity.Foro;
import com.example.app_gestion_estudiantil.entity.Representante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepresentanteRepository {
    @Autowired
    private RepresentanteCrudRepository representantecrud;

    public List<Representante> findAll(){ return(List<Representante>) representantecrud.findAll(); }

    public Optional<Representante> findById(Long id) {
        return representantecrud.findById(id);
    }

    public Representante save(Representante e) {return representantecrud.save(e); }
}