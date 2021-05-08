/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54895.atl.stibride.presenter;

import atl.observer.Observable;
import atl.observer.Observer;
import g54895.atl.stibride.model.Model;
import g54895.atl.stibride.view.View;

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
    
    public void doResearch(String origin, String destination) {
        
        model.search(0, 0);
    }
    
    @Override
    public void update(Observable observable, Object arg) {
        
    }
    
}
