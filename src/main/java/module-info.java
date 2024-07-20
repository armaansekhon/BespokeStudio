module com.example.projtailor {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.mail;
    requires activation;

    opens com.example.projtailor to javafx.fxml;
    exports com.example.projtailor;

    opens com.example.projtailor.enrollmentform to javafx.fxml;
    exports com.example.projtailor.enrollmentform;

    opens com.example.projtailor.WorkersConsole to javafx.fxml;
    exports com.example.projtailor.WorkersConsole;

    opens com.example.projtailor.measurement to javafx.fxml;
    exports com.example.projtailor.measurement;

    opens com.example.projtailor.getdresses to javafx.fxml;
    exports com.example.projtailor.getdresses;

    opens com.example.projtailor.tabworker to javafx.fxml;
    exports com.example.projtailor.tabworker;

    opens com.example.projtailor.measureExp to javafx.fxml;
    exports com.example.projtailor.measureExp;
    opens com.example.projtailor.DeliveryPanel to javafx.fxml;
    exports com.example.projtailor.DeliveryPanel;

    opens com.example.projtailor.mainpage to javafx.fxml;
    exports com.example.projtailor.mainpage;

    opens com.example.projtailor.Dashboard to javafx.fxml;
    exports com.example.projtailor.Dashboard;








}