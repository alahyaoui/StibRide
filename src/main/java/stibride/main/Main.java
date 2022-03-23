/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.main;

import stibride.exception.RepositoryException;
import stibride.model.PathFinder;
import stibride.presenter.Presenter;
import stibride.view.View;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ayoub
 */
public class Main extends Application {

    /**
     * Entry points to the <code> StibRide </code> application..
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Main() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        PathFinder model = new PathFinder();

        View view = new View(/*scene*/stage);

        Presenter presenter = new Presenter(model, view);

        view.showStage();
    }
}
