package views.spelview;

import com.sun.javafx.scene.traversal.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Bord;

public class BordPresenter {
    private Bord model;
    private BordView view;

    //TODO: aantalMoves variabele zou nog verplaats moeten worden naar een andere model (Spel), mag niet in presenter blijven, vergeet ook niet dat deze teller ook in elke move case staat
    int aantalMoves = 0;

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
                        System.out.println("UP");
                        model.verplaats(Direction.UP);
                        ++aantalMoves;
                        updateView();
                        break;
                    case DOWN:
                        System.out.println("DOWN");
                        model.verplaats(Direction.DOWN);
                        updateView();
                        ++aantalMoves;
                        break;
                    case RIGHT:
                        System.out.println("RIGHT");
                        model.verplaats(Direction.RIGHT);
                        updateView();
                        ++aantalMoves;
                        break;
                    case LEFT:
                        System.out.println("LEFT");
                        model.verplaats(Direction.LEFT);
                        updateView();
                        ++aantalMoves;
                        break;
                    default:
                        event.consume();
                        break;
                }

                // testcode, moet waarschijnlijk nog herwerkt worden
                if (!model.isVol()) {
                    model.genereerNieuweTegel();

                    // console testcode
                    System.out.println("Aantal moves: "+aantalMoves);
                    System.out.println();
                    System.out.println(model.toString());
                }
            }
        });
    }

    private void updateView() {

        //TODO: view updaten (regels volgen pls haha no messy code, sucks to debug)
        System.out.println("updateview reached!");
    }
}
