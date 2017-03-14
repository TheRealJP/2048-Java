package views.topscoreview;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Bord;
import views.menuview.MenuPresenter;
import views.menuview.MenuView;
import views.verlorenview.VerlorenPresenter;
import views.verlorenview.VerlorenView;

public class TopScorePresenter {

    private Bord model;
    private TopScoreView view;
    private String sender;

    // sender geeft hier aan van welke view de presenter opgeroepen is, zodat we makkelijk terug kunnen gaan naar de correcte view.
    public TopScorePresenter(Bord model, TopScoreView view, String sender) {
        this.model = model;
        this.view = view;
        this.sender = sender;
        addEventHandlers();
        updateLayout();
    }

    private void addEventHandlers() {

        view.getMenuBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MenuView menuView;
                //met deze switch (op basis van de sender die we meekrijgen bij constructor) bepalen we naar welke menu we terug moeten gaan
                switch (sender) {
                    case "verloren":
                        VerlorenView verlorenView = new VerlorenView();
                        new VerlorenPresenter(model, verlorenView);
                        view.getScene().setRoot(verlorenView);
                        break;
                    case "initial":
                        menuView = new MenuView(true);
                        new MenuPresenter(model, menuView);
                        view.getScene().setRoot(menuView);
                        break;
                    case "menu":
                        menuView = new MenuView(false);
                        new MenuPresenter(model, menuView);
                        view.getScene().setRoot(menuView);
                        break;
                }
            }
        });
    }

    private void updateLayout() {
        view.setSpelers(model.getTopScores().getSpelerLijst());
    }
}