package com.example.utenti.services;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.exceptions.IndirizzoNotFoundException;
import com.example.utenti.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Indirizzo getIndirizzoById(Long id) {
        Optional<Indirizzo> indirizzoOptional = indirizzoRepository.findById(id);
        if (indirizzoOptional.isEmpty())
            throw new IndirizzoNotFoundException("L'indirizzo con id " + id + " non esiste!" );
        return indirizzoOptional.get();
    }

    public List<Indirizzo> getAll() {
        return indirizzoRepository.findAll();
    }

    public Indirizzo create (Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    public Indirizzo update (Long id, Indirizzo newindirizzo) {
        Indirizzo oldIndirizzo = getIndirizzoById(id);
        oldIndirizzo.setVia(newindirizzo.getVia());
        oldIndirizzo.setCivico(newindirizzo.getCivico());
        oldIndirizzo.setCap(newindirizzo.getCap());
        oldIndirizzo.setComune(newindirizzo.getComune());
        return oldIndirizzo;
    }

    public void deleteById (Long id) {
        getIndirizzoById(id);
        indirizzoRepository.deleteById(id);
    }

}
