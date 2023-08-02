package jobfitpackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MainScreenController {
    private File file;
    private JobList jobList;
    private String field;
    private Profile profile;

    @FXML
    private Text degreeField;

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

        String fileName = profile.username + "Details.txt";
        String filePath = "src/main/resources/profile-details/" + fileName;

        jobList = new JobList();
        jobList.loadJobs();

        // Display all jobs
        for (int i = 0; i < jobList.getSize(); i++) {
            Job currentJob = jobList.getJob(i);
            HBox jobHBox = createJobHBox(currentJob);
            jobsVBox.getChildren().add(jobHBox);
        }

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
        nameField.setText(profile.getName());
        degreeField.setText(profile.getDegree());
        universityField.setText(profile.getUniversity());
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
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) profileButton.getScene().getWindow();

        // Load the FXML file for the profile screen scene
        FXMLLoader profileScreenLoader = new FXMLLoader(getClass().getResource("/FXML-Files/edit-profile.fxml"));
        Parent profileScreenRoot = profileScreenLoader.load();

        Scene profileScreenScene = new Scene(profileScreenRoot);
        currentStage.setScene(profileScreenScene);
        currentStage.setTitle("Profile");
        currentStage.centerOnScreen();
    }

    @FXML
    void jobFinderClicked() {
        List<String> medicalKeywords = readIndustryKeywords("MedicalKeywords.txt");
        List<String> engineeringKeywords = readIndustryKeywords("EngineeringKeywords.txt");
        List<String> technologyKeywords = readIndustryKeywords("TechnologyKeywords.txt");
        List<String> creativesKeywords = readIndustryKeywords("CreativesKeywords.txt");
        List<String> financeKeywords = readIndustryKeywords("FinanceKeywords.txt");

        jobsVBox.getChildren().clear();

        String jobDegree = degreeField.getText();
        if (containsAnyKeyword(jobDegree, medicalKeywords)) {
            field = "Medical";
            displayJobsByField(field);
        }
        if (containsAnyKeyword(jobDegree, engineeringKeywords)) {
            field = "Engineering";
            displayJobsByField(field);
        }
        if (containsAnyKeyword(jobDegree, technologyKeywords)) {
            field = "Technology";
            displayJobsByField(field);
        }
        if (containsAnyKeyword(jobDegree, creativesKeywords)) {
            field = "Creatives";
            displayJobsByField(field);
        }
        if (containsAnyKeyword(jobDegree, financeKeywords)) {
            field = "Finance";
            displayJobsByField(field);
        }
    }

    private List<String> readIndustryKeywords(String fileName) {
        String filePath = "src/main/resources/industry-keywords/" + fileName;
        List<String> keywords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    keywords.add(line.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywords;
    }

    // Check if the given degree contains any of the specified keywords
    private boolean containsAnyKeyword(String degree, List<String> keywords) {
        for (String keyword : keywords) {
            // Use toLowerCase for case-insensitive searching
            if (degree.toLowerCase().contains(keyword)) return true;
        }
        return false;
    }

    @FXML
    void medicalClicked() {
        field = "Medical";
        jobsVBox.getChildren().clear();
        displayJobsByField(field);
        setStatisticsImageView(field);
    }

    @FXML
    void engineeringClicked() {
        field = "Engineering";
        jobsVBox.getChildren().clear();
        displayJobsByField(field);
        setStatisticsImageView(field);
    }

    @FXML
    void technologyClicked() {
        field = "Technology";
        jobsVBox.getChildren().clear();
        displayJobsByField(field);
        setStatisticsImageView(field);
    }

    @FXML
    void creativesClicked() {
        field = "Creatives";
        jobsVBox.getChildren().clear();
        displayJobsByField(field);
        setStatisticsImageView(field);
    }

    @FXML
    void financeClicked() {
        field = "Finance";
        jobsVBox.getChildren().clear();
        displayJobsByField(field);
        setStatisticsImageView(field);
    }

    public void setStatisticsImageView(String field) {
        InputStream imagePath = getClass().getResourceAsStream("/assets/" + field + "Statistics.png");
        Image statisticsImage = new Image(imagePath);

        statisticsImageView.setImage(statisticsImage);
    }

    public void displayJobsByField(String field) {
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

        nameLabel.setPadding(new Insets(0, 0, 0, 5));

        hbox.getChildren().addAll(nameLabel, salaryLabel, workSetupLabel, degreeLabel);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    public Label createLabel(String string, double width, double height, double fontSize, Pos pos) {
        Label label = new Label(string);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setAlignment(pos);
        label.setFont(new Font(fontSize));
        return label;
    }

    @FXML
    void signOutClicked() throws IOException {
        // Get a reference to the Stage from the current scene
        Stage currentStage = (Stage) nameField.getScene().getWindow();

        // Load the FXML file for the new scene
        Parent signinSceneRoot = FXMLLoader.load(getClass().getResource("/FXML-Files/sign-in.fxml"));
        Scene signinScene = new Scene(signinSceneRoot);
        currentStage.setScene(signinScene);
        currentStage.setTitle("Sign In");
        currentStage.centerOnScreen();
    }
}
