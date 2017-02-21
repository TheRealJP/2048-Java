package views.gewonnenview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Bord;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.spelview.BordPresenter;
import views.spelview.BordView;

import javax.xml.soap.Node;

public class GewonnenPresenter {
    private GewonnenView view;
    private Bord model;

    public GewonnenPresenter(GewonnenView view, Bord model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BordView bordView = new BordView();
                BordPresenter bordPresenter = new BordPresenter(model, bordView);
                view.getScene().setRoot(bordView);
                bordView.getScene().getWindow().sizeToScene();
            }
        });

        view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                NieuwSpelView nieuwSpelView = new NieuwSpelView();
                NieuwSpelPresenter nieuwSpelPresenter = new NieuwSpelPresenter(model, nieuwSpelView);
                view.getScene().setRoot(nieuwSpelView);
                nieuwSpelView.getScene().getWindow().sizeToScene();
            }
        });

        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit(); //zorgt ervoor dat de javafx applicatie stopt
            }
        });
    }
}
