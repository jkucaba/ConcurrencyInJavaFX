module com.jakubku.concurrency.concurrency {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jakubku.concurrency.concurrency to javafx.fxml;
    exports com.jakubku.concurrency.concurrency;
}