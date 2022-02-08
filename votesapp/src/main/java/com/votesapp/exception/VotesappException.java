package com.votesapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VotesappException extends Exception {

    private HttpStatus status;

    public VotesappException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
