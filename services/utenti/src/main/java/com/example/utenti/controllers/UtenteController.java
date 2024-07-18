package com.example.utenti.controllers;

import com.example.utenti.dto.IndirizzoRequest;
import com.example.utenti.dto.UtenteRequest;
import com.example.utenti.dto.UtenteResponse;
import com.example.utenti.entities.Indirizzo;
import com.example.utenti.handler.GenericResponse;
import com.example.utenti.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<UtenteResponse> getUtenteById(@PathVariable Long id) {
        return new ResponseEntity<>(utenteService.getUtenteResponseById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtenteResponse>> getAllUtenti() {
        return new ResponseEntity<>(utenteService.getAllUtenti(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UtenteResponse> createUtente(@RequestBody UtenteRequest request) {
        return new ResponseEntity<>(utenteService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UtenteResponse> updateUtente(@PathVariable Long id, @RequestBody UtenteRequest request) {
        return new ResponseEntity<>(utenteService.update(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteUtenteById(@PathVariable Long id) {
        utenteService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Utente con id " + id + " eliminato correttamente!"), HttpStatus.OK);
    }

    @GetMapping("/get_indirizzi/{id}")
    public ResponseEntity<List<Indirizzo>> getIndirizziByUtenteId (@PathVariable Long id_utente) {
        return new ResponseEntity<>(utenteService.getIndirizziUtente(id_utente), HttpStatus.OK);
    }

    @PutMapping("/add_indirizzo")
    public ResponseEntity<Indirizzo> addIndirizzo(@RequestBody IndirizzoRequest request) {
        return new ResponseEntity<>(utenteService.addIndirizzo(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete_indirizzo/{id}")
    public ResponseEntity<GenericResponse> deleteIndirizzoById(@PathVariable Long id_Indirizzo) {
        utenteService.deleteIndirizzo(id_Indirizzo);
        return new ResponseEntity<>(new GenericResponse("Indirizzo con id " +id_Indirizzo+ " eliminato con successo!"), HttpStatus.OK);
    }



}
