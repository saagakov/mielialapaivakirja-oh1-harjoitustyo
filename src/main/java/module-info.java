module com.example.harjoitustyo_saaga {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harjoitustyo_saaga to javafx.fxml;
    exports com.example.harjoitustyo_saaga;
}