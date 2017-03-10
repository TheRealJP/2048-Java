package views.topscoreview;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Bord;
import views.menuview.MenuPresenter;
import views.menuview.MenuView;

public class TopScorePresenter {

    private Bord model;
    private TopScoreView view;

    public TopScorePresenter(Bord model, TopScoreView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateLayout();
    }

    private void addEventHandlers() {

        view.getMenuBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //TODO: nakijken welke menu we hier moeten gebruiken!!!
                MenuView menuView = new MenuView();
                new MenuPresenter(model,menuView);
                view.getScene().setRoot(menuView);
            }
        });
    }

    private void updateLayout() {
        view.setSpelers(model.getTopScores().getSpelerLijst());
    }
}