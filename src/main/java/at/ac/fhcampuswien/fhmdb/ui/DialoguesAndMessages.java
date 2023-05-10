package at.ac.fhcampuswien.fhmdb.ui;

import javafx.scene.control.Alert;


public class DialoguesAndMessages {


    public DialoguesAndMessages(String titleText, String headerText, String contextMessage)   {

    /*this constructor takes 3 strings to display any simple pop-up message in the UI:
    1 title
    2 header
    3 context=the message itself
     */

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contextMessage);

        alert.showAndWait();

    }

    public String UIdialogue(String question) {
    

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


}
