package edu.kit.informatik.ui.commands;


import java.util.List;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.SemanticException;
import edu.kit.informatik.exceptions.SyntacticException;
import edu.kit.informatik.logic.Graph;
import edu.kit.informatik.ui.Session;
/**
 * 
 * @author Rodi
 *@version 1.0
 */

public class Flow implements UserRequest {
    
    private static final int NUM_OF_PARAMETERS = 3;
    private static final String FLOW_COMMAND_FORMAT = "^(flow)\\s[A-Z]{1,6}\\s[a-z]{1,6}\\s[a-z]{1,6}$";
    
    private Session session;
    private String graphIdentifier;
    private String sourceNodeIdentifier;
    private String sinkNodeIdentifier;
    
    
    /**
     * constructor
     */
    public Flow() {
        
    }
    
    /**
     * executes the command
     */
    public void execute() throws SyntacticException, SemanticException {
        Graph graph = session.getEscapeNetworks().getGraph(graphIdentifier);
      //prints the flow of the network for given source and sink nodes
        Terminal.printLine(graph.computeMaximalFlow(graph.getNodeFromIdentifier(sourceNodeIdentifier),
                graph.getNodeFromIdentifier(sinkNodeIdentifier))); 
    }
    
    /**
     * @param session as Session,
     * @param parameters as ArrayList, 
     */
    public void setReady(Session session, List<String> parameters) throws SyntacticException {
        this.session = session;
        if (parameters.size() != NUM_OF_PARAMETERS) { //if there are insufficent parameters
            throw new SyntacticException();
        }
        this.graphIdentifier = parameters.get(0);
        this.sourceNodeIdentifier = parameters.get(1);
        this.sinkNodeIdentifier = parameters.get(2);
    }
    /**
     * checks whether given inputString has the correct form
     * @param inputString
     */
    
    public void hasCorrectForm(String inputString) throws SyntacticException {
        if (!inputString.matches(FLOW_COMMAND_FORMAT)) {
            throw new SyntacticException();
        }
    }
}
