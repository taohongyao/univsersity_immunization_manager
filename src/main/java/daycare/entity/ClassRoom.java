package daycare.entity;


import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private int roomID;
    private int schoolId;
    private int studentMinAgeMonths = 6;
    private int studentMaxAgeMonths = 12;

    private int maxGroups = 3;
    private int student2TeacherRatio = 4;
    private int maxGroupSize = 4;
    private List<Person> teachers;
    private List<Person> students;

    public ClassRoom() {
        teachers = new ArrayList<Person>();
        students = new ArrayList<Person>();
    }

    public ClassRoom(DayCareRatioRule dayCareRatioRule) {
        teachers = new ArrayList<Person>();
        students = new ArrayList<Person>();
        studentMinAgeMonths = dayCareRatioRule.getStudentMinAgeMonths();
        studentMaxAgeMonths = dayCareRatioRule.getStudentMaxAgeMonths();
        maxGroups = dayCareRatioRule.getMaxGroups();
        student2TeacherRatio = dayCareRatioRule.getStudent2TeacherRatio();
        maxGroupSize = dayCareRatioRule.getMaxGroupSize();
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public boolean canAddTeacher() {
        if (teachers.size() < maxGroupSize){ return true;}
        else{ return false;}
    }

    public boolean canAddStudent() {
        if (students.size() < teachers.size() * maxGroupSize) {return true;}
        else{ return false;}
    }

    public boolean isStudentMatchRoom(Student student) {
        if (student.getAgeMonths() >= studentMinAgeMonths & student.getAgeMonths() <= studentMaxAgeMonths) {
            return true;
        } else {
            return false;
        }
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeTeacherById(int teacherID) {
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = (Teacher) teachers.get(i);
            if (teacher.getPersonID() == teacherID) {
                teachers.remove(i);
                return;
            }
        }
    }

    public void removeStudentById(int studentID) {
        for (int i = 0; i < students.size(); i++) {
            Student student = (Student) teachers.get(i);
            if (student.getPersonID() == studentID) {
                students.remove(i);
                return;
            }
        }
    }


    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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

    public int getGroupSize() {
        return teachers.size();
    }


    public List<Person> getTeachers() {
        return teachers;
    }


    public List<Person> getStudents() {
        return students;
    }

    public String teachersToString(){
        StringBuffer stringBuffer=new StringBuffer();
        for (Person p: teachers){
            stringBuffer.append(((Teacher)p).toString());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public String studentsToString(){
        StringBuffer stringBuffer=new StringBuffer();
        for (Person p: students){
            stringBuffer.append(((Student)p).toString());
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "roomID=" + roomID +
                ", school id=" + getSchoolId() +
                ", studentMinAgeMonths=" + studentMinAgeMonths +
                ", studentMaxAgeMonths=" + studentMaxAgeMonths +
                ", maxGroups=" + maxGroups +
                ", student2TeacherRatio=" + student2TeacherRatio +
                ", maxGroupSize=" + maxGroupSize +
                ", \nteachers=[\n" + teachersToString() +
                "], \nstudents=[\n" + studentsToString() +
                "]}";
    }
}
