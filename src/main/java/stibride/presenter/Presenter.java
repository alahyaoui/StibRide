/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.presenter;

import observer.Observable;
import observer.Observer;
import stibride.config.ConfigManager;
import stibride.dto.FavoriteRoutesDto;
import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.model.Model;
import stibride.repository.FavoriteRoutesRepository;
import stibride.repository.StationsRepository;
import stibride.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayoub
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) throws RepositoryException, IOException {
        this.model = model;
        this.view = view;
        initialize();
    }

    public void initialize() throws RepositoryException, IOException {
        //model.initialize();
        model.addObserver(this);
        view.initialize(this);
        initChoiceBox();
    }

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

    public void doResearch(int origin, int destination) {
        model.search(origin, destination);
    }

    public void doResearch(String origin, String destination) {
        model.search(origin, destination);
    }

    private void initFavoriteRoutesChoiceBox() throws RepositoryException, IOException {
        List<FavoriteRoutesDto> favoriteRoutes = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            List<FavoriteRoutesDto> dtos = repository.getAll();

            for (FavoriteRoutesDto dto : dtos) {
                favoriteRoutes.add(dto);
            }

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }

        view.initFavoriteRoutesChoiceBox(favoriteRoutes);
    }

    public void addFavorite(String origin, String destination) throws RepositoryException, IOException {
        FavoriteRoutesDto dto = null;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            FavoriteRoutesRepository repository = new FavoriteRoutesRepository();
            //dto = repository.get(origin, destination);
            repository.add(new FavoriteRoutesDto(0, origin, destination));

        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
        initFavoriteRoutesChoiceBox();
    }

    public void updateFavorite(int key, String origin, String destination) throws RepositoryException, IOException {
        FavoriteRoutesDto dto = new FavoriteRoutesDto(key, origin, destination);
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
        initFavoriteRoutesChoiceBox();
    }

    public void deleteFavorite(FavoriteRoutesDto item) throws RepositoryException, IOException {
        FavoriteRoutesDto dto = item;
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
        initFavoriteRoutesChoiceBox();
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("Update");
        Model savedModel = (Model) observable;
        try {
            //view.updateTableView(savedModel.getSearchResult2());
            view.updateTableView(savedModel.getSearchResult3());

        } catch (IOException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
