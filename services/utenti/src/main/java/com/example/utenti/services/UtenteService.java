package com.example.utenti.services;

import com.example.utenti.entities.Utente;
import com.example.utenti.entities.UtenteRequest;
import com.example.utenti.entities.UtenteResponse;
import com.example.utenti.exceptions.UtenteNotFoundException;
import com.example.utenti.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private UtenteMapper utenteMapper;

    public Utente getUtenteById (Long id){
        Optional<Utente> optionalUtente = utenteRepository.findById(id);
        if (optionalUtente.isEmpty())
            throw new UtenteNotFoundException("L'utente con l'id " + id + " non esiste!");
        return optionalUtente.get();
    }

    public UtenteResponse getUtenteResponseById(Long id) {
        return utenteMapper.responseFromUtente(getUtenteById(id));
    }

    public List<UtenteResponse> getAllUtenti() {
        return utenteRepository.findAll()
                .stream()
                .map(utenteMapper::responseFromUtente)
                .toList();
    }

    public UtenteResponse create (UtenteRequest request){
        Utente utente = utenteMapper.utenteFromRequest(request);
        utenteRepository.save(utente);
        return utenteMapper.responseFromUtente(utente);
    }

    public UtenteResponse update (Long id, UtenteRequest request){
        getUtenteById(id);
        Utente utente = utenteMapper.utenteFromRequest(request);
        utente.setId(id);
        utenteRepository.save(utente);
        return utenteMapper.responseFromUtente(utente);
    }

    public void deleteById(Long id) {
        getUtenteById(id);
        utenteRepository.deleteById(id);
    }

}
