package g54895.atl.stibride.view;

import atl.handler.ButtonHandler;
import g54895.atl.stibride.presenter.Presenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * JavaFX App
 */
public class View {

    private static Stage primaryStage;
    private static Scene scene;

    public View(Scene scene) throws IOException {
        this.scene = scene;//= new Scene(loadFXML("primary"), 640, 480);
        //primaryStage = stage;
        //this.primaryStage.setScene(scene);

    }

    public void initialize(Stage stage) throws IOException {

    }

    /**
     * Method showStage, shows the stage.
     */
    public void showStage() {
        this.primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
