package jobfitpackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
        Label nameLabel = createLabel(job.getName(), 180, 50);
        Label salaryLabel = createLabel(job.getSalary(), 170, 50);
        Label workSetupLabel = createLabel(job.getWorkSetup(), 150, 50);
        Label degreeLabel = createLabel(job.getDegree(), 250, 50);

        hbox.getChildren().addAll(nameLabel, salaryLabel, workSetupLabel, degreeLabel);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    public Label createLabel(String string, double width, double height) {
        Label label = new Label(string);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setTextFill(Color.BLACK);
        return label;
    }

    @FXML
    void creativesClicked() {

    }

    @FXML
    void editProfileClicked() {

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
