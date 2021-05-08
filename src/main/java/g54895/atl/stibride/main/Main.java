/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.main;

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

    private static Scene scene;

    /**
     * Entry points to the <code> Lotto </code> application..
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
        Main main = new Main();
        PathFinder model = new PathFinder();
        
        scene = new Scene(loadFXML("primary"), 640, 480);
        View view = new View(scene/*stage*/);      
        stage.setScene(scene);

        Presenter presenter = new Presenter(model, view);

        model.addObserver(presenter);
        //view.addHandlerButton(presenter);
        presenter.initialize();
        view.showStage();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
