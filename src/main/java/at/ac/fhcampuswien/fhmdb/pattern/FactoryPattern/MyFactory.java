package at.ac.fhcampuswien.fhmdb.pattern.FactoryPattern;

import at.ac.fhcampuswien.fhmdb.HomeController;
import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {
    //make it a singleton

    @Override
    public Object call (Class<?> aClass)
    {
        try{
            return (HomeController) aClass.getDeclaredConstructor().newInstance();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
