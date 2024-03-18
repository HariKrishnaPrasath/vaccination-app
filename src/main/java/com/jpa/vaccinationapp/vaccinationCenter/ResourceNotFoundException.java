package com.jpa.vaccinationapp.vaccinationCenter;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(String msg){

        super(msg);
    }

}
