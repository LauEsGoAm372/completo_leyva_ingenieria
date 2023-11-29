package com.example.app_gestion_estudiantil.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
   // private String apodo;
    private String firstname;
   // private String lastname;
    private String email;
    private String password;
   // private String carrera;
}

