import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bord;
import views.spelview.SpelPresenter;
import views.spelview.SpelView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Bord model = new Bord();
        SpelView view = new SpelView();
        new SpelPresenter(model, view);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.show();
        view.getGrid().requestFocus(); // deze call doen we zodat de arrowkeys worden herkend
    }

    public static void main(String[] args) {
        launch(args);
    }
}
