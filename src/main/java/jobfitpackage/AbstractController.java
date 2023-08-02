package jobfitpackage;

import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractController {
    public void createAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public abstract void initialize();

    public void switchScene(String fxmlFilePath, String title) throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = getCurrentStage();

        // Load the FXML file for the new scene
        Parent sceneRoot = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        Scene scene = new Scene(sceneRoot);
        currentStage.setScene(scene);
        currentStage.setTitle(title);
        currentStage.centerOnScreen();
    }

    public Stage getCurrentStage() {
        return (Stage) SessionManager.getPrimaryStage().getScene().getWindow();
    }
}
