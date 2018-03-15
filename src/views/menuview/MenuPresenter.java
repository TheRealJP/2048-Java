package views.menuview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import model.Animaties;
import model.Bord;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.spelview.BordPresenter;
import views.spelview.BordView;
import views.topscoreview.TopScorePresenter;
import views.topscoreview.TopScoreView;

import java.util.Optional;

public class MenuPresenter {
    private Bord model;
    private MenuView menuView;
    private Animaties animaties = new Animaties();


    public MenuPresenter(Bord model, MenuView view) {
        this.model = model;
        this.menuView = view;
        addEventHandlers();
    }

    private void addEventHandlers() {

        // deze eventhandler roept niew spel op.
        menuView.getBtnNew().setOnAction(event -> {
            NieuwSpelView nieuwSpelView = new NieuwSpelView();
            new NieuwSpelPresenter(model, nieuwSpelView);
            menuView.getScene().setRoot(nieuwSpelView);
            nieuwSpelView.getScene().getWindow().sizeToScene();
        });

        // deze eventhandler handelt de actie af als er op load game geklikt is.
        menuView.getBtnLoad().setOnAction(event -> {
            BordView bordView = new BordView();
            model.setTegels(model.bordLaden());
            bordView.getLblSpelerNaam().setText(model.spelerLaden().getNaam());
            bordView.getLblCurrentScoreNumber().setText(model.spelerLaden().getScore() + "");
            new BordPresenter(model, bordView, false); //start NIET (false) met nieuwe tegels
            menuView.getScene().setRoot(bordView);
            bordView.getScene().getWindow().sizeToScene();
        });

        // exit, spreekt voor zich :)
        menuView.getBtnExit().setOnAction(event -> {

            model.getTopScores().voegScoreToe(model.getSpeler()); // score van speler toevoegen aan topscores.
            Platform.exit();
        });

        menuView.getBtnContinue().setOnAction(event -> {
            BordView bordViewContinue = new BordView();
            new BordPresenter(model, bordViewContinue, false);
            menuView.getScene().setRoot(bordViewContinue);
            bordViewContinue.getScene().getWindow().sizeToScene();

            bordViewContinue.getLblSpelerNaam().setText(model.getSpeler().getNaam());
            bordViewContinue.getLblCurrentScoreNumber().setText(model.getSpeler().getScore() + "");
        });

        // deze eventhandler handelt de actie af als er op save game geklikt is.
        menuView.getBtnSave().setOnAction(event -> {

            //als de lengte van die files niet gelijk is aan 0 (er zit dus iets in) toon alert box
            if (!(model.getSpelbestand().length() == 0 && model.getSpelerbestand().length() == 0)) {

                //alert box
                Alert overschrijfMelding = new Alert(Alert.AlertType.CONFIRMATION);
                overschrijfMelding.setHeaderText("Are you sure you want to overwrite your previous save file?");
                overschrijfMelding.setTitle("Overwrite save warning!");

                Optional<ButtonType> result = overschrijfMelding.showAndWait();
                //checken of het resultaat van de alertbox gelijk is aan buttontype "ok"
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    model.bordOpslaan(); //data opgeslagen in spel.bin
                    model.spelerOpslaan(); //data opgeslagen in speler.bin

                    animaties.fadeTransition(menuView.getLblSaved(), true, Duration.millis(1000));
                }

            } else {
                //opslaan als er WEL een lege savefile is
                model.bordOpslaan(); //data opgeslagen in spel.bin
                model.spelerOpslaan(); //data opgeslagen in speler.bin
            }
        });

        // deze eventhandler handelt de actie af als er op scoreboard geklikt is.
        menuView.getBtnscoreBord().setOnAction(event -> {
            TopScoreView topScoreView = new TopScoreView();

            if (menuView.isFirstTime()) {
                new TopScorePresenter(model, topScoreView, "initial");
            } else {
                new TopScorePresenter(model, topScoreView, "menu");
            }
            menuView.getScene().setRoot(topScoreView);
        });
    }
}