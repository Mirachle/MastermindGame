package com.mycompany.mastermind;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main osztály, ami a kezdpképernyő megjelenését biztosítja.
 * @author minori
 */
public class MainApp extends Application {
     /**
     * Logger példány a naplózáshoz.
     */
    private Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        fxmlLoader.<FXMLController>getController().afterInit();
        stage.show();
        logger.info("Fő ablak megnyilt.");
    }

    /**
     * main metódus.
     * @param args argomentumok
     */
    public static void main(String[] args) {
        launch(args);
    }

}
