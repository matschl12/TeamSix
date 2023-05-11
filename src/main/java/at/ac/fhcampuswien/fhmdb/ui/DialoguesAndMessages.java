package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.scene.control.Alert;


public class DialoguesAndMessages {

    public static void alertBox(String titleText, String headerText, String contextMessage)   {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contextMessage);

        alert.showAndWait();}

    public static void infoBox(String titleText, String headerText, String contextMessage)   {

     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contextMessage);

        alert.showAndWait();}

    public static String UIdialogue(String question) {
    

        return "okay";
        /*
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Question");

        grid.add(new Label("Q:"), 0, 0);
        grid.add(name, 1, 0);

        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return name.getText();
            }
            return null;
        });

         */
    }

    public void setTextTo_myCustomMessageField(String text) {
        HomeController.myCustomMessageField.setText("Tri my custom text");
    }

    public void appendTextTo_myCustomMessageField(String text) {
        HomeController.myCustomMessageField.appendText("Tri my custom text");
    }




}
