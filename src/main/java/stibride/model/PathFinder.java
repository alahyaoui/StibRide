/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.model;

import observer.Observable;
import observer.Observer;
import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.model.Network;
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
        shortestPath = new LinkedList<>();
    }

    @Override
    public void search(int idOrigin, int idDestination) {
        Graph graph = network.getGraphStations();

        Node source = graph.search(idDestination);
        Dijkstra.calculateShortestPathFromSource(graph, source);
        shortestPath = graph.search(idOrigin).getShortestPath();
        System.out.println("Taille du plus cours chemin " + shortestPath.size());
        notifyObservers();
        //graph.clearResearch();
    }

    @Override
    public void search(String origin, String destination) {    
        network.getGraphStations().clearResearch();
        Graph graph = network.getGraphStations();
        
        Node destinationNode = graph.search(destination); 
        
        graph = Dijkstra.calculateShortestPathFromSource(graph, destinationNode);
        
        Node originNode = graph.search(origin);
        shortestPath = new LinkedList<>(originNode.getShortestPath());     
        
        System.out.println("source =" + destinationNode.getStation().getName());
        System.out.println("origin =" +graph.search(origin).getStation().getName());
        System.out.println("Taille du plus cours chemin " + shortestPath.size());     
        notifyObservers();
    }

    public Network getNetwork() {
        return network;
    }

    @Override
    public List<Node> getSearchResult() {
        return shortestPath;
    }

    @Override
    public List<String> getSearchResult2() {
        List<String> path = new ArrayList<>();

        for (Node node : shortestPath) {
            String stationName = node.getStation().getName();
            path.add(stationName);
        }

        return path;
    }

    @Override
    public List<StationsDto> getSearchResult3() {
        List<StationsDto> path = new ArrayList<>();

        for (Node node : shortestPath) {
            StationsDto station = node.getStation();
            path.add(station);
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
