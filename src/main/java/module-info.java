module com.filmplanner {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.filmplanner to javafx.fxml;
    exports com.filmplanner;
}
