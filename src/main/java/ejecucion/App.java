package ejecucion;


import com.mycompany.mavenproject12.TicTacToeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.AnchorPane;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private TicTacToeController interCon;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader();
        System.out.println(getClass().getResource("/fxml/y.fxml"));
        loginLoader.setLocation(getClass().getResource("/fxml/TicTacToe.fxml"));
        Parent root =loginLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
