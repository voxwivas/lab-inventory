package com.labfolder.labexperimentinventory.exceptions;

public class ChemicalExistsException extends RuntimeException {


    public ChemicalExistsException(String message) {
        super(message);
    }
    public ChemicalExistsException(String message, Throwable cause) {
        super(message,cause);
    }
}
