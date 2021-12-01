module com.filmplanner {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.filmplanner to javafx.fxml;
    exports com.filmplanner;
    exports com.filmplanner.models;
    opens com.filmplanner.models to javafx.fxml;
    exports com.filmplanner.facades;
    opens com.filmplanner.facades to javafx.fxml;
}
