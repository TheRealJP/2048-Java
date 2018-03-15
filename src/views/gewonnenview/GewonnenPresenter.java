package views.gewonnenview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Bord;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.spelview.BordPresenter;
import views.spelview.BordView;

public class GewonnenPresenter {
    private GewonnenView view;
    private Bord model;

    public GewonnenPresenter(GewonnenView view, Bord model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnContinue().setOnAction(event -> {
            BordView bordView = new BordView();
            new BordPresenter(model, bordView, false); //start NIET (false) met nieuwe tegels
            view.getScene().setRoot(bordView);
            bordView.getScene().getWindow().sizeToScene();
        });

        view.getBtnNewGame().setOnAction(event -> {

            NieuwSpelView nieuwSpelView = new NieuwSpelView();
            new NieuwSpelPresenter(model, nieuwSpelView);
            view.getScene().setRoot(nieuwSpelView);
            nieuwSpelView.getScene().getWindow().sizeToScene();
        });

        view.getBtnExit().setOnAction(event -> {
            Platform.exit();
        });
    }

    private void updateView() {
        view.getLblHighScoreNumber().setText(model.getTopScores().getTopscore() + "");
    }
}