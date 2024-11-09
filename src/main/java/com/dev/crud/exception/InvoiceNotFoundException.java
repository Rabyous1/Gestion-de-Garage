package com.dev.crud.exception;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException() {
        super();
    }

    public InvoiceNotFoundException(String customMessage){
        super(customMessage);
    }
}
