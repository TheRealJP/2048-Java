import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bord;
import views.spelview.BordPresenter;
import views.spelview.BordView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Bord model = new Bord();
        BordView view = new BordView();
        new BordPresenter(model, view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setResizable(false);
        primaryStage.show();
        view.getGrid().requestFocus(); // deze call doen we zodat de arrowkeys worden herkend
    }

    public static void main(String[] args) {
        launch(args);
    }
}
