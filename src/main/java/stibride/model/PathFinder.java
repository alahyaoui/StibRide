package stibride.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import observer.Observable;
import observer.Observer;
import stibride.config.ConfigManager;
import stibride.dto.FavoriteTripDto;
import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.repository.FavoriteTripsRepository;

/**
 * The PathFinder class is used to find the shortest path between two stations
 * but also to get update and delete the favorite trips of the user.
 * 
 * @author Ayoub Lahyaoui
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
        // graph.clearResearch();
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
        System.out.println("origin =" + graph.search(origin).getStation().getName());
        System.out.println("Taille du plus cours chemin " + shortestPath.size());
        notifyObservers();
    }

    public Network getNetwork() {
        return network;
    }

    @Override
    public List<StationsDto> getSearchResult() {
        List<StationsDto> path = new ArrayList<>();

        for (StationNode node : shortestPath) {
            StationsDto station = node.getStation();
            path.add(station);
        }

        return path;
    }

    public List<FavoriteTripDto> getFavoriteTrips() {
        List<FavoriteTripDto> favoriteTrips = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            List<FavoriteTripDto> dtos = repository.getAll();

            for (FavoriteTripDto dto : dtos) {
                favoriteTrips.add(dto);
            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
        return favoriteTrips;
    }

    public void addFavoriteTrip(String origin, String destination) {
        FavoriteTripDto dto = null;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.add(new FavoriteTripDto(0, origin, destination));

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
    }

    public void updateFavoriteTrip(int key, String origin, String destination) {
        FavoriteTripDto dto = new FavoriteTripDto(key, origin, destination);
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.update(dto);

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
    }

    public void deleteFavoriteTrip(FavoriteTripDto trip) {
        FavoriteTripDto dto = trip;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.delete(dto);

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
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
