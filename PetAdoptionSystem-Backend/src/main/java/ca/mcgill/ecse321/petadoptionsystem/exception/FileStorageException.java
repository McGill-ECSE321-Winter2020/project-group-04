package ca.mcgill.ecse321.petadoptionsystem.exception;


public class FileStorageException extends RuntimeException {
    public FileStorageException(String error){
        super(error);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}