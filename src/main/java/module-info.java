module ejecucion{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    requires transitive java.desktop;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    opens ejecucion to javafx.fxml;
    exports ejecucion;
    exports com.mycompany.mavenproject12;
    opens com.mycompany.mavenproject12 to javafx.fxml;
    
}

