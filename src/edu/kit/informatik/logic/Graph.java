package edu.kit.informatik.logic;

import edu.kit.informatik.comparators.EdgeComparator;
import edu.kit.informatik.comparators.MaxFlowComparator;
import edu.kit.informatik.exceptions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 * {@summary} A graph class, which includes most of 
 * the key methods for the functionality of the program.
 * @author Rodi
 * @version 1.0
 */

public class Graph {
    /**
     * unique name of a graph(network).
     */
    private String identifier;
    /**
     * a list of nodes of the graph, which is dynamic due to user actions.
     */
    private List<Node> nodes = new ArrayList<Node>();
  /**
     * a list of edges of the graph, which is dynamic due to user actions.
     */
    private List<Edge> edges = new ArrayList<Edge>();
    /**
     * an ArrayList of string arrays, which stores calculated maximal flows
     * 0th index is the maximal flow
     * 1th index is the identifier of fromNode
     * 2th index is the identifier of toNode
     */
    private List<String[]> maxFlowList = new ArrayList<String[]>();
    
    /**
     * 
     * @param identifier String: a unique name of a graph(network)
     * @param edgesString List<String[]>, which store edges:
     * 0th index of string array is identifier of fromNode,
     * 1th index of string array is capacity of the edge,
     * 2th index of string array is identifier of toNode
     * @throws SemanticException if the given parameters does not fill specified requirements for a graph
     */
    public Graph(String identifier, List<String[]> edgesString) throws SemanticException {
        this.identifier = identifier;
        this.initializeNodesAndEdges(edgesString);
        giveIndex();
        if (this.getSourceNodes().isEmpty() || this.getSinkNodes().isEmpty()) {
            throw new SemanticException();
        }
    }    
    /**
     * 
     * @return String identifier of the Graph
     */
    
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * 
     * @param identifier as String 
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    /**
     * 
     * @return edges in a in assignment specified order
     */
    
    public List<Edge> getEdgesSorted() {
        Collections.sort(edges, new EdgeComparator());
        return edges;
    }
    
    /**
     * 
     * @return node list of the graph as List<Node>
     */
    public List<Node> getNodes() {
        return nodes;
    }
    
