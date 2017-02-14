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
import model.Speler;
import model.TopScores;

public class BordView extends BorderPane {
    private static int GRIDSIZE = 4;
    private Label[][] labels;
    private Label lblCurrentScore;
    private Label lblHighScore;
    private Label lblCurrentScoreNumber;
    private Label lblHighScoreNumber;
    private Label lblControls;

    private GridPane grid;

    //    private ImageView controls;
    private Image up;
    private Image left;
    private Image right;
    private Image down;

    private Button menu;
    private Button regels;

    //voor scores op te roepen
    private Speler speler;
    private TopScores topScores;


    public BordView() {
        initialiseNodes();
        layoutNodes();
        addLabelsToGrid();
    }

    private void initialiseNodes() {
        Bord model = new Bord();
        menu = new Button("Menu");
        regels = new Button("Rules");
        lblCurrentScore = new Label("Score");
        lblHighScore = new Label("Best");
        lblCurrentScoreNumber = new Label("0"/*+ speler.getScore()*/);  //Todo: voorlopig in commentaar gaf NullPointerException
        lblHighScoreNumber = new Label("0"/*+ topScores.getTopScoreVanSpeler(speler)*/);
        lblControls = new Label("Controls:");
        grid = new GridPane();
        labels = new Label[GRIDSIZE][GRIDSIZE];

        up = new Image("afbeeldingen/computer_key_Arrow_Up.png",50,50,true,true);
        left = new Image("afbeeldingen/computer_key_Arrow_Left.png",50,50,true,true);
        down = new Image("afbeeldingen/computer_key_Arrow_Down.png",50,50,true,true);
        right = new Image("afbeeldingen/computer_key_Arrow_Right.png",50,50,true,true);
    }

    private void layoutNodes() {

        //Boxes
        VBox currentScoreBox = new VBox(lblCurrentScore, lblCurrentScoreNumber);
        VBox highScoreBox = new VBox(lblHighScore, lblHighScoreNumber);
        HBox scores = new HBox(30, currentScoreBox, highScoreBox);
        VBox buttons = new VBox(20, menu, regels);
        VBox controlsBox = new VBox(10);

        //Borderpane inside main borderpane
        BorderPane buttonPane = new BorderPane(buttons);
        GridPane controlPane = new GridPane();

        //adding arrowkey images to gridPane
        controlPane.add(new ImageView(up), 1, 0);
        controlPane.add(new ImageView(left), 0, 1);
        controlPane.add(new ImageView(down), 1, 1);
        controlPane.add(new ImageView(right), 2, 1);
        controlsBox.getChildren().addAll(lblControls,controlPane);

        //tussenruimte
        lblCurrentScore.setPadding(new Insets(5, 50, 5, 50));
        lblHighScore.setPadding(new Insets(5, 50, 5, 50));
        highScoreBox.setPadding(new Insets(20, 0, 5, 0));
        currentScoreBox.setPadding(new Insets(20, 0, 5, 0));
        controlsBox.setPadding(new Insets(20));
        buttonPane.setPadding(new Insets(20));
        grid.setPadding(new Insets(20, 0, 20, 0));
        BorderPane.setMargin(menu, new Insets(20));
        BorderPane.setMargin(regels, new Insets(20));
        regels.setMinSize(120, 40);
        menu.setMinSize(120, 40);

        //uitlijning
        lblCurrentScore.setAlignment(Pos.CENTER);
        currentScoreBox.setAlignment(Pos.CENTER);
        highScoreBox.setAlignment(Pos.CENTER);
        scores.setAlignment(Pos.TOP_CENTER);
        grid.setAlignment(Pos.CENTER);
        setAlignment(buttons, Pos.TOP_CENTER);

        //orientering van elementen op de borderpane
        setCenter(grid);
        setTop(scores);
        setLeft(buttonPane);
        setRight(controlsBox);

        //grid settings
        grid.setGridLinesVisible(true);
        grid.setMinSize(200, 200);

        //CSS
        currentScoreBox.setId("currentScoreBox");
        highScoreBox.setId("highScoreBox");
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
                labels[x][y].setMinWidth(80);
                labels[x][y].setMinHeight(80);
                labels[x][y].setAlignment(Pos.CENTER);
                grid.add(labels[x][y], x, y);
                GridPane.setMargin(labels[x][y], new Insets(50));
                labels[x][y].setFont(new Font(40)); // fontsize instellen (wss tijdelijk tot css)
            }
        }
    }

    public void setLblCurrentScore(Label lblCurrentScore) {
        this.lblCurrentScore = lblCurrentScore;
    }

    public void setLblHighScore(Label lblHighScore) {
        this.lblHighScore = lblHighScore;
    }

    public Label getLblHighScore() {
        return lblHighScore;
    }

    public Label getLblCurrentScore() {
        return lblCurrentScore;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public void setTopScores(TopScores topScores) {
        this.topScores = topScores;
    }


}
