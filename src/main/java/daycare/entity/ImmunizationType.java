package daycare.entity;

import java.util.ArrayList;
import java.util.List;

public class ImmunizationType {
    private int vaccineID;
    private String vaccineName;
    private int minDoses;
    private List<Integer> injectInterval;

    public ImmunizationType() {
        injectInterval=new ArrayList<Integer>();
    }

    public ImmunizationType(int vaccineID, String vaccineName, int minDoses, List<Integer> injectInterval) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.minDoses = minDoses;
        this.injectInterval = injectInterval;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public int getMinDoses() {
        return minDoses;
    }

    public void setMinDoses(int minDoses) {
        this.minDoses = minDoses;
    }

    public List<Integer> getInjectInterval() {
        return injectInterval;
    }

    public void setInjectInterval(List<Integer> injectInterval) {
        this.injectInterval = injectInterval;
    }

    @Override
    public String toString() {
        return "ImmunizationType{" +
                "vaccineID=" + vaccineID +
                ", vaccineName='" + vaccineName + '\'' +
                ", minDoses=" + minDoses +
                ", injectInterval=" + injectInterval +
                '}';
    }
}
