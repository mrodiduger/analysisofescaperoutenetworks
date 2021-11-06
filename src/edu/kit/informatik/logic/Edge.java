package edu.kit.informatik.logic;

import java.util.Objects;
/**
 * 
 * @author Rodi
 * @version 1.0
 */

public class Edge {
    
    private Node fromNode;
    private Node toNode;
    private int capacity;
    private int flow;
    
    /**
     * constructor of an edge
     * @param fromNode as Node
     * @param toNode as Node 
     * @param capacity as integer
     */
    public Edge(Node fromNode, Node toNode, int capacity) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.capacity = capacity;
    }
    
    /**
     * getter of fromNode
     * @return fromNode attribute
     */
    public Node getFromNode() {
        return fromNode;
    }
    
    /**
     * setter of fromNode
     * @param fromNode as Node
     */
    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }
    
    /**
     * getter of toNode
     * @return toNode attribute
     */
    public Node getToNode() {
        return toNode;
    }
    
    /**
     * setter of toNode
     * @param toNode
     */
    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }
    
    /**
     * getter of capacity
     * @return capacity as int
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * setter of capacity
     * @param capacity as int
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    /**
     * getter of flow
     * @return flow as int
     */
    public int getFlow() {
        return flow;
    }
    
    /**
     * setter of flow
     * @param flow as int
     */
    public void setFlow(int flow) {
        this.flow = flow;
    }
    

    /**
     * 
     * @param isResidual as boolean
     * @return if isResidual == true returns residual capacity (capacity - flow), if not returns capacity
     */
    public int getCapacity(boolean isResidual) {
        if (isResidual) {
            return (this.capacity - this.flow);
        } else {
            return this.capacity;
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        final Edge other = (Edge) object;
        
        if (!other.getFromNode().equals(this.getFromNode()) || !other.getToNode().equals(this.getToNode())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.getFromNode().getIdentifier() + this.getToNode().getIdentifier());
    }
}
