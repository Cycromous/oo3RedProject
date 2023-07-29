package jobfitpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileList {
    private static ProfileList instance;
    private ProfileList() {}
    private ArrayList<Profile> profileArrayList = new ArrayList<>();
    private static final String LOGIN_DETAILS_PATH = "LoginDetails.txt";
    public static ProfileList getInstance() {
        if (instance == null) {
            instance = new ProfileList();
        }
        return instance;
    }

    public void loadLoginDetails() {
        profileArrayList.clear(); // Clear the existing profiles

        try (InputStream inputStream = ProfileList.class.getClassLoader().getResourceAsStream(LOGIN_DETAILS_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Profile profile = getProfileFromLine(line);
                if (profile != null) {
                    profileArrayList.add(profile);
                    System.out.println("Loaded Profile: " + profile.getUsername() + ", " + profile.getEmail() + ", " + profile.getPassword());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Profile getProfileFromLine(String line) {
        String[] fields = line.split(",");
        if (fields.length == 3) {
            String username = fields[0];
            String email = fields[1];
            String password = fields[2];
            return new Profile(username, email, password);
        }
        else return null; // Return null if the line is not in the correct format
    }

    public int searchProfileUsername(String username) {
        for (int i = 0; i < profileArrayList.size(); i++) {
            if (profileArrayList.get(i).getUsername().equalsIgnoreCase(username)) {
                return i; // Return the index of the profile if the username matches (case-insensitive)
            }
        }
        return -1; // Return -1 if the username is not found in any profile
    }

    public int searchProfileEmail(String email) {
        for (int i = 0; i < profileArrayList.size(); i++) {
            if (profileArrayList.get(i).getEmail().equalsIgnoreCase(email)) {
                return i; // Return the index of the profile if the email matches (case-insensitive)
            }
        }
        return -1; // Return -1 if the email is not found in any profile
    }

    public Profile getProfile(int index) {
        return profileArrayList.get(index);
    }

    public void addProfile(Profile profile) {
        profileArrayList.add(profile);
    }
}
