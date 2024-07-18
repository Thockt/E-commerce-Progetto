package com.example.utenti.services;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.entities.Utente;
import com.example.utenti.dto.UtenteRequest;
import com.example.utenti.dto.UtenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteMapper {

    @Autowired
    private IndirizzoService indirizzoService;

    public Utente utenteFromRequest (UtenteRequest request) {
        return Utente.builder()
                .nome(request.getNome())
                .cognome(request.getCognome())
                .dataNascita(request.getDataNascita())
                .email(request.getEmail())
                .password(request.getPassword())
                .codiceFiscale(request.getCodiceFiscale())
                .indirizzi(request.getIdIndirizzi()
                        .stream().map(indirizzoService::getIndirizzoById)
                        .toList())
                .build();
    }

    public UtenteResponse responseFromUtente (Utente utente){
        return UtenteResponse.builder()
                .id(utente.getId())
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .dataNascita(utente.getDataNascita())
                .email(utente.getEmail())
                .password(utente.getPassword())
                .codiceFiscale(utente.getCodiceFiscale())
                .idIndirizzi(utente.getIndirizzi()
                        .stream().map(Indirizzo::getId)
                        .toList())
                .build();
    }

}
