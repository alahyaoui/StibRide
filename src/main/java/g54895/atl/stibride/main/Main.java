/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.main;

import g54895.atl.stibride.model.Model;
import g54895.atl.stibride.presenter.Presenter;
import g54895.atl.stibride.view.View;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author ayoub
 */
public class Main extends Application {

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
        System.out.println("DEBUG | MAIN       | Début du programme");

        //Model model = new Model();
        View view = new View(stage);
        //Presenter presenter = new Presenter(model, view);
        System.out.println("");

        System.out.println("DEBUG | MAIN       | Ajoute le lien observateur-observé entre le presenter et le modèle");
        //model.addObserver(presenter);
        //view.addHandlerButton(presenter);
        System.out.println("");

        //presenter.initialize();
        System.out.println("");
    }
