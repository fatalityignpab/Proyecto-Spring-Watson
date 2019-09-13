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

    //String correoPara = "8d6b88ee32-515714@inbox.mailtrap.io";
    String correoPara = "torneoesport2@gmail.com";

    @Override
    public int saveRegistro(TorneoDocument document) {
        try {
            String correo = document.getCorreo();
            if (!torneoRepo.findByCorreo(document.getCorreo()).isPresent()) {
                enviarCorreo(correo, "Registro correcto", "Listo para el torneo!\nBuena Suerte!");
                torneoRepo.save(document);
                return 200;
            } else {
                enviarCorreo(correo, "Registro existente", "Perdon! pero su correo ya ha sido registrado");
                return 500;
            }
        } catch (Exception e) {
            throw new InvalidTorneoException("Error en el Services");
        }
    }

    @Override
    public void enviarCorreo(String correo, String asunto, String texto) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(this.correoPara);
        mensaje.setTo(correo);
        mensaje.setSubject(asunto);
        mensaje.setText(texto);
        email.send(mensaje);
    }

    /*
     * @Override public Optional<TorneoDocument> saveRegistro(TorneoDocument
     * document) { torneoRepo.save(document); return
     * torneoRepo.findById(document.getId()); }
     */

}