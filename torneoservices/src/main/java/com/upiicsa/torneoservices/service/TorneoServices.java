package com.upiicsa.torneoservices.service;

import com.upiicsa.torneoservices.errorhandling.InvalidTorneoException;

//import java.util.Optional;

import com.upiicsa.torneoservices.model.TorneoDocument;
import com.upiicsa.torneoservices.repository.TorneoMongoRepository;
import com.upiicsa.torneoservices.service.api.ITorneoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TorneoServices implements ITorneoServices {

    @Autowired
    TorneoMongoRepository torneoRepo;

    @Autowired
    private JavaMailSender email;

    String equipo, personaje, clase;

    @Override
    public int saveRegistro(TorneoDocument document) {
        try {
            String correo = document.getCorreo();
            if (!torneoRepo.findByCorreo(document.getCorreo()).isPresent()) {
                equipo = document.getEquipo();
                personaje = document.getPersonaje();
                clase = document.getClase();
                enviarCorreo(correo, "Registro correcto", 
                "Listo para el torneo!\nTú escogiste el equipo de "+equipo+
                ":\n- "+personaje+"\n- Clase "+clase+"\n\nBuena Suerte!");
                torneoRepo.save(document);
                return 200;
            } else {
                enviarCorreo(correo, "Registro existente", 
                "Perdon! pero su correo ya ha sido registrado\nTú escogiste el equipo de "+equipo+
                ":\n- "+personaje+"\n- Clase "+clase+"\n\n");
                return 500;
            }
        } catch (MailException e) {
            throw new InvalidTorneoException("Error en el Services, desconecte su Antivirus");
        }
    }

    @Override
    public void enviarCorreo(String correo, String asunto, String texto) throws MailException{
        SimpleMailMessage mensaje = new SimpleMailMessage();
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