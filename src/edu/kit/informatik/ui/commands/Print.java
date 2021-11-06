package edu.kit.informatik.ui.commands;

import edu.kit.informatik.logic.*;


import java.util.List;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.SemanticException;
import edu.kit.informatik.exceptions.SyntacticException;
import edu.kit.informatik.ui.Session;
/**
 * 
 * @author Rodi
 *@version 1.0
 */

public class Print implements UserRequest {
    /**
     * regex for in the assignment specified format of print command
     */
    public static final String PRINT_COMMAND_FORMAT = "^(print)\\s[A-Z]{1,6}$";
    
    private Session session;
    private String graphIdentifier;
    

    /**
     * executes the command
     */
    public void execute() throws SyntacticException, SemanticException {
        List<Edge> edges = session.getEscapeNetworks().getGraph(this.graphIdentifier).getEdgesSorted();
        for (Edge edge : edges) {
            if (edge.getCapacity() > 0) {
                Terminal.printLine(edge.getFromNode().getIdentifier() + edge.getCapacity() 
                    + edge.getToNode().getIdentifier());
            }
        }
    }
    
    /**
     * sets the instance of this class ready to execute
     * @param session as Session,
     * @param parameters as ArrayList,
     */
    public void setReady(Session session, List<String> parameters) throws SyntacticException {
        this.session = session;
        if (parameters.size() != 1) {
            throw new SyntacticException();
        }
        this.graphIdentifier = parameters.get(0);
    }
    /**
     * checks whether given inputString has the correct form
     * @param inputString
     */
    
    public void hasCorrectForm(String inputString) throws SyntacticException {
        if (!inputString.matches(PRINT_COMMAND_FORMAT)) {
            throw new SyntacticException();
        }
    }
}
