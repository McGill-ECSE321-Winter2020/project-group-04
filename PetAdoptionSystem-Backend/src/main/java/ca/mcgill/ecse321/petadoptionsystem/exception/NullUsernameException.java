package ca.mcgill.ecse321.petadoptionsystem.exception;

public class NullUsernameException extends RuntimeException{
    public NullUsernameException(String message){
        super(message);
    }
}
