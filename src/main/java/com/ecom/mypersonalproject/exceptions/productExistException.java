package com.ecom.mypersonalproject.exceptions;

public class productExistException extends RuntimeException{
    public productExistException(String message){
        super(message);
    }
}
