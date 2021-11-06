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

public class ListCommand implements UserRequest {
    /**
     * regex for in the assignment specified list command format
     */
    public static final String LIST_COMMAND_FORMAT = "^(list)(\\s[A-Z]{1,6}){0,1}$";
    
    private Session session;
    private String graphIdentifier;

    /**
     * executes the command
     */
    public void execute() throws SyntacticException, SemanticException {
        if (graphIdentifier == null) {
            List<Graph> graphs = session.getEscapeNetworks().getGraphsSorted();
            if (graphs.isEmpty()) {
                Terminal.printLine("EMPTY");
            }
            for (Graph graph : graphs) {
                Terminal.printLine(graph.getIdentifier() + " " + graph.getNodes().size());
            }
        } else {
            Graph graph = session.getEscapeNetworks().getGraph(this.graphIdentifier);
            List<String[]> maxFlowList = graph.getMaxFlowList();
            if (maxFlowList == null) {
                Terminal.printLine("EMPTY");
            } else {
                for (String[] element : maxFlowList) {
                    Terminal.printLine(element[0] + " " + element[1] + " " + element[2]);
                }
            }
        }
    }
    
    /**
     * sets the instance of this class ready to execute
     * @param session as Session,
     * @param parameters as ArrayList,
     */
    public void setReady(Session session,  List<String> parameters) throws SyntacticException {
        this.session = session;
        if (parameters.isEmpty()) { // if no parameters are given 
            this.graphIdentifier = null;
        } else if (parameters.size() == 1) {
            this.graphIdentifier = parameters.get(0);
        } else {
            throw new SyntacticException();
        }
    }
    /**
     * checks whether given inputString has the correct form
     * @param inputString
     */
    
    public void hasCorrectForm(String inputString) throws SyntacticException {
        if (!inputString.matches(LIST_COMMAND_FORMAT)) {
            throw new SyntacticException();
        }
    }
}
