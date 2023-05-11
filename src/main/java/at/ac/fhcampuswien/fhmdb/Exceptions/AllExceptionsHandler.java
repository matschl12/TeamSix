package at.ac.fhcampuswien.fhmdb.Exceptions;


import at.ac.fhcampuswien.fhmdb.ui.DialoguesAndMessages;


public class AllExceptionsHandler extends RuntimeException  {

//this will catch and handle all exceptions also beyond the 2 custom exception classes



    public AllExceptionsHandler(String headerText, String contextMessage) {
        super(contextMessage);

//here is our alert box:
        DialoguesAndMessages.alertBox("An exception occurred",headerText, contextMessage);

//HomeController.myCustomMessageField.setText(contextMessage);

        System.out.print("contextMessage");

    }

    /*
         public AllExceptionsHandler(String message){
             super(message);
             DialoguesAndMessages.alertBox("An exception occurred","A built-in exception occurred", message);

         }

         public static int catchExceptions() {
             return 0;
         }
     /*
         public static void forAnyOtherExceptions(){
             System.out.println("nothing");

         Thread thread = new Thread(() -> {
             try {
                 // whatever
             } catch (Exception e) {

                 myCustomMessagesTextArea.setText(e.getMessage());



             });
             thread.setUncaughtExceptionHandler((t, e) -> {
                 myCustomMessagesTextArea.setText(e.getMessage());
             });
             thread.start();

             Scene scene = new Scene(root, 300, 200);
             stage.setScene(scene);
             stage.show();
    */
}


