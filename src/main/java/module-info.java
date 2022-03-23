module stibride {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    
//opens g54895.atl.stibride.view to javafx.controls;
    opens g54895.atl.stibride.view to javafx.fxml, javafx.controls, org.controlsfx.controls;
    exports stibride.view;
    exports stibride.main;
    
    opens g54895.atl.stibride.dto to javafx.base;


    opens g54895.atl.stibride.jdbc to java.sql;
    exports stibride.jdbc;
}
