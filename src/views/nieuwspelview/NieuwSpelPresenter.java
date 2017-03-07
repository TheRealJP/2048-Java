package views.nieuwspelview;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import model.Bord;
import views.spelview.BordPresenter;
import views.spelview.BordView;

public class NieuwSpelPresenter {

    private Bord model;
    private NieuwSpelView view;

    public NieuwSpelPresenter(Bord model, NieuwSpelView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {

        // controleren of btnspeel geklikt is, zo ja, startSpel oproepen
        view.getBtnSpeel().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                startSpel(event);
            }
        });

        // controleren of enter ingedrukt is, zo ja, startSpel oproepen
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    startSpel(event);
                }
            }
        });
    }

    // met deze methode laden we de bordview in
    private void startSpel(Event event){

        if (view.getTxtName().getText().length() >= 3
                && view.getTxtName().getText().length() <= 10) {

            model.bordLeegMaken();
            BordView bordView = new BordView();
            new BordPresenter(model, bordView);
            model.getSpeler().setNaam(view.getTxtName().getText().toUpperCase());
            bordView.getLblSpelerNaam().setText(model.getSpeler().getNaam());
            view.getScene().setRoot(bordView);
            bordView.getScene().getWindow().sizeToScene();
        } else {

            event.consume();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Player name");
            alert.setContentText("The player name should contain 3 to 10 characters!");
            alert.showAndWait();
        }
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Hierdoor stopt het spel!");
                alert.setContentText("Ben je zeker?");
                alert.setTitle("Opgelet!");
                alert.getButtonTypes().clear();
                ButtonType neen = new ButtonType("Neen");
                ButtonType ja = new ButtonType("Ja");
                alert.getButtonTypes().addAll(neen, ja);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(neen)) {
                    event.consume();
                }
            }
        });
    }

}
