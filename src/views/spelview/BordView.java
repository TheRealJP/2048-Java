package views.spelview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BordView extends BorderPane {
    private Label[][] labels = new Label[4][4];
    private Label currentScore;
    private Label highScore;
    private GridPane grid;
    private boolean firstTime = true;

    public BordView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        currentScore = new Label();
        highScore = new Label();
        grid = new GridPane();
    }

    private void layoutNodes() {

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        this.setCenter(grid);
        grid.setGridLinesVisible(true);

    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void setLabels(int[][] waardes) {

        if (firstTime) {

            for (int x = 0; x < labels.length; x++) {
                for (int y = 0; y < labels[x].length; y++) {

                    labels[x][y] = new Label("" + waardes[y][x]);
                    grid.add(labels[x][y], x, y);
                    GridPane.setMargin(labels[x][y], new Insets(50));
                    labels[x][y].setFont(new Font(50)); // fontsize instellen (wss tijdelijk tot css)
                    firstTime = false;
                }
            }
        } else {

            for (int x = 0; x <labels.length; x++) {
                for (int y = 0; y < labels[x].length; y++) {

                    labels[x][y].textProperty().setValue(""+waardes[y][x]);
                }
            }

        }
    }

    public Label getHighScore() {
        return highScore;
    }

    public Label getCurrentScore() {
        return currentScore;
    }

    public void addLabelsToGrid() {

    }
}
