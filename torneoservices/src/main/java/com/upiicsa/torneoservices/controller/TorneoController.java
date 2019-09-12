package com.upiicsa.torneoservices.controller;

/* import java.util.Optional; */

//import com.upiicsa.torneoservices.errorhandling.InvalidTorneoException;
import com.upiicsa.torneoservices.model.ResponseCode;
import com.upiicsa.torneoservices.model.TorneoDocument;
import com.upiicsa.torneoservices.service.api.ITorneoServices;

import org.springframework.beans.factory.annotation.Autowired;
/* import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TorneoController {
    @Autowired
    ITorneoServices torneoService;

    @PostMapping(path = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseCode registrar(@RequestBody TorneoDocument document) {
        if (torneoService.saveRegistro(document) == 200) {
            return new ResponseCode(200, "Registro");
        } else {
            return new ResponseCode(500, "Correo Existente");
        }
    }
}