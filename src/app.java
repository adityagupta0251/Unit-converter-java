
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = FXMLLoader.load(getClass().getResource("/converter.fxml"));
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("🧮 Unit Converter");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setMinWidth(500);
        stage.setMinHeight(450);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}