package daycare.entity;

public class Student extends Person {
    private String fatherName;
    private String motherName;
    private double grade;

    public Student() {

    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "personID=" + super.getPersonID() +
                ", schoolID=" + super.getSchoolID() +
                ", name='" + super.getName() + '\'' +
                ", ageMonths=" + super.getAgeMonths() +
                ", address='" + super.getAddress() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", grade=" + grade +
                '}';
    }
}
