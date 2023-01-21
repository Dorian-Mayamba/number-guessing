package com.numbers.exceptions;

public class NoRemainingAttemptsException extends Exception{
    public NoRemainingAttemptsException(String message){
        super(message);
    }
}
