package views.spelview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class BordView extends BorderPane {
    private static final int GRIDSIZE = 4;

    private Label[][] labels;
    private Label lblCurrentScore;
    private Label lblHighScore;
    private Label lblCurrentScoreNumber;
    private Label lblHighScoreNumber;
    private Label lblControls;
    private Label lblSpelerNaam;

    private GridPane grid;
    private Button up;
    private Button left;
    private Button right;
    private Button down;

    private Button menu;
    private Button regels;

    public BordView() {
        initialiseNodes();
        layoutNodes();
        addLabelsToGrid();
    }

    private void initialiseNodes() {
        menu = new Button("Menu");
        regels = new Button("Rules");

        labels = new Label[GRIDSIZE][GRIDSIZE];
        lblCurrentScore = new Label("Score");
        lblHighScore = new Label("Best");
        lblCurrentScoreNumber = new Label("0");
        lblHighScoreNumber = new Label("0");
        lblControls = new Label("Controls");
        lblSpelerNaam = new Label();

        grid = new GridPane();

        up = new Button();
        left = new Button();
        down = new Button();
        right = new Button();
    }

    private void layoutNodes() {

        // initial styling controls
        up.setId("UP");
        left.setId("LEFT");
        down.setId("DOWN");
        right.setId("RIGHT");

        //Boxes
        VBox currentScoreBox = new VBox(lblCurrentScore, lblCurrentScoreNumber);
        VBox highScoreBox = new VBox(lblHighScore, lblHighScoreNumber);
        HBox scores = new HBox(10, currentScoreBox, highScoreBox);
        VBox buttons = new VBox(10, menu, regels);
        VBox controlsBox = new VBox();

        //Borderpane inside main borderpane
        GridPane controlPane = new GridPane();

        //adding arrowkey images to gridPane
        controlPane.add(up, 1, 1);
        controlPane.add(left, 0, 2);
        controlPane.add(down, 1, 2);
        controlPane.add(right, 2, 2);
        controlsBox.getChildren().addAll(lblControls, controlPane);

        //tussenruimte
        // TODO: 15/02/2017 score labels checken op invloed view
        lblSpelerNaam.setPadding(new Insets(20));
        lblCurrentScore.setPadding(new Insets(0, 20, 5, 20));
        lblHighScore.setPadding(new Insets(0, 20, 5, 20));
        lblHighScoreNumber.setPadding(new Insets(10, 40, 10, 40));
        lblCurrentScoreNumber.setPadding(new Insets(10, 40, 10, 40));
        lblControls.setPadding(new Insets(0, 0, 10, 0));
        highScoreBox.setPadding(new Insets(20, 0, 25, 0));
        currentScoreBox.setPadding(new Insets(20, 0, 25, 0));
        controlsBox.setPadding(new Insets(10, 35, 10, 35));
        menu.setPadding(new Insets(10, 40, 10, 40));
        regels.setPadding(new Insets(10, 40, 10, 40));
        regels.setMinSize(120, 40);
        menu.setMinSize(120, 40);
        buttons.setPadding(new Insets(45, 35, 10, 35));

        //uitlijning
        lblSpelerNaam.setAlignment(Pos.CENTER); // positioneert het label "spelernaam"
        lblControls.setAlignment(Pos.CENTER); // positioneert het label "controls"
        grid.setAlignment(Pos.CENTER); // positioneert de grid met getallen in
        currentScoreBox.setAlignment(Pos.CENTER); // positioneert de score en de label
        highScoreBox.setAlignment(Pos.CENTER); // positioneert de highscore en de label
        scores.setAlignment(Pos.TOP_CENTER); // positioneert de "best" en "score" boxelementen
        buttons.setAlignment(Pos.TOP_CENTER); // positioneert de buttons menu en regels
        controlPane.setAlignment(Pos.CENTER);
        controlsBox.setAlignment(Pos.TOP_CENTER);

        //orientering van elementen op de borderpane
        setCenter(grid);
        setTop(scores);
        setLeft(buttons);
        setRight(controlsBox);
        setBottom(lblSpelerNaam);

        //grid settings
        grid.setMaxHeight(480);
        grid.setMaxWidth(480);
        controlPane.setHgap(1);
        controlPane.setVgap(1);

        //CSS
        lblCurrentScore.setId("currentScore");
        lblHighScore.setId("highScore");
        lblCurrentScoreNumber.setId("currentNumber");
        lblHighScoreNumber.setId("highNumber");
        grid.setId("grid");
        lblControls.setId("lblControl");
        lblSpelerNaam.setId("lblSpeler");
    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void setLabels(String[][] waardes) {
        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {
                labels[x][y].setText(waardes[y][x]);

                //CSS - id toewijzen per mogelijke labelwaarde
                //hogere waardes zijn in dit spel niet in rekening gehouden
                //het spel veranderd van scherm als de waarde 2048 is bereikt

                switch (waardes[y][x]) {
                    case " ":
                        labels[x][y].setId("0");
                        break;
                    case "2":
                        labels[x][y].setId("2");
                        break;
                    case "4":
                        labels[x][y].setId("4");
                        break;
                    case "8":
                        labels[x][y].setId("8");
                        break;
                    case "16":
                        labels[x][y].setId("16");
                        break;
                    case "32":
                        labels[x][y].setId("32");
                        break;
                    case "64":
                        labels[x][y].setId("64");
                        break;
                    case "128":
                        labels[x][y].setId("128");
                        break;
                    case "256":
                        labels[x][y].setId("256");
                        break;
                    case "512":
                        labels[x][y].setId("512");
                        break;
                    case "1024":
                        labels[x][y].setId("1024");
                        break;
                    case "2048":
                        labels[x][y].setId("2048");
                        break;
                    default:
                        labels[x][y].setId("2048Plus");
                        break;
                }
            }
        }
    }

    // labels toevoegen aan grid (doen we bij initializer)
    private void addLabelsToGrid() {
        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {
                labels[x][y] = new Label();
                labels[x][y].setMinWidth(120);
                labels[x][y].setMinHeight(120);
                labels[x][y].setAlignment(Pos.CENTER);
                grid.add(labels[x][y], x, y);
            }
        }
    }

    public void setControlButtonsColor(String direction) {

        switch (direction) {

            case "UP":
                up.setId("UP-PRESSED");
                break;
            case "DOWN":
                down.setId("DOWN-PRESSED");
                break;
            case "LEFT":
                left.setId("LEFT-PRESSED");
                break;
            case "RIGHT":
                right.setId("RIGHT-PRESSED");
                break;
            case "reset":
                up.setId("UP");
                down.setId("DOWN");
                left.setId("LEFT");
                right.setId("RIGHT");
                break;
        }
    }

    public Label getLblHighScoreNumber() {
        return lblHighScoreNumber;
    }

    public Label getLblCurrentScoreNumber() {
        return lblCurrentScoreNumber;
    }

    public Label getLblSpelerNaam() {
        return lblSpelerNaam;
    }

    public Button getMenu() {
        return menu;
    }

    public Button getRegels() {
        return regels;
    }
}