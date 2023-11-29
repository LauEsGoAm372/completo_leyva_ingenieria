package com.example.app_gestion_estudiantil.entity;

import com.example.app_gestion_estudiantil.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comentario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_comentarios;
    private String contenido;
    private LocalDateTime fecha;
    private Integer reacciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("comentariosList")
    private User user;

}