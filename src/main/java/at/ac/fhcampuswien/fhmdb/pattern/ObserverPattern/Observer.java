package at.ac.fhcampuswien.fhmdb.pattern.ObserverPattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface Observer {
    void watchListUpdate(String type);
}
