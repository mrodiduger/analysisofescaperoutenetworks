package edu.kit.informatik.exceptions;

/**
 * An exception, thrown when a syntactic problem occurs
 * @author Rodi
 * @version 1.0
 */

public class SyntacticException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3517916233183448254L;
    /**
     * to throw Exception with given message
     * @param message as String
     */
    
    public SyntacticException(String message) {
        super(message);
    }
    
    /**
     * to throw exception with the message "a syntactic problem occured!"
     */
    public SyntacticException() {
        super("a syntactic problem occured!");
    }


}
