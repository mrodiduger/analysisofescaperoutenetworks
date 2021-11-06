package edu.kit.informatik.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kit.informatik.comparators.GraphComparator;
import edu.kit.informatik.exceptions.*;

/**
 * this class represents the memory of the program, where added graphs are stored and accessible
 * @author Rodi
 * @version 1.0
 */

public class Networks {
    
    private List<Graph> graphs;
    
    /**
     * constructor
     */
    public Networks() {
        this.graphs = new ArrayList<Graph>();
    }
    
    /**
     * adds graph to the list of the graphs
     * @param graph as Graph
     */
    public void addNetwork(Graph graph) {
        this.graphs.add(graph);
    }
    
    /**
     * returns graph with the given identifier
     * @param identifier
     * @return graph if there is a graph with the given identifier
     * @throws SemanticException if there is no such graph
     */
    public Graph getGraph(String identifier) throws SemanticException {
        for (Graph graph : graphs) {
            if (graph.getIdentifier().equals(identifier)) {
                return graph;
            }
        }
        throw new SemanticException("there is no such escape network");
    }
    
    /**
     * sorts graph with GraphComparator
     * @return sorted list of graphs
     */
    public List<Graph> getGraphsSorted() {
        Collections.sort(this.graphs, new GraphComparator());
        return graphs;
    }
    /**
     * getter of graphs attribute
     * @return graphs as List
     */
    
    public List<Graph> getGraphs() {
        return graphs;
    }

}
