package views.verlorenview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Bord;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.topscoreview.TopScorePresenter;
import views.topscoreview.TopScoreView;

public class VerlorenPresenter {
    Bord model;
    VerlorenView verlorenView;

    public VerlorenPresenter(Bord model, VerlorenView verlorenView) {
        this.model = model;
        this.verlorenView = verlorenView;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        verlorenView.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwSpelView nieuwSpelView = new NieuwSpelView();
                new NieuwSpelPresenter(model, nieuwSpelView);
                verlorenView.getScene().setRoot(nieuwSpelView);
                nieuwSpelView.getScene().getWindow().sizeToScene();
            }
        });

        verlorenView.getBtnScoreBoard().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TopScoreView topScoreView = new TopScoreView();
                new TopScorePresenter(model, topScoreView, "verloren");
                verlorenView.getScene().setRoot(topScoreView);
            }
        });

        verlorenView.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.getTopScores().voegScoreToe(model.getSpeler()); // score van speler toevoegen aan topscores.
                Platform.exit();
            }
        });
    }

    private void updateView() {
        verlorenView.getLblscoreNumber().setText("" + model.getSpeler().getScore());
    }
}