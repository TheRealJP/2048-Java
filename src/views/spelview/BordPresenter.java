package views.spelview;

import com.sun.javafx.scene.traversal.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Bord;
import model.TopScores;
import views.gewonnenview.GewonnenPresenter;
import views.gewonnenview.GewonnenView;
import views.verlorenview.VerlorenPresenter;
import views.verlorenview.VerlorenView;

public class BordPresenter {
    private Bord model;
    private BordView view;
    private TopScores topscore;

    public BordPresenter(Bord model, BordView view) {
        this.model = model;
        this.view = view;
        topscore = new TopScores();
        addEventHandlers();
        updateView();
        // deze call doen we zodat de arrowkeys worden herkend
        view.getGrid().requestFocus();
    }

    private void addEventHandlers() {
        //start spel met 2 tegels
        for (int i = 0; i < 2; i++) {
            model.genereerNieuweTegel();
        }

        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                    switch (event.getCode()) {
                    // KeyCode.UP, DOWN, RIGHT, LEFT
                    case UP:
                        model.verplaats(Direction.UP);
                        view.setControlButtonsColor("UP");
                        break;
                    case DOWN:
                        model.verplaats(Direction.DOWN);
                        view.setControlButtonsColor("DOWN");
                        break;
                    case RIGHT:
                        model.verplaats(Direction.RIGHT);
                        view.setControlButtonsColor("RIGHT");
                        break;
                    case LEFT:
                        model.verplaats(Direction.LEFT);
                        view.setControlButtonsColor("LEFT");
                        break;
                    default:
                        event.consume();
                        break;
                }

                //zorgt ervoor dat alleen arrowkeys nieuwe tegels genereren
                if (!model.isVol() && event.getCode().isArrowKey()) {

                    // als het bord niet vol is, nieuwe tegel genereren
                    model.genereerNieuweTegel();

                    // view refreshen
                    updateView();
                }

                // roep verlorenView op
                if (model.isVol()) {

                    topscore.voegScoreToe(model.getSpeler());
                    VerlorenView verlorenView = new VerlorenView();
                    new VerlorenPresenter(model,verlorenView);
                    view.getScene().setRoot(verlorenView);
                    verlorenView.getScene().getWindow().sizeToScene();
                }

                // boolean die checkt naar de waarde 2048 in het grid,
                // if TRUE? --> roep gewonnenView op
                if (model.heeft2048()) {

                    topscore.voegScoreToe(model.getSpeler());
                    GewonnenView gewonnenView = new GewonnenView();
                    new GewonnenPresenter(gewonnenView, model);
                    view.getScene().setRoot(gewonnenView);
                    gewonnenView.getScene().getWindow().sizeToScene();
                }
            }
        });

        view.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case UP:
                    case DOWN:
                    case RIGHT:
                    case LEFT:
                        view.setControlButtonsColor("reset");
                        break;
                    default:
                        event.consume();
                        break;
                }
            }
        });
    }

    private void updateView() {
        // labels instellen
        view.setLabels(model.getTegels());

        //voegt score & topscore toe aan label
        view.getLblCurrentScoreNumber().setText("" + model.getSpeler().getScore());
        view.getLblHighScoreNumber().setText(""+topscore.getTopscore());
    }
}
