module com.numbers.numbers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.numbers.numbers to javafx.fxml;
    opens com.numbers.controllers to javafx.fxml;
    exports com.numbers.numbers;
}