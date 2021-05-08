package g54895.atl.stibride.view;

import atl.handler.ButtonHandler;
import g54895.atl.stibride.presenter.Presenter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PrimaryController {
    Presenter presenter;

    @FXML
    private void switchToSecondary() throws IOException {
        View.setRoot("secondary");
    }

    @FXML
    private ChoiceBox<String> origin;

    @FXML
    private ChoiceBox<String> destination;
    
    @FXML
    private Button searchButton;

    @FXML
    void search(ActionEvent event) {
        String originText = origin.getValue();
        String destinationText = destination.getValue();
        presenter.doResearch(originText, destinationText);
    }
    
    /*public void addHandlerButton(Presenter presenter) {
        ButtonHandler handler = new ButtonHandler(presenter);
        searchButton.setOnAction(handler);
    }*/

}
