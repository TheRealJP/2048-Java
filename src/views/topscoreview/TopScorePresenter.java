package views.topscoreview;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.TopScores;
import views.menuview.MenuView;

public class TopScorePresenter {

    private TopScores model;
    private TopScoreView view;

    public TopScorePresenter(TopScores model, TopScoreView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateLayout();
    }

    private void addEventHandlers() {

        view.getMenuBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //TODO: nakijken!
                MenuView menuView = new MenuView();
                view.getScene().setRoot(menuView);
            }
        });
    }

    private void updateLayout() {

        view.setSpelers(model.getSpelerLijst());
    }
}