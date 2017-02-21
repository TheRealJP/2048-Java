package views.spelview;

import com.sun.javafx.scene.traversal.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Bord;
import model.Speler;
import views.gewonnenview.GewonnenPresenter;
import views.gewonnenview.GewonnenView;
import views.verlorenview.VerlorenPresenter;
import views.verlorenview.VerlorenView;

public class BordPresenter {
    private Bord model;
    private BordView view;
    private Speler speler;
    //TODO: aantalMoves variabele zou nog verplaats moeten worden naar een andere model (Spel), mag niet in presenter blijven, vergeet ook niet dat deze teller ook in elke move case staat
    private int aantalMoves = 0;

    public BordPresenter(Bord model, BordView view) {
        this.model = model;
        this.view = view;
        speler = new Speler();  //TODO: MARK: maken we aan om speler aan te kunnen roepen, kan zijn dat dit anders moet in de toekomst.
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
                        ++aantalMoves;
                        break;
                    case DOWN:
                        model.verplaats(Direction.DOWN);
                        ++aantalMoves;
                        break;
                    case RIGHT:
                        model.verplaats(Direction.RIGHT);
                        ++aantalMoves;
                        break;
                    case LEFT:
                        model.verplaats(Direction.LEFT);
                        ++aantalMoves;
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

                    // console testcode
                    System.out.println("Aantal moves: " + aantalMoves);
                    System.out.println();
                    System.out.println(model.toString());
                }

                // roep verlorenView op
                // TODO: 21/02/2017 testen
                if (model.isVol()) {
                    VerlorenView verlorenView = new VerlorenView();
                    VerlorenPresenter verlorenPresenter = new VerlorenPresenter(model,verlorenView);
                    view.getScene().setRoot(verlorenView);
                    verlorenView.getScene().getWindow().sizeToScene();
                }

                // boolean die checkt naar de waarde 2048 in het grid,
                // if TRUE? --> roep gewonnenView op
                if (model.heeft2048()) {
                    GewonnenView gewonnenView = new GewonnenView();
                    GewonnenPresenter gewonnenPresenter = new GewonnenPresenter(gewonnenView, model);
                    view.getScene().setRoot(gewonnenView);
                    gewonnenView.getScene().getWindow().sizeToScene();
                }
            }
        });
    }

    private void updateView() {
        // labels instellen
        view.setLabels(model.getTegels());

        //voegt score toe aan label
        view.getLblCurrentScoreNumber().textProperty().setValue("" + speler.getScore());
    }


}
