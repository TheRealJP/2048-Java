package views.menuview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Bord;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.spelview.BordPresenter;
import views.spelview.BordView;

public class MenuPresenter {
    private Bord model;
    private MenuView menuView;

    public MenuPresenter(Bord model, MenuView view) {
        this.model = model;
        this.menuView = view;
        addEventHandlers();
    }

    private void addEventHandlers() {

        // deze eventhandler roept niew spel op.
        menuView.getBtnNew().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwSpelView nieuwSpelView = new NieuwSpelView();
                new NieuwSpelPresenter(model, nieuwSpelView);
                menuView.getScene().setRoot(nieuwSpelView);
                nieuwSpelView.getScene().getWindow().sizeToScene();
            }
        });

        // deze eventhandler handelt de actie af als er op load game geklikt is.
        menuView.getBtnLoad().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BordView bordView = new BordView();
                new BordPresenter(model, bordView);
                menuView.getScene().setRoot(bordView);
                bordView.getScene().getWindow().sizeToScene();

                model.bordLaden();
//              model.spelerLaden(); TODO: instellen/verwijderen!
            }
        });

        // exit, spreekt voor zich :)
        menuView.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        menuView.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                BordView bordViewContinue = new BordView();
                new BordPresenter(model, bordViewContinue);
                menuView.getScene().setRoot(bordViewContinue);
                bordViewContinue.getScene().getWindow().sizeToScene();
                //TODO: teruggaan naar spel (zonder nieuwe tegel aan te maken, dus geen nieuw bord model oproepen?)
            }
        });

        // deze eventhandler handelt de actie af als er op save game geklikt is.
        menuView.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.bordOpslaan();
                model.spelerOpslaan();
            }
        });
    }
}
