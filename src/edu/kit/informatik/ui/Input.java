package edu.kit.informatik.ui;



import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.*;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.UserRequest;

/**
 * 
 * @author Rodi
 *@version 1.0
 */
public class Input {
    
    private Session session;
    private UserRequest userRequest;
    private List<String> parameters;
    
    /**
     * constructor
     * @param inputString as String
     * @param session as Session
     * @throws SyntacticException if input is not compatible
     */
    public Input(String inputString, Session session) throws SyntacticException {
        this.session = session;
        parameters = new ArrayList<String>();
        this.parseInputString(inputString);
        
        
    }
    
    /**
     * get method for session attribute
     * @return session as Session
     */
    public Session getSession() {
        return session;
    }
    /**
     * get method for parameters attribute
     * @return parameters as ArrayList
     */
    
    public List<String> getParameters() {
        return parameters;
    }
    /**
     * getter for userRequest attribute
     * @return userRequest as UserRequest
     */
    
    public UserRequest getUserRequest() {
        return userRequest;
    }


    
    private void parseInputString(String inputString) throws SyntacticException {
        String[] parameterArray = inputString.split(Resources.PARAMETER_SEPERATOR); 
        if (parameterArray.length > 1) {
            for (int i = 1; i < parameterArray.length; i++) { //saving parameters in to a list
                this.parameters.add(parameterArray[i]);
            }
        }
        
        try {
            if (parameterArray.length == 0) {
                throw new SyntacticException();
            }
          //gets the corresponding instance of a command
            UserRequest userRequest = Command.getCorrespondingInstance(parameterArray[0]); 
            userRequest.hasCorrectForm(inputString);
            userRequest.setReady(this.session, this.parameters);
            this.userRequest = userRequest;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException | NoSuchMethodException | SecurityException | SyntacticException e) {
            Terminal.printError(e.getMessage());
        }
    }
    
    

}
