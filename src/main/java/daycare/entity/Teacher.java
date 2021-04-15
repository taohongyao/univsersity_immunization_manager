package daycare.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Teacher extends Person {
    private int credit;
    private long lastTimeAnnualReview=-1;

    public Teacher() {
    }

    public long getLastTimeAnnualReview() {
        return lastTimeAnnualReview;
    }

    public void setLastTimeAnnualReview(long lastTimeAnnualReview) {
        this.lastTimeAnnualReview = lastTimeAnnualReview;
    }

    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sdf.format(lastTimeAnnualReview);
        return "Teacher{" +
                "personID=" + super.getPersonID() +
                ", schoolID=" + super.getSchoolID() +
                ", name='" + super.getName() + '\'' +
                ", ageMonths=" + super.getAgeMonths() +
                ", address='" + super.getAddress() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", credit=" + credit +
                ", lastTimeAnnualReview=" + stringDate +
                '}';
    }
}
