package stibride.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import observer.Observable;
import observer.Observer;
import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;

/**
 *
 * @author ayoub
 */
public class PathFinder extends Observable implements Model {

    private Network network;

    private List<StationNode> shortestPath;

    public PathFinder() throws RepositoryException {
        network = new Network();
        shortestPath = new LinkedList<>();
    }

    @Override
    public void search(int idOrigin, int idDestination) {
        StationGraph graph = network.getGraphStations();

        StationNode source = graph.search(idDestination);
        Dijkstra.calculateShortestPathFromSource(graph, source);
        shortestPath = graph.search(idOrigin).getShortestPath();
        System.out.println("Taille du plus cours chemin " + shortestPath.size());
        notifyObservers();
        //graph.clearResearch();
    }

    @Override
    public void search(String origin, String destination) {    
        network.getGraphStations().clearResearch();
        StationGraph graph = network.getGraphStations();
        
        StationNode destinationNode = graph.search(destination); 
        
        graph = Dijkstra.calculateShortestPathFromSource(graph, destinationNode);
        
        StationNode originNode = graph.search(origin);
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
    public List<StationNode> getSearchResult() {
        return shortestPath;
    }

    @Override
    public List<String> getSearchResult2() {
        List<String> path = new ArrayList<>();

        for (StationNode node : shortestPath) {
            String stationName = node.getStation().getName();
            path.add(stationName);
        }

        return path;
    }

    @Override
    public List<StationsDto> getSearchResult3() {
        List<StationsDto> path = new ArrayList<>();

        for (StationNode node : shortestPath) {
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
