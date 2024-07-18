package com.example.prodotti.services;

import com.example.prodotti.dto.ProdottoPurchaseRequest;
import com.example.prodotti.dto.ProdottoPurchaseResponse;
import com.example.prodotti.dto.ProdottoRequest;
import com.example.prodotti.dto.ProdottoResponse;
import com.example.prodotti.entities.*;
import com.example.prodotti.exceptions.InputErratoException;
import com.example.prodotti.exceptions.ProdottoNotFoundException;
import com.example.prodotti.exceptions.ProdottoPurchaseException;
import com.example.prodotti.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ProdottoMapper prodottoMapper;

    public Prodotto getProdotto(Long id) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isEmpty()) throw new ProdottoNotFoundException("Il prodotto con id " +id+ " non esiste!");
        return optionalProdotto.get();
    }

    public ProdottoResponse getProdottoById(Long id) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isEmpty()) throw new ProdottoNotFoundException("Il prodotto con id " +id+ " non esiste!");
        Prodotto myProdotto = optionalProdotto.get();
        return prodottoMapper.responseFromProdotto(myProdotto);
    }

    public ProdottoResponse createProdotto(ProdottoRequest request) {
        if (request.getPrezzo() < 0)
            throw new InputErratoException("Il prezzo non puo essere negativo");
        if (request.getQuantita() < 0)
            throw new InputErratoException("La quantita non puo essere negativa!");
        Prodotto prodotto = prodottoMapper.prodottoFromRequest(request);
        prodottoRepository.save(prodotto);
        return prodottoMapper.responseFromProdotto(prodotto);
    }

    public List<ProdottoResponse> getAll() {
        return prodottoRepository.findAll()
                .stream()
                .map(prodottoMapper::responseFromProdotto)
                .toList();
    }

    public ProdottoResponse updateProdotto(Long id, ProdottoRequest request) {
        if (request.getPrezzo() < 0)
            throw new InputErratoException("Il prezzo non puo essere negativo");
        if (request.getQuantita() < 0)
            throw new InputErratoException("La quantita non puo essere negativa!");
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        getProdotto(id);
        Prodotto prodotto = prodottoMapper.prodottoFromRequest(request);
        prodotto.setId(id);
       prodottoRepository.save(prodotto);
       return prodottoMapper.responseFromProdotto(prodotto);
    }

    public void deleteById(Long id){
        getProdotto(id);
        prodottoRepository.deleteById(id);
    }


    public List<ProdottoPurchaseResponse> purchaseProducts(List<ProdottoPurchaseRequest> requestList) {
        List<ProdottoPurchaseResponse> result = new ArrayList<>();
        requestList.forEach(request -> {
            if (request.getQuantita() < 0)
                throw new InputErratoException("La quantita quantita riferita al prodotto con id "
                +request.getId_prodotto()+ " non puo essere zero o meno!");
            Optional<Prodotto> optionalProdotto= prodottoRepository.findById(request.getId_prodotto());
            if (optionalProdotto.isEmpty())
                throw new ProdottoNotFoundException("Il prodotto con id " +request.getId_prodotto()+ " non esiste!");
            Prodotto prodotto = optionalProdotto.get();
            if (prodotto.getQuantita() < request.getQuantita())
                throw new ProdottoPurchaseException("Non ci sono abbastanza scorte del prodotto " + prodotto.getNome() + " con id " + prodotto.getId());
            prodotto.setQuantita(prodotto.getQuantita() - request.getQuantita());
            prodottoRepository.save(prodotto);
            result.add(ProdottoPurchaseResponse.builder()
                    .id_prodotto(prodotto.getId())
                    .quantita(request.getQuantita())
                    .nome(prodotto.getNome())
                    .build());
        });
        return result;
    }
}
