package jobfitpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    @FXML
    private Text DegreeField;

    @FXML
    private ImageView creativesImageView;

    @FXML
    private ImageView engineeringImageView;

    @FXML
    private ImageView financeImageView;

    @FXML
    private VBox industryVBox;

    @FXML
    private TextField jobNameTextField;

    @FXML
    private ScrollPane jobsScrollPane;

    @FXML
    private VBox jobsVBox;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView medicalImageView;

    @FXML
    private Text nameField;

    @FXML
    private ImageView statisticsImageView;

    @FXML
    private ImageView technologyImageView;

    @FXML
    private Text universityField;

    @FXML
    private Button profileButton;

    @FXML
    public void initialize() {
        jobList = new JobList();
        jobList.loadJobs();

        for (int i = 0; i < jobList.getSize(); i++) {
            Job currentJob = jobList.getJob(i);
            HBox jobHBox = createJobHBox(currentJob);
            jobsVBox.getChildren().add(jobHBox);
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

    @FXML
    void creativesClicked() {

    }

    @FXML
    void editProfileClicked() throws IOException {
        switchToProfileScreen();
    }

    void switchToProfileScreen() throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) profileButton.getScene().getWindow();

        // Load the FXML file for the profile screen scene
        FXMLLoader profileScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/edit-profile.fxml"));
        Parent profileScreenRoot = profileScreenLoader.load();
        Scene profileScreenScene = new Scene(profileScreenRoot);

        // Set the new scene without affecting the jobsVBox
        currentStage.setScene(profileScreenScene);
        currentStage.setTitle("Profile");
    }

    @FXML
    void engineeringClicked() {

    }

    @FXML
    void financeClicked() {

    }

    @FXML
    void medicalClicked(ActionEvent event) {

    }

    @FXML
    void searchClicked(ActionEvent event) {

    }

    @FXML
    void technologyClicked(ActionEvent event) {

    }
}
