/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.presenter;

import g54895.atl.stibride.model.Model;
import g54895.atl.stibride.view.View;
import java.util.Observable;
import java.util.Observer;

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

    public void initialize() {
        //model.initialize();
        //view.initialize();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
