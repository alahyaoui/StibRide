package stibride.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import observer.Observable;
import observer.Observer;
import stibride.config.ConfigManager;
import stibride.dto.FavoriteTripDto;
import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.model.Model;
import stibride.repository.FavoriteRoutesRepository;
import stibride.repository.StationsRepository;
import stibride.view.View;

/**
 * The presenter is the link between the view and the model. It is the presenter of the application in the MVP design patter.
 * 
 * @author Ayoub Lahyaoui
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) throws RepositoryException, IOException {
        this.model = model;
        this.view = view;
        initialize();
    }

    /**
     * Initialize the view and model
     */
    public void initialize() throws RepositoryException, IOException {
        model.addObserver(this);
        view.initialize(this);
        initChoiceBox();
    }

    /**
     * Initialize the choice box with the list of stations
     * @TODO move the business code in model class
     */
    private void initChoiceBox() throws RepositoryException, IOException {
        List<String> stations = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            StationsRepository repository = new StationsRepository();
            List<StationsDto> dtos = repository.getAll();

            for (StationsDto dto : dtos) {
                stations.add(dto.getName());
            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }

        view.initChoiceBox(stations);
    }

    /**
     * Calls the `search` function on the `model` object
     * 
     * @param origin the id of the origin station
     * @param destination the id of the destination station
     */
    public void doResearch(int origin, int destination) {
        model.search(origin, destination);
    }

    /**
     * Calls the `search` function on the `model` object
     * 
     * @param origin the title of the origin station
     * @param destination the title of the destination station
     */
    public void doResearch(String origin, String destination) {
        model.search(origin, destination);
    }

   /**
    * Loads the favorite trips from the database and displays them in the favorite routes choice box
    * @TODO move the business code in model class
    */
    private void initFavoriteTripsChoiceBox() throws RepositoryException, IOException {
        List<FavoriteTripDto> favoriteTrips = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            List<FavoriteTripDto> dtos = repository.getAll();

            for (FavoriteTripDto dto : dtos) {
                favoriteTrips.add(dto);
            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }

        view.initFavoriteTripsChoiceBox(favoriteTrips);
    }

    /**
     * Add a favorite trip to the database
     * 
     * @param origin the origin of the trip
     * @param destination the destination of the trip
     */
    public void addFavoriteTrip(String origin, String destination) throws RepositoryException, IOException {
        FavoriteTripDto dto = null;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            repository.add(new FavoriteTripDto(0, origin, destination));

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
        initFavoriteTripsChoiceBox();
    }

    /**
     * Update the favorite trip in the database
     * 
     * @param key The primary key of the favorite trip to update.
     * @param origin the origin of the trip
     * @param destination the destination of the trip
     */
    public void updateFavoriteTrip(int key, String origin, String destination) throws RepositoryException, IOException {
        FavoriteTripDto dto = new FavoriteTripDto(key, origin, destination);
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            repository.update(dto);

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
        initFavoriteTripsChoiceBox();
    }

    /**
     * Delete a favorite trip from the database
     * 
     * @param item The item to be deleted.
     */
    public void deleteFavoriteTrip(FavoriteTripDto item) throws RepositoryException, IOException {
        FavoriteTripDto dto = item;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            repository.delete(dto);

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
        initFavoriteTripsChoiceBox();
    }

    /**
     * Update the table view with the search results
     * 
     * @param observable The object that is being observed.
     * @param arg The argument passed to the update method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("Update");
        Model savedModel = (Model) observable;
        try {
            view.updateTableView(savedModel.getSearchResult3());

        } catch (IOException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
