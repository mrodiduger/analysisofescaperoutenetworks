package edu.kit.informatik.comparators;

import edu.kit.informatik.logic.Edge;
import java.util.Comparator;



/**
 * {@summary} A comparator, which is used to print edges of a graph in in assignment given order.
 * @author Rodi
 *@version 1.0
 */
public class EdgeComparator implements Comparator<Edge> {
    /**
     * @param left  Edge to compare
     * @param right Edge to compare
     * @return -1,1 or 0 
     */
    @Override
    public int compare(Edge left, Edge right) {
        //compares the fromNode ids of edges in lexicographical order
        int result = left.getFromNode().getIdentifier().compareTo(right.getFromNode().getIdentifier());
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            int result1 = left.getToNode().getIdentifier().compareTo(right.getToNode().getIdentifier());
            if (result1 < 0) {
                return -1;
            } else if (result1 > 0) {
                return 1;
            } else {
                return 0; //returns 0 if they are the same
            }
        }
    }
    
}
