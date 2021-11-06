package edu.kit.informatik.comparators;

import java.util.Comparator;

/**
 * this comparator sorts max flows
 * @author Rodi
 * @version 1.0
 */

public class MaxFlowComparator implements Comparator<String[]> {
    
    @Override
    public int compare(String[] left, String[] right) {
        if (Integer.parseInt(left[0]) > Integer.parseInt(right[0])) {
            return 1;
        } else if (Integer.parseInt(left[0]) < Integer.parseInt(right[0])) {
            return -1;
        } else {
            int result1 = left[1].compareTo(right[1]);
            if (result1 < 0) {
                return -1;
            } else if (result1 > 0) {
                return 1;
            } else {
                int result2 = left[2].compareTo(right[2]);
                if (result2 < 0) {
                    return -1;
                } else if (result2 > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

}
