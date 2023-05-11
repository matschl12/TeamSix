package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.TextArea;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

public class CustomMessages {
/*you can
either setText
or appendText
by passing a string to the right method
 */


    public void setTextTo_myCustomMessageField(String text) {
        HomeController.myCustomMessageField.setText("Tri my custom text");
    }

    public void appendTextTo_myCustomMessageField(String text) {
        HomeController.myCustomMessageField.appendText("Tri my custom text");
    }


}
