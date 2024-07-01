module net.stumpwiz.mrradb {
    requires org.jooq;
    requires org.jetbrains.annotations;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports net.stumpwiz.mrradb;
    exports net.stumpwiz.mrradb.controllers;
    exports net.stumpwiz.mrradb.generated.tables.records to org.jooq;
    opens net.stumpwiz.mrradb to javafx.fxml;
    opens net.stumpwiz.mrradb.controllers to javafx.fxml;
}
