package view;

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
        GamePanel gp = new GamePanel();
        gp.setUpGame();
        BorderPane root = new BorderPane();
        root.setCenter(gp);
        root.getStyleClass().add("root");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(gp.getKeyH());
        scene.setOnKeyReleased(gp.getKeyH());

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        gp.startGameThread();
    }
}
