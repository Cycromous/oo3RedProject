package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    private ProfileList profileList;
    private Profile currentProfile;
    @FXML
    private TextField passwordTextField, usernameTextField;
    @FXML
    private Hyperlink signupHyperlink;

    public void setProfileList(ProfileList profileList) {
        this.profileList = profileList;
    }

    @FXML
    void initialize() {
        profileList = ProfileList.getInstance();
    }

    @FXML
    void signInClicked() throws IOException {
        String usernameOrEmail = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Do nothing if Sign In button is clicked and either TextFields is empty
        if (usernameOrEmail.isEmpty() || password.isEmpty()) return;

        // Search for profile by username and email
        int usernameIndex = profileList.searchProfileUsername(usernameOrEmail);
        int emailIndex = profileList.searchProfileEmail(usernameOrEmail);

        // Check if either username or email is found
        if (usernameIndex != -1 || emailIndex != -1) {
            // max() gets the higher value between usernameIndex and emailIndex (The index that isn't -1)
            int profileIndex = Math.max(usernameIndex, emailIndex);

            Profile profile = profileList.getProfile(profileIndex);
            if (profile.getPassword().equals(password)) {
                switchToMainScreen(currentProfile);
                System.out.println("Login successful!");
            } else {
                createAlert("Incorrect password. Please try again.", "Incorrect Password");
                System.out.println("Incorrect password");
            }
        } else {
            System.out.println("Profile not found. Please check your input.");
        }
    }

    @FXML
    void signUpClicked() throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) signupHyperlink.getScene().getWindow();

        // Load the FXML file for the new scene
        Parent signupSceneRoot = FXMLLoader.load(getClass().getResource("/FXML-Files/sign-up.fxml"));
        Scene signupScene = new Scene(signupSceneRoot);
        currentStage.setScene(signupScene);
        currentStage.setTitle("Sign Up");
    }

    public void createAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToMainScreen(Profile profile) throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) signupHyperlink.getScene().getWindow();

        // Load the FXML file for the main screen scene
        FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/main-screen.fxml"));
        Parent mainScreenRoot = mainScreenLoader.load();
        Scene mainScreenScene = new Scene(mainScreenRoot);
        currentStage.setScene(mainScreenScene);
        currentStage.setTitle("JobFit Explorer");
    }
}
