package stibride.model;

import java.io.IOException;
import java.util.List;

import stibride.config.ConfigManager;
import stibride.dto.StationsDto;
import stibride.dto.StopsDto;
import stibride.exception.RepositoryException;
import stibride.repository.StationsRepository;
import stibride.repository.StopsRepository;

/**
 *
 * @author Ayoub
 */
public class Network {

    private Graph graphStations;

    public Network(Graph graphStations) {
        this.graphStations = graphStations;
    }

    public Network() throws RepositoryException {
        this.graphStations = new Graph();
        initGraph();
        //System.out.println("La liste de node " + graphStations.getNodes().size());
        initDestinations();
    }

    public void initGraph() throws RepositoryException {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            StationsRepository repository = new StationsRepository();
            List<StationsDto> dtos = repository.getAll();

            for (StationsDto dto : dtos) {
                graphStations.addNode(new Node(dto));
            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
    }

    private void initDestinations() {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            StopsRepository repository = new StopsRepository();
            List<StopsDto> dtos = repository.getAll();

            for (int i = 0; i < dtos.size(); i++) {
                if (i == dtos.size() - 1) {
                    break;
                }

                int originStationKey = dtos.get(i).getStationKey();
                int originLine = dtos.get(i).getLine();
                int originOrder = dtos.get(i).getKey();

                int destinationStationKey = dtos.get(i + 1).getStationKey();
                int destinationLine = dtos.get(i + 1).getLine();
                int destinationOrder = dtos.get(i + 1).getKey();

                if (originLine == destinationLine && originOrder == destinationOrder - 1) {
                    addDestination(originStationKey, destinationStationKey);
                }

            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
    }

    private void addDestination(int originStationKey, int destinationStationKey) {
        Node originNode = graphStations.search(originStationKey);
        Node destinationNode = graphStations.search(destinationStationKey);

        if (!originNode.containsDestination(destinationNode)) {
            originNode.addDestination(destinationNode, 1);
        }

        if (!destinationNode.containsDestination(originNode)) {
            destinationNode.addDestination(originNode, 1);
        }
    }

    public Graph getGraphStations() {
        return graphStations;
    }

    public void setGraphStations(Graph graphStations) {
        this.graphStations = graphStations;
    }

}