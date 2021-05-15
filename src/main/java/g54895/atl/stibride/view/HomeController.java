package g54895.atl.stibride.view;

import g54895.atl.stibride.dto.FavoriteRoutesDto;
import g54895.atl.stibride.dto.StationsDto;
import g54895.atl.stibride.exception.RepositoryException;
import g54895.atl.stibride.presenter.Presenter;
import org.controlsfx.control.SearchableComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable {

    private Presenter presenter;

    @FXML
    private SearchableComboBox<String> origin;

    @FXML
    private SearchableComboBox<String> destination;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<FavoriteRoutesDto> favorite;

    @FXML
    private Button chooseButton;

    @FXML
    private Button addFavorite;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView/*<StationsDto/*String*//*>*/ resultResearch;

    @FXML
    private TableColumn<StationsDto, String> stationCol;

    ObservableList<StationsDto> data = FXCollections.observableArrayList();

    @FXML
    void search(ActionEvent event) {
        String originText = origin.getValue();
        String destinationText = destination.getValue();

        presenter.doResearch(originText, destinationText);
    }

    @FXML
    void addFavorite(ActionEvent event) throws RepositoryException, IOException {
        String originText = origin.getValue();
        String destinationText = destination.getValue();

        presenter.addFavorite(originText, destinationText);
    }

    @FXML
    void chooseFavorite(ActionEvent event) {
        String originText = favorite.getValue().getOrigin();
        String destinationText = favorite.getValue().getDestination();

        origin.setValue(originText);
        destination.setValue(destinationText);
    }

    @FXML
    void updateFavorite(ActionEvent event) throws RepositoryException, IOException {
        String originText = origin.getValue();
        String destinationText = destination.getValue();

        presenter.updateFavorite(favorite.getValue().getKey(), originText, destinationText);
    }

    @FXML
    void deleteFavorite(ActionEvent event) throws RepositoryException, IOException {
        presenter.deleteFavorite(favorite.getValue());
    }

    public void initChoiceBox(List<String> stations) {
        origin.setItems(FXCollections.observableArrayList(stations));
        destination.setItems(FXCollections.observableArrayList(stations));

        origin.setValue(origin.getItems().get(0));
        destination.setValue(destination.getItems().get(1));

    }

    public void initFavoriteRoutesChoiceBox(List<FavoriteRoutesDto> favoriteRoutes) {
        favorite.getItems().clear();
        favorite.setItems(FXCollections.observableArrayList(favoriteRoutes));

        favorite.setValue(favorite.getItems().get(0));
    }

    private List<StationsDto> convertStationsToDisplay(List<StationsDto> stations) {
        List<StationsDto> path = new ArrayList<>();
        path.add(new StationsDto(0, origin.getValue()));
        for (int i = stations.size() - 1; i >= 0; i--) {
            path.add(stations.get(i));
        }
        return path;
    }

    public void updateTableView(List<StationsDto> stations) {

        List<StationsDto> path = convertStationsToDisplay(stations);
        
        //Manière 1
        resultResearch.getItems().clear();
        resultResearch.refresh();
        data.addAll(path);
        resultResearch.setItems(data);

        //Manière 2
        /*resultResearch.getItems().remove(data);
            data.removeAll(data);
            data.clear();
            data.addAll(stations);
            resultResearch.setItems(data);*/
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        View.setHomeController(this);

        stationCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

}
