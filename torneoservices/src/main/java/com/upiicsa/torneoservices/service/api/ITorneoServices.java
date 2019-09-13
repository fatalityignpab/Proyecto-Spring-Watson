package com.upiicsa.torneoservices.service.api;

//import java.util.Optional;

import com.upiicsa.torneoservices.model.TorneoDocument;

import org.springframework.mail.MailException;

public interface ITorneoServices {

    int saveRegistro(TorneoDocument document);
    void enviarCorreo(String correo, String asunto, String texto) throws MailException;
    
}