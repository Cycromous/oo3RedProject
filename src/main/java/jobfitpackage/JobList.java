package jobfitpackage;

import java.util.ArrayList;

public class JobList {
    private ArrayList<Job> jobArrayList = new ArrayList<>();

    // Default constructor
    public JobList() {}

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
}
