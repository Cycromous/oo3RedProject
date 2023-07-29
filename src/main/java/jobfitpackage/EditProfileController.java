package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class EditProfileController {
    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView logoImageView1;

    @FXML
    private TextField nameTextField, degreeTextField, universityTextField, experienceTextField, achievementsTextField;

    @FXML
    private TextArea introductionTextArea;

    private Profile profile;

    @FXML
    public void initialize() {
        profile = SessionManager.getCurrentProfile();

        if (profile.getName() != null) nameTextField.setText(profile.getName());
        if (profile.getIntroduction() != null) introductionTextArea.setText(profile.getIntroduction());
        if (profile.getDegree() != null) degreeTextField.setText(profile.getDegree());
        if (profile.getUniversity() != null) universityTextField.setText(profile.getUniversity());
        if (profile.getExperience() != null) experienceTextField.setText(profile.getExperience());
        if (profile.getAchievements() != null) achievementsTextField.setText(profile.getAchievements());
    }

    @FXML
    void editProfileClicked() {
        updateProfile();
    }

    @FXML
    void mainMenuClicked() throws IOException {
        switchToMainScreen(profile);
    }

    public void updateProfile() {
        String name = nameTextField.getText();
        String introduction = introductionTextArea.getText();
        String degree = degreeTextField.getText();
        String university = universityTextField.getText();
        String experience = experienceTextField.getText();
        String achievements = achievementsTextField.getText();

        if (!name.isEmpty()) profile.setName(name);
        if (!introduction.isEmpty()) profile.setIntroduction(introduction);
        if (!degree.isEmpty()) profile.setDegree(degree);
        if (!university.isEmpty()) profile.setUniversity(university);
        if (!experience.isEmpty()) profile.setExperience(experience);
        if (!achievements.isEmpty()) profile.setAchievements(achievements);

        System.out.println("New Name: " + profile.getName());
        System.out.println("Introduction: " + profile.getIntroduction());
        System.out.println("Degree: " + profile.getDegree());
        System.out.println("University: " + profile.getUniversity());
        System.out.println("Experience: " + profile.getExperience());
        System.out.println("Achievements: " + profile.getAchievements());
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
