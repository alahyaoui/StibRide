/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.model;

import g54895.atl.stibride.dto.StationsDto;
import g54895.atl.stibride.model.Node;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Ayoub
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    // getters and setters 
    public Set<Node> getNodes() {
        return nodes;
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
            if ((stationName.equals(node.getStation().getStationName()))) {
                return node;
            }
        }
        return null;
    }

}
