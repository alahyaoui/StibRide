module g54895.atl.stibride {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;

    opens g54895.atl.stibride.view to javafx.fxml;
    exports g54895.atl.stibride.view;
    exports g54895.atl.stibride.main;

    opens g54895.atl.stibride.jdbc to java.sql;
    exports g54895.atl.stibride.jdbc;
}
