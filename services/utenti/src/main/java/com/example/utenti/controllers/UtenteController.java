package com.example.utenti.controllers;

import com.example.utenti.entities.UtenteRequest;
import com.example.utenti.entities.UtenteResponse;
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

}
