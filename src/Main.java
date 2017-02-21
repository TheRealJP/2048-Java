import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bord;
import views.gewonnenview.GewonnenView;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Bord model = new Bord();
        NieuwSpelView view = new NieuwSpelView();
        GewonnenView gewonnenView = new GewonnenView();
        new NieuwSpelPresenter(model, view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}