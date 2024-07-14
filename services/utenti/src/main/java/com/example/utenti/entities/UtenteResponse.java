package com.example.utenti.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UtenteResponse {

    private Long id;
    private String nome;
    private String cognome;
    private LocalDateTime dataNascita;
    private String email;
    private String password;
    private String codiceFiscale;
    private List<Long> idIndirizzi;
}