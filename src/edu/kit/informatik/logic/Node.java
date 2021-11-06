package edu.kit.informatik.logic;

import java.util.Objects;

/**
 * Class, which represent a node of a graph
 * @author Rodi
 * @version 1.0
 */
public class Node {
    
    private String identifier;
    private int nodeIndex;
    private boolean isVisited;
    private Node parent; 
    
    /**
     * constructor
     * @param identifier as String
     */
    public Node(String identifier) {
        this.identifier = identifier;
        this.parent = null;
    }
    
    /**
     * getter of identifier
     * @return identifier attribute
     */
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * setter of identifier
     * @param identifier as String
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    /**
     * getter of nodeIndex
     * @return nodeIndex as int
     */
    public int getNodeIndex() {
        return nodeIndex;
    }
    
    /**
     * setter of nodeIndex
     * @param nodeIndex as int
     */
    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
    
    /**
     * this method is to be used in during breadth first search
     * @return true if node is visited during bfs, if not false
     */
    public boolean isVisited() {
        return this.isVisited;
    }
    
    /**
     * this method is to be used in during breadth first search
     * @param value as boolean
     */
    public void setVisited(boolean value) {
        this.isVisited = value;
    }

    /**
     * this method is to be used in breadth first search 
     * @return parent node of the node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * this method is to be used in breadth first search
     * @param parent as Node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        final Node other = (Node) object;
        
        if (!other.getIdentifier().equals(this.getIdentifier())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.getIdentifier());
    }
}
