package com.labfolder.labexperimentinventory.exceptions;

public class SampleExistsException extends RuntimeException {

    public SampleExistsException(String message) {
        super(message);
    }

    public SampleExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
