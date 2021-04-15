package daycare.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VaccineNotification {
    private int notificationId;
    private int schoolID;
    private int personID;
    private int vaccineID;
    private long due;
    private static int count;

    public VaccineNotification() {
        notificationId = count;
        count++;
    }

    public VaccineNotification(int schoolID, int personID, int vaccineID, long due) {
        notificationId = count;
        count++;
        this.schoolID = schoolID;
        this.personID = personID;
        this.vaccineID = vaccineID;
        this.due = due;
    }

    public boolean isDue() {
        if (System.currentTimeMillis() > due) {
            return true;
        } else {
            return false;
        }
    }


    public int getNotificationId() {
        return notificationId;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public long getDue() {
        return due;
    }

    public void setDue(long due) {
        this.due = due;
    }

    @Override
    public String toString() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sdf.format(due);
        return "VaccineNotification{" +
                "notificationId=" + notificationId +
                ", schoolID=" + schoolID +
                ", personID=" + personID +
                ", vaccineID=" + vaccineID +
                ", due=" + stringDate.toString() +
                '}';
    }
}
