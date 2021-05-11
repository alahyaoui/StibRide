/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.model;

import atl.observer.Observable;
import atl.observer.Observer;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.model.Network;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author ayoub
 */
public class PathFinder extends Observable implements Model {

    private Network network;

    private List<Node> shortestPath;

    public PathFinder() throws RepositoryException {
        network = new Network();
    }

    @Override
    public void search(int idOrigin, int idDestination) {
        Graph graph = network.getGraphStations();

        Node source = graph.search(idDestination);
        Dijkstra.calculateShortestPathFromSource(graph, source);
        shortestPath = graph.search(idOrigin).getShortestPath();
    }
    
    @Override
    public void search(String origin, String destination) {
        Graph graph = network.getGraphStations();

        Node source = graph.search(destination);
        Dijkstra.calculateShortestPathFromSource(graph, source);
        shortestPath = graph.search(origin).getShortestPath();
    }
    
    public Network getNetwork() {
        return network;
    }
    
    @Override
    public List<Node> getSearchResult() {
        return shortestPath;
    }

    //@Override
    public List<String> getSearchResult2() {
        List<String> path = new ArrayList<>();

        for (Node node : shortestPath) {
            String stationName = node.getStation().getStationName();
            path.add(stationName);
        }

        return path;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }
}
