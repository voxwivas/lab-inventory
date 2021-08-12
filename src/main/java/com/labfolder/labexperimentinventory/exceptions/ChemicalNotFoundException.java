package com.labfolder.labexperimentinventory.exceptions;

public class ChemicalNotFoundException extends RuntimeException {

    public ChemicalNotFoundException(String message) {
        super(message);
    }

    public ChemicalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
