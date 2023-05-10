package at.ac.fhcampuswien.fhmdb.Exceptions;

import at.ac.fhcampuswien.fhmdb.ui.DialoguesAndMessages;


public class ThrowExceptionsHere extends RuntimeException  {



    public ThrowExceptionsHere(String headerText, String contextMessage) {
        super(contextMessage);
        DialoguesAndMessages popUp=new DialoguesAndMessages("An exception occurred", headerText, contextMessage);
    }


    public static int catch_Exceptions() {
        return 0;
    }
}