package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;

public interface ClickEventHandler<T> {
    void onClick (T t) throws DatabaseException;
}
