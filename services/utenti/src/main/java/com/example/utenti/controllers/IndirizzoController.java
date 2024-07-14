package com.example.utenti.controllers;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.handler.GenericResponse;
import com.example.utenti.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Indirizzo> getIndirizzoById (@PathVariable Long id) {
        return new ResponseEntity<>(indirizzoService.getIndirizzoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Indirizzo>> getAll() {
        return new ResponseEntity<>(indirizzoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Indirizzo> createIndirizzo(@RequestBody Indirizzo indirizzo){
        return new ResponseEntity<>(indirizzoService.create(indirizzo), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Indirizzo> updateIndirizzo(@PathVariable Long id, @RequestBody Indirizzo indirizzo){
        return new ResponseEntity<>(indirizzoService.update(id, indirizzo), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteIndirizzoById(@PathVariable Long id){
        indirizzoService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Indirizzo con id " + id + " eliminato correttamente!"), HttpStatus.OK);
    }

}
