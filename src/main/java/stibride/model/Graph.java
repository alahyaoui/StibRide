package stibride.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Ayoub
 */
public class Graph {

    private Set<Node> nodes;// = new HashSet<>();

    public Graph() {
        nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node search(int key) {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((key == node.getStation().getKey())) {
                return node;
            }
        }
        return null;
    }

    public Node search(String stationName) {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((stationName.equals(node.getStation().getName()))) {
                return node;
            }
        }
        return null;
    }

    public void clearResearch() {
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            node.clearShortestPath();
            node.setDistance(Integer.MAX_VALUE);
        }
    }

    // getters and setters 
    public Set<Node> getNodes() {
        return nodes;
    }

}
