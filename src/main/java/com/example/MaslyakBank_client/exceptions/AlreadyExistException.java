package com.example.MaslyakBank_client.exceptions;

public class AlreadyExistException extends RuntimeException  {

    public AlreadyExistException(String message) {
        super(message);
    }
}
