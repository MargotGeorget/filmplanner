module com.filmplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dotenv.java;
    requires com.calendarfx.view;

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
    opens com.filmplanner.controllers.shoot to javafx.fxml;
    exports com.filmplanner.controllers.shoot;
    exports com.filmplanner.controllers.project;
    opens com.filmplanner.controllers.project to javafx.fxml;
    exports com.filmplanner.controllers.user;
    opens com.filmplanner.controllers.user to javafx.fxml;
    exports com.filmplanner.controllers.role;
    opens com.filmplanner.controllers.role to javafx.fxml;
}
