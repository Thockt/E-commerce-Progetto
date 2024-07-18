package com.example.ordini.exceptions;

public class CarrelloNotFoundException extends RuntimeException{

    public CarrelloNotFoundException (String message){
        super(message);
    }
}
