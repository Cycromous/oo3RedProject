module jobfitpackage.jobfitexplorer {
    requires javafx.controls;
    requires javafx.fxml;


    opens jobfitpackage to javafx.fxml;
    exports jobfitpackage;
}