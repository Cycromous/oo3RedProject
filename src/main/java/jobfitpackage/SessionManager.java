package jobfitpackage;

import javafx.stage.Stage;

public class SessionManager {
    private static Profile currentProfile;
    private static Stage primaryStage;

    public static Profile getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(Profile profile) {
        currentProfile = profile;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
}
