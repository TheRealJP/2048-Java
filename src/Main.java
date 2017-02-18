import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bord;
import views.gewonnenview.GewonnenView;
import views.nieuwspelview.NieuwSpelPresenter;
import views.nieuwspelview.NieuwSpelView;
import views.spelview.BordPresenter;
import views.spelview.BordView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Bord model = new Bord();
        NieuwSpelView view = new NieuwSpelView();
        new NieuwSpelPresenter(model, view);
//        Scene scene = new Scene(view);
        Scene scene = new Scene(new GewonnenView());
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
