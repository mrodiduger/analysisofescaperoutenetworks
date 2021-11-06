package edu.kit.informatik.exceptions;

/**
 * 
 * @author Rodi
 * @version 1.0
 */

public class SemanticException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = 2047334297870319647L;
    /**
     * to throw exception with the given message
     * @param message as String
     */
    
    public SemanticException(String message) {
        super(message);
    }
    
    /**
     * to throw exception with the message "a semantic problem occured!"
     */
    public SemanticException() {
        super("a semantic problem occured!");
    }

}
