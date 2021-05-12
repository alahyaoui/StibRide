/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.presenter;

import atl.observer.Observable;
import atl.observer.Observer;
import g54895.atl.stibride.config.ConfigManager;
import g54895.atl.stibride.dto.StationsDto;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.model.Model;
import g54895.atl.stibride.repository.StationsRepository;
import g54895.atl.stibride.view.View;
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

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initialize() throws RepositoryException, IOException {
        //model.initialize();
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
