package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.pattern.FactoryPattern.MyFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml")); <- old
        MyFactory myFactory = MyFactory.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        fxmlLoader.setControllerFactory(myFactory);

        stage.getIcons().add(new Image(FhmdbApplication.class.getResourceAsStream("TeamSixIcon.png")));
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("TeamSix-FHMDb");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();


    }



}