package edu.kit.informatik.ui;

import edu.kit.informatik.logic.Networks;

/**
 * 
 * @author Rodi
 * @version 1.0
 */

public final class Main {
  
    private Main() {
        
    }
    /**
     * main method for the program
     * @param args
     */
    
    public static void main(String[] args) {
        Networks networks = new Networks();
        Session session = new Session(networks);
        session.begin();
    }


}
