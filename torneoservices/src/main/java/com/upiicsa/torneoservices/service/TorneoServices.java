package com.upiicsa.torneoservices.service;

//import java.util.Optional;

import com.upiicsa.torneoservices.model.TorneoDocument;
import com.upiicsa.torneoservices.repository.TorneoMongoRepository;
import com.upiicsa.torneoservices.service.api.ITorneoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TorneoServices implements ITorneoServices {

    @Autowired
    TorneoMongoRepository torneoRepo;

    @Override
    public int saveRegistro(TorneoDocument document) {
        if (!torneoRepo.findByCorreo(document.getCorreo()).isPresent()) {
            torneoRepo.save(document);
            return 200;
        } else {
            return 500;
        }
    }

    /*
     * @Override public Optional<TorneoDocument> saveRegistro(TorneoDocument
     * document) { torneoRepo.save(document); return
     * torneoRepo.findById(document.getId()); }
     */

}