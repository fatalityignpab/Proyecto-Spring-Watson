package com.upiicsa.torneoservices.errorhandling;


public class InvalidTorneoException  extends RuntimeException {

    private static final long serialVersionUID = -4957281189808005732L;

    public InvalidTorneoException() {
      super();
    }
  
    public InvalidTorneoException(String message) {
      super(message);
    }
    
}