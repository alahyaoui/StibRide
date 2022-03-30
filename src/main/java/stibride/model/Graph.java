package stibride.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A graph is a collection of nodes
 * 
* @author Ayoub Lahyaoui
 */
public class Graph {

    private Set<Node> nodes;

    public Graph() {
        nodes = new HashSet<>();
    }

    /**
     * Add a node to the graph
     * 
     * @param node The node to be added to the graph.
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Search for a node in the graph by its key
     * 
     * @param key The key of the station to search for.
     * @return The node with the given key.
     */
    public Node search(int key) {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((key == node.getStation().getKey())) {
                return node;
            }
        }
        return null;
    }

    /**
     * Search for a node in the graph by its station name
     * 
     * @param stationName The name of the station to search for.
     * @return The node that matches the station name.
     */
    public Node search(String stationName) {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((stationName.equals(node.getStation().getName()))) {
                return node;
            }
        }
        return null;
    }

    /**
     * Clear the shortest path and distance for all nodes.
     */
    public void clearResearch() {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            node.clearShortestPath();
            node.setDistance(Integer.MAX_VALUE);
        }
    }

    /**
     * Returns the nodes in the graph
     * 
     * @return The set of nodes.
     */
    public Set<Node> getNodes() {
        return nodes;
    }
}
