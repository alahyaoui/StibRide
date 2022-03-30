package stibride.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stibride.dto.StationsDto;

/**
 *
 * @author Ayoub
 */
public class StationNode {

    private StationsDto station;

    private List<StationNode> shortestPath;

    private Integer distance;

    Map<StationNode, Integer> adjacentStations;

    public StationNode(StationsDto station) {
        this.station = station;
        shortestPath = new LinkedList<>();
        distance = Integer.MAX_VALUE;
        adjacentStations = new HashMap<>();
    }

    public void addDestination(StationNode destination, int distance) {
        adjacentStations.put(destination, distance);
    }

    public boolean containsDestination(StationNode destination) {
        for (StationNode node : adjacentStations.keySet()) {
            if (node.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public void clearShortestPath() {
        shortestPath.clear();
    }

    //Getters

    public StationsDto getStation() {
        return station;
    }

    public Map<StationNode, Integer> getAdjacentNodes() {
        return adjacentStations;
    }

    public Integer getDistance() {
        return distance;
    }

    public List<StationNode> getShortestPath() {
        return shortestPath;
    }

    //Setters

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<StationNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

}
