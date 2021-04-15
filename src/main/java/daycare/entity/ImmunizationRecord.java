package daycare.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ImmunizationRecord {

    private int recordID;
    private int schoolID;
    private int personID;
    private int vaccineID;
    private long date;
    private static int count;

    public int getRecordID() {
        return recordID;
    }

    public ImmunizationRecord() {
        this.recordID=count;
        count++;
    }

    public ImmunizationRecord(int schoolID, int personID, int vaccineID, long date) {
        this.recordID=count;
        count++;
        this.schoolID = schoolID;
        this.personID = personID;
        this.vaccineID = vaccineID;
        this.date = date;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ImmunizationRecord.count = count;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public void setRecordID(int i) {
        this.recordID = recordID;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sdf.format(date);
        return "ImmunizationRecord{" +
                "recordID=" + recordID +
                ", schoolID=" + schoolID +
                ", personID=" + personID +
                ", vaccineID=" + vaccineID +
                ", date=" + stringDate +
                '}';
    }
}
