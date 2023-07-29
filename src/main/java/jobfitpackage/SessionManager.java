package jobfitpackage;

public class SessionManager {
    private static Profile currentProfile;

    public static Profile getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(Profile profile) {
        currentProfile = profile;
    }
}
