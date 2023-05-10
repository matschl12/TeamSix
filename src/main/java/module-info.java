module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires java.sql;
    requires ormlite.jdbc;
    requires ormlite.core;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml, ormlite.core;
    opens at.ac.fhcampuswien.fhmdb.models to com.fasterxml.jackson.databind;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.Exceptions;
    opens at.ac.fhcampuswien.fhmdb.Exceptions to javafx.fxml, ormlite.core;
}