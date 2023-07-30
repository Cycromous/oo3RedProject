package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;

public class EditProfileController {
    File file;
    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView logoImageView1;

    @FXML
    private TextField nameTextField, degreeTextField, universityTextField, experienceTextField, achievementsTextField;

    @FXML
    private TextArea introductionTextArea;

    private Profile profile;

    /*@FXML
    public void initialize() {
        profile = SessionManager.getCurrentProfile();

        String fileName = profile.username + "Details.txt";
        Path filePath = Paths.get("src/main/resources/profile-details/", fileName);

        try {
            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                if (lines.size() >= 6) { // Ensure that there are at least 6 lines in the file
                    // Read each line and set the corresponding profile attributes
                    profile.setName(lines.get(0));
                    profile.setIntroduction(lines.get(1));
                    profile.setDegree(lines.get(2));
                    profile.setUniversity(lines.get(3));
                    profile.setExperience(lines.get(4));
                    profile.setAchievements(lines.get(5));

                    // Set the text fields and text area
                    setTextFields();
                }
            }

            else {
                // The file does not exist, create a new one with default values
                System.out.println("Profile file does not exist. Creating a new one.");
                saveProfileToFile(); // This will create the new file with default values
                setTextFields();
            }
        } catch (IOException e) {
            // If reading or file creation fails, handle this case accordingly
            e.printStackTrace();
        }
    }*/
    @FXML
    public void initialize() {
        profile = SessionManager.getCurrentProfile();

        String fileName = profile.username + "Details.txt";
        String filePath = "src/main/resources/profile-details/" + fileName;

        try {
            file = new File(filePath);
            if (!file.exists()) {
                // If the file doesn't exist, create a new one with default values
                createNewProfile();
            } else {
                // If the file exists, load the details from the file and set them to the profile
                loadProfileDetails();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewProfile() throws IOException {
        // Create a new file with default values
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("");
        writer.newLine();
        writer.write("");
        writer.newLine();
        writer.write("");
        writer.newLine();
        writer.write("");
        writer.newLine();
        writer.write("");
        writer.newLine();
        writer.write("");
        writer.newLine();
        writer.close();

        // Set the default values to the profile
        profile.setName("");
        profile.setIntroduction("");
        profile.setDegree("");
        profile.setUniversity("");
        profile.setExperience("");
        profile.setAchievements("");
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

    public void updateProfile() {
        profile.setName(nameTextField.getText());
        profile.setIntroduction(introductionTextArea.getText());
        profile.setDegree(degreeTextField.getText());
        profile.setUniversity(universityTextField.getText());
        profile.setExperience(universityTextField.getText());
        profile.setAchievements(achievementsTextField.getText());
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
        switchToMainScreen(profile);
    }

    public void switchToMainScreen(Profile profile) throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) nameTextField.getScene().getWindow();

        // Load the FXML file for the main screen scene
        FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/main-screen.fxml"));
        Parent mainScreenRoot = mainScreenLoader.load();

        Scene mainScreenScene = new Scene(mainScreenRoot);
        currentStage.setScene(mainScreenScene);
        currentStage.setTitle("JobFit Explorer");
    }
}
