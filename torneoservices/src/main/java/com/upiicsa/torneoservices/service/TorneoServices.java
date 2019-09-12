package com.upiicsa.torneoservices.service;

import com.upiicsa.torneoservices.errorhandling.InvalidTorneoException;

//import java.util.Optional;

import com.upiicsa.torneoservices.model.TorneoDocument;
import com.upiicsa.torneoservices.repository.TorneoMongoRepository;
import com.upiicsa.torneoservices.service.api.ITorneoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TorneoServices implements ITorneoServices {

    @Autowired
    TorneoMongoRepository torneoRepo;

    @Autowired
    private JavaMailSender email;

    @Override
    public int saveRegistro(TorneoDocument document) {
        try {
            if (!torneoRepo.findByCorreo(document.getCorreo()).isPresent()) {
                enviarCorreo(document.getCorreo());
                torneoRepo.save(document);
                return 200;
            } else {
                return 500;
            }
        } catch (Exception e) {
            throw new InvalidTorneoException("Error en el Services");
        }
    }

    @Override
    public void enviarCorreo(String correo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("8d6b88ee32-515714@inbox.mailtrap.io");
        mensaje.setTo(correo);
        mensaje.setSubject("subject");
        mensaje.setText("text");
        email.send(mensaje);
    }

    /*
     * @Override public Optional<TorneoDocument> saveRegistro(TorneoDocument
     * document) { torneoRepo.save(document); return
     * torneoRepo.findById(document.getId()); }
     */

}