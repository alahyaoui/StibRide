package atl.handler;

import g54895.atl.stibride.presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author jlc
 */
public class ButtonHandler implements EventHandler<ActionEvent> {

    private Presenter presenter;

    public ButtonHandler(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(ActionEvent t) {
        //presenter.doResearch();
    }

}
