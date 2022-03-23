/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.model;

import stibride.dto.StationsDto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ayoub
 */
public class Node {

    private StationsDto station;

    private List<Node> shortestPath;// = new LinkedList<>();

    private Integer distance;// = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes;// = new HashMap<>();

    public Node(StationsDto station) {
        this.station = station;
        shortestPath = new LinkedList<>();
        distance = Integer.MAX_VALUE;
        adjacentNodes = new HashMap<>();
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public boolean containsDestination(Node destination) {
        for (Node node : adjacentNodes.keySet()) {
            if (node.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public void clearShortestPath() {
        shortestPath.clear();
    }

    public StationsDto getStation() {
        return station;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

}
