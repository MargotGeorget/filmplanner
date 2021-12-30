module com.filmplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dotenv.java;

    opens com.filmplanner to javafx.fxml;
    exports com.filmplanner;
    exports com.filmplanner.models;
    opens com.filmplanner.models to javafx.fxml;
    exports com.filmplanner.facades;
    opens com.filmplanner.facades to javafx.fxml;
    exports com.filmplanner.controllers;
    opens com.filmplanner.controllers to javafx.fxml;
    exports com.filmplanner.controllers.client;
    opens com.filmplanner.controllers.client to javafx.fxml;
    exports com.filmplanner.controllers.gear;
    opens com.filmplanner.controllers.gear to javafx.fxml;
    exports com.filmplanner.controllers.project;
    opens com.filmplanner.controllers.project to javafx.fxml;
    exports com.filmplanner.controllers.user;
    opens com.filmplanner.controllers.user to javafx.fxml;
}
