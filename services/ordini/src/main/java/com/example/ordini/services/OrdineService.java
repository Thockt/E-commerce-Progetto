package com.example.ordini.services;

import com.example.ordini.dto.OrdineResponse;
import com.example.ordini.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private OrdineMapper ordineMapper;

    public List<OrdineResponse> getOrdineByCarrelloId(Long idCarrello) {
        return ordineRepository.getOrdiniByCarrelloId(idCarrello)
                .stream()
                .map(ordineMapper::responseFromOrdine)
                .toList();
    }
}
