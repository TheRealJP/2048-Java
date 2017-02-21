package views.spelview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Bord;
import views.nieuwspelview.NieuwSpelView;

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

    private Image up;
    private Image left;
    private Image right;
    private Image down;

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

        up = new Image("afbeeldingen/up-normal.png", 50, 50, true, true);
        left = new Image("afbeeldingen/left-normal.png", 50, 50, true, true);
        down = new Image("afbeeldingen/down-normal.png", 50, 50, true, true);
        right = new Image("afbeeldingen/right-normal.png", 50, 50, true, true);
    }

    private void layoutNodes() {
        //Boxes
        VBox currentScoreBox = new VBox(lblCurrentScore, lblCurrentScoreNumber);
        VBox highScoreBox = new VBox(lblHighScore, lblHighScoreNumber);
        HBox scores = new HBox(10, currentScoreBox, highScoreBox);
        VBox buttons = new VBox(20, menu, regels);
        VBox controlsBox = new VBox();

        //Borderpane inside main borderpane
        BorderPane buttonPane = new BorderPane(buttons);
        GridPane controlPane = new GridPane();

        //adding arrowkey images to gridPane
        controlPane.add(new ImageView(up), 1, 1);
        controlPane.add(new ImageView(left), 0, 2);
        controlPane.add(new ImageView(down), 1, 2);
        controlPane.add(new ImageView(right), 2, 2);
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
        buttonPane.setPadding(new Insets(10, 35, 10, 35));
        grid.setPadding(new Insets(10));
        menu.setPadding(new Insets(10, 40, 10, 40));
        regels.setPadding(new Insets(10, 40, 10, 40));
        regels.setMinSize(120, 40);
        menu.setMinSize(120, 40);

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
        setLeft(buttonPane);
        setRight(controlsBox);
        this.setBottom(lblSpelerNaam);

        //grid settings
        grid.setGridLinesVisible(true);
        grid.setMinSize(200, 200);
        grid.setHgap(8);
        grid.setVgap(8);
        controlPane.setHgap(10);
        controlPane.setVgap(10);

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
                labels[x][y].textProperty().setValue(waardes[y][x]);
            }
        }
    }

    // labels toevoegen aan grid (doen we bij initializer)
    public void addLabelsToGrid() {
        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {
                labels[x][y] = new Label();
                labels[x][y].setMinWidth(45);
                labels[x][y].setMinHeight(45);
                labels[x][y].setAlignment(Pos.CENTER);
                grid.add(labels[x][y], x, y);
                GridPane.setMargin(labels[x][y], new Insets(50));
                labels[x][y].setFont(new Font(40));
                labels[x][y].setId("tegels");

                //CSS - id toewijzen per mogelijke labelwaarde
                //hogere waardes zijn in dit spel niet in rekening gehouden
                //het spel veranderd van scherm als de waarde 2048 is bereikt
                switch (labels[x][y].textProperty().getValue()) {
                    case "0":
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
                }
            }
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
}
