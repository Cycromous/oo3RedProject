package jobfitpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JobList {
    private ArrayList<Job> jobArrayList = new ArrayList<>();

    // Default constructor
    public JobList() {}

    public void loadJobs() {

        try (InputStream inputStream = ProfileList.class.getClassLoader().getResourceAsStream("JobDataset.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Job job = getJobFromLine(line);
                if (job != null) {
                    jobArrayList.add(job);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Job getJobFromLine(String line) {
        String[] fields = line.split(",");
        if (fields.length == 5) {
            String name = fields[0];
            String field = fields[1];
            String salary = fields[2];
            String workSetup = fields[3];
            String degree = fields[4];

            System.out.print("Job Name: " + name);
            System.out.print("Field: " + field);
            System.out.print("Salary: " + salary);
            System.out.print("Work Setup: " + workSetup);
            System.out.print("Degree: " + degree + "\n");

            return new Job(name, field, salary, workSetup, degree);
        }
        else return null; // Return null if the line is not in the correct format
    }

    public int searchJobByField(String field) {
        for (int i = 0; i < jobArrayList.size(); i++) {
            if (jobArrayList.get(i).getField().equalsIgnoreCase(field)) {
                return i; // Return the index of the job if the field  matches (case-insensitive)
            }
        }
        return -1; // Return -1 if the username is not found in any profile
    }

    public int searchJobByName(String name) {
        for (int i = 0; i < jobArrayList.size(); i++) {
            if (jobArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i; // Return the index of the profile if the username matches (case-insensitive)
            }
        }
        return -1; // Return -1 if the username is not found in any profile
    }

    public Job getJob(int index) {
        return jobArrayList.get(index);
    }

    public void addJob(Job job) {
        jobArrayList.add(job);
    }

    public int getSize() {
        return jobArrayList.size();
    }
}
