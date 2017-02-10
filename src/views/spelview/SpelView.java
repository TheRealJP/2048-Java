package views.spelview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SpelView extends BorderPane {
    private Label[][] labels;
    private Label currentScore;
    private Label highScore;
    private GridPane grid;

    public SpelView() {
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

/*
      for (int x = 0; x < labels.length; x++) {
        for (int y = 0; y < labels[x].length; y++) {
            grid.add(labels[x][y], x, y);
            GridPane.setMargin(labels[x][y], new Insets(30));
        }
      }

      //TODO: BUGFIX - momenteel doet zich hier nog een bug voor (java.lang.NullPointerException)
*/

    }

    public GridPane getGrid() {
        return this.grid;
    }

    public void setLabels(Label[][] labels) {
        this.labels = labels;
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
