module com.example.al9ani {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires java.datatransfer;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.example.al9ani to javafx.fxml;
    opens com.example.al9ani.entities to javafx.fxml;
    opens com.example.al9ani.controllers to javafx.fxml;
    opens com.example.al9ani.controllers.back to javafx.fxml;
    opens com.example.al9ani.controllers.front to javafx.fxml;
    opens com.example.al9ani.controllers.front.post to javafx.fxml;
    opens com.example.al9ani.controllers.front.commentaire to javafx.fxml;
    opens com.example.al9ani.controllers.front.etablissement to javafx.fxml;
    opens com.example.al9ani.controllers.front.reservation to javafx.fxml;
    opens com.example.al9ani.controllers.back.post to javafx.fxml;
    opens com.example.al9ani.controllers.back.commentaire to javafx.fxml;
    opens com.example.al9ani.controllers.back.etablissement to javafx.fxml;
    opens com.example.al9ani.controllers.back.reservation to javafx.fxml;

    exports com.example.al9ani;
    exports com.example.al9ani.entities;
    exports com.example.al9ani.controllers;
    exports com.example.al9ani.controllers.back;
    exports com.example.al9ani.controllers.front;
    exports com.example.al9ani.controllers.front.post;
    exports com.example.al9ani.controllers.front.commentaire;
    exports com.example.al9ani.controllers.front.etablissement;
    exports com.example.al9ani.controllers.front.reservation;
    exports com.example.al9ani.controllers.back.post;
    exports com.example.al9ani.controllers.back.commentaire;
    exports com.example.al9ani.controllers.back.etablissement;
    exports com.example.al9ani.controllers.back.reservation;
}