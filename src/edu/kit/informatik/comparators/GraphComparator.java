package edu.kit.informatik.comparators;

import java.util.Comparator;

import edu.kit.informatik.logic.Graph;

/**
 * this comparator sorts graphs with the in assignment given specifications
 * @author Rodi
 * @version 1.0
 */
public class GraphComparator implements Comparator<Graph> {
    
    @Override
    public int compare(Graph left, Graph right) {
        int leftNodes = left.getNodes().size();
        int rightNodes = right.getNodes().size();
        if (rightNodes > leftNodes) {
            return 1;
        } else if (rightNodes < leftNodes) {
            return -1;
        } else {
            int lexico = left.getIdentifier().compareTo(right.getIdentifier());
            if (lexico < 0) {
                return -1;
            } else if (lexico > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
