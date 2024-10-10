package com.alexandergonzalez.miTienditaSystem.exception;

public class InvalidJWT extends RuntimeException {
    public InvalidJWT(String message) {
        super(message);
    }
}
