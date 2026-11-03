module com.mcgui.viruswar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.mcgui.client to javafx.fxml;
    opens com.mcgui.ui to javafx.fxml;
    exports com.mcgui.client;
    exports com.mcgui.ui;
    exports com.mcgui.server;
    opens com.mcgui.server to javafx.fxml;
}