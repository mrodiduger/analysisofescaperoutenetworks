package edu.kit.informatik.ui;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.SemanticException;
import edu.kit.informatik.exceptions.SyntacticException;
import edu.kit.informatik.logic.*;
/**
 * 
 * @author Rodi
 *@version 1.0
 */

public class Session {
    
    private Networks escapeNetworks;
    
    /**
     * constructor
     * @param escapeNetworks as Networks
     */
    public Session(Networks escapeNetworks) {
        this.escapeNetworks = escapeNetworks;
    }
    
    /**
     * getter for escpapeNetworks attribute
     * @return escapeNetworks as Networks
     */
    public Networks getEscapeNetworks() {
        return this.escapeNetworks;
    }
    
    /**
     * starts the session
     */
    public void begin() {
        String inputString = Terminal.readLine();
        while (!inputString.equals("quit")) {
            try {
                Input input = new Input(inputString, this);
                if (input.getUserRequest() != null) {
                    input.getUserRequest().execute();
                }
            } catch (SyntacticException | SemanticException | NumberFormatException e) {
                Terminal.printError(e.getMessage());
            }    
            inputString = Terminal.readLine();
        }
    }
}
