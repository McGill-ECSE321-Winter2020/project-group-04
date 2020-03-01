package ca.mcgill.ecse321.petadoptionsystem.exception;


public class ImageStorageException extends RuntimeException {
    public ImageStorageException(String error) {
        super(error);
    }

    public ImageStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}