import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Bord;
import views.menuview.MenuPresenter;
import views.menuview.MenuView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(Main.class.getResource("fonts/bitFont.TTF").toExternalForm(),10);
        Bord model = new Bord();
//        NieuwSpelView view = new NieuwSpelView();
//        VerlorenView view = new VerlorenView();
//        GewonnenView view = new GewonnenView();
        MenuView view = new MenuView();
//        new GewonnenPresenter(view,model);
//        new NieuwSpelPresenter(model,view);
//        new VerlorenPresenter(model,view);
        new MenuPresenter(model,view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(800);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("afbeeldingen/2048icon.png")); //favicon
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}