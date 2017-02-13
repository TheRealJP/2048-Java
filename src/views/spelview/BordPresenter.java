package views.spelview;

import com.sun.javafx.scene.traversal.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Bord;

public class BordPresenter {
    private Bord model;
    private BordView view;

    //TODO: aantalMoves variabele zou nog verplaats moeten worden naar een andere model (Spel), mag niet in presenter blijven, vergeet ook niet dat deze teller ook in elke move case staat
    private int aantalMoves = 0;

    public BordPresenter(Bord model, BordView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {

                    // KeyCode.UP, DOWN, RIGHT, LEFT
                    // print statements zijn voor terminal tests
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

                // als het bord niet vol is, nieuwe tegel genereren
                if (!model.isVol()) {
                    model.genereerNieuweTegel();

                    // console testcode
                    System.out.println("Aantal moves: "+aantalMoves);
                    System.out.println();
                    System.out.println(model.toString());
                    // view refreshen
                    updateView();
                }
            }
        });
    }

    private void updateView() {

        view.setLabels(model.getTegels());
    }
}
