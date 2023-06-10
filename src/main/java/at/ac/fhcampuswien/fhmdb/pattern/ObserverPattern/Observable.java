package at.ac.fhcampuswien.fhmdb.pattern.ObserverPattern;

public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);

    void notifyObserver(String type);

}