    /**
     * {@summary} checks whether the node is already in graph, if not adds node to the node list of graph
     * @param node 
     */
    public void addNode(Node node) {
        if (!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }
    
    // a method to use in constructor to give nodes indices
    private void giveIndex( ) {
        for (int i = 0; i < this.nodes.size(); i++) {
            this.nodes.get(i).setNodeIndex(i);
        }
    }
    
    /**
     * 
     * @param inputArray List<String[]> 0th index of String[] is the identifier of fromNode,
     * 1th index is the capacity of edge, 2th index is the identifier of toNode
     * @throws SemanticException if given inputArray of edges does not fill requirements to create a graph
     * @throws NumberFormatException if a capacity of an edge bigger than MAX_INT_VALUE
     */
    public void initializeNodesAndEdges(List<String[]> inputArray) throws SemanticException, NumberFormatException {
        for (int i = 0; i < inputArray.size(); i++) {
            int capacity = Integer.parseInt(inputArray.get(i)[1]);
            if (!(capacity > 0)) { 
                throw new SemanticException("capacity can not be 0");
            }
            String from = inputArray.get(i)[0];
            String to = inputArray.get(i)[2];
            if (from.equals(to)) {
                throw new SemanticException();
            }
            //This algorithm checks if there are any reverse edges by checking the edges
            //followed by the current index, by doing this the algorithm checks for reverse edges 
            //with least amount of iterations. Therefore the last edge does not need to be checked
            if (i != inputArray.size() - 1) {
                for (int j = i + 1; j < inputArray.size(); j++) {
                    if (inputArray.get(j)[0].equals(to) && inputArray.get(j)[2].equals(from)) {
                        throw new SemanticException("a reverse edge can not exist");
                    } else if (inputArray.get(j)[0].equals(from) && inputArray.get(j)[2].equals(to)) {
                        throw new SemanticException();
                    }
                }
            }

            Node fromNode;
            Node toNode;
            if (this.getNodeFromIdentifier(from) != null) { //if the node is found
                fromNode = this.getNodeFromIdentifier(from);
            } else {
                fromNode = new Node(from);
            }
            if (this.getNodeFromIdentifier(to) != null) { // if node is found
                toNode = this.getNodeFromIdentifier(to);
            } else {
                toNode = new Node(to);
            }
            Edge edge = new Edge(fromNode, toNode, capacity);
            Edge reverse = new Edge(toNode, fromNode, 0);
            this.addNode(fromNode);
            this.addNode(toNode);
            this.addEdge(edge);
            this.addEdge(reverse);
        }
    }
    
    /**
     * 
     * @param identifier of a node
     * @return the node, which has the given identifier
     * @throws SemanticException if there is no such a node with given identifier
     */
    public Node getNodeFromIdentifier(String identifier) throws SemanticException {
        for (Node node : this.nodes) {
            if (node.getIdentifier().equals(identifier)) {
                return node;
            }
        }
        return null;
    }
    /**
     * 
     * @param nodes as a List<Node>
     */
    
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    /**
     * 
     * @return edge list of the graph as List<Edge>
     */
    
    public List<Edge> getEdges() {
        return edges;
    }
    
    /**
     * 
     * @return List<String[]> of calculated max flows of the graph in in assignment specified order
     */
    public List<String[]> getMaxFlowList() {
        if (this.maxFlowList.isEmpty()) {
            return null;
        }
        Collections.sort(this.maxFlowList, new MaxFlowComparator());
        return maxFlowList;
    }

    private Edge getEdge(Node from, Node to) {
        for (Edge element : edges) {
            if (element.getFromNode().equals(from) && element.getToNode().equals(to) ) {
                return element;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param edgeString edge to be added as a String[]
     * @throws SemanticException if a problem related to program logic occurs
     * @throws NumberFormatException if the capacity of given edge is more than MAX_INT_VALUE
     */
    public void addExtraEdge(String[] edgeString) throws SemanticException, NumberFormatException {
        String from = edgeString[0];
        int capacity = Integer.parseInt(edgeString[1]);
        if (capacity <= 0) {
            throw new SemanticException();
        }
        String to = edgeString[2];
        if (to.equals(from)) {
            throw new SemanticException();
        }
        Node fromNode = getNodeFromIdentifier(from);
        Node toNode = getNodeFromIdentifier(to);
        if (fromNode == null && toNode == null) { // if the nodes of the given edge do not exist
            fromNode = new Node(from);
            toNode = new Node(to);
            fromNode.setNodeIndex(this.nodes.size());
            this.addNode(fromNode);
            toNode.setNodeIndex(this.nodes.size());
            this.addNode(toNode);
            this.addEdge(new Edge(fromNode, toNode, capacity));
            this.addEdge(new Edge(toNode, fromNode, 0));
        } else if (fromNode == null) { // if only the start node of the given edge does not exist
            fromNode = new Node(from);
            fromNode.setNodeIndex(this.nodes.size());
            this.addNode(fromNode);
            this.addEdge(new Edge(fromNode, toNode, capacity));
            this.addEdge(new Edge(toNode, fromNode, 0));
        } else if (toNode == null) { // if only the target node of the given edge does not exist
            toNode = new Node(to);
            toNode.setNodeIndex(this.nodes.size());
            this.addNode(toNode);
            this.addEdge(new Edge(fromNode, toNode, capacity));
            this.addEdge(new Edge(toNode, fromNode, 0));
        } else { // if both nodes of the edge are included in the graph 
            //if there is an edge from given start node to given target node
            if (this.getEdge(fromNode, toNode) != null && this.getEdge(fromNode, toNode).getCapacity() > 0) {
                this.getEdge(fromNode, toNode).setCapacity(capacity);
            } else { //if there is no such edge
                if (this.getEdge(toNode, fromNode) != null) {
                    throw new SemanticException();
                }
                Edge newEdge = new Edge(fromNode, toNode, capacity);
                this.addEdge(newEdge);
                this.addEdge(new Edge(toNode, fromNode, 0));
            }
            
        }
        if (this.getSourceNodes().isEmpty() || this.getSinkNodes().isEmpty()) {
            this.edges.remove(this.getEdge(fromNode, toNode));
            throw new SemanticException();
        }
        this.maxFlowList.clear();
    }
    
    
    private void addEdge(Edge newEdge) {
        if (!this.edges.contains(newEdge)) {
            this.edges.add(newEdge);
        }
    }
    
    
    private Edge getReverseEdge(Edge edge) {
        return this.getEdge(edge.getToNode(), edge.getFromNode());
    }
    
    // if parameter is true,returns residual capacity matrix, if not returns capacity matrix
    private int[][] getMatrix(boolean isResidual) {
        int[][] matrix = new int[nodes.size()][nodes.size()];
        for (Edge edge : this.edges) {
            int from = edge.getFromNode().getNodeIndex();
            int to = edge.getToNode().getNodeIndex();
            matrix[from][to] = edge.getCapacity(isResidual);
        }
        return matrix;
    }
    
    // this method finds the source nodes of the graph by finding nodes, that are not toNode of any edge
    private List<Node> getSourceNodes() {
        Set<Node> auxiliarySet = new HashSet<Node>();
        for (Edge edge : edges) {
            if (edge.getCapacity() != 0) {
                auxiliarySet.add(edge.getToNode());    
            }
        }
        List<Node> copy = new ArrayList<>(this.nodes);
        copy.removeAll(auxiliarySet);
        return copy;
    }
    
    //this method finds the sink nodes of the graph by finding nodes, that are not fromNode of any edge
    private List<Node> getSinkNodes() throws SemanticException {
        Set<Node> auxiliarySet = new HashSet<Node>();
        for (Edge edge : edges) {
            if (edge.getCapacity() != 0) {
                auxiliarySet.add(edge.getFromNode());
            }
        }
        List<Node> copy = new ArrayList<>(this.nodes);
        copy.removeAll(auxiliarySet);
        return copy;
    }
    
    
    private Node getNodeFromIndex(int i) {
        for (Node element : this.nodes) {
            if (element.getNodeIndex() == i) {
                return element;
            }
        }
        return null;
    }
    
    // this method determines the neighbour node of the given node by using residual capacity matrix
    private List<Node> getNeighbourNodes(Node a) {
        List<Node> neighbourNodes = new ArrayList<Node>();
        int nodeIndex = a.getNodeIndex();
        int[][] residualMatrix = this.getMatrix(true);
        for (int i = 0; i < this.nodes.size(); i++) {
            if (residualMatrix[nodeIndex][i] > 0) {
                neighbourNodes.add(getNodeFromIndex(i));
            }
        }
        if (!neighbourNodes.isEmpty()) {
            return neighbourNodes;
        } else {
            return null;
        }
    }
    
    // this method is called after every call of a breadth first search algorithm
    //to set nodes ready for another breadth first search
    private void setAllNodesReadyForBfs() {
        for (Node element : nodes) {
            element.setVisited(false);
            element.setParent(null);
        }
    }
    
    // breadth first search algorithm which returns a path according to residual capacities of edges
    private List<Edge> bfs(Node startNode, Node endNode) throws SemanticException {
        if (!getSourceNodes().contains(startNode) || !getSinkNodes().contains(endNode)) {
            throw new SemanticException("given nodes are not source or sink nodes!");
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(startNode);
        startNode.setVisited(true);
        Node currentNode = null;
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            if (currentNode.getIdentifier().equals(endNode.getIdentifier())) {
                break;
            } else {
                if (getNeighbourNodes(currentNode) == null) {
                    continue;
                }
                for (Node neighbour : getNeighbourNodes(currentNode)) {
                    if (!neighbour.isVisited()) {
                        neighbour.setParent(currentNode);
                        queue.add(neighbour);
                        neighbour.setVisited(true);
                    }
                }
            }
            
        }
        
        List<Edge> edgePath = new ArrayList<Edge>();
        if (currentNode.equals(endNode)) {
            while (currentNode.getParent() != null) {
                edgePath.add(getEdge(currentNode.getParent(), currentNode));
                currentNode = currentNode.getParent();
            }
            Collections.reverse(edgePath);
            setAllNodesReadyForBfs();
            return edgePath;
        } else {
            setAllNodesReadyForBfs();
            return null;
        }
    }
    
    //this method determines the minimum residual capacity of edges of the given path
    private static int getMinCapacityOf(List<Edge> path) {
        int minCapacity = path.get(0).getCapacity(true);
        for (Edge edge : path) {
            if (edge.getCapacity(true) < minCapacity) {
                minCapacity = edge.getCapacity(true);
            }
        }
        return minCapacity;
    }
    
    /**
     * 
     * @param startNode Node which should be a source Node
     * @param endNode Node which should be a sink Node
     * @return maximal flow from startNode to endNode as long
     * @throws SemanticException if a semantic problem occurs with the given parameters
     */
    public long computeMaximalFlow(Node startNode, Node endNode) throws SemanticException {
        if (!this.maxFlowList.isEmpty()) {
            for (String[] array : this.maxFlowList) {
                if (array[1].equals(startNode.getIdentifier()) && array[2].equals(endNode.getIdentifier())) {
                    return Integer.parseInt(array[0]);
                }
            }
        }
        
        for (Edge edge : edges) {
            edge.setFlow(0);
        }
        List<Edge> path = this.bfs(startNode, endNode);
        if (path == null) {
            // returns 0 by default if there is no path 
            String[] maxFlowData = {String.valueOf(0), startNode.getIdentifier(), endNode.getIdentifier()}; 
            this.maxFlowList.add(maxFlowData);
            return 0;
        }
        int minCapacity = getMinCapacityOf(path);
        while (path != null) {
            for (Edge edge : path) {
                int a = edge.getFlow() + minCapacity;
                int b = getReverseEdge(edge).getFlow() - minCapacity;
                edge.setFlow(a);
                getReverseEdge(edge).setFlow(b);
            }
            path = this.bfs(startNode, endNode);
            if (path != null) {
                minCapacity = getMinCapacityOf(path);
            }
            
        }
        long maxFlow = 0;
        for (Edge edge : edges) {
            if (edge.getToNode().equals(endNode)) {
                maxFlow = maxFlow + edge.getFlow();
            }
        }
        //saves the computed flow for purpose of parallel programming
        String[] maxFlowData = {String.valueOf(maxFlow), startNode.getIdentifier(), endNode.getIdentifier()};
        this.maxFlowList.add(maxFlowData);
        return maxFlow;
    }
    
}
