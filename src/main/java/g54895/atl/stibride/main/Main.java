/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.main;

import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.model.PathFinder;
import g54895.atl.stibride.presenter.Presenter;
import g54895.atl.stibride.view.View;
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
