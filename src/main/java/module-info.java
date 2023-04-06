module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.models to com.fasterxml.jackson.databind;
    exports at.ac.fhcampuswien.fhmdb;
}