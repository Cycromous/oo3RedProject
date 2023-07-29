package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class MainScreenController {
    private JobList jobList;
    private String field;
    private Profile profile;

    @FXML
    private Text degreeField;

    @FXML
    private VBox industryVBox;

    @FXML
    private TextField jobNameTextField;

    @FXML
    private VBox jobsVBox;

    @FXML
    private Text nameField;

    @FXML
    private ImageView statisticsImageView;

    @FXML
    private Text universityField;

    @FXML
    private Button profileButton;

    @FXML
    public void initialize() {
        profile = SessionManager.getCurrentProfile();

        jobList = new JobList();
        jobList.loadJobs();

        // Display all jobs
        for (int i = 0; i < jobList.getSize(); i++) {
            Job currentJob = jobList.getJob(i);
            HBox jobHBox = createJobHBox(currentJob);
            jobsVBox.getChildren().add(jobHBox);
        }

        // Update Name, Degree, and University Attended TextFields
        if (profile.getName() != null) nameField.setText(profile.getName());
        if (profile.getDegree() != null) degreeField.setText(profile.getDegree());
        if (profile.getUniversity() != null) universityField.setText(profile.getUniversity());
    }

    @FXML
    void searchClicked() {
        String jobName = jobNameTextField.getText();
        jobsVBox.getChildren().clear();

        for (int i = 0; i < jobList.getSize(); i++) {
            int jobIndex = jobList.searchJobByName(jobName);
            if (jobIndex != -1) {
                Job currentJob = jobList.getJob(jobIndex);
                HBox jobHBox = createJobHBox(currentJob);
                jobsVBox.getChildren().add(jobHBox);
                jobNameTextField.clear();
                break;
            }
        }
    }

    @FXML
    void allClicked() {
        for (int i = 0; i < jobList.getSize(); i++) {
            Job currentJob = jobList.getJob(i);
            HBox jobHBox = createJobHBox(currentJob);
            jobsVBox.getChildren().add(jobHBox);
        }
    }

    @FXML
    void editProfileClicked() throws IOException {
        switchToProfileScreen(this.profile);
    }

    public void switchToProfileScreen(Profile profile) throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) profileButton.getScene().getWindow();

        // Load the FXML file for the profile screen scene
        FXMLLoader profileScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/edit-profile.fxml"));
        Parent profileScreenRoot = profileScreenLoader.load();

        Scene profileScreenScene = new Scene(profileScreenRoot);
        currentStage.setScene(profileScreenScene);
        currentStage.setTitle("Profile");
    }

    @FXML
    void medicalClicked() {
        field = "Medical";
        displayJobsByField(field);
        // TODO: Display ImageView of Industry Statistics of respective field
    }

    @FXML
    void engineeringClicked() {
        field = "Engineering";
        displayJobsByField(field);
        // TODO: Display ImageView of Industry Statistics of respective field
    }

    @FXML
    void technologyClicked() {
        field = "Technology";
        displayJobsByField(field);
        // TODO: Display ImageView of Industry Statistics of respective field
    }

    @FXML
    void creativesClicked() {
        field = "Creatives";
        displayJobsByField(field);
        // TODO: Display ImageView of Industry Statistics of respective field
    }

    @FXML
    void financeClicked() {
        field = "Finance";
        displayJobsByField(field);
        // TODO: Display ImageView of Industry Statistics of respective field
    }

    public void displayJobsByField(String field) {
        jobsVBox.getChildren().clear();

        for (int i = 0; i < jobList.getSize(); i++) {
            Job currentJob = jobList.getJob(i);

            if (currentJob.getField().equalsIgnoreCase(field)) {
                HBox jobHBox = createJobHBox(currentJob);
                jobsVBox.getChildren().add(jobHBox);
            }
        }
    }

    public HBox createJobHBox(Job job) {
        HBox hbox = new HBox();
        Label nameLabel = createLabel(job.getName(), 180, 50, 18, Pos.CENTER_LEFT);
        Label salaryLabel = createLabel(job.getSalary(), 170, 50, 22, Pos.CENTER);
        Label workSetupLabel = createLabel(job.getWorkSetup(), 150, 50, 15, Pos.CENTER);
        Label degreeLabel = createLabel(job.getDegree(), 250, 50, 18, Pos.CENTER);

        hbox.getChildren().addAll(nameLabel, salaryLabel, workSetupLabel, degreeLabel);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    public Label createLabel(String string, double width, double height, double fontSize, Pos pos) {
        Label label = new Label(string);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font(fontSize));
        return label;
    }
}
