package jobfitpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController extends AbstractController {
    private ProfileList profileList;
    @FXML
    private TextField passwordTextField, usernameTextField;
    @FXML
    private Hyperlink signupHyperlink;

    @FXML
    @Override
    public void initialize() {
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
                SessionManager.setCurrentProfile(profile); // Set the current profile
                switchToMainScreen();
                System.out.println("Login successful!");
            } else {
                createAlert("Incorrect password. Please try again.", "Incorrect Password");
                System.out.println("Incorrect password");
            }
        } else {
            createAlert("Profile not found. Please check your input", "Profile not Found");
        }
    }

    @FXML
    void signUpClicked() throws IOException {
        switchScene("/FXML-Files/sign-up.fxml", "Sign Up");
    }

    public void switchToMainScreen() throws IOException {
        switchScene("/FXML-Files/main-screen.fxml", "JobFit Explorer");
    }
}
