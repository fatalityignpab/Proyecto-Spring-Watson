package com.upiicsa.torneoservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCode {

    private int status;
    private String mensaje;
}