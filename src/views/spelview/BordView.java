package views.spelview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BordView extends BorderPane {
    private Label[][] labels;
    private static int GRIDSIZE = 4;
    private Label currentScore;
    private Label highScore;
    private GridPane grid;

    public BordView() {
        initialiseNodes();
        layoutNodes();
        addLabelsToGrid();
    }

    private void initialiseNodes() {

        currentScore = new Label();
        highScore = new Label();
        grid = new GridPane();
        labels = new Label[GRIDSIZE][GRIDSIZE];
    }

    private void layoutNodes() {
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        this.setCenter(grid);
        grid.setGridLinesVisible(true);
        grid.setMinSize(200,200);
    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void setLabels(int[][] waardes) {

        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {

                labels[x][y].textProperty().setValue("" + waardes[y][x]);
            }
        }
    }

    public Label getHighScore() {
        return highScore;
    }

    public Label getCurrentScore() {
        return currentScore;
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
}
