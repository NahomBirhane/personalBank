package miu.edu.eaxmcs425.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String massage){
        super(massage);
    }
}
