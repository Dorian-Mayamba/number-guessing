module com.numbers.numbers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.numbers.numbers to javafx.fxml;
    exports com.numbers.numbers;
}