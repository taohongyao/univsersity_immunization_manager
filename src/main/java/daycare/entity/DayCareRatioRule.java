package daycare.entity;

public class DayCareRatioRule {
    private int ratioRuleID;
    private int studentMinAgeMonths=6;
    private int studentMaxAgeMonths=12;

    private int maxGroups=3;
    private int student2TeacherRatio=4;
    private int maxGroupSize=4;

    public DayCareRatioRule(int ratioRuleID, int studentMinAgeMonths, int studentMaxAgeMonths, int maxGroups, int student2TeacherRatio, int maxGroupSize) {
        this.ratioRuleID = ratioRuleID;
        this.studentMinAgeMonths = studentMinAgeMonths;
        this.studentMaxAgeMonths = studentMaxAgeMonths;
        this.maxGroups = maxGroups;
        this.student2TeacherRatio = student2TeacherRatio;
        this.maxGroupSize = maxGroupSize;
    }

    public DayCareRatioRule() {
    }

    public int getRatioRuleID() {
        return ratioRuleID;
    }

    public void setRatioRuleID(int ratioRuleID) {
        this.ratioRuleID = ratioRuleID;
    }

    public int getStudentMinAgeMonths() {
        return studentMinAgeMonths;
    }

    public void setStudentMinAgeMonths(int studentMinAgeMonths) {
        this.studentMinAgeMonths = studentMinAgeMonths;
    }

    public int getStudentMaxAgeMonths() {
        return studentMaxAgeMonths;
    }

    public void setStudentMaxAgeMonths(int studentMaxAgeMonths) {
        this.studentMaxAgeMonths = studentMaxAgeMonths;
    }

    public int getMaxGroups() {
        return maxGroups;
    }

    public void setMaxGroups(int maxGroups) {
        this.maxGroups = maxGroups;
    }

    public int getStudent2TeacherRatio() {
        return student2TeacherRatio;
    }

    public void setStudent2TeacherRatio(int student2TeacherRatio) {
        this.student2TeacherRatio = student2TeacherRatio;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    @Override
    public String toString() {
        return "DayCareRatioRule{" +
                "ratioRuleID=" + ratioRuleID +
                ", studentMinAgeMonths=" + studentMinAgeMonths +
                ", studentMaxAgeMonths=" + studentMaxAgeMonths +
                ", maxGroups=" + maxGroups +
                ", student2TeacherRatio=" + student2TeacherRatio +
                ", maxGroupSize=" + maxGroupSize +
                '}';
    }
}
