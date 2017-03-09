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

        menuView.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BordView bordView = new BordView();
                new BordPresenter(model,bordView);
                menuView.getScene().setRoot(bordView);
                bordView.getScene().getWindow().sizeToScene();
            }
        });

        menuView.getBtnLoad().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: 23/02/2017 save en load game model
            }
        });

        menuView.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: 23/02/2017 save en load game in model
            }
        });

        menuView.getBtnNew().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NieuwSpelView nieuwSpelView = new NieuwSpelView();
                new NieuwSpelPresenter(model, nieuwSpelView);
                menuView.getScene().setRoot(nieuwSpelView);
                nieuwSpelView.getScene().getWindow().sizeToScene();
            }
        });

        menuView.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }
}
