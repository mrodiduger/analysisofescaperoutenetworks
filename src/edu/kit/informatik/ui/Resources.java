package edu.kit.informatik.ui;

/**
 * a static class to access some regexes
 * @author Rodi
 * @version 1.0
 */
public class Resources {
    /**
     * a space string, which seperates parameters
     */
    public static final String PARAMETER_SEPERATOR = " ";
    /**
     * a semicolon, which seperates edges
     */
    public static final String EDGE_SEPERATOR = ";";
    /**
     * regex of a graph identifier
     */
    public static final String GRAPH_IDENTIFIER = "[A-Z]{1,6}";
    /**
     * regex of a node identifier
     */
    public static final String NODE_IDENTIFIER = "[a-z]{1,6}";
    /**
     * regex of an edge
     */
    public static final String EDGE_FORMAT = "^[a-z]+[0-9]+[a-z]+$";
}
