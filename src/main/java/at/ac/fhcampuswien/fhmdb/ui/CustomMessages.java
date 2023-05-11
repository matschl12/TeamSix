package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.TextArea;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

public class CustomMessages {



    public void setTextToTextArea(String text) {
        HomeController.myCustomMessageField.setText("Tri my custom text");
    }

    public void appendTextToTextArea(String text) {
        HomeController.myCustomMessageField.appendText("Tri my custom text");
    }


}
