package jobfitpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create the ProfileList instance
        ProfileList profileList = ProfileList.getInstance();
        profileList.loadLoginDetails();

        // Load the FXML file for the sign-in scene
        FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("/FXML-Files/sign-in.fxml"));
        Parent signInRoot = signInLoader.load();
        SignInController signInController = signInLoader.getController();
        signInController.setProfileList(profileList);

        Scene scene = new Scene(signInRoot);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
