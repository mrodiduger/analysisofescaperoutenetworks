package edu.kit.informatik.ui.commands;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.exceptions.*;
import edu.kit.informatik.logic.*;
import edu.kit.informatik.ui.Resources;
import edu.kit.informatik.ui.Session;
import java.util.*;

/**
 * 
 * @author Rodi
 *@version 1.0
 */

public class Add implements UserRequest {
    
    private static final String ADD_COMMAND_FORMAT = "^(add)\\s[A-Z]{0,6}\\s([a-z]{1,6}[0-9]+[a-z]"
            + "{1,6};)*([a-z]{1,6}[0-9]+[a-z]{1,6})$";
    
    private Session session;
    private String graphIdentifier;
    private ArrayList<String[]> edges;
    /**
     * construtor
     */
    
    public Add() {
        this.edges = new ArrayList<String[]>();
    }
    
    /**
     * 
     */
    public void execute() throws SyntacticException, SemanticException, NumberFormatException {
 
        
        if (edges.size() == 1) { //this is for adding a section to the network
            Graph graphAlready = session.getEscapeNetworks().getGraph(this.graphIdentifier);
            graphAlready.addExtraEdge(edges.get(0));
            String section = edges.get(0)[0] + Integer.parseInt(edges.get(0)[1]) + edges.get(0)[2];
            Terminal.printLine("Added new section" + " " + section + " " + "to escape network" + " " 
                + this.graphIdentifier + ".");
        } else if (edges.size() > 1) { // this is for adding a new network
            try {
                session.getEscapeNetworks().getGraph(this.graphIdentifier);
                Terminal.printError("There is already such a graph");
            } catch (SemanticException e) {
                Graph graphNew = new Graph(this.graphIdentifier, this.edges);
                session.getEscapeNetworks().addNetwork(graphNew);
                Terminal.printLine("Added new escape network with identifier" + " " + this.graphIdentifier + ".");
            }
        }

    }
    /**
     * @param session as Session, should be in main class generated session
     * @param parameters as ArrayList<String> 
     */
    
    public void setReady(Session session, List<String> parameters) throws SyntacticException {
        this.session = session;
        this.graphIdentifier = parameters.get(0); // 0th index is the graph identifier
        String[] edgeString = parameters.get(1).split(Resources.EDGE_SEPERATOR); // seperates the edges 
        
        for (String element : edgeString) {
            /*
            if (!element.matches(Resources.EDGE_FORMAT)) { //remove this and try again-rodi
                throw new SyntacticException();
            }
            */
         // this is for separating capacity and nodes of an edge
            String[] edge = element.split("(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)"); 
            this.edges.add(edge);
        }
    }
    /**
     * @param inputString
     * @throws SyntacticException if given String does not have correct form
     */
    
    public void hasCorrectForm(String inputString) throws SyntacticException {
        if (!inputString.matches(ADD_COMMAND_FORMAT)) {
            throw new SyntacticException("input is not as expected.");
        }
    }

    
    
}
