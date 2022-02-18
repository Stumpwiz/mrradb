module net.stumpwiz.mrradb {
    requires org.jooq;
    requires org.jetbrains.annotations;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens net.stumpwiz.mrradb to javafx.fxml;
    exports net.stumpwiz.mrradb;
    exports net.stumpwiz.mrradb.controllers;
    exports net.stumpwiz.mrradb.model.tables.records to org.jooq;
    opens net.stumpwiz.mrradb.controllers to javafx.fxml;
}
