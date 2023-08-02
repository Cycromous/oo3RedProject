package jobfitpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpController extends AbstractController {
    private ProfileList profileList;

    @FXML
    private TextField usernameTextField, emailTextField, passwordTextField;

    @FXML
    private Hyperlink signinHyperlink;

    @FXML
    public void initialize() {
        profileList = ProfileList.getInstance();
    }

    @FXML
    void signInClicked() throws IOException {
        switchScene("/FXML-Files/sign-in.fxml", "Sign In");
    }

    @FXML
    void createAccountClicked() throws IOException {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty())
            createAlert("Please fill in all the fields.", "Missing Information");

        int usernameIndex = profileList.searchProfileUsername(username);
        int emailIndex = profileList.searchProfileEmail(email);

        // Username already exists
        if (usernameIndex != -1) {
            usernameTextField.clear();
            String errorMessage = "The username you entered is already in use.";
            createAlert(errorMessage, "Username Already Taken");
        }

        // Email already exists
        if (emailIndex != -1) {
            emailTextField.clear();
            String errorMessage = "The email you entered is already in use. Proceed to login";
            createAlert(errorMessage, "Email Already Taken");
        }

        if (usernameIndex == -1 && emailIndex == -1){
            createNewAccount(username, email, password);
            usernameTextField.clear();
            emailTextField.clear();
            passwordTextField.clear();
        }
    }

    public void createNewAccount(String username, String email, String password) throws IOException {
        String newAccount = "\n" + username + "," + email + "," +  password;

        String filePath = "src/main/resources/LoginDetails.txt";
        File file = new File(filePath);

        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
        bufferWritter.write(newAccount);

        bufferWritter.close();
        fileWriter.close();

        // Create a new Profile object for the new account
        Profile newProfile = new Profile(username, email, password);
        // Add the new Profile to the ProfileList
        profileList.addProfile(newProfile);

        System.out.print("Added Profile: " + username + ", " + email + ", " + password);

        String message = "Account has successfully been created. You may now proceed to sign in.";
        createAlert(message, "Account Creation Successful");
    }

    public void createAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
