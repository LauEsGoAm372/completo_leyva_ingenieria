package com.example.app_gestion_estudiantil.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.app_gestion_estudiantil.user.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String firstname;
    private String description;
    @ElementCollection
    private List<String> files;
    @ElementCollection
    private List<String> notas;
    private Integer votos;
    @ElementCollection
    private List<Long> votantes;


    public void addvote(){
        votos++;
    }

    public void addFile(String file) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(file);
    }

    public void addNota(String nota) {
        if (notas == null) {
            notas = new ArrayList<>();
        }
        notas.add(nota);
    }
    public void addVotante(Long user) {
        if (votantes == null) {
            votantes = new ArrayList<>();
        }
        votantes.add(user);
    }
}