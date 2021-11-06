package edu.kit.informatik.ui.commands;

import edu.kit.informatik.exceptions.*;
import java.util.*;
import edu.kit.informatik.ui.*;
/**
 * 
 * @author Rodi
 *@version 1.0
 */

public interface UserRequest {
    /**
     * 
     * @throws SyntacticException if a user input related problem occurs
     * @throws SemanticException if a problem because of program logic occurs
     */
    void execute() throws SyntacticException, SemanticException;
    
    /**
     * 
     * @param session as a Session, in which program runs
     * @param parameters as an ArrayList of Strings 
     * @throws SyntacticException if the parameters does not fill command specific requirements
     */
    void setReady(Session session, List<String> parameters) throws SyntacticException;
    
    /**
     * 
     * {@summary} checks whether input string matches the specified regex
     * @throws SyntacticException if the input string does not match the regex
     * @param  inputString takes input String as parameter
     */
    void hasCorrectForm(String inputString) throws SyntacticException;
    
}