package com.labfolder.labexperimentinventory.exceptions;

public class SampleNotFoundException extends RuntimeException {

    public SampleNotFoundException(String message) {
        super(message);
    }

    public SampleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
