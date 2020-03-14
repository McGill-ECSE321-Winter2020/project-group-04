package ca.mcgill.ecse321.petadoptionsystem.exception;

public class AmountInvalidException extends RuntimeException {
    public AmountInvalidException(String message){
        super(message);
    }
}
