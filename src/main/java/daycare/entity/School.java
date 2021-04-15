package daycare.entity;

public class School {
    private int schoolID;
    private String schoolName;


    public int getSchoolID() {
        return schoolID;
    }



    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public School() {

    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolID=" + schoolID +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
