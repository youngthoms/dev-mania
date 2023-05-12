package view;

import controller.KeyHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DevMania extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dev Mania");
        KeyHandler keyH = new KeyHandler();
        GamePanel gp = new GamePanel(keyH);

        BorderPane root = new BorderPane();
        root.setCenter(gp);
        root.getStyleClass().add("root");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(keyH);
        scene.setOnKeyReleased(keyH);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        gp.startGameThread();
    }
}
