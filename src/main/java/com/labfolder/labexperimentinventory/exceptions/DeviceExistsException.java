package com.labfolder.labexperimentinventory.exceptions;

public class DeviceExistsException extends RuntimeException {
    public DeviceExistsException(String message) {
        super(message);
    }

    public DeviceExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
