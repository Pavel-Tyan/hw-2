package org.example.exceptions;

public class ResourceNotEnoughException extends RuntimeException{
    public ResourceNotEnoughException(String message) {
        super(message);
    }
}
