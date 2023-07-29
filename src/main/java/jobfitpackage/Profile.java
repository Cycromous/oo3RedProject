package jobfitpackage;

public class Profile {
    String username, email, password; // Sign in details
    String name, introduction, degree, university, experience, achievements;

    public Profile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

        this.name = null;
        this.introduction = null;
        this.degree = null;
        this.university = null;
        this.experience = null;
        this.achievements = null;
    }

    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getDegree() {
        return degree;
    }

    public String getUniversity() {
        return university;
    }

    public String getExperience() {
        return experience;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
}
