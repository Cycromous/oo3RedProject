package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class EditProfileController {
    private File file;

    @FXML
    private TextField nameTextField, degreeTextField, universityTextField, experienceTextField, achievementsTextField;

    @FXML
    private TextArea introductionTextArea;

    private Profile profile;

    @FXML
    public void initialize() throws IOException {
        profile = SessionManager.getCurrentProfile();
        String fileName = profile.username + "Details.txt";
        String filePath = "src/main/resources/profile-details/" + fileName;

        file = new File(filePath);
        loadProfileDetails();
    }

    public void loadProfileDetails() throws IOException {
        // Load the details of the Profile using the file's lines
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String name = reader.readLine();
        String introduction = reader.readLine();
        String degree = reader.readLine();
        String university = reader.readLine();
        String experience = reader.readLine();
        String achievements = reader.readLine();
        reader.close();

        // Set the values to the profile's attributes
        profile.setName(name);
        profile.setIntroduction(introduction);
        profile.setDegree(degree);
        profile.setUniversity(university);
        profile.setExperience(experience);
        profile.setAchievements(achievements);

        // Update the text fields and text area with the loaded profile details
        setTextFields();
    }

    @FXML
    public void setTextFields() {
        nameTextField.setText(profile.getName());
        introductionTextArea.setText(profile.getIntroduction());
        degreeTextField.setText(profile.getDegree());
        universityTextField.setText(profile.getUniversity());
        experienceTextField.setText(profile.getExperience());
        achievementsTextField.setText(profile.getAchievements());
    }

    @FXML
    void updateProfileClicked() throws IOException {
        updateProfile(); // Updates the Profile object
        saveProfileToFile(); // Updates the corresponding txt file of the Profile
        createAlert("Your profile has successfully been updated.", "Profile Edited Successfully");
    }

    public void updateProfile() {
        // Only update each profile's attributes when text field is not blank
        if (!nameTextField.getText().isBlank()) profile.setName(nameTextField.getText());
        if (!introductionTextArea.getText().isBlank()) profile.setIntroduction(introductionTextArea.getText());
        if (!degreeTextField.getText().isBlank()) profile.setDegree(degreeTextField.getText());
        if (!universityTextField.getText().isBlank())profile.setUniversity(universityTextField.getText());
        if (!experienceTextField.getText().isBlank()) profile.setExperience(universityTextField.getText());
        if (!achievementsTextField.getText().isBlank()) profile.setAchievements(achievementsTextField.getText());
    }

    private void saveProfileToFile() throws  IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(profile.getName());
        writer.newLine();
        writer.write(profile.getIntroduction());
        writer.newLine();
        writer.write(profile.getDegree());
        writer.newLine();
        writer.write(profile.getUniversity());
        writer.newLine();
        writer.write(profile.getExperience());
        writer.newLine();
        writer.write(profile.getAchievements());

        writer.close();
    }

    public void createAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void mainMenuClicked() throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) nameTextField.getScene().getWindow();

        // Load the FXML file for the main screen scene
        FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/main-screen.fxml"));
        Parent mainScreenRoot = mainScreenLoader.load();

        Scene mainScreenScene = new Scene(mainScreenRoot);
        currentStage.setScene(mainScreenScene);
        currentStage.setTitle("JobFit Explorer");
        currentStage.centerOnScreen();
    }
}
