package jobfitpackage;

public class Job {
    String name, field, salary, workSetup, degree;

    // Constructor class
    public Job(String name, String field, String salary, String workSetup, String degree) {
        this.name = name;
        this.field = field;
        this.salary = salary;
        this.workSetup = workSetup;
        this.degree = degree;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getField() {
        return this.field;
    }

    public String getSalary() {
        return this.salary;
    }

    public String getWorkSetup() {
        return this.workSetup;
    }

    public String getDegree() {
        return this.degree;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setWorkSetup(String workSetup) {
        this.workSetup = workSetup;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
